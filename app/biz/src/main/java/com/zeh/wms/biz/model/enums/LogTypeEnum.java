package com.zeh.wms.biz.model.enums;

/**
 * 日志操作类型枚举
 *
 * @author hxy43938
 * @version Id: LogOperatorTypeEnum, v 0.1 2017/2/17 14:07 hxy43938 Exp $
 */
public enum LogTypeEnum {
    CREATE("CREATE", "新建"),

    EDIT("EDIT", "编辑"),

    DELETE("DELETE", "删除"),

    DEAL("DEAL", "处理"),

    PUBLISH("PUBLISH", "发布");

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
    private LogTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 获取枚举类型
     *
     * @param code 枚举码
     * @return
     */
    public static LogTypeEnum getEnumByCode(String code) {
        for (LogTypeEnum e : LogTypeEnum.values()) {
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
