package com.zeh.wms.biz.model;

import lombok.Data;

/**
 * 权限，角色关联模型
 *
 * @author allen
 * @create $ ID: RoleAuthorizationLinkVO, 18/2/6 16:13 allen Exp $
 * @since 1.0.0
 */
@Data
public class RoleAuthorizationLinkVO extends BaseVO {
    /**  */
    private static final long serialVersionUID = 1L;

    /** 角色ID */
    private long              roleId;
    /** 权限ID */
    private long              authId;
}