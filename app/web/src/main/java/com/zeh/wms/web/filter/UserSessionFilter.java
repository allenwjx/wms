package com.zeh.wms.web.filter;

import com.zeh.jungle.core.support.ExceptionUtils;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.jungle.utils.serializer.FastJsonUtils;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.UserVO;
import com.zeh.wms.biz.service.UserService;
import com.zeh.wms.biz.session.UserSession;
import com.zeh.wms.biz.session.UserSessionManager;
import com.zeh.wms.web.utils.ApiRequestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author weijun
 * @create $ v 1.0.0 2018/3/10 下午3:34 Exp $
 */
@Service("userSessionFilter")
public class UserSessionFilter implements Filter {
    @Resource
    private UserSessionManager sessionManager;
    @Resource
    private UserService        userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // no-op
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            ApiRequestUtils.setHttpServletRequest(request);
            ApiRequestUtils.setHttpServletResponse(response);

            UserSession userSession;
            String jsCode = request.getParameter("jsCode");
            if (StringUtils.isNotBlank(jsCode)) {
                // 微信侧用户会话失效，用户端发起登录操作，系统需重新创建用户会话信息
                userSession = sessionManager.createSession(jsCode);
                if (userSession.getUser() == null) {
                    // 新用户，默认进行注册
                    UserVO user = registerUser(request, userSession);
                    userSession.setUser(user);
                }
            } else {
                // 非微信侧用户会话失效，获取系统中用户会话信息
                userSession = sessionManager.getSession(request);
                if (userSession == null) {
                    // 用户会话失效，要求用户重新发起微信登录流程
                    SingleResult<String> sessionExpiredRet = new SingleResult<>("", "-2001", "session expired");
                    response.getWriter().append(FastJsonUtils.toJSONString(sessionExpiredRet));
                    return;
                }
            }
            request.getSession().setAttribute(UserSession.SESSION_ID_KEY, userSession);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            response.getWriter().append(FastJsonUtils.toJSONString(ExceptionUtils.getErrorResult(e, SingleResult.class)));
        } finally {
            ApiRequestUtils.clearHttpReqResponse();
        }
    }

    @Override
    public void destroy() {
        // no-op
    }

    /**
     * 注册新用户
     * @param request
     * @param userSession
     * @return
     */
    private UserVO registerUser(HttpServletRequest request, UserSession userSession) throws ServiceException {
        String nickName = request.getParameter("nickName");
        UserVO newUser = new UserVO();
        newUser.setOpenId(userSession.getWechatUser().getOpenId());
        newUser.setNickName(nickName);
        newUser = userService.createUser(newUser);
        return newUser;
    }

}
