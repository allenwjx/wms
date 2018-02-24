package com.zeh.wms.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;

/**
 * @author hzy24985
 * @version $Id: OrderContoller, v 0.1 2018/2/24 14:01 hzy24985 Exp $
 */
@Api(value = "订单管理")
@Controller
@RequestMapping("/api/order")
public class OrderContoller {

    @ApiOperation(value = "获得订单列表", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "orders", method = RequestMethod.GET)
    @ResponseBody
    public String getOrderList() {
        return "OK";
    }

    @ApiOperation(value = "下单", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "order", method = RequestMethod.POST)
    @ResponseBody
    public String createOrder() {
        return "OK";
    }

    @ApiOperation(value = "订单详情", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "order", method = RequestMethod.GET)
    @ResponseBody
    public String orderDetail() {
        return "OK";
    }

    @ApiOperation(value = "生成支付单", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "pay", method = RequestMethod.POST)
    @ResponseBody
    public String pay() {
        return "OK";
    }

    @ApiOperation(value = "计算总费用", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "calculate", method = RequestMethod.POST)
    @ResponseBody
    public String calculate() {
        return "OK";
    }
}
