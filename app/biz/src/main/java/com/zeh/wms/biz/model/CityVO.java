package com.zeh.wms.biz.model;

import lombok.Data;

/**
 * 城市模型
 * 
 * @author allen
 * @create $ ID: CityVO, 18/2/6 15:21 allen Exp $
 * @since 1.0.0
 */
@Data
public class CityVO extends BaseVO {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 市编码 */
    private String            code;
    /** 市中文名称 */
    private String            name;
}