package com.zeh.wms.biz.service.impl;

import javax.annotation.Resource;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.LoginException;
import com.zeh.wms.biz.mapper.UserBgMapper;
import com.zeh.wms.biz.model.UserBgVO;
import com.zeh.wms.biz.service.LoginService;
import com.zeh.wms.dal.daointerface.UserBgDAO;
import com.zeh.wms.dal.dataobject.UserBgDO;

/**
 * @author allen
 * @create $ ID: LoginServiceImpl, 18/2/11 15:43 allen Exp $
 * @since 1.0.0
 */
@Service
public class LoginServiceImpl implements LoginService {
    /** 错误工厂 */
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();
    /** 密码加解密服务 */
    @Resource
    private PasswordEncoder              passwordEncoder;
    /** 后台用户DAO */
    @Resource
    private UserBgDAO                    userBgDAO;
    /** mapper */
    @Resource
    private UserBgMapper                 mapper;

    /**
     * 后台用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录用户
     * @throws LoginException 登录异常
     */
    @Override
    public UserBgVO loginBg(String username, String password) throws LoginException {
        UserBgDO userBgDO = userBgDAO.queryByUsername(username);
        if (userBgDO == null) {
            throw new LoginException(ERROR_FACTORY.usernameInvalid());
        }
        boolean valid = passwordEncoder.matches(password, userBgDO.getPassword());
        if (!valid) {
            throw new LoginException(ERROR_FACTORY.passwordInvalid());
        }
        UserBgVO userBg = mapper.do2vo(userBgDO);
        return userBg;
    }
}