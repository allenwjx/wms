package com.zeh.wms.biz.service.impl;

import com.zeh.jungle.utils.common.UUID;
import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.Session;
import com.zeh.wms.biz.model.UserVO;
import com.zeh.wms.biz.model.WechatUser;
import com.zeh.wms.biz.service.SessionManager;
import com.zeh.wms.biz.service.UserService;
import com.zeh.wms.biz.service.WechatService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author weijun
 * @create $ v 1.0.0 2018/3/10 下午2:18 Exp $
 */
@Service (value = "sessionManager")
public class SessionManagerImpl implements SessionManager {

    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();

    @Resource private WechatService wechatService;

    @Resource private UserService userService;

    private Map<String, Session> sessions = new HashMap<>();

    @Override public Session getSessionById(String sessionId) throws ServiceException {
        if (sessions.containsKey(sessionId))
            return sessions.get(sessionId);
        else
            throw new ServiceException(ERROR_FACTORY.getSessionError("指定会话不存在"));
    }

    /**
     * 生成系统会话并缓存
     * 当前策略是直接缓存在内存中
     *
     * @param session
     * @return
     * @throws ServiceException
     */
    @Override public Session generateSession(Session session) throws ServiceException {
        if (session == null) {
            throw new ServiceException(ERROR_FACTORY.generateSessionError("查询参数不能为空"));
        }
        if (StringUtils.isBlank(session.getWechatJscode())) {
            throw new ServiceException(ERROR_FACTORY.generateSessionError("缺少必要参数"));
        }

        WechatUser wechatUser = wechatService.getUserByJSCode(session.getWechatJscode());
        UserVO user = null;
        try {
            user = userService.queryByOpenId(wechatUser.getOpenid());
        } catch (Exception ex) {
        }
        if (user != null) {
            session.setUserVO(user);
        }

        session.setId(UUID.generateRandomUUID());
        session.setWechatOpenid(wechatUser.getOpenid());
        session.setWechatSessionKey(wechatUser.getSession_key());

        // 存入缓存
        sessions.put(session.getId(), session);
        return session;
    }

    /**
     *
     * @param request
     * @return
     * @throws ServiceException
     */
    @Override public Session getSessionFromSevlet(HttpServletRequest request) throws ServiceException {
        if (request == null) {
            throw new ServiceException(ERROR_FACTORY.getSessionError("缺少必要参数"));
        }
        if (request.getSession().getAttribute(Session.SESSION_FLAG) == null) {
            throw new ServiceException(ERROR_FACTORY.getSessionError("当前会话未登陆"));
        }
        return (Session) request.getSession().getAttribute(Session.SESSION_FLAG);
    }

    /**
     *
     * @param sessionId
     * @param user
     * @throws ServiceException
     */
    @Override public void refreshSessionUser(String sessionId, UserVO user) throws ServiceException {
        Session session = sessions.get(sessionId);
        if (session == null) {
            throw new ServiceException(ERROR_FACTORY.getSessionError("指定会话不存在"));
        }
        session.setUserVO(user);
    }
}
