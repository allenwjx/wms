package com.zeh.wms.web.form;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author allen
 * @create $ ID: FreightForm, 18/2/9 01:11 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class FreightForm implements Serializable {
    /**  */
    private static final long serialVersionUID = 1L;
    /** id */
    private Long              id;
    /** 省编码 */
    private String            provinceCode;
    /** 首重，单位：500克 */
    private String            firstWeight;
    /** 首重每500克单价，单价：分 */
    private String            firstPrice;
    /** 续重每500克价格，单位：分 */
    private String            additionalPrice;
    /** 启用，禁用 */
    private Integer           enabled;
}