package com.zeh.wms.biz.model.enums;

/**
 * @author allen
 * @create $ ID: AddressTypeEnum, 18/2/6 15:15 allen Exp $
 * @since 1.0.0
 */
public enum AddressTypeEnum {
    /** 发件人 */
    SENDER("SENDER", "发件人"),
    /** 线下月结 */
    RECEIVER("RECEIVER", "寄件人");

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
    private AddressTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 获取枚举类型
     *
     * @param code 枚举码
     * @return
     */
    public static AddressTypeEnum getEnumByCode(String code) {
        for (AddressTypeEnum e : AddressTypeEnum.values()) {
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