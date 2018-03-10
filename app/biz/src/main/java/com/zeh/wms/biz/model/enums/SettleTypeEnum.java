package com.zeh.wms.biz.model.enums;

/**
 * @author allen
 * @create $ ID: SettleTypeEnum, 18/2/6 15:15 allen Exp $
 * @since 1.0.0
 */
public enum SettleTypeEnum {
                            ONLINE(0, "在线支付"),
                            /** 线下现付 */
                            REAL(1, "线下现付"),
                            /** 线下月结 */
                            MONTHLY(2, "线下月结");

    /** 枚举编码 */
    private int    code;
    /** 枚举描述 */
    private String desc;

    /**
     * 构造器
     *
     * @param code
     * @param desc
     */
    private SettleTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 获取枚举类型
     *
     * @param code 枚举码
     * @return
     */
    public static SettleTypeEnum getEnumByCode(int code) {
        for (SettleTypeEnum e : SettleTypeEnum.values()) {
            if (e.getCode() == code) {
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
    public int getCode() {
        return code;
    }

    /**
     * 获取枚举描述
     */
    public String getDesc() {
        return desc;
    }
}