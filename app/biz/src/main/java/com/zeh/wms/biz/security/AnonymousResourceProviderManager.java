package com.zeh.wms.biz.security;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author allen
 * @create $ ID: AnonymousResourceProviderManager, 18/2/24 17:44 allen Exp $
 * @since 1.0.0
 */
public interface AnonymousResourceProviderManager {

    /**
     * 是否是白名单资源
     *
     * @param request 资源
     * @return
     */
    boolean isAnonymous(HttpServletRequest request);

    /**
     * 获取白名单资源
     *
     * @return
     */
    Collection<String> getAnonymousUrls();

    /**
     * 获取白名单提供器
     * 
     * @return
     */
    Collection<AnonymousResourceProvider> getProviders();
}
