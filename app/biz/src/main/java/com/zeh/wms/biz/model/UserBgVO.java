package com.zeh.wms.biz.model;

import com.google.common.collect.Lists;
import com.zeh.wms.biz.model.enums.StateEnum;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * 后台用户模型
 * 
 * @author allen
 * @create $ ID: UserBgVO, 18/2/6 16:34 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class UserBgVO extends BaseVO {
    /**  */
    private static final long  serialVersionUID = 1L;
    /** 后台用户账号 */
    private String             username;
    /** 后台用户账号密码 */
    private String             password;
    /** 用户状态 */
    private StateEnum          enabled;
    /** 用户角色 */
    private Collection<RoleVO> roles            = Lists.newArrayList();
}