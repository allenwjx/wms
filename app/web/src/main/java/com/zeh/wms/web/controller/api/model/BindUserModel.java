package com.zeh.wms.web.controller.api.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BindUserModel implements Serializable {
    /**  */
    private static final long serialVersionUID = 1L;
    private int needDecoder;
    private String errMsg;
    private String iv;
    private String encryptedData;
    private String mobile;
}
