package com.zeh.wms.biz.security;

import java.util.Collection;

/**
 * @author allen
 * @create $ ID: AnonymousResourceProvider, 18/2/24 17:43 allen Exp $
 * @since 1.0.0
 */
public interface AnonymousResourceProvider {
    /**
     * 获取白名单资源
     * 
     * @return
     */
    Collection<String> getAnonymousUrls();
}
