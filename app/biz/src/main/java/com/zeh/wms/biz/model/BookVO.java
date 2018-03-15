package com.zeh.wms.biz.model;

import com.zeh.wms.biz.model.enums.ExpressOrderStateEnum;
import com.zeh.wms.biz.model.enums.SettleTypeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author allen
 * @create $ ID: BookVO, 18/3/15 14:12 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class BookVO extends AbstractModel<String> {
    /**  */
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private long              userId;
    /** 快递公司类型 */
    private String            expressType;
    /** 商品名称或商品类型 */
    private String            commodityName;
    /** 商品总重量 */
    private int               commodityWeight;
    /** 订单备注 */
    private String            remark;

    // ~寄件人、收件人信息 ---------------------------

    /** 寄件人姓名 */
    private String            senderName;
    /** 寄件人电话 */
    private String            senderTel;
    /** 寄件人地址：省 */
    private String            senderProvince;
    /** 寄件人地址：城市 */
    private String            senderCity;
    /** 寄件人地址：区 */
    private String            senderRegion;
    /** 寄件人地址明细 */
    private String            senderAddressDetail;
    /** 寄件人邮编 */
    private String            senderZipCode;
    /** 发件人公司 */
    private String            senderCompany;
    /** 收件人姓名 */
    private String            receiverName;
    /** 收件人电话 */
    private String            receiverTel;
    /** 收件人地址：省 */
    private String            receiverProvince;
    /** 收件人地址：城市 */
    private String            receiverCity;
    /** 收件人地址：区 */
    private String            receiverRegion;
    /** 收件人详细地址 */
    private String            receiverAddressDetail;
    /** 收件人邮编 */
    private String            receiverZipCode;
    /** 收件人公司 */
    private String            receiverCompany;
}