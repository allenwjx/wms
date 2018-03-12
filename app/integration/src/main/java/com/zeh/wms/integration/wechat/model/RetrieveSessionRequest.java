package com.zeh.wms.integration.wechat.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * @author allen
 * @create $ ID: RetrieveSessionRequest, 18/3/12 14:25 allen Exp $
 * @since 1.0.0
 */
@Data
@RequiredArgsConstructor
public class RetrieveSessionRequest implements Serializable {
    /**  */
    private static final long serialVersionUID = -4629413986270730546L;

    /** 小程序唯一标识*/
    @JSONField(name = "appid")
    @NonNull
    private String            appId;

    /** 小程序的 app secret */
    @NonNull
    private String            secret;

    /** 登录时获取的 code */
    @JSONField(name = "js_code")
    @NonNull
    private String            jsCode;

    /** 填写为 authorization_code */
    @JSONField(name = "grant_type")
    private String            grantType        = "authorization_code";
}