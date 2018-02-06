package com.zeh.wms.biz.model.enums;

/**
 * @author allen
 * @create $ ID: AddressTypeEnum, 18/2/6 15:15 allen Exp $
 * @since 1.0.0
 */
public enum StateEnum {
    /** 无效 */
    N(0, "无效"),
    /** 有效 */
    Y(1, "有效");

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
    private StateEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 获取枚举类型
     *
     * @param code 枚举码
     * @return
     */
    public static StateEnum getEnumByCode(int code) {
        for (StateEnum e : StateEnum.values()) {
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