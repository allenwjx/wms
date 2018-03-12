package com.zeh.wms.integration.wechat.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.io.Serializable;

/**
 * 商户在小程序中先调用该接口在微信支付服务后台生成预支付交易单，返回正确的预支付交易后调起支付。
 * 返回参数。
 * @author hzy24985
 * @version $Id: UnifiedorderRespDto, v 0.1 2018/2/7 15:20 hzy24985 Exp $
 */
@Data
public class UnifiedorderRespDto implements Serializable {
    private static final long serialVersionUID = 1406280387114609736L;

    /**
     * SUCCESS/FAIL
     */
    @XStreamAlias("return_code")
    private String            returnCode;

    /** 返回信息，如非空，为错误原因 签名失败 */
    @XStreamAlias("return_msg")
    private String            returnMsg;

    /** ===========以下字段在return_code为SUCCESS的时候有返回==================*/
    @XStreamAlias("appid")
    private String            appId;

    /** 商户号 */
    @XStreamAlias("mch_id")
    private String            merchantId;

    /** 设备号 */
    @XStreamAlias("device_info")
    private String            deviceInfo;

    /** 随机字符串 */
    @XStreamAlias("nonce_str")
    private String            nonceStr;

    /** 签名 */
    private String            sign;

    /** 业务结果 SUCCESS/FAIL */
    @XStreamAlias("result_code")
    private String            resultCode;

    /** 错误代码 UnifiedorderErrorCodeEnum */
    @XStreamAlias("err_code")
    private String            errCode;

    /** 错误代码描述 */
    @XStreamAlias("err_code_des")
    private String            errCodeDes;

    /** ==========以下字段在return_code 和result_code都为SUCCESS的时候有返回======== */

    /** 交易类型 小程序取值如下：JSAPI*/
    @XStreamAlias("trade_type")
    private String            tradeType;

    /** 预支付交易会话标识: 微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时*/
    @XStreamAlias("prepay_id")
    private String            prepayId;

    /**二维码链接: trade_type为NATIVE时有返回，用于生成二维码，展示给用户进行扫码支付 */
    @XStreamAlias("code_url")
    private String            codeUrl;
}
