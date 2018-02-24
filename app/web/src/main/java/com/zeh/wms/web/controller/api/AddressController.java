package com.zeh.wms.web.controller.api;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hzy24985
 * @version $Id: AddressController, v 0.1 2018/2/24 13:52 hzy24985 Exp $
 */
@Api(value = "地址管理")
@Controller
@RequestMapping("/api/address")
public class AddressController {

    @ApiOperation(value = "新增收件人地址", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "receiver", method = RequestMethod.POST)
    @ResponseBody
    public String addReceiver() {
        return "OK";
    }

    @ApiOperation(value = "修改收件人地址", httpMethod = "PUT")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "receiver", method = RequestMethod.PUT)
    @ResponseBody
    public String updateReceiver() {
        return "OK";
    }

    @ApiOperation(value = "添加寄件人地址", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "sender", method = RequestMethod.POST)
    @ResponseBody
    public String addSender() {
        return "OK";
    }

    @ApiOperation(value = "修改寄件人地址", httpMethod = "PUT")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "sender", method = RequestMethod.PUT)
    @ResponseBody
    public String updateSender() {
        return "OK";
    }

    @ApiOperation(value = "获得默认收件人、寄件人地址", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "defAddress", method = RequestMethod.GET)
    @ResponseBody
    public String getDefaultAddress() {
        return "OK";
    }

    @ApiOperation(value = "获得所有收件人地址", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "address", method = RequestMethod.GET)
    @ResponseBody
    public String getAllAddress() {
        return "OK";
    }
}
