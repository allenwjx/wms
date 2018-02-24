package com.zeh.wms.biz.security;

import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.google.common.collect.Lists;
import com.zeh.wms.biz.service.AuthorizationService;

/**
 * @author allen
 * @create $ ID: BGSecurityMetadataSource, 18/2/24 14:39 allen Exp $
 * @since 1.0.0
 */
public class BGSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    /** 资源权限服务 */
    private AuthorizationService authorizationService;

    /**
     * Accesses the {@code ConfigAttribute}s that apply to a given secure object.
     *
     * @param object the object being secured
     * @return the attributes that apply to the passed in secured object. Should return an empty collection if there
     * are no applicable attributes.
     * @throws IllegalArgumentException if the passed object is not of a type supported by the
     *                                  <code>SecurityMetadataSource</code> implementation
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (!(object instanceof FilterInvocation)) {
            throw new IllegalArgumentException("Parameter is not a valid FilterInvocation type");
        }
        // Do nothing
        Collection<ConfigAttribute> configAttributes = Lists.newArrayList();
        ConfigAttribute defaultConfigAttribute = new SecurityConfig("none");
        configAttributes.add(defaultConfigAttribute);
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        // no-op
        return null;
    }

    /**
     * Indicates whether the {@code SecurityMetadataSource} implementation is able to provide
     * {@code ConfigAttribute}s for the indicated secure object type.
     *
     * @param clazz the class that is being queried
     * @return true if the implementation can process the indicated class
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    public void setAuthorizationService(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }
}