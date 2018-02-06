package com.zeh.wms.biz.exception;

import com.zeh.jungle.core.error.JGError;
import com.zeh.jungle.core.exception.JGException;

/**
 * @author allen
 * @create $ ID: QRCodeException, 18/2/6 13:29 allen Exp $
 * @since 1.0.0
 */
public class QRCodeException extends JGException {
    /**
     * 构造方法
     *
     * @param error 错误实例
     */
    public QRCodeException(JGError error) {
        super(error);
    }

    /**
     * 构造方法
     *
     * @param error 错误实例
     * @param cause 异常
     */
    public QRCodeException(JGError error, Throwable cause) {
        super(error, cause);
    }

    /**
     * 构造方法
     *
     * @param cause 异常
     */
    public QRCodeException(Throwable cause) {
        super(cause);
    }
}
