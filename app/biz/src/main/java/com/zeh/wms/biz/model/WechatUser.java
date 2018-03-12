package com.zeh.wms.biz.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * 用户会话对象
 *
 * @author weijun
 * @create $ v 1.0.0 2018/3/10 下午12:40 Exp $
 */
@Getter
@Setter
public class WechatUser implements Serializable {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 微信侧，用户open id */
    private String            openId;
    /** 微信侧，用户session key */
    private String            sessionKey;
    /** union id */
    private String            unionId;
}
