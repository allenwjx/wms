package com.zeh.wms.web.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hzy24985
 * @version $Id: ApiRequestUtils, v 0.1 2018/3/12 23:02 hzy24985 Exp $
 */
public class ApiRequestUtils {
    private static ThreadLocal<HttpServletRequest> reqLocal = new ThreadLocal<>();
    private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<>();

    public static void setHttpServletRequest(HttpServletRequest request) {
        reqLocal.set(request);
    }

    public static void clearHttpReqResponse() {
        reqLocal.remove();
        responseLocal.remove();
    }

    public static void setHttpServletResponse(HttpServletResponse response) {
        responseLocal.set(response);
    }

    public static HttpServletRequest getHttpServletRequest() {
        return reqLocal.get();
    }

    public static HttpServletResponse getHttpServletResponse() {
        return responseLocal.get();
    }

    public static String getString(HttpServletRequest request, String key, String defaultValue) {
        String value = request.getParameter(key);
        return StringUtils.isEmpty(value) ? defaultValue : value.replace("'", "''").trim();
    }
}
