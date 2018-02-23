package com.zeh.wms.web.form;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

import lombok.Getter;
import lombok.Setter;

/**
 * @author allen
 * @create $ ID: RoleForm, 18/2/23 16:58 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class RoleForm implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 角色ID */
    private Long              id;
    /** 角色名称 */
    private String            name;
    /** 角色状态 */
    private Integer           enabled;
    /** 角色包含的权限 */
    private List<String>      authorizations   = Lists.newArrayList();
}