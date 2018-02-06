package com.zeh.wms.biz.model.enums;

/**
 * @author allen
 * @create $ ID: UserLinkTypeEnum, 18/2/6 15:15 allen Exp $
 * @since 1.0.0
 */
public enum UserLinkTypeEnum {
    /** 代理商 */
    A("A", "代理商"),
    /** 厂商 */
    B("B", "厂商"),
    /** 散客 */
    C("C", "散客");

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
    private UserLinkTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 获取枚举类型
     *
     * @param code 枚举码
     * @return
     */
    public static UserLinkTypeEnum getEnumByCode(String code) {
        for (UserLinkTypeEnum e : UserLinkTypeEnum.values()) {
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