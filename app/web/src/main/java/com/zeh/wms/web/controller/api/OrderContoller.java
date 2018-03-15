package com.zeh.wms.web.controller.api;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.BookServiceException;
import com.zeh.wms.biz.model.BookVO;
import com.zeh.wms.biz.model.ExpressOrderVO;
import com.zeh.wms.biz.service.*;
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
    private BookService           bookService;

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
