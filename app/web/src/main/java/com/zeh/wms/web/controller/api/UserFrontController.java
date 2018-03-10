package com.zeh.wms.web.controller.api;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.zeh.wms.biz.model.UserVO;
import com.zeh.wms.web.controller.BaseController;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author weijun
 * @create $ v 1.0.0 2018/3/10 下午3:17 Exp $
 */
@Api(value = "地址管理")
@Controller
@RequestMapping("/api/user/front")
public class UserFrontController extends BaseController {

    @ApiOperation(value = "微信用户登陆", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = UserVO.class)
    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public UserVO loginByCode (@Param ("jsCode") String jsCode) {
        return null;
    }
}
