package com.zeh.wms.web.controller.api;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.Session;
import com.zeh.wms.biz.model.UserVO;
import com.zeh.wms.biz.service.SessionManager;
import com.zeh.wms.biz.service.UserService;
import com.zeh.wms.biz.service.WechatService;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.controller.api.model.UserModel;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author weijun
 * @create $ v 1.0.0 2018/3/10 下午3:17 Exp $
 */
@Api(value = "地址管理")
@Controller
@RequestMapping("/api/user/front")
public class UserFrontController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private SessionManager sessionManager;
    @Resource
    private WechatService wechatService;

    @ApiOperation(value = "微信用户注册", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = UserVO.class)
    @RequestMapping(value = "registe", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult<UserVO> registe (@ApiParam("注册用户信息") @RequestBody UserModel userModel, HttpServletRequest request) {
        Session session = null;
        try {
            session = sessionManager.getSessionFromSevlet(request);
        } catch (ServiceException ex) {
            return createErrorResult(ex.getLocalizedMessage());
        }

        UserVO user = new UserVO();
        user.setId(session.getUserVO().getId());
        user.setNickName(userModel.getNickName());
        user.setMobile(userModel.getMobile());

        try {
            userService.updateUser(user);
            user = userService.queryByUserId(session.getUserVO().getUserId());
            return createSuccessResult(user);
        } catch (Exception ex) {
            return createErrorResult (ex);
        }
    }


    @ApiOperation(value = "微信用户登陆", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = SingleResult.class)
    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<Session> login (@ApiParam("微信身份CODE") @Param("jsCode") String jsCode, HttpServletRequest request) {
        Session session = null;
        try {
            session = sessionManager.getSessionFromSevlet(request);
        } catch (ServiceException ex) {
            return createErrorResult(ex.getLocalizedMessage());
        }

        return createSuccessResult(session);
    }
}
