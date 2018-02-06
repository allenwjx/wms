package com.zeh.wms.biz.model;

import com.zeh.wms.biz.model.enums.UserLinkTypeEnum;

import lombok.Data;

/**
 * 用户模型
 *
 * @author allen
 * @create $ ID: UserVO, 18/2/6 16:35 allen Exp $
 * @since 1.0.0
 */
@Data
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
    /** 客户类型； 代理商：A； 厂商：B； 散客: C；*/
    private UserLinkTypeEnum  type;
}