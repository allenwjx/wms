package com.zeh.wms.biz.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 省模型
 * 
 * @author allen
 * @create $ ID: ProvinceVO, 18/2/6 15:38 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class ProvinceVO extends BaseVO {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 省编码 */
    private String            code;
    /** 省中文名称 */
    private String            name;
}