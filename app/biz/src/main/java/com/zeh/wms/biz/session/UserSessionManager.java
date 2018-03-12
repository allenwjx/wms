package com.zeh.wms.biz.session;

import javax.servlet.http.HttpServletRequest;

/**
 * @author allen
 * @create $ ID: UserSessionManager, 18/3/12 16:13 allen Exp $
 * @since 1.0.0
 */
public interface UserSessionManager {

    /**
     * 获取用户会话
     *
     * @param sessionId 会话ID
     * @return 用户会话
     */
    UserSession getSession(String sessionId);

    /**
     * 获取用户会话
     *
     * @param request
     * @return 用户会话
     */
    UserSession getSession(HttpServletRequest request);

    /**
     * 根据用户微信js_code，创建用户会话
     *
     * @param jsCode 用户微信js_code
     * @return 用户会话
     */
    UserSession createSession(String jsCode);
}