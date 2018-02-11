package com.zeh.wms.biz.exception;

import com.zeh.jungle.core.error.JGError;
import com.zeh.jungle.core.exception.JGException;

/**
 * @author allen
 * @create $ ID: LoginException, 18/2/11 15:41 allen Exp $
 * @since 1.0.0
 */
public class LoginException extends JGException {
    /**
     * 构造方法
     *
     * @param error 错误实例
     */
    public LoginException(JGError error) {
        super(error);
    }

    /**
     * 构造方法
     *
     * @param error 错误实例
     * @param cause 异常
     */
    public LoginException(JGError error, Throwable cause) {
        super(error, cause);
    }

    /**
     * 构造方法
     *
     * @param cause 异常
     */
    public LoginException(Throwable cause) {
        super(cause);
    }
}