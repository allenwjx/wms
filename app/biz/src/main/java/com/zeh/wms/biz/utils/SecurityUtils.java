package com.zeh.wms.biz.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.common.collect.Lists;
import com.zeh.wms.biz.model.UserBgVO;
import com.zeh.wms.biz.security.UserWrapper;

/**
 * @author allen
 * @create $ ID: SecurityUtils, 18/2/24 16:35 allen Exp $
 * @since 1.0.0
 */
public class SecurityUtils {
    private static List<String> ADMIN_USERNAMES = Lists.newArrayList();
    static {
        ADMIN_USERNAMES.add("zehadmin1qaz2wsx");
    }

    /**
     * 获取已登录用户
     *
     * @return
     */
    public static UserBgVO getLoginedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        UserWrapper user = (UserWrapper) authentication.getPrincipal();
        return user.getUser();
    }

    /**
     * 获取已登录用户的用户名
     * 
     * @return
     */
    public static String getLoginedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserWrapper user = (UserWrapper) authentication.getPrincipal();
        return user.getUsername();
    }

    /**
     * 获取已登录用户的用户ID
     *
     * @return
     */
    public static Long getLoginedUserId() {
        return getLoginedUser().getId();
    }

    /**
     * 判断用户是否已登录
     * 
     * @return
     */
    public static boolean isLogined() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return true;
    }

    /**
     * 是否是超级管理员
     *
     * @return
     */
    public static boolean isAdmin() {
        String username = getLoginedUsername();
        if (ADMIN_USERNAMES.contains(username)) {
            return true;
        }
        return false;
    }

    /**
     * 获取用户访问的资源
     *
     * @param request
     * @return
     */
    public static String getUserAccessUrl(HttpServletRequest request) {
        return request.getRequestURI().replaceFirst(request.getContextPath(), "");
    }

}