package com.zeh.wms.biz.session;

/**
 * @author allen
 * @create $ ID: UserSessionStore, 18/3/12 16:22 allen Exp $
 * @since 1.0.0
 */
public interface UserSessionStore {
    /**
     * 查询用户会话
     *
     * @param id 用户会话ID
     * @return 用户会话
     */
    UserSession querySession(String id);

    /**
     * 保存用户会话
     *
     * @param session 用户会话
     * @return 用户会话
     */
    UserSession saveSession(UserSession session);

    /**
     * 删除用户会话
     * 
     * @param id 用户会话ID
     */
    void deleteSession(String id);

}