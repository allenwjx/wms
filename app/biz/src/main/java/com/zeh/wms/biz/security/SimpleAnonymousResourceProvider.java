package com.zeh.wms.biz.security;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * @author allen
 * @create $ ID: SimpleAnonymousResourceProvider, 18/2/24 17:20 allen Exp $
 * @since 1.0.0
 */
public class SimpleAnonymousResourceProvider implements AnonymousResourceProvider {
    /** 不需要权限控制的资源 */
    private Set<String> anonymousUrls = Sets.newHashSet();

    @Override
    public Collection<String> getAnonymousUrls() {
        return anonymousUrls;
    }

    public void setAnonymousUrls(Set<String> anonymousUrls) {
        this.anonymousUrls = anonymousUrls;
    }
}