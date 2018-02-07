package com.zeh.wms.integration.dto.enums;

/**
 * The enum Unifiedorder error code enum.
 *
 * @author hzy24985
 * @version $Id : UnifiedorderErrorCodeEnum, v 0.1 2018/2/7 15:35 hzy24985 Exp $
 */
public enum UnifiedorderErrorCodeEnum {

    NOAUTH("NOAUTH","商户无此接口权限","商户未开通此接口权限","请商户前往申请此接口权限"),
    NOTENOUGH("NOTENOUGH","余额不足","用户帐号余额不足","用户帐号余额不足，请用户充值或更换支付卡后再支付"),
    ORDERPAID("ORDERPAID","商户订单已支付","商户订单已支付，无需重复操作","商户订单已支付，无需更多操作"),
    ORDERCLOSED("ORDERCLOSED","订单已关闭","当前订单已关闭，无法支付","当前订单已关闭，请重新下单"),
    SYSTEMERROR("SYSTEMERROR","系统错误","系统超时","系统异常，请用相同参数重新调用"),
    APPID_NOT_EXIST("APPID_NOT_EXIST","APPID不存在","参数中缺少APPID","请检查APPID是否正确"),
    MCHID_NOT_EXIST("MCHID_NOT_EXIST","MCHID不存在","参数中缺少MCHID","请检查MCHID是否正确"),
    APPID_MCHID_NOT_MATCH("APPID_MCHID_NOT_MATCH","appid和mch_id不匹配","appid和mch_id不匹配","请确认appid和mch_id是否匹配"),
    LACK_PARAMS("LACK_PARAMS","缺少参数","缺少必要的请求参数","请检查参数是否齐全"),
    OUT_TRADE_NO_USED("OUT_TRADE_NO_USED","商户订单号重复","同一笔交易不能多次提交","请核实商户订单号是否重复提交"),
    SIGNERROR("SIGNERROR","签名错误","参数签名结果不正确","请检查签名参数和方法是否都符合签名算法要求"),
    XML_FORMAT_ERROR("XML_FORMAT_ERROR","XML格式错误","XML格式错误","请检查XML参数格式是否正确"),
    REQUIRE_POST_METHOD("REQUIRE_POST_METHOD","请使用post方法","未使用post传递参数 ","请检查请求参数是否通过post方法提交"),
    POST_DATA_EMPTY("POST_DATA_EMPTY","post数据为空","post数据不能为空","请检查post数据是否为空"),
    NOT_UTF8("NOT_UTF8","编码格式错误","未使用指定编码格式","请使用UTF-8编码格式");

    /**
     * 枚举编码
     */
    private String code;
    /**
     * 枚举描述
     */
    private String desc;
    /**
     * 原因
     */
    private String reason;
    /**
     * 解决方法
     */
    private String solution;

    /**
     * 构造器
     *
     * @param code the code
     * @param desc the desc
     */
    UnifiedorderErrorCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * Instantiates a new Unifiedorder error code enum.
     *
     * @param code     the code
     * @param desc     the desc
     * @param reason   the reason
     * @param solution the solution
     */
    UnifiedorderErrorCodeEnum(String code, String desc, String reason, String solution) {
        this.code = code;
        this.desc = desc;
        this.reason = reason;
        this.solution = solution;
    }

    /**
     * 获取枚举类型
     *
     * @param code 枚举码
     * @return enum by code
     */
    public static UnifiedorderErrorCodeEnum getEnumByCode(String code) {
        for (UnifiedorderErrorCodeEnum e : UnifiedorderErrorCodeEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

    /**
     * 获取枚举编码
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * 获取枚举描述
     *
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }
}
