package com.zeh.wms.biz.service;

import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.Session;

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
     * 生成会话
     *
     * @param session
     * @return
     * @throws ServiceException
     */
    Session generateSession (Session session) throws ServiceException;
}
