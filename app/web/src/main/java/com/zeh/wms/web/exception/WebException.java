package com.zeh.wms.web.exception;

import com.zeh.jungle.core.error.JGError;
import com.zeh.jungle.core.exception.JGException;

/**
 * @author hzy24985
 * @version $Id: WebException, v 0.1 2018/2/11 22:41 hzy24985 Exp $
 */
public class WebException extends JGException {
    public WebException(JGError error) {
        super(error);
    }

    public WebException(JGError error, Throwable cause) {
        super(error, cause);
    }

    public WebException(Throwable cause) {
        super(cause);
    }
}
