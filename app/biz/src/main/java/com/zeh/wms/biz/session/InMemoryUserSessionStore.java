package com.zeh.wms.biz.session;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

/**
 * @author allen
 * @create $ ID: InMemoryUserSessionStore, 18/3/12 16:25 allen Exp $
 * @since 1.0.0
 */
@Service("inMemoryUserSessionStore")
public class InMemoryUserSessionStore implements UserSessionStore {
    /** 本地内存Session存储 */
    private static final Map<String, UserSession> SESSION_CACHE = Maps.newConcurrentMap();

    /**
     * 查询用户会话
     *
     * @param id 用户会话ID
     * @return 用户会话
     */
    @Override
    public UserSession querySession(String id) {
        return SESSION_CACHE.get(id);
    }

    /**
     * 保存用户会话
     *
     * @param session 用户会话
     * @return 用户会话
     */
    @Override
    public UserSession saveSession(UserSession session) {
        SESSION_CACHE.put(session.getId(), session);
        return session;
    }

    /**
     * 删除用户会话
     *
     * @param id 用户会话ID
     */
    @Override
    public void deleteSession(String id) {
        SESSION_CACHE.remove(id);
    }
}