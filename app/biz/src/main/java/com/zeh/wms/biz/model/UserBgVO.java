package com.zeh.wms.biz.model;

import lombok.Data;

/**
 * 后台用户模型
 * 
 * @author allen
 * @create $ ID: UserBgVO, 18/2/6 16:34 allen Exp $
 * @since 1.0.0
 */
@Data
public class UserBgVO extends BaseVO {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 后台用户账号 */
    private String            username;
    /** 后台用户账号密码 */
    private String            password;
}