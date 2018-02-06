package com.zeh.wms.biz.model.enums;

/**
 * @author allen
 * @create $ ID: PaymentChannelEnum, 18/2/6 15:15 allen Exp $
 * @since 1.0.0
 */
public enum PaymentChannelEnum {
    /** 微信支付 */
    WX("WX", "微信支付"),
    /** 支付宝支付 */
    ALIPAY("ALIPAY", "支付宝支付");

    /** 枚举编码 */
    private String code;
    /** 枚举描述 */
    private String desc;

    /**
     * 构造器
     *
     * @param code
     * @param desc
     */
    private PaymentChannelEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 获取枚举类型
     *
     * @param code 枚举码
     * @return
     */
    public static PaymentChannelEnum getEnumByCode(String code) {
        for (PaymentChannelEnum e : PaymentChannelEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

    /**
     *
     *
     * 获取枚举编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 获取枚举描述
     */
    public String getDesc() {
        return desc;
    }
}