package com.zeh.wms.integration.dto.jscode2session.request;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author hzy24985
 * @version $Id: Jscode2SessionDto, v 0.1 2018/2/6 16:34 hzy24985 Exp $
 */
public class Jscode2SessionDto implements Serializable {
    private String appid;

    private String secret;

    @JSONField(name = "js_code")
    private String jsCode;

    @JSONField(name = "grant_type")
    private String grantType;
}
