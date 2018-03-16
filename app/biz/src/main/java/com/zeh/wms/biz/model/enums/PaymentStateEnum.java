package com.zeh.wms.biz.model.enums;

/**
 * @author allen
 * @create $ ID: PaymentStateEnum, 18/2/6 15:15 allen Exp $
 * @since 1.0.0
 */
public enum PaymentStateEnum {
    /** 待支付 */
    WATI_PAY("WATI_PAY", "待支付"),
    /** 支付中 */
    PAYING("PAYING", "支付中"),
    /** 已支付 */
    PAYED("PAYED", "已支付"),
    /** 已支付 */
    PAY_FAIL("PAY_FAIL", "支付失败"),
    /** 支付取消 */
    PAY_CANCEL("PAY_CANCEL", "支付取消");

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
    private PaymentStateEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 获取枚举类型
     *
     * @param code 枚举码
     * @return
     */
    public static PaymentStateEnum getEnumByCode(String code) {
        for (PaymentStateEnum e : PaymentStateEnum.values()) {
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