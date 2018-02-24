package com.zeh.wms.web.form;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author allen
 * @create $ ID: LoginForm, 18/2/24 20:12 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class LoginForm implements Serializable {
    /**  */
    private static final long serialVersionUID = 1L;
    /** username */
    private String            username;
    /** password */
    private String            password;
}