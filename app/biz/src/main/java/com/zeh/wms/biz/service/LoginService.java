package com.zeh.wms.biz.service;

import com.zeh.wms.biz.exception.LoginException;
import com.zeh.wms.biz.model.UserBgVO;

/**
 * @author allen
 * @create $ ID: LoginService, 18/2/11 15:41 allen Exp $
 * @since 1.0.0
 */
public interface LoginService {
    /**
     * 后台用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录用户
     * @throws LoginException 登录异常
     */
    UserBgVO loginBg(String username, String password) throws LoginException;
}
