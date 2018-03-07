package com.zeh.wms.web.controller.api.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author allen
 * @create $ ID: StaticOptionsModel, 18/3/7 15:53 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class StaticOptionsModel implements Serializable {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 枚举名称 */
    private String            name;
    /** 枚举值 */
    private Object            value;
    /** 是否选中 */
    private boolean           checked;
}