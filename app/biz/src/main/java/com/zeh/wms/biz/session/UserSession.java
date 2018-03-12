package com.zeh.wms.biz.session;

import java.io.Serializable;

import com.zeh.wms.biz.model.UserVO;
import com.zeh.wms.biz.model.WechatUser;

/**
 * @author allen
 * @create $ ID: UserSession, 18/3/12 15:47 allen Exp $
 * @since 1.0.0
 */
public interface UserSession extends Serializable {

    String SESSION_ID_KEY = "token";

    /**
     * 获取用户会话ID
     *
     * @return
     */
    String getId();

    /**
     * 获取用户当前JS_CODE
     * @return
     */
    String getCurrentJsCode();

    /**
     * 获取当前用户
     *
     * @return
     */
    UserVO getUser();

    /**
     * 设置用户信息
     * 
     * @param user
     */
    void setUser(UserVO user);

    /**
     * 获取微信侧用户信息
     * 
     * @return
     */
    WechatUser getWechatUser();
}
