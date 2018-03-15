package com.zeh.wms.biz.exception;

import com.zeh.jungle.core.error.JGError;
import com.zeh.jungle.core.exception.JGException;

/**
 * @author allen
 * @create $ ID: BookServiceException, 18/3/15 14:26 allen Exp $
 * @since 1.0.0
 */
public class BookServiceException extends JGException {
    /**  */
    private static final long serialVersionUID = 1L;

    /**
     * 构造方法
     *
     * @param error 错误实例
     */
    public BookServiceException(JGError error) {
        super(error);
    }

    /**
     * 构造方法
     *
     * @param error 错误实例
     * @param cause 异常
     */
    public BookServiceException(JGError error, Throwable cause) {
        super(error, cause);
    }

    /**
     * 构造方法
     *
     * @param cause 异常
     */
    public BookServiceException(Throwable cause) {
        super(cause);
    }
}