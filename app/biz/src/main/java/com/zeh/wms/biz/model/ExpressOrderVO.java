package com.zeh.wms.biz.model;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.zeh.wms.biz.model.enums.ExpressOrderStateEnum;
import com.zeh.wms.biz.model.enums.SettleTypeEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 快递单模型
 *
 * @author allen
 * @create $ ID: ExpressOrderVO, 18/2/6 15:30 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class ExpressOrderVO extends BaseVO {
    /**  */
    private static final long        serialVersionUID = 1L;
    /** 快递单号 */
    private String                   orderNo;
    /** 第三方订单号 */
    private String                   otherOrderNo;
    /** 用户ID */
    private long                     userId;
    /** 支付方式：0-线上支付，1-线下现结，2-线下月结 */
    private SettleTypeEnum           paymentType;
    /** 快递公司类型 */
    private String                   expressType;
    /** 商品名称或商品类型 */
    private String                   commodityName;
    /** 商品总数量 */
    private int                      commodityQuanity;
    /** 商品总重量 */
    private int                      commodityWeight;
    /**  首重，单位（克）*/
    private int                      firstWeight;
    /** 续重，单位：克 */
    private int                      additionalWeight;
    /** 首重价格，单位：分 */
    private int                      firstWeightPrice;
    /** 续重价格，单位：分 */
    private int                      additionalWeightPrice;
    /** 快递费总价，单位：分 */
    private int                      totalPrice;
    /** 订单备注 */
    private String                   remark;
    /** 订单状态; WATI_PAY（待支付）； WAIT_PICKUP（待取件）； WAIT_SEND（待发货）； SENDED（已发货）； CANCELED（订单取消）*/
    private ExpressOrderStateEnum    status;
    /** 寄件人姓名 */
    private String                   senderName;
    /** 寄件人电话 */
    private String                   senderTel;
    /** 寄件人地址：省 */
    private String                   senderProvince;
    /** 寄件人地址：城市 */
    private String                   senderCity;
    /** 寄件人地址：区 */
    private String                   senderRegion;
    /** 寄件人地址明细 */
    private String                   senderAddressDetail;
    /** 寄件人邮编 */
    private String                   senderZipCode;
    /** 发件人公司 */
    private String                   senderCompany;
    /** 收件人姓名 */
    private String                   receiverName;
    /** 收件人电话 */
    private String                   receiverTel;
    /** 收件人地址：省 */
    private String                   receiverProvince;
    /** 收件人地址：城市 */
    private String                   receiverCity;
    /** 收件人地址：区 */
    private String                   receiverRegion;
    /** 收件人详细地址 */
    private String                   receiverAddressDetail;
    /** 收件人邮编 */
    private String                   receiverZipCode;
    /** 收件人公司 */
    private String                   receiverCompany;
    /** 快递商品项 */
    private List<ExpressOrderItemVO> items            = Lists.newArrayList();

    /**
     * 寄件人所有字段拼接的地址.
     * @return 寄件人地址.
     */
    public String getSenderAddress() {
        return Joiner.on(" ").join(senderProvince, senderCity, senderRegion, senderAddressDetail);
    }

    /**
     * 收件人所有地址字段拼接的地址.
     * @return 收件人地址.
     */
    public String getReceiverAddress() {
        return Joiner.on(" ").join(receiverProvince, receiverCity, receiverRegion, receiverAddressDetail);
    }
}