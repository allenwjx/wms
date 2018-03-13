package com.zeh.wms.web.controller.api.model;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 微信获取手机号返回对象
 */
@Getter
@Setter
public class WechatPhoneNumberResult implements Serializable {
    private String phoneNumber;
    private String purePhoneNumber;
    private String countryCode;

    private WxMaUserInfo.Watermark watermark;
}
