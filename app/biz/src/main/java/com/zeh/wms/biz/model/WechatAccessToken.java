package com.zeh.wms.biz.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 微信标准返回对象
 * @author weijun
 * @create $ v 1.0.0 2018/3/10 下午12:35 Exp $
 */
@Setter
@Getter
public class WechatAccessToken implements Serializable {
    private Integer errcode;
    private String errmsg;
    private String access_token;
    private Integer expires_in;
}
