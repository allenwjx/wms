package com.zeh.wms.web.filter;

import com.alibaba.druid.support.json.JSONUtils;
import com.zeh.jungle.core.support.ExceptionUtils;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.model.Session;
import com.zeh.wms.biz.model.UserVO;
import com.zeh.wms.biz.service.SessionManager;
import com.zeh.wms.biz.service.UserService;
import com.zeh.wms.web.utils.WebTools;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author weijun
 * @create $ v 1.0.0 2018/3/10 下午3:34 Exp $
 */
public class UserSessionFilter implements Filter {

    /**
     * http 请求头参数
     * 塞入这个参数，即可以换到服务端身份
     *
     */
    private static final String REQUEST_HEADER_SESSION_FLAG = "session_flag";

    private SessionManager sessionManager;

    private UserService userService;

    @Override public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        // 通过请求头中的参数，进行真正的登陆操作
        if(WebTools.getHeaderValue(request, Session.SESSION_FLAG) != null) {
            try {
                Session session = sessionManager.getSessionById(request.getHeader(Session.SESSION_FLAG));
                request.getSession().setAttribute(Session.SESSION_FLAG, session);
            } catch (Exception ex) {
                ex.printStackTrace();
                return ;
            }
        }

        // 如果存在会话，则跳出
        if (request.getSession().getAttribute(Session.SESSION_FLAG) != null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return ;
        }

        // 否则检查是否传入 jsCode 参数
        // 意即，微信直接发起登陆请求
        if (StringUtils.isNotBlank(request.getParameter("jsCode"))) {
            Session session = new Session();
            session.setWechatJscode(request.getParameter("jsCode"));

            try {
                session = sessionManager.generateSession(session);
                if (session.getUserVO() == null || session.getUserVO().getId() <= 0) {
                    // 此处需要直接招待注册
                    UserVO tobeRegisteUser = new UserVO();
                    tobeRegisteUser.setOpenId(session.getWechatOpenid());
                    tobeRegisteUser.setNickName(session.getWechatOpenid());
                    tobeRegisteUser = userService.createUser(tobeRegisteUser);
                    session.setUserVO(tobeRegisteUser);
                }
            } catch (Exception ex) {
                // 异常时报错
                // TODO 需要处理好异常显示
                // ex.printStackTrace();
                response.getWriter().append(JSONUtils.toJSONString(ExceptionUtils.getErrorResult(ex, SingleResult.class)));
                return ;
            }

            request.getSession().setAttribute(Session.SESSION_FLAG, session);
            filterChain.doFilter(servletRequest, servletResponse);
            return ;
        }

        filterChain.doFilter(servletRequest, servletResponse);
        return;
    }

    @Override public void destroy() {

    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
