package com.zeh.wms.biz.model;

import java.util.Collection;

import com.google.common.collect.Lists;
import com.zeh.wms.biz.model.enums.StateEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 角色模型
 *
 * @author allen
 * @create $ ID: RoleVO, 18/2/6 16:14 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class RoleVO extends BaseVO {
    /**  */
    private static final long           serialVersionUID = 1L;
    /** 角色名称 */
    private String                      name;
    /** 角色状态 */
    private StateEnum                   enabled;
    /** 角色包含的权限 */
    private Collection<AuthorizationVO> authorizations   = Lists.newArrayList();
}