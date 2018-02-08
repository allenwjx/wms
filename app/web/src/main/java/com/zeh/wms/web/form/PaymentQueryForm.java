package com.zeh.wms.web.form;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hzy24985
 * @version $Id: PaymentQueryForm, v 0.1 2018/2/8 21:31 hzy24985 Exp $
 */
@Data
public class PaymentQueryForm implements Serializable {

    private static final long serialVersionUID = -8695445500417492411L;
    /** 快递单号 */
    private String            orderNo;
    /** 三方快递单号 */
    private String            otherOrderNo;
    /** agent用户类型的下单：agent电话号码； 大客户类型的下单：厂商编码（授权码） 散客：空值 */
    private String            code;
    /** 平台支付流水号 */
    private String            paymentOrderNo;
    /** 微信支付流水号 */
    private String            otherPaymentNo;
    /** 支付状态； WATI_PAY（待支付）； PAYING（支付中）； PAYED（已支付）； PAY_CANCEL（支付取消） */
    private String            status;
    /** 创建时间 */
    private java.util.Date    fromDate;
    /** 创建时间 */
    private java.util.Date    toDate;
}
