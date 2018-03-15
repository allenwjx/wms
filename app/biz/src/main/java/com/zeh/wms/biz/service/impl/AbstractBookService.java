package com.zeh.wms.biz.service.impl;

import javax.annotation.Resource;

import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.BookServiceException;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.BookVO;
import com.zeh.wms.biz.model.ExpressOrderVO;
import com.zeh.wms.biz.model.enums.ExpressOrderStateEnum;
import com.zeh.wms.biz.model.enums.SettleTypeEnum;
import com.zeh.wms.biz.service.BookService;
import com.zeh.wms.biz.service.ExpressOrderService;

/**
 * @author allen
 * @create $ ID: AbstractBookService, 18/3/15 14:20 allen Exp $
 * @since 1.0.0
 */
public abstract class AbstractBookService implements BookService {

    /** Error Factory */
    protected final static BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();

    /** 快递单服务 */
    @Resource
    private ExpressOrderService            expressOrderService;

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
            throw new BookServiceException(ERROR_FACTORY.bookFail());
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
    protected abstract ExpressOrderPrice calculatePrice(BookVO bookVO) throws BookServiceException;

    /**
     * 创建快递单模型
     *
     * @param bookVO 预定模型
     * @param price 价格模型
     * @return
     */
    private ExpressOrderVO createExpressOrder(BookVO bookVO, ExpressOrderPrice price) {
        ExpressOrderVO expressOrder = new ExpressOrderVO();
        // 价格，预订人、商品信息
        expressOrder.setUserId(bookVO.getUserId());
        expressOrder.setPaymentType(price.paymentType);
        expressOrder.setTotalPrice(price.totalPrice);
        expressOrder.setAdditionalWeight(price.additionalWeight);
        expressOrder.setAdditionalWeightPrice(price.additionalWeightPrice);
        expressOrder.setFirstWeight(price.firstWeight);
        expressOrder.setFirstWeightPrice(price.firstWeightPrice);
        expressOrder.setCommodityName(price.commodityName);
        expressOrder.setCommodityQuanity(price.commodityQuanity);
        expressOrder.setCommodityWeight(price.commodityWeight);
        expressOrder.setExpressType(bookVO.getExpressType());
        expressOrder.setOtherOrderNo("");
        expressOrder.setStatus(ExpressOrderStateEnum.WATI_PAY);
        expressOrder.setRemark(bookVO.getRemark());

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
        /** 支付方式：0-线上支付，1-线下现结，2-线下月结 */
        private SettleTypeEnum paymentType;
        /** 商品名称或商品类型 */
        private String         commodityName;
        /** 商品总数量 */
        private int            commodityQuanity;
        /** 商品总重量 */
        private int            commodityWeight;
        /**  首重，单位（克）*/
        private int            firstWeight;
        /** 续重，单位：克 */
        private int            additionalWeight;
        /** 首重价格，单位：分 */
        private int            firstWeightPrice;
        /** 续重价格，单位：分 */
        private String         additionalWeightPrice;
        /** 快递费总价，单位：分 */
        private int            totalPrice;
    }
}