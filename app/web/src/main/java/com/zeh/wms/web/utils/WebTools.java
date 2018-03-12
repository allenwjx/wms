package com.zeh.wms.web.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class WebTools {
    public static String getHeaderValue(HttpServletRequest request, String keyIndex) {
        if (request != null) {
            Enumeration headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = (String) headerNames.nextElement();
                if (key.equals(keyIndex)) {
                    return request.getHeader(key);
                }
            }
        }
        return null;
    }
}
