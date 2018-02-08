package com.zeh.wms.biz.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author allen
 * @create $ ID: RegionsVO, 18/2/9 02:39 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class RegionsVO extends BaseVO {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 中国行政编码 */
    private String            code;
    /** 名称 */
    private String            name;
    /** 上级行政id */
    private long              parentId;
    /** 省市区三个层级 */
    private int               level;
    /** 拼音首字母 */
    private String            firstPinyinChar;
    /** 拼音名称 */
    private String            pinyinName;
}