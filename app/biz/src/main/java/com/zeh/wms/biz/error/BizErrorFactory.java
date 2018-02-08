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

    /**
     * JG0510011001=厂商创建失败，厂商数据不能为空
     *
     * @return JGError
     */
    public JGError createManufacturerError() {
        return createError("JG0510011001");
    }

    /**
     * JG0510011002=厂商更新失败，厂商数据ID不能为空
     *
     * @return JGError
     */
    public JGError updateManufacturerError() {
        return createError("JG0510011002");
    }

    /**
     * JG0510011003=厂商查询失败，查询条件不能为空
     * 
     * @return JGError
     */
    public JGError queryManufacturerError() {
        return createError("JG0510011003");
    }

    /**
     * JG0510012001=代理商创建失败，代理商数据不能为空
     *
     * @return JGError
     */
    public JGError createAgentError() {
        return createError("JG0510012001");
    }

    /**
     * JG0510012002=代理商更新失败，代理商数据ID不能为空
     *
     * @return JGError
     */
    public JGError updateAgentError() {
        return createError("JG0510012002");
    }

    /**
     * JG0510012003=代理商查询失败，查询条件不能为空
     *
     * @return JGError
     */
    public JGError queryAgentError() {
        return createError("JG0510012003");
    }

    /**
     * 未找到快递单详情。id: {0}
     * @param id 主键
     * @return
     */
    public JGError notFindExpressOrderDetail(Long id) {
        return createError("JG0510021001", id);
    }

}
