package com.zeh.wms.integration.dto.unifiedorder;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.zeh.jungle.utils.serializer.FastJsonUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * 商户在小程序中先调用该接口在微信支付服务后台生成预支付交易单，返回正确的预支付交易后调起支付。
 * 请求参数。
 * @author hzy24985
 * @version $Id: UnifiedorderReqDto, v 0.1 2018/2/6 18:17 hzy24985 Exp $
 */
@Data
public class UnifiedorderReqDto implements Serializable {
    private static final long serialVersionUID = -140950831151126353L;

    /** 小程序ID */
    @XStreamAlias("appid")
    private String            appId;

    /** 用户标识 trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。*/
    @XStreamAlias("openid")
    private String            openId;

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

    /** 签名类型,默认为MD5，支持HMAC-SHA256和MD5。 */
    @XStreamAlias("sign_type")
    private String            sign_type;

    /** 商品简单描述，该字段请按照规范传递，具体请见参数规定 */
    private String            body;

    /** 商品详细描述，对于使用单品优惠的商户，改字段必须按照规范上传，详见“单品优惠参数说明 */
    private String            detail;

    /** 附加数据 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。*/
    private String            attach;

    /** 商户订单号 */
    @XStreamAlias("out_trade_no")
    private String            outTradeNo;

    /** 标价币种 默认人民币：CNY*/
    @XStreamAlias("fee_type")
    private String            feeType;

    /** 标价金额 订单总金额，单位为分*/
    @XStreamAlias("total_fee")
    private int               totalFee;

    /** 终端IP APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP */
    @XStreamAlias("spbill_create_ip")
    private String            spbillCreateIp;

    /** 交易起始时间 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。*/
    @XStreamAlias("time_start")
    private String            timeStart;

    /** 交易结束时间 订单失效时间，格式为yyyyMMddHHmmss，
    * 如2009年12月27日9点10分10秒表示为20091227091010。
    * 订单失效时间是针对订单号而言的，
    * 由于在请求支付的时候有一个必传参数prepay_id只有两小时的有效期，
    * 所以在重入时间超过2小时的时候需要重新请求下单接口获取新的prepay_id*/
    @XStreamAlias("time_expire")
    private String            timeExpire;

    /** 订单优惠标记 订单优惠标记，使用代金券或立减优惠功能时需要的参数 */
    @XStreamAlias("goods_tag")
    private String            goodsTag;

    /** 通知地址 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数 */
    @XStreamAlias("notify_url")
    private String            notifyUrl;

    /** 交易类型 小程序取值如下：JSAPI*/
    @XStreamAlias("trade_type")
    private String            tradeType;

    /** 商品ID trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义*/
    @XStreamAlias("product_id")
    private String            productId;

    /** 指定支付方式 上传此参数no_credit--可限制用户不能使用信用卡支付*/
    @XStreamAlias("limit_pay")
    private String            limitPay;

    /**
     * 设置商品明细.
     * @param details UnifiedorderDetailReqDto
     */
    public void setDetails(UnifiedorderDetailReqDto details) {
        this.detail = FastJsonUtils.toJSONString(details);
    }

}
