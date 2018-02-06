package com.zeh.wms.biz.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 商品模型
 * 
 * @author allen
 * @create $ ID: CommodityVO, 18/2/6 15:23 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class CommodityVO extends BaseVO {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 厂商ID */
    private long              manufacturerId;
    /** 商品编号 */
    private String            code;
    /** 商品名称 */
    private String            name;
    /** 商品单价，单位：分 */
    private int               price;
    /** 商品单位，如：件，箱 */
    private String            unit;
    /** 商品重量（单件），单位：克 */
    private int               weight;
    /** 商品简介 */
    private String            description;
}