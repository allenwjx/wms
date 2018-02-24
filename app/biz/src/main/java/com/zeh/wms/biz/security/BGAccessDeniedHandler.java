package com.zeh.wms.biz.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import com.zeh.jungle.core.error.JGError;
import com.zeh.jungle.utils.serializer.FastJsonUtils;
import com.zeh.wms.biz.error.BizErrorFactory;

/**
 * @author allen
 * @create $ ID: BGAccessDeniedHandler, 18/2/24 21:35 allen Exp $
 * @since 1.0.0
 */
public class BGAccessDeniedHandler implements AccessDeniedHandler {
    protected static final Logger logger = LoggerFactory.getLogger(AccessDeniedHandlerImpl.class);
    private String                errorPage;

    /**
     * Handles an access denied failure.
     *
     * @param request               that resulted in an <code>AccessDeniedException</code>
     * @param response              so that the user agent can be advised of the failure
     * @param accessDeniedException that caused the invocation
     * @throws IOException      in the event of an IOException
     * @throws ServletException in the event of a ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        boolean isAjax = isAjaxRequest(request);
        if (isAjax) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            // 记录异常日志(错误码LY0500102007)
            JGError error = BizErrorFactory.getInstance().accessDenied();
            out.print(FastJsonUtils.toJSONString(error));
            logger.error(new StringBuffer("通用异常处理器 ：Catch a ").append(accessDeniedException.getClass().getSimpleName()).append(" and write to response json errors = ")
                .append(FastJsonUtils.toJSONString(error)).append("\\r\\n==异常信息如下：\\r\\n========").append(accessDeniedException.getCause().toString()).toString());
            out.flush();
        } else if (!response.isCommitted()) {
            if (errorPage != null) {
                // Put exception into request scope (perhaps of use to a view)
                request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);

                // Set the 403 status code.
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);

                // forward to error page.
                request.getRequestDispatcher(this.errorPage).forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
            }
        }
    }

    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }

    /**
     * Is ajax request boolean.
     *
     * @param request the request
     * @return the boolean
     */
    private boolean isAjaxRequest(HttpServletRequest request) {
        if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        }
        return false;
    }
}