package com.zeh.wms.web.controller.api;

import com.zeh.jungle.utils.serializer.FastJsonUtils;
import me.chanjar.weixin.common.exception.WxErrorException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.zeh.wms.biz.exception.ServiceException;

/**
 * @author hzy24985
 * @version $Id: PersonalController, v 0.1 2018/2/24 13:46 hzy24985 Exp $
 */
@Api(value = "个人中心")
@Controller
@RequestMapping("/api/my")
public class PersonalController {
    private static Logger logger = LoggerFactory.getLogger(PersonalController.class);

    @Autowired
    private WxMaService wxService;

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


    /**
     * 登陆接口
     */
    @ApiOperation(value = "登陆", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public String login(String code) {
        if (StringUtils.isBlank(code)) {
            return "empty jscode";
        }

        try {
            WxMaJscode2SessionResult session = this.wxService.getUserService().getSessionInfo(code);
            logger.info(session.getSessionKey());
            logger.info(session.getOpenid());
            logger.info(session.getExpiresin().toString());
            //TODO 关联业务相关数据
            return FastJsonUtils.toJSONString(session);
        } catch (WxErrorException e) {
            logger.error(e.getMessage(), e);
            return e.toString();
        }
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @ApiOperation(value = "获取用户信息", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "info", method = RequestMethod.GET)
    @ResponseBody
    public String info(String sessionKey, String signature, String rawData, String encryptedData, String iv) {
        // 用户信息校验
        if (!this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密用户信息
        WxMaUserInfo userInfo = this.wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        return FastJsonUtils.toJSONString(userInfo);
    }
}
