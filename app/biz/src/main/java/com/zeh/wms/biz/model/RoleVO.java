package com.zeh.wms.biz.model;

import lombok.Data;

/**
 * 角色模型
 *
 * @author allen
 * @create $ ID: RoleVO, 18/2/6 16:14 allen Exp $
 * @since 1.0.0
 */
@Data
public class RoleVO extends BaseVO {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 角色名称 */
    private String            name;
}