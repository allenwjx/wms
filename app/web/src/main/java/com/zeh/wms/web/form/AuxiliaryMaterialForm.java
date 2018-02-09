package com.zeh.wms.web.form;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author allen
 * @create $ ID: AuxiliaryMaterialForm, 18/2/9 11:32 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class AuxiliaryMaterialForm implements Serializable {
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long              id;
    /** 辅料、辅材、包装名称 */
    private String            name;
    /** 辅料，辅材，包装费用，单位：分*/
    private String            price;
    /** 多少商品需要增加辅材费用 */
    private String            quantity;
    /** * 商品ID */
    private Long              commodityId;
    /** 启用状态 */
    private Integer           enabled;
}