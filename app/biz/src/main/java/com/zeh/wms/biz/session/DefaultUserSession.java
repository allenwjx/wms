package com.zeh.wms.biz.session;

import com.zeh.jungle.utils.common.UUID;
import com.zeh.wms.biz.model.UserVO;
import com.zeh.wms.biz.model.WechatUser;

/**
 * @author allen
 * @create $ ID: DefaultUserSession, 18/3/12 16:07 allen Exp $
 * @since 1.0.0
 */
public class DefaultUserSession implements UserSession {
    /**  */
    private static final long serialVersionUID = 1L;
    private String            id;
    private UserVO            user;
    private WechatUser        wechatUser;
    private String            currentJsCode;

    public DefaultUserSession(UserVO user, WechatUser wechatUser, String currentJsCode) {
        id = UUID.generateTimeBasedUUID();
        this.user = user;
        this.wechatUser = wechatUser;
        this.currentJsCode = currentJsCode;
    }

    /**
     * 获取用户会话ID
     *
     * @return
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    @Override
    public UserVO getUser() {
        return user;
    }

    /**
     * 设置用户信息
     *
     * @param user
     */
    @Override
    public void setUser(UserVO user) {
        this.user = user;
    }

    /**
     * 获取微信侧用户信息
     *
     * @return
     */
    @Override
    public WechatUser getWechatUser() {
        return wechatUser;
    }

    /**
     * 获取用户当前JS_CODE
     *
     * @return
     */
    @Override
    public String getCurrentJsCode() {
        return currentJsCode;
    }
}