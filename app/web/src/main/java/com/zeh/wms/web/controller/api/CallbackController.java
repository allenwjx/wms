package com.zeh.wms.web.controller.api;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.zeh.wms.biz.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author hzy24985
 * @version $Id: ListenerController, v 0.1 2018/2/24 14:10 hzy24985 Exp $
 */
@Api(value = "微信回调")
@Controller
@RequestMapping("/api/callback")
public class CallbackController {

    @Resource
    private PaymentService paymentService;

    @ApiOperation(value = "支付回调", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "payCallback", method = RequestMethod.POST)
    @ResponseBody
    public String payCallback(@RequestBody String xmlData)  {
        return paymentService.payCallback(xmlData);
    }
}
