package com.zeh.wms.biz.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author allen
 * @create $ ID: DistrictVO, 18/2/6 15:28 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class DistrictVO extends BaseVO {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 区编码 */
    private String            code;
    /** 区中文名称 */
    private String            name;
}