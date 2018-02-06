package com.zeh.wms.biz.error;

import com.zeh.jungle.core.error.AbstractErrorFactory;
import com.zeh.jungle.core.error.JGError;

/**
 * 业务模块错误工厂
 * 
 * @author allen
 * @version $Id: BizErrorFactory.java, v 0.1 2016年2月26日 下午4:14:42 allen Exp $
 */
public class BizErrorFactory extends AbstractErrorFactory {

    /** 
     * @see com.zeh.jungle.core.error.AbstractErrorFactory#provideErrorBundleName()
     */
    @Override
    protected String provideErrorBundleName() {
        return "wms-biz";
    }

    /**
     * 获取JpaDalErrorFactory单例
     * 
     * @return BizErrorFactory
     */
    public static BizErrorFactory getInstance() {
        return BizErrorFactoryHolder.FACTORY;
    }

    /**
     * BizErrorFactoryHolder instance keeper
     * 
     * @author allen
     * @version $Id: BizErrorFactoryHolder.java, v 0.1 2016年2月26日 下午4:20:31 allen Exp $
     */
    private static final class BizErrorFactoryHolder {
        /** instance */
        private static final BizErrorFactory FACTORY = new BizErrorFactory();
    }

    /**
     * JG0510010001=二维码生成失败，二维码内容：{0}
     * 
     * @param content 生成二维码的内容
     * @return JGError
     */
    public JGError encodeQRCodeError(String content) {
        return createError("JG0510010001", content);
    }

    /**
     * JG0510010002=二维码解析失败，二维码内容：{0}
     *
     * @param qrcode 二维码Base64数据
     * @return JGError
     */
    public JGError decodeQRCodeError(String qrcode) {
        return createError("JG0510010002", qrcode);
    }

}
