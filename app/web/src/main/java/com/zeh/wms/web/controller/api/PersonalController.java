package com.zeh.wms.web.controller.api;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.zeh.wms.biz.exception.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hzy24985
 * @version $Id: PersonalController, v 0.1 2018/2/24 13:46 hzy24985 Exp $
 */
@Api(value = "个人中心")
@Controller
@RequestMapping("/api/my")
public class PersonalController {

    @ApiOperation(value = "用户注册", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public String register() throws ServiceException {

        return "OK";
    }

    @ApiOperation(value = "注册商户", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "link", method = RequestMethod.POST)
    @ResponseBody
    public String linkMerchant(@ApiParam("商户编码") String code) {
        return "ok";
    }
}
