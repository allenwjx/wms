package com.zeh.wms.biz.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 权限模型
 * 
 * @author allen
 * @create $ ID: AuthorizationVO, 18/2/6 15:08 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class AuthorizationVO extends BaseVO {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 权限名称 */
    private String            name;
    /** 权限编码 */
    private String            code;
    /** 页面路径 */
    private String            path;
}