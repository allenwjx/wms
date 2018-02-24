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
 * @version $Id: CommonDataController, v 0.1 2018/2/24 14:13 hzy24985 Exp $
 */
@Api(value = "通用数据")
@Controller
@RequestMapping("/api/data")
public class CommonDataController {

    @ApiOperation(value = "商品信息", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "product", method = RequestMethod.GET)
    @ResponseBody
    public String product() {
        return "OK";
    }
}
