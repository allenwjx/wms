package com.zeh.wms.web.controller.api.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author weijun
 * @create $ v 1.0.0 2018/3/10 下午6:03 Exp $
 */
@Getter
@Setter
public class UserModel implements Serializable {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 用户手机 */
    private String            mobile;
    private String            userId;
    private String            sid;
    private String            openId;

}
