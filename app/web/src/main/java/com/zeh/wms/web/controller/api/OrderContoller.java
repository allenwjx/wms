package com.zeh.wms.web.controller.api;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.BookServiceException;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.BookVO;
import com.zeh.wms.biz.model.ExpressOrderVO;
import com.zeh.wms.biz.model.enums.ExpressOrderStateEnum;
import com.zeh.wms.biz.model.enums.SettleTypeEnum;
import com.zeh.wms.biz.service.BookService;
import com.zeh.wms.biz.service.PaymentService;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.controller.api.model.OrderBookModel;
import com.zeh.wms.web.controller.api.model.OrderPriceModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
    private BookService    bookService;
    /** 支付服务 */
    @Resource
    private PaymentService paymentService;

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
    public SingleResult<OrderPriceModel> book(@RequestBody OrderBookModel orderBookModel) {
        try {
            ExpressOrderVO expressOrder = createExpressOrderVO(orderBookModel);
            OrderPriceModel price = new OrderPriceModel();
            price.setAdditionalWeight(expressOrder.getAdditionalWeight());
            price.setAdditionalWeightPrice(expressOrder.getAdditionalWeightPrice());
            price.setFirstWeight(expressOrder.getFirstWeight());
            price.setFirstWeightPrice(expressOrder.getFirstWeightPrice());
            price.setPaymentType(expressOrder.getPaymentType().getCode());
            price.setTotalPrice(expressOrder.getTotalPrice());
            price.setOrderNo(expressOrder.getOrderNo());
            return createSuccessResult(price);
        } catch (BookServiceException | ServiceException e) {
            return createErrorResult(e);
        }
    }

    private ExpressOrderVO createExpressOrderVO(OrderBookModel orderBookModel) throws BookServiceException, ServiceException {
        BookVO bookVO = createBookVO(orderBookModel);
        if (getCurrentApiUser().getPaymentType() != SettleTypeEnum.ONLINE) {
            return bookService.inventoryBook(bookVO, orderBookModel.getCommodityId(), getCurrentApiUser().getMobile());
        }

        return bookService.book(bookVO);
    }

    @ApiOperation(value = "订单详情", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String orderDetail() {
        // TODO: 2018/3/14  
        return "OK";
    }

    @ApiOperation(value = "生成支付单", httpMethod = "POST")
    @RequestMapping(value = "pay", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult<WxPayMpOrderResult> pay(String orderNo) throws ServiceException {
        return createSuccessResult(paymentService.goPay(orderNo, getCurrentApiUser()));
    }

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

        if (getCurrentApiUser().getPaymentType() == SettleTypeEnum.ONLINE) {
            bookVO.setStatus(ExpressOrderStateEnum.WATI_PAY);
        } else {
            bookVO.setStatus(ExpressOrderStateEnum.WAIT_SEND);
        }

        return bookVO;
    }

}
