package com.zeh.wms.biz.model;

import com.zeh.wms.biz.model.enums.StateEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 运费模型
 *
 * @author allen
 * @create $ ID: FreightVO, 18/2/6 16:03 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class FreightVO extends BaseVO {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 省编码，目前使用的中文 */
    private String            provinceCode;
    /** 快递公司类型,SF-顺丰，DEPPON-德邦 */
    private String            expressCode;
    /** 首重，单位：500克 */
    private Integer           firstWeight;
    /** 首重每500克单价，单价：分 */
    private Integer           firstOriginalPrice;
    /** 续重每500克价格，单位：分 */
    private Integer           additionalOriginalPrice;

    /** 首重每500克单价成本价，单价：分 */
    private Integer           firstCostPrice;

    /** 续重每500克价格成本价，单位：分 */
    private Integer           additionalCostPrice;
    /** 启用，禁用 */
    private StateEnum         enabled;
}