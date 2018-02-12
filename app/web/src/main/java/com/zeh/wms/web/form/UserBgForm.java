package com.zeh.wms.web.form;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author allen
 * @create $ ID: UserBgForm, 18/2/11 16:01 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class UserBgForm implements Serializable {
    /**  */
    private static final long serialVersionUID = 1L;
    /** id */
    private Long              id;
    /** 后台用户账号 */
    private String            username;
    /** 后台用户账号密码 */
    private String            password;
    /** 确认后的密码 */
    private String            confirmedPassword;
    /** 用户状态 */
    private Integer           enabled;
}