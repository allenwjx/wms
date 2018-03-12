package com.zeh.wms.web.controller.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.session.UserSession;
import com.zeh.wms.biz.session.UserSessionManager;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.controller.api.model.UserModel;

/**
 * @author allen
 * @create $ ID: WechatUserController, 18/3/12 15:09 allen Exp $
 * @since 1.0.0
 */
@Api(value = "微信用户管理")
@Controller
@RequestMapping("/api/user")
public class WechatUserController extends BaseController {
    @Resource
    private UserSessionManager userSessionManager;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<UserModel> login(String jsCode, String nickName, HttpServletRequest request) {
        UserModel userModel = getUserModel(request);
        return createSuccessResult(userModel);
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<UserModel> getUserInfo(HttpServletRequest request) {
        UserModel userModel = getUserModel(request);
        return createSuccessResult(userModel);
    }

    private UserModel getUserModel(HttpServletRequest request) {
        UserSession userSession = userSessionManager.getSession(request);
        UserModel userModel = new UserModel();
        userModel.setMobile(userSession.getUser().getMobile());
        userModel.setOpenId(userSession.getWechatUser().getOpenId());
        userModel.setUserId(userSession.getUser().getUserId());
        userModel.setToken(userSession.getId());
        return userModel;
    }

}