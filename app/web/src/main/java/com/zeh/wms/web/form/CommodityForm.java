package com.zeh.wms.web.form;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author allen
 * @create $ ID: CommodityForm, 18/2/8 18:56 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class CommodityForm implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 商品ID */
    private Long              id;
    /** 厂商ID */
    private Long              manufacturerId;
    /** 商品编号 */
    private String            code;
    /** 商品名称 */
    private String            name;
    /** 商品单价，单位：元 */
    private String            price;
    /** 商品单位，如：件，箱 */
    private String            unit;
    /** 商品重量（单件），单位：KG */
    private String            weight;
    /** 商品简介 */
    private String            description;
    /** 商品状态 */
    private Integer           enabled;
}