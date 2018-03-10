package com.zeh.wms.biz.service;

import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.Session;
import com.zeh.wms.biz.model.UserVO;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author weijun
 * @create $ v 1.0.0 2018/3/10 下午2:07 Exp $
 */
public interface SessionManager {

    /**
     * 通过系统会话id查询当前会话
     *
     * @param sessionId
     * @return
     * @throws ServiceException
     */
    Session getSessionById (String sessionId) throws ServiceException;

    /**
     * 从 servlet 中获取会话
     *
     * @param request
     * @return
     * @throws ServiceException
     */
    Session getSessionFromSevlet (HttpServletRequest request) throws ServiceException;

    /**
     * 刷新指定缓存中的用户对象
     *
     * @param sessionId
     * @param user
     * @throws ServiceException
     */
    void refreshSessionUser (String sessionId, UserVO user) throws ServiceException;

    /**
     * 生成会话
     *
     * @param session
     * @return
     * @throws ServiceException
     */
    Session generateSession (Session session) throws ServiceException;
}
