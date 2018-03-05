package com.zeh.wms.web.controller.api.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author allen
 * @create $ ID: ManufacturerModel, 18/3/5 19:52 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class ManufacturerModel implements Serializable {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 主键  */
    private long              id;
    /** 厂商编号 */
    private String            code;
    /** 厂商名称 */
    private String            name;
}