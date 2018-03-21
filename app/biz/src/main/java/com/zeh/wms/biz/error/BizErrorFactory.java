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
     * JG0510013001=商品创建失败，商品数据不能为空
     *
     * @return JGError
     */
    public JGError createCommodityError() {
        return createError("JG0510013001");
    }

    /**
     * JG0510013002=商品更新失败，商品数据ID不能为空
     *
     * @return JGError
     */
    public JGError updateCommodityError() {
        return createError("JG0510013002");
    }

    /**
     * JG0510013003=商品查询失败，查询条件不能为空
     *
     * @return JGError
     */
    public JGError queryCommodityError() {
        return createError("JG0510013003");
    }

    /**
     * JG0510014001=运价创建失败，运价数据不能为空
     *
     * @return JGError
     */
    public JGError createFreightError() {
        return createError("JG0510014001");
    }

    /**
     * JG0510014002=运价更新失败，运价数据ID不能为空
     *
     * @return JGError
     */
    public JGError updateFreightError() {
        return createError("JG0510014002");
    }

    /**
     * JG0510014003=运价查询失败，查询条件不能为空
     *
     * @return JGError
     */
    public JGError queryFreightError() {
        return createError("JG0510014003");
    }

    /**
     * JG0510014004=运价创建失败，{0}有效运价已存在
     *
     * @return JGError
     */
    public JGError freightExistError(String province, String expressCode, long id) {
        return createError("JG0510014004", province, expressCode, id);
    }

    /**
     * JG0510015001=辅材创建失败，辅材数据不能为空
     *
     * @return JGError
     */
    public JGError createAuxiliaryError() {
        return createError("JG0510015001");
    }

    /**
     * JG0510015002=辅材更新失败，辅材数据ID不能为空
     *
     * @return JGError
     */
    public JGError updateAuxiliaryError() {
        return createError("JG0510015002");
    }

    /**
     * JG0510015003=辅材查询失败，查询条件不能为空
     *
     * @return JGError
     */
    public JGError queryAuxiliaryError() {
        return createError("JG0510015003");
    }

    /**
     * JG0510015004=辅材创建失败，{0}有效辅材已存在，辅材ID：{1}
     *
     * @return JGError
     */
    public JGError auxiliaryExistError(long commodity, long id) {
        return createError("JG0510015004", commodity, id);
    }

    /**
     * JG0510016001=二维码批次创建失败，二维码批次数据不能为空
     *
     * @return JGError
     */
    public JGError createQRCodeBatchError() {
        return createError("JG0510016001");
    }

    /**
     * JG0510016002=二维码批次更新失败，二维码批次数据ID不能为空
     *
     * @return JGError
     */
    public JGError updateQRCodeBatchError() {
        return createError("JG0510016002");
    }

    /**
     * JG0510016003=二维码批次查询失败，查询条件不能为空
     *
     * @return JGError
     */
    public JGError queryQRCodeBatchError() {
        return createError("JG0510016003");
    }

    /**
     * JG0510017001=后台用户创建失败，后台用户数据不能为空
     *
     * @return JGError
     */
    public JGError createUserBgError() {
        return createError("JG0510017001");
    }

    /**
     * JG0510017100=用户创建失败，{0}
     *
     * @return JGError
     */
    public JGError createUserError(String msg) {
        return createError("JG0510017100", msg);
    }

    /**
     * JG0510017101=用户更新失败，{0}
     *
     * @return JGError
     */
    public JGError updateUserError(String msg) {
        return createError("JG0510017101", msg);
    }

    /**
     * JG0510017002=后台用户更新失败，后台用户ID不能为空
     *
     * @return JGError
     */
    public JGError updateUserBgError() {
        return createError("JG0510017002");
    }

    /**
     * JG0510017003=后台用户查询失败，查询条件不能为空
     *
     * @return JGError
     */
    public JGError queryUserBgError() {
        return createError("JG0510017003");
    }

    /**
     * JG0510017004=后台用户创建失败，用户名{0}已存在
     *
     * @return
     */
    public JGError userBgNameExistError(String username) {
        return createError("JG0510017004", username);
    }

    /**
     * JG0510017005=后台用户密码更新失败，用户名{0}
     *
     * @return
     */
    public JGError userBgPasswordUpdateError(String username) {
        return createError("JG0510017005", username);
    }

    /**
     * JG0510018001=资源权限创建失败，资源权限数据不能为空
     *
     * @return JGError
     */
    public JGError createAuthorizatonError() {
        return createError("JG0510018001");
    }

    /**
     * JG0510018002=资源权限更新失败，资源权限ID不能为空
     *
     * @return JGError
     */
    public JGError updateAuthorizatonError() {
        return createError("JG0510018002");
    }

    /**
     * JG0510018003=资源权限查询失败，查询条件不能为空
     *
     * @return JGError
     */
    public JGError queryAuthorizatonError() {
        return createError("JG0510018003");
    }

    /**
     * JG0510018004=资源权限编码【{0}】已存在
     *
     * @param code
     * @return
     */
    public JGError authorizatonCodeExistError(String code) {
        return createError("JG0510018004", code);
    }

    /**
     * JG0510018005=资源权限名称{0}已存在
     * 
     * @param name
     * @return
     */
    public JGError authorizatonNameExistError(String name) {
        return createError("JG0510018005", name);
    }

    /**
     * JG0510018006=资源{0}已存在
     * 
     * @param resource
     * @return
     */
    public JGError authorizationResourceExistError(String resource) {
        return createError("JG0510018006", resource);
    }

    /**
     * JG0510018007=角色创建失败，角色数据不能为空
     *
     * @return JGError
     */
    public JGError createRoleError() {
        return createError("JG0510018007");
    }

    /**
     * JG0510018008=角色更新失败，角色ID不能为空
     *
     * @return JGError
     */
    public JGError updateRoleError() {
        return createError("JG0510018008");
    }

    /**
     * JG0510018009=角色查询失败，查询条件不能为空
     *
     * @return JGError
     */
    public JGError queryRoleError() {
        return createError("JG0510018009");
    }

    /**
     * JG0510018010=角色【{0}】已存在
     *
     * @param name
     * @return
     */
    public JGError roleExistError(String name) {
        return createError("JG0510018010", name);
    }

    /**
     * JG0510018011=根据【{0}】资源无法获取对应的资源权限
     * 
     * @param resource
     * @return
     */
    public JGError authNotFoundByResourceError(String resource) {
        return createError("JG0510018011", resource);
    }

    /**
     * 没有配置{0}的快递费，请联系管理员。
     * @param province 省
     * @return
     */
    public JGError notFindFreightConfig(String province) {
        return createError("JG0510021002", province);
    }

    /**
     * 未找到快递单详情。id: {0}
     * @param id 主键
     * @return
     */
    public JGError notFindExpressOrderDetail(Long id) {
        return createError("JG0510021001", id);
    }

    /**
     * JG0510021004=没有配置{0}的快递费，请联系管理员。
     *
     * @return
     */
    public JGError priceCalculateFail() {
        return createError("JG0510021004");
    }

    /**
     * JG0510021003=预定失败
     *
     * @return
     */
    public JGError bookFail(String error) {
        return createError("JG0510021003", error);
    }

    /**
     * 下单商品数量({0})超出库存数量({1}), 请重新下单。
     *
     * @return
     */
    public JGError inventoryNumberError(int total, int num) {
        return createError("JG0510021005", total, num);
    }

    /**
     * 微信下单失败。msg: {0}
     * @param msg msg
     * @return jg error
     */
    public JGError wechatUniOrderFail(String msg) {
        return createError("JG0510022001", msg);
    }

    /**
     * 支付失败，未找到订单。订单号：{0}
     * @param orderNo 订单号
     * @return jg error
     */
    public JGError notFondOrderNoForPay(String orderNo) {
        return createError("JG0510022002", orderNo);
    }

    /**
     * JG0510031001=用户名无效
     *
     * @return
     */
    public JGError usernameInvalid() {
        return createError("JG0510031001");
    }

    /**
     * JG0510031002=密码无效
     *
     * @return
     */
    public JGError passwordInvalid() {
        return createError("JG0510031002");
    }

    /**
     * JG0510031003=无权限访问该资源
     *
     * @return
     */
    public JGError accessDenied() {
        return createError("JG0510031003");
    }

    /**
     * 参数异常。{0}不能为空.
     * @param parameterName 参数名
     * @return
     */
    public JGError parameterEmptyError(String parameterName) {
        return createError("JG0510001001", parameterName);
    }

    /**
     * {0}插入数据失败，更新返回的主键不大于0.
     * @param bussiness 参数名
     * @return
     */
    public JGError insertError(String bussiness) {
        return createError("JG0510001002", bussiness);
    }

    /**
     * {0}更新数据失败，更新记录条数不大于0.
     * @param bussiness 参数名
     * @return
     */
    public JGError updateError(String bussiness) {
        return createError("JG0510001003", bussiness);
    }

    /**
     * JG0510001004=用户会话失效
     * 
     * @return
     */
    public JGError invalidSession() {
        return createError("JG0510001004");
    }

    /**
     * 用户更新密码失败。
     * @return
     */
    public JGError updateUserPasswordError() {
        return createError("JG0510041001");
    }

    /**
     * 用户关联信息失败。
     * @return
     */
    public JGError updateLinkError() {
        return createError("JG0510041002");
    }

    /**
     * JG0510016011=查询参数为空
     * @return
     */
    public JGError queryQRCodeError() {
        return createError("JG0510016011");
    }

    /**
     * JG0510016011=带消息查询参数错误
     * @param message
     * @return
     */
    public JGError queryQRCodeError(String message) {
        return createError("JG0510016011", message);
    }

    /**
     * JG0510016012=更新二维码失败
     * @return
     */
    public JGError updateQRCodeError() {
        return createError("JG0510016012");
    }

    /**
     * JG0510016013=二维码绑定商品失败
     * @return
     */
    public JGError bindCommodityError(String message) {
        return createError("JG0510016013", message);
    }

    /**
     * JG0510051001=无效代理人信息
     * @return
     */
    public JGError invalidAgent() {
        return createError("JG0510051001");
    }

    /**
     * JG0510051002=无效二维码信息
     * @return
     */
    public JGError invalidQRCode() {
        return createError("JG0510051002");
    }

    /**
     * JG0510051003=无效商品信息
     * @return
     */
    public JGError invalidCommodity() {
        return createError("JG0510051003");
    }

    /**
     * JG0510051004=二维码已使用
     * @return
     */
    public JGError qrCodeUsed() {
        return createError("JG0510051004");
    }

    /**
     * JG0510051005=未能获取关联货品信息
     * @return
     */
    public JGError shipRecordNotFound() {
        return createError("JG0510051005");
    }

    /**
     * 获取微信accesstoken异常
     *
     * @param msg
     * @return
     */
    public JGError getWechatAccessTokenError(String msg) {
        return createError("JG0510031101", msg);
    }

    public JGError getSessionError(String msg) {
        return createError("JG0510031101", msg);
    }

    /**
     * JG0510031103=生成系统会话异常，{0}
     * @param msg
     * @return
     */
    public JGError generateSessionError(String msg) {
        return createError("JG0510031103", msg);
    }
}
