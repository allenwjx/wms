package com.zeh.wms.web.controller.api.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @author weijun
 * @create $ v 1.0.0 2018/3/10 下午6:03 Exp $
 */
@Getter
@Setter
public class UserModel implements Serializable {
    /** 待注册用户的手机 */
    private String mobile;
    /** 待注册用户的名称 */
    private String nickName;
}
