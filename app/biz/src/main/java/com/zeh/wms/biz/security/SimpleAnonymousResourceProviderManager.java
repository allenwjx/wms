package com.zeh.wms.biz.security;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

/**
 * @author allen
 * @create $ ID: SimpleAnonymousResourceProviderManager, 18/2/24 17:45 allen Exp $
 * @since 1.0.0
 */
@Service("anonymousResourceProviderManager")
public class SimpleAnonymousResourceProviderManager implements AnonymousResourceProviderManager, BeanPostProcessor {
    /** 不需要权限控制的资源 */
    private Collection<String>                    anonymousUrls     = Sets.newHashSet();
    private Collection<RequestMatcher>            anonymousMatchers = Sets.newHashSet();
    private Collection<AnonymousResourceProvider> providers         = Sets.newHashSet();

    /**
     * 是否是白名单资源
     *
     * @param request 资源
     * @return
     */
    @Override
    public boolean isAnonymous(HttpServletRequest request) {
        for (RequestMatcher matcher : anonymousMatchers) {
            if (matcher.matches(request)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取白名单资源
     *
     * @return
     */
    @Override
    public Collection<String> getAnonymousUrls() {
        return anonymousUrls;
    }

    /**
     * 获取白名单提供器
     *
     * @return
     */
    @Override
    public Collection<AnonymousResourceProvider> getProviders() {
        return providers;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // no-op
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (AnonymousResourceProvider.class.isAssignableFrom(bean.getClass())) {
            AnonymousResourceProvider provider = AnonymousResourceProvider.class.cast(bean);
            Collection<String> resources = provider.getAnonymousUrls();
            anonymousUrls.addAll(resources);
            providers.add(provider);
            for (String resource : resources) {
                RequestMatcher matcher = new AntPathRequestMatcher(resource);
                anonymousMatchers.add(matcher);
            }
        }
        return bean;
    }
}