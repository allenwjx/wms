package com.zeh.wms.web.controller.api;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.BookServiceException;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.BookVO;
import com.zeh.wms.biz.model.ExpressOrderVO;
import com.zeh.wms.biz.model.FreightVO;
import com.zeh.wms.biz.model.UserAddressVO;
import com.zeh.wms.biz.model.calate.OrderCalculatePriceVO;
import com.zeh.wms.biz.model.enums.ExpressOrderStateEnum;
import com.zeh.wms.biz.model.enums.SettleTypeEnum;
import com.zeh.wms.biz.service.*;
import com.zeh.wms.dal.operation.inventory.GetInfoByMobileResult;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.controller.api.model.OrderBookModel;
import com.zeh.wms.web.controller.api.model.OrderPriceModel;
import com.zeh.wms.web.exception.WebException;
import com.zeh.wms.web.mapper.OrderModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author hzy24985
 * @version $Id: OrderContoller, v 0.1 2018/2/24 14:01 hzy24985 Exp $
 */
@Api(value = "订单管理")
@Controller
@RequestMapping("/api/order")
public class OrderContoller extends BaseController {
    /** 下单服务 */
    @Resource
    private BookService           bookService;

    @Resource
    private OrderCalculateService orderCalculateService;

    @Resource
    private InventoryService      inventoryService;
    @Resource
    private OrderModelMapper      orderModelMapper;
    @Resource
    private AddressService        addressService;
    @Resource
    private ExpressOrderService   expressOrderService;
    @Resource
    private FreightService        freightService;

    @ApiOperation(value = "获得订单列表", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "orders", method = RequestMethod.GET)
    @ResponseBody
    public String getOrderList() {
        // TODO: 2018/3/14  
        return "OK";
    }

