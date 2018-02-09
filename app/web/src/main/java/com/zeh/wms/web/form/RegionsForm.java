package com.zeh.wms.web.form;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author allen
 * @create $ ID: RegionsForm, 18/2/9 15:18 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class RegionsForm implements Serializable {
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long              id;
    /** 中国行政编码 */
    private String            code;
    /** 名称 */
    private String            name;
    /** 省ID */
    private Long              provinceId;
    /** 市ID */
    private Long              cityId;
    /** 区ID */
    private Long              districtId;
}