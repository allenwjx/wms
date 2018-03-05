package com.zeh.wms.web.controller.api.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author allen
 * @create $ ID: CommodityModel, 18/3/5 19:42 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class CommodityModel implements Serializable {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 主键  */
    private long              id;
    /** 厂商ID */
    private Long              manufacturerId;
    /** 商品编号 */
    private String            code;
    /** 商品名称 */
    private String            name;
    /** 商品单价，单位：分 */
    private Integer           price;
    /** 商品单位，如：件，箱 */
    private String            unit;
    /** 商品重量（单件），单位：克 */
    private Integer           weight;
    /** 商品简介 */
    private String            description;
}