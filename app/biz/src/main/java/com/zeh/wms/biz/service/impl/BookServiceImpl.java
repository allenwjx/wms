package com.zeh.wms.biz.service.impl;

import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.BookServiceException;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.BookVO;
import com.zeh.wms.biz.model.ExpressOrderVO;
import com.zeh.wms.biz.model.FreightVO;
import com.zeh.wms.biz.model.RegionsVO;
import com.zeh.wms.biz.model.enums.ExpressOrderStateEnum;
import com.zeh.wms.biz.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author allen
 * @create $ ID: BookServiceImpl, 18/3/15 14:20 allen Exp $
 * @since 1.0.0
 */
@Service
public class BookServiceImpl implements BookService {

    /** Error Factory */
    protected final static BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();

    /** 快递单服务 */
    @Resource
    private ExpressOrderService            expressOrderService;

    /** 会员服务 */
    @Resource
    private UserService                    userService;

    /** 运价服务 */
    @Resource
    private FreightService                 freightService;

    /** 区域服务 */
    @Resource
    private RegionsService                 regionsService;

    /**
     * 快递预定
     *
     * @param bookVO
     * @return
     * @throws ServiceException
     */
    @Override
    public ExpressOrderVO book(BookVO bookVO) throws BookServiceException {
        if (bookVO == null) {
            throw new BookServiceException(ERROR_FACTORY.bookFail("param null"));
        }
        ExpressOrderPrice price = calculatePrice(bookVO);
        if (price == null) {
            throw new BookServiceException(ERROR_FACTORY.priceCalculateFail());
        }

        try {
            ExpressOrderVO expressOrder = createExpressOrder(bookVO, price);
            expressOrderService.createOrder(expressOrder);
            return expressOrder;
        } catch (ServiceException e) {
            throw new BookServiceException(e.getError(), e);
        }
    }

    /**
     * 计算快递单价格
     * @param bookVO
     * @return
     * @throws BookServiceException
     */
    protected ExpressOrderPrice calculatePrice(BookVO bookVO) throws BookServiceException {
        ExpressOrderPrice price = new ExpressOrderPrice();
        try {
            // 获取用户该物流公司的运价折扣
            BigDecimal discount = userService.getDiscount(bookVO.getUserId(), bookVO.getExpressType());
            // 获取省份中文对应的省份编码
            RegionsVO region = regionsService.queryByName(bookVO.getReceiverProvince());
            if (region == null) {
                throw new BookServiceException(ERROR_FACTORY.bookFail("无法获取省份编码，省份名称：" + bookVO.getReceiverProvince()));
            }
            // 获取用户该物流公司，寄送目的地省份的基础运价
            FreightVO freight = freightService.queryFreightByExpressProvince(bookVO.getExpressType(), region.getId());
            if (freight == null) {
                throw new BookServiceException(ERROR_FACTORY.bookFail("无法获取运价，物流公司：" + bookVO.getExpressType() + "，省份：" + bookVO.getReceiverProvince()));
            }

            // 计算客户运价
            price.setFirstWeight(freight.getFirstWeight());
            price.setAdditionalWeight(calculateAdditionalWeight(bookVO, freight));
            price.setFirstWeightPrice(calculatePrice(freight.getFirstOriginalPrice(), discount));
            price.setAdditionalWeightPrice(calculateAdditionalPrice(bookVO, freight, discount));
            return price;
        } catch (ServiceException e) {
            throw new BookServiceException(e.getError(), e);
        }
    }

    /**
     * 计算折扣价
     * 
     * @param price
     * @param discount
     * @return
     */
    private int calculatePrice(int price, BigDecimal discount) {
        return new BigDecimal(price).multiply(discount).setScale(0, BigDecimal.ROUND_UP).intValue();
    }

    /**
     * 计算续重价格
     * 
     * @param bookVO
     * @param freight
     * @param discount
     * @return
     */
    private int calculateAdditionalPrice(BookVO bookVO, FreightVO freight, BigDecimal discount) {
        int additionalWeight = calculateAdditionalWeight(bookVO, freight);
        // 续重 = 每500克的价格 * 500克的数量
        int orgiPrice = (int) (Math.ceil(additionalWeight / 500) * freight.getAdditionalOriginalPrice());
        return calculatePrice(orgiPrice, discount);
    }

