package com.zeh.wms.biz.model.enums;

/**
 * @author allen
 * @create $ ID: ExpressOrderStateEnum, 18/2/6 15:15 allen Exp $
 * @since 1.0.0
 */
public enum ExpressOrderStateEnum {
    /** 待支付 */
    WATI_PAY("WATI_PAY", "待支付"),
    /** 待取件 */
    WAIT_PICKUP("WAIT_PICKUP", "待取件"),
    /** 待发货 */
    WAIT_SEND("WAIT_SEND", "待发货"),
    /** 已发货 */
    SENDED("SENDED", "已发货"),
    /** 订单取消 */
    MONTHLY("CANCEL", "订单取消");

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
    private ExpressOrderStateEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 获取枚举类型
     *
     * @param code 枚举码
     * @return
     */
    public static ExpressOrderStateEnum getEnumByCode(String code) {
        for (ExpressOrderStateEnum e : ExpressOrderStateEnum.values()) {
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