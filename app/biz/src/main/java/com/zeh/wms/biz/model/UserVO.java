package com.zeh.wms.biz.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户模型
 *
 * @author allen
 * @create $ ID: UserVO, 18/2/6 16:35 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class UserVO extends BaseVO {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 昵称 */
    private String            nickName;
    /** 用户账号，用户电话 */
    private String            userId;
    /** 密码 */
    private String            password;
    /** openid */
    private String            openId;

    /** 用户手机号 */
    private String            mobile;
}