package com.zeh.wms.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.web.form.DeleteForm;

/**
* 测试
 * @author hzy24985
 * @version $Id: DeleteController.java, v 0.1 2018/2/13 16:29 hzy24985 Exp $
 */
@Api(value = "待删除controller")
@Controller
@RequestMapping("/api/delete")
public class DeleteController {

    @ApiOperation(value = "获取订单信息", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = DeleteForm.class)
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public DeleteForm list(@ApiParam("请求订单参数")DeleteForm form) throws ServiceException {

        return form;
    }
}