    @ApiOperation(value = "预定", httpMethod = "POST")
    @RequestMapping(value = "book", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult<OrderPriceModel> book(OrderBookModel orderBookModel) {
        try {
            BookVO bookVO = createBookVO(orderBookModel);
            ExpressOrderVO expressOrder = bookService.book(bookVO);
            OrderPriceModel price = new OrderPriceModel();
            price.setAdditionalWeight(expressOrder.getAdditionalWeight());
            price.setAdditionalWeightPrice(expressOrder.getAdditionalWeightPrice());
            price.setFirstWeight(expressOrder.getFirstWeight());
            price.setFirstWeightPrice(expressOrder.getFirstWeightPrice());
            price.setPaymentType(expressOrder.getPaymentType().getCode());
            price.setTotalPrice(expressOrder.getTotalPrice());
            return createSuccessResult(price);
        } catch (BookServiceException e) {
            return createErrorResult(e);
        }
    }

    //    @ApiOperation(value = "下单", httpMethod = "GET")
    //    @ApiResponse(code = 200, message = "success", response = String.class)
    //    @RequestMapping(value = "create", method = RequestMethod.GET)
    //    @ResponseBody
    //    public SingleResult createOrder(ExpressOrderModel model) throws ServiceException, WebException {
    //        ExpressOrderVO orderVO = orderModelMapper.model2Vo(model);
    //        UserAddressVO sender = addressService.queryAddress(model.getSenderAddressId());
    //        orderVO.setUserId(getCurrentApiUserId());
    //        orderVO.setSenderName(sender.getName());
    //        orderVO.setSenderTel(sender.getTel());
    //        orderVO.setSenderProvince(sender.getProvince());
    //        orderVO.setSenderCity(sender.getCity());
    //        orderVO.setSenderRegion(sender.getRegion());
    //        orderVO.setSenderAddressDetail(sender.getDetail());
    //        orderVO.setSenderCompany(sender.getCompany());
    //        if (getCurrentApiUser().getPaymentType() == SettleTypeEnum.ONLINE) {
    //            orderVO.setStatus(ExpressOrderStateEnum.WATI_PAY);
    //        } else {
    //            orderVO.setStatus(ExpressOrderStateEnum.WAIT_SEND);
    //        }
    //
    //        //获取运价信息
    //        FreightVO freightVO = freightService.queryFreightByExpressProvince(model.getExpressType(), model.getReceiverProvince());
    //        orderVO.setFirstWeight(freightVO.getFirstWeight());
    //        orderVO.setAdditionalWeight(500);
    //        orderVO.setPaymentType(getCurrentApiUser().getPaymentType());
    //
    //        //再次校验价格，这里简单做，如果价格变动了，则提示重新下单。
    //        //或者在点击进入下单下面时，把价格快照缓存下来，下单时直接使用缓存的价格。这个后期再优化。
    //        OrderCalculateModel calculate = new OrderCalculateModel();
    //        calculate.setCommodityId(model.getInventoryId());
    //        calculate.setCommodityQuantity(model.getCommodityQuanity());
    //        calculate.setExpressType(model.getExpressType());
    //        calculate.setProvince(model.getReceiverProvince());
    //        SingleResult<OrderCalculatePriceVO> price = calculate(calculate);
    //        if (price.getData().getTotal() != model.getTotalPrice()) {
    //            return createErrorResult("价格变动，请后退重新下单");
    //        }
    //        insertSecurityApiVO(orderVO);
    //        expressOrderService.createOrder(orderVO);
    //        return createSuccessResult();
    //    }

    @ApiOperation(value = "订单详情", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String orderDetail() {
        // TODO: 2018/3/14  
        return "OK";
    }

    @ApiOperation(value = "生成支付单", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "pay", method = RequestMethod.POST)
    @ResponseBody
    public String pay() {
        // TODO: 2018/3/14
        return "OK";
    }

    //    @ApiOperation(value = "计算总费用", httpMethod = "GET")
    //    @ApiResponse(code = 200, message = "success", response = String.class)
    //    @RequestMapping(value = "calculate", method = RequestMethod.GET)
    //    @ResponseBody
    //    public SingleResult<OrderCalculatePriceVO> calculate(OrderCalculateModel calculateModel) throws ServiceException, WebException {
    //        assertObjectNull(calculateModel, "计算模型");
    //
    //        GetInfoByMobileResult inventory = inventoryService.getInfoByMobileAndId(getCurrentApiUser().getMobile(), calculateModel.getCommodityId());
    //
    //        if (calculateModel.getCommodityQuantity() > inventory.getAmount()) {
    //            return createErrorResult("下单数量不能大于库存数量.");
    //        }
    //        int wight = inventory.getWeight() * calculateModel.getCommodityQuantity();
    //
    //        OrderCalculatePriceVO model = orderCalculateService.inventoryOrderPrice(calculateModel.getExpressType(), calculateModel.getProvince(), wight, getCurrentApiUserId());
    //
    //        return createSuccessResult(model);
    //    }

    private BookVO createBookVO(OrderBookModel orderBookModel) {
        BookVO bookVO = new BookVO();

        bookVO.setUserId(getCurrentApiUserId());
        bookVO.setCommodityQuanity(orderBookModel.getCommodityQuanity());
        bookVO.setCommodityName(orderBookModel.getCommodityName());
        bookVO.setCommodityWeight(orderBookModel.getCommodityWeight());
        bookVO.setExpressType(orderBookModel.getExpressType());
        bookVO.setPaymentType(getCurrentApiUser().getPaymentType());
        bookVO.setRemark(orderBookModel.getRemark());

        bookVO.setSenderAddressDetail(orderBookModel.getSenderAddressDetail());
        bookVO.setSenderCity(orderBookModel.getSenderCity());
        bookVO.setSenderCompany(orderBookModel.getSenderCompany());
        bookVO.setSenderName(orderBookModel.getSenderName());
        bookVO.setSenderProvince(orderBookModel.getSenderProvince());
        bookVO.setSenderRegion(orderBookModel.getSenderRegion());
        bookVO.setSenderTel(orderBookModel.getSenderTel());
        bookVO.setSenderZipCode("");

        bookVO.setReceiverAddressDetail(orderBookModel.getReceiverAddressDetail());
        bookVO.setReceiverCity(orderBookModel.getReceiverCity());
        bookVO.setReceiverCompany(orderBookModel.getReceiverCompany());
        bookVO.setReceiverName(orderBookModel.getReceiverName());
        bookVO.setReceiverProvince(orderBookModel.getReceiverProvince());
        bookVO.setReceiverRegion(orderBookModel.getReceiverRegion());
        bookVO.setReceiverTel(orderBookModel.getReceiverTel());
        bookVO.setReceiverZipCode("");

        return bookVO;
    }

}
