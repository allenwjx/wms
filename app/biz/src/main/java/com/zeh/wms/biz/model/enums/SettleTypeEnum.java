package com.zeh.wms.biz.model.enums;

/**
 * @author allen
 * @create $ ID: SettleTypeEnum, 18/2/6 15:15 allen Exp $
 * @since 1.0.0
 */
public enum SettleTypeEnum {
                            ONLINE("ONLINE", "在线支付"),
                            /** 线下现付 */
                            OFFLINE("OFFLINE", "线下现付"),
                            /** 线下月结 */
                            MONTHLY("CANCEL", "线下月结");

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
    private SettleTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 获取枚举类型
     *
     * @param code 枚举码
     * @return
     */
    public static SettleTypeEnum getEnumByCode(String code) {
        for (SettleTypeEnum e : SettleTypeEnum.values()) {
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