    /**
     * 计算续重重量
     * @param bookVO
     * @param freight
     * @return
     */
    private int calculateAdditionalWeight(BookVO bookVO, FreightVO freight) {
        // 货物重量小于首重的，续重为0
        if (bookVO.getCommodityWeight() <= freight.getFirstWeight()) {
            return 0;
        }
        return bookVO.getCommodityWeight() - freight.getFirstWeight();
    }

    /**
     * 创建快递单模型
     *
     * @param bookVO 预定模型
     * @param price 价格模型
     * @return
     */
    private ExpressOrderVO createExpressOrder(BookVO bookVO, ExpressOrderPrice price) {
        ExpressOrderVO expressOrder = new ExpressOrderVO();
        // 预订人、商品信息
        expressOrder.setOtherOrderNo("");
        expressOrder.setUserId(bookVO.getUserId());
        expressOrder.setCommodityName(bookVO.getCommodityName());
        expressOrder.setCommodityQuanity(bookVO.getCommodityQuanity());
        expressOrder.setCommodityWeight(bookVO.getCommodityWeight());
        expressOrder.setPaymentType(bookVO.getPaymentType());
        expressOrder.setExpressType(bookVO.getExpressType());
        expressOrder.setStatus(ExpressOrderStateEnum.WATI_PAY);
        expressOrder.setRemark(bookVO.getRemark());

        // 价格
        expressOrder.setTotalPrice(price.getTotalPrice());
        expressOrder.setFirstWeightPrice(price.firstWeightPrice);
        expressOrder.setAdditionalWeightPrice(price.additionalWeightPrice);
        expressOrder.setFirstWeight(price.firstWeight);
        expressOrder.setAdditionalWeight(price.additionalWeight);

        // 发件人
        expressOrder.setReceiverAddressDetail(bookVO.getReceiverAddressDetail());
        expressOrder.setReceiverCity(bookVO.getReceiverCity());
        expressOrder.setReceiverCompany(bookVO.getReceiverCompany());
        expressOrder.setReceiverName(bookVO.getReceiverName());
        expressOrder.setReceiverProvince(bookVO.getReceiverProvince());
        expressOrder.setReceiverRegion(bookVO.getReceiverRegion());
        expressOrder.setReceiverTel(bookVO.getReceiverTel());
        expressOrder.setReceiverZipCode(bookVO.getReceiverZipCode());

        // 收件人
        expressOrder.setSenderAddressDetail(bookVO.getSenderAddressDetail());
        expressOrder.setSenderCity(bookVO.getSenderCity());
        expressOrder.setSenderCompany(bookVO.getSenderCompany());
        expressOrder.setSenderName(bookVO.getSenderName());
        expressOrder.setSenderProvince(bookVO.getSenderProvince());
        expressOrder.setSenderRegion(bookVO.getSenderRegion());
        expressOrder.setSenderTel(bookVO.getSenderTel());
        expressOrder.setSenderZipCode(bookVO.getSenderZipCode());

        // 操作人
        expressOrder.setCreateBy(String.valueOf(bookVO.getUserId()));
        expressOrder.setModifyBy(String.valueOf(bookVO.getUserId()));
        return expressOrder;
    }

    /**
     * 快递单价格
     */
    protected class ExpressOrderPrice {
        /**  首重，单位（克）*/
        private int firstWeight;
        /** 续重，单位：克 */
        private int additionalWeight;
        /** 首重价格，单位：分 */
        private int firstWeightPrice;
        /** 续重价格，单位：分 */
        private int additionalWeightPrice;

        public int getTotalPrice() {
            return firstWeightPrice + additionalWeightPrice;
        }

        public void setFirstWeight(int firstWeight) {
            this.firstWeight = firstWeight;
        }

        public void setAdditionalWeight(int additionalWeight) {
            this.additionalWeight = additionalWeight;
        }

        public void setFirstWeightPrice(int firstWeightPrice) {
            this.firstWeightPrice = firstWeightPrice;
        }

        public void setAdditionalWeightPrice(int additionalWeightPrice) {
            this.additionalWeightPrice = additionalWeightPrice;
        }
    }
}