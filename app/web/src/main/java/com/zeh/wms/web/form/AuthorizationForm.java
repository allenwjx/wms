package com.zeh.wms.web.form;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author allen
 * @create $ ID: AuthorizationForm, 18/2/23 14:20 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class AuthorizationForm implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 权限资源ID */
    private Long              id;
    /** 权限资源编号 */
    private String            code;
    /** 权限资源名称 */
    private String            name;
    /** 权限资源 */
    private String            path;
    /** 权限资源状态 */
    private Integer           enabled;
}