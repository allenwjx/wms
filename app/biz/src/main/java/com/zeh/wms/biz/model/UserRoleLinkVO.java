package com.zeh.wms.biz.model;

import com.zeh.wms.biz.model.enums.UserTypeEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户，角色关联模型
 * 
 * @author allen
 * @create $ ID: UserRoleLinkVO, 18/2/6 16:38 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class UserRoleLinkVO extends BaseVO {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 会员ID，或后台用户ID */
    private long              userId;
    /** 角色ID */
    private long              roleId;
    /** 用户类型； 前台用户：F 后台用户：B */
    private UserTypeEnum      type;
}