package com.zeh.wms.biz.session;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.zeh.jungle.core.configuration.AppConfiguration;
import com.zeh.jungle.core.configuration.AppConfigurationAware;
import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.exception.SessionException;
import com.zeh.wms.biz.model.UserVO;
import com.zeh.wms.biz.model.WechatUser;
import com.zeh.wms.biz.service.UserService;
import com.zeh.wms.integration.exceptions.IntegrationException;
import com.zeh.wms.integration.wechat.client.WechatClient;
import com.zeh.wms.integration.wechat.model.RetrieveSessionRequest;
import com.zeh.wms.integration.wechat.model.RetrieveSessionResponse;

/**
 * @author allen
 * @create $ ID: DefaultUserSessionManager, 18/3/12 16:18 allen Exp $
 * @since 1.0.0
 */
@Service
public class DefaultUserSessionManager implements UserSessionManager, AppConfigurationAware {
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();
    /** 用户session存储 */
    @Resource(name = "inMemoryUserSessionStore")
    private UserSessionStore             userSessionStore;
    /** 微信客户端 */
    @Resource
    private WechatClient                 wechatClient;
    /** 用户服务 */
    @Resource
    private UserService                  userService;
    /** 配置感知器 */
    private AppConfiguration             appConfiguration;

    /**
     * 获取用户会话
     *
     * @param sessionId 会话ID
     * @return 用户会话
     */
    @Override
    public UserSession getSession(String sessionId) {
        return userSessionStore.querySession(sessionId);
    }

    /**
     * 获取用户会话
     *
     * @param request
     * @return 用户会话
     */
    @Override
    public UserSession getSession(HttpServletRequest request) {
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            if (StringUtils.equalsIgnoreCase(key, UserSession.SESSION_ID_KEY)) {
                // 获取sid
                String sid = request.getHeader(UserSession.SESSION_ID_KEY);
                // 获取UserSession存储中的用户会话数据
                UserSession userSession = userSessionStore.querySession(sid);
                if (userSession == null) {
                    // 会话失效
                    request.getSession().removeAttribute(UserSession.SESSION_ID_KEY);
                    return null;
                }
                return userSession;
            }
        }
        // 获取当前用户会话
        Object obj = request.getSession().getAttribute(UserSession.SESSION_ID_KEY);
        if (obj != null) {
            return UserSession.class.cast(obj);
        }
        return null;
    }

    /**
     * 根据用户微信js_code，创建用户会话
     *
     * @param jsCode 用户微信js_code
     * @return 用户会话
     */
    @Override
    public UserSession createSession(String jsCode) {
        // 1. 获取用户微信会话信息
        WechatUser wechatUser = queryWechatUser(jsCode);
        // 2. 查询系统中用户的信息
        UserVO user = queryUser(wechatUser.getOpenId());
        // 3. 创建用户会话
        UserSession session = new DefaultUserSession(user, wechatUser, jsCode);
        userSessionStore.saveSession(session);
        return session;
    }

    /**
     * 设置应用配置信息
     *)
     * @param appConfiguration
     */
    @Override
    public void setAppConfiguration(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
    }

    /**
     * 查询微信侧用户信息
     * 
     * @param jsCode
     * @return
     */
    private WechatUser queryWechatUser(String jsCode) {
        String appId = appConfiguration.getPropertyValue("wechat.appId");
        String appSecret = appConfiguration.getPropertyValue("wechat.appSecret");
        RetrieveSessionRequest request = new RetrieveSessionRequest(appId, appSecret, jsCode);
        try {
            RetrieveSessionResponse response = wechatClient.retrieveWechatSession(request);
            if (StringUtils.isNotBlank(response.getErrCode())) {
                throw new SessionException(ERROR_FACTORY.generateSessionError("获取微信用户身份失败，错误信息：" + response.getErrCode() + ", " + response.getErrMsg()));
            }
            WechatUser wechatUser = new WechatUser();
            wechatUser.setOpenId(response.getOpenId());
            wechatUser.setSessionKey(response.getSessionKey());
            wechatUser.setUnionId(response.getUnionId());
            return wechatUser;
        } catch (IntegrationException e) {
            throw new SessionException(ERROR_FACTORY.generateSessionError(e.getMessage()), e);
        }
    }

    /**
     * 查询系统中，该微信用户的信息
     * @param openId
     * @return
     */
    private UserVO queryUser(String openId) {
        try {
            return userService.queryByOpenId(openId);
        } catch (ServiceException e) {
            throw new SessionException(ERROR_FACTORY.generateSessionError(e.getMessage()), e);
        }
    }
}