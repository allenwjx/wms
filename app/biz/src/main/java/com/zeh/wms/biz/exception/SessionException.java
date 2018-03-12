package com.zeh.wms.biz.exception;

import com.zeh.jungle.core.error.JGError;
import com.zeh.jungle.core.exception.JGRuntimeException;

/**
 * @author allen
 * @create $ ID: SessionException, 18/3/12 17:21 allen Exp $
 * @since 1.0.0
 */
public class SessionException extends JGRuntimeException {
    /**
     * @param error
     */
    public SessionException(JGError error) {
        super(error);
    }

    /**
     * @param error
     * @param cause
     */
    public SessionException(JGError error, Throwable cause) {
        super(error, cause);
    }

    /**
     * @param cause
     */
    public SessionException(Throwable cause) {
        super(cause);
    }
}