package com.zeh.wms.integration.wechat.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author allen
 * @create $ ID: RetrieveSessionResponse, 18/3/12 14:27 allen Exp $
 * @since 1.0.0
 */
@Data
public class RetrieveSessionResponse implements Serializable {
    private static final long serialVersionUID = 6746375273235370802L;

    @JSONField(name = "openid")
    private String            openId;

    @JSONField(name = "session_key")
    private String            sessionKey;

    @JSONField(name = "unionid")
    private String            unionId;

    @JSONField(name = "errcode")
    private String            errCode;

    @JSONField(name = "errmsg")
    private String            errMsg;
}