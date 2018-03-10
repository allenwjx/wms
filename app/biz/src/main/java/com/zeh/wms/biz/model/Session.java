package com.zeh.wms.biz.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 *
 * 我方系统会话
 *
 * @author weijun
 * @create $ v 1.0.0 2018/3/10 下午12:37 Exp $
 */
@Getter @Setter public class Session implements Serializable {

    public static final String SESSION_FLAG = "user_session_key";

    /** 我方系统会话id */
    private String id;
    /** 我方平台用户对象 */
    UserVO userVO;

    /** 微信相关属性 */
    private String wechatSessionKey;
    private String wechatUnionid;
    private String wechatOpenid;
    private String wechatJscode;
}
