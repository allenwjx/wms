package com.zeh.wms.integration.dto.jscode2session;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hzy24985
 * @version $Id: Jsoncode2SessionRespDto, v 0.1 2018/2/6 16:50 hzy24985 Exp $
 */
@Data
public class Jsoncode2SessionRespDto implements Serializable {

    private static final long serialVersionUID = 6746375273235370802L;

    @JSONField(name = "openid")
    private String openId;

    @JSONField(name = "session_key")
    private String sessionKey;

    @JSONField(name = "unionid")
    private String unionId;

    @JSONField(name = "errcode")
    private String errCode;

    @JSONField(name = "errmsg")
    private String errMsg;
}
