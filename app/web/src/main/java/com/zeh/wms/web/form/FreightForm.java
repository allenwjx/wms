package com.zeh.wms.web.form;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
    private Long              provinceCode;
    /** 快递公司类型,SF-顺丰，DEPPON-德邦 */
    private String            expressCode;
    /** 首重，单位：500克 */
    private String            firstWeight;
    /** 首重每500克单价，单价：分 */
    private String            firstOriginalPrice;
    /** 续重每500克价格，单位：分 */
    private String            additionalOriginalPrice;

    /** 首重每500克单价成本价，单价：分 */
    private String            firstCostPrice;

    /** 续重每500克价格成本价，单位：分 */
    private String            additionalCostPrice;
    /** 启用，禁用 */
    private Integer           enabled;
}