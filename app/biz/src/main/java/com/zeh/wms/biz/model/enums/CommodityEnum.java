package com.zeh.wms.biz.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author allen
 * @create $ ID: CommodityEnum, 18/3/8 16:18 allen Exp $
 * @since 1.0.0
 */
public enum CommodityEnum {
    FS(1, "服饰"),
    XZ(2, "鞋靴"),
    GY(3, "工业用品"),
    XB(4, "箱包"),
    SH(5, "生活用品"),
    SM(6, "数码用品"),
    SP(7, "食品"),
    FJ(8, "文件"),
    QT(9, "其他");

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
    private CommodityEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 获取枚举类型
     *
     * @param code 枚举码
     * @return
     */
    public static CommodityEnum getEnumByCode(int code) {
        for (CommodityEnum e : CommodityEnum.values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }

    /**
     *
     * 获取枚举编码
     */
    @JsonValue
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