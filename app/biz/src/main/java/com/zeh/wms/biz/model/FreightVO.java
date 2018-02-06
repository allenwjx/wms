package com.zeh.wms.biz.model;

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
    /** 主键 */
    private long              id;
    /** 省编码 */
    private String            provinceCode;
    /** 首重，单位：500克 */
    private int               firstWeight;
    /** 首重每500克单价，单价：分 */
    private int               firstPrice;
    /** 续重每500克价格，单位：分 */
    private int               additionalPrice;
}