package com.zeh.wms.web.error;

import com.zeh.jungle.core.error.AbstractErrorFactory;
import com.zeh.jungle.core.error.JGError;

/**
 * @author hzy24985
 * @version $Id: WebErrorFactory, v 0.1 2018/2/11 22:37 hzy24985 Exp $
 */
public class WebErrorFactory extends AbstractErrorFactory {
    /**
     * @see com.zeh.jungle.core.error.AbstractErrorFactory#provideErrorBundleName()
     */
    @Override
    protected String provideErrorBundleName() {
        return "wms-web";
    }

    /**
     * 获取JpaDalErrorFactory单例
     *
     * @return BizErrorFactory
     */
    public static WebErrorFactory getInstance() {
        return WebErrorFactoryHolder.FACTORY;
    }

    /**
     * BizErrorFactoryHolder instance keeper
     *
     * @author allen
     * @version $Id: BizErrorFactoryHolder.java, v 0.1 2016年2月26日 下午4:20:31 allen Exp $
     */
    private static final class WebErrorFactoryHolder {
        /** instance */
        private static final WebErrorFactory FACTORY = new WebErrorFactory();
    }

    /**
     * 参数异常。{0}不能为空.
     * @param parameterName 参数名
     * @return
     */
    public JGError parameterEmptyError(String parameterName) {
        return createError("JG0510101001", parameterName);
    }
}
