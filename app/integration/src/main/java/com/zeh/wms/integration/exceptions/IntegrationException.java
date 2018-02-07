package com.zeh.wms.integration.exceptions;

import com.zeh.jungle.core.error.JGError;
import com.zeh.jungle.core.exception.JGException;

/**
 * @author hzy24985
 * @version $Id: IntegrationException, v 0.1 2018/2/7 16:23 hzy24985 Exp $
 */
public class IntegrationException extends JGException {
    public IntegrationException(JGError error) {
        super(error);
    }

    public IntegrationException(JGError error, Throwable cause) {
        super(error, cause);
    }

    public IntegrationException(Throwable cause) {
        super(cause);
    }
}
