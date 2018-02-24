package com.zeh.wms.biz.security;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zeh.wms.biz.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;

import com.zeh.jungle.utils.common.LoggerUtils;

/**
 * @author allen
 * @create $ ID: BGSecurityInterceptor, 18/2/24 15:49 allen Exp $
 * @since 1.0.0
 */
public class BGSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
    private static final Logger              logger = LoggerFactory.getLogger(BGSecurityInterceptor.class);
    /** Security metadata source */
    private SecurityMetadataSource           securityMetadataSource;
    /** 白名单资源  */
    private AnonymousResourceProviderManager providerManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // no-op
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String url = SecurityUtils.getUserAccessUrl(httpRequest);
        // 检查是否白名单资源
        if (providerManager.isAnonymous(httpRequest)) {
            chain.doFilter(request, response);
            return;
        }
        // 检查用户是否已登录
        if (!SecurityUtils.isLogined() && !isAjaxRequest(httpRequest)) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/page/login/index");
            LoggerUtils.warn(logger, "User did not login, access URI {}", url);
            return;
        }
        // 超级管理员放行
        if (SecurityUtils.isAdmin()) {
            chain.doFilter(request, response);
            return;
        }
        FilterInvocation filterInvocation = new FilterInvocation(request, response, chain);
        doSeucurity(filterInvocation);
    }

    @Override
    public void destroy() {
        // no-op

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return securityMetadataSource;
    }

    /**
     * 授权验证
     * @param filterInvocation
     * @throws IOException
     * @throws ServletException
     */
    protected void doSeucurity(FilterInvocation filterInvocation) throws IOException, ServletException {
        // 进行授权验证
        InterceptorStatusToken token;
        try {
            token = super.beforeInvocation(filterInvocation);
        } catch (IllegalArgumentException e) {
            HttpServletRequest httpRequest = filterInvocation.getRequest();
            HttpServletResponse httpResponse = filterInvocation.getResponse();
            String url = SecurityUtils.getUserAccessUrl(httpRequest);
            LoggerUtils.warn(logger, "User [{}] access unauthorized resource {}", SecurityUtils.getLoginedUsername(), url);
            httpResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/page/error/403");
            dispatcher.forward(httpRequest, httpResponse);
            return;
        }

        try {
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    private boolean isAjaxRequest(HttpServletRequest httpRequest) {
        return "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With"));
    }

    public void setSecurityMetadataSource(SecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }

    public void setProviderManager(AnonymousResourceProviderManager providerManager) {
        this.providerManager = providerManager;
    }
}