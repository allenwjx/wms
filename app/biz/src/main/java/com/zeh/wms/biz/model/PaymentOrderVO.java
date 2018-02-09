package com.zeh.wms.biz.model;

import lombok.Getter;
import lombok.Setter;

import com.zeh.wms.biz.model.enums.PaymentChannelEnum;
import com.zeh.wms.biz.model.enums.PaymentStateEnum;

/**
 * 支付单模型
 * 
 * @author allen
 * @create $ ID: PaymentOrderVO, 18/2/6 16:05 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class PaymentOrderVO extends BaseVO {
    /**  */
    private static final long  serialVersionUID = 1L;
    /** 快递单号 */
    private String             orderNo;
    /** 三方快递单号 */
    private String             otherOrderNo;
    /** 用户id */
    private Long               userId;
    /** agent用户类型的下单：agent电话号码； 大客户类型的下单：厂商编码（授权码） 散客：空值 */
    private String             code;
    /** 平台支付流水号 */
    private String             paymentOrderNo;
    /** 微信支付流水号 */
    private String             otherPaymentNo;
    /** 支付金额，单位：分 */
    private Integer            amount;
    /** 支付渠道：WX: 微信; ALIPAY: 支付宝 */
    private PaymentChannelEnum channel;
    /** 支付状态； WATI_PAY（待支付）； PAYING（支付中）； PAYED（已支付）； PAY_CANCEL（支付取消）*/
    private PaymentStateEnum   status;
    /** 支付时限，单位：秒 */
    private Integer            payLimited;
}