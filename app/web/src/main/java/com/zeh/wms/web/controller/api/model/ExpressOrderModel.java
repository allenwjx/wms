package com.zeh.wms.web.controller.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hzy24985
 * @version $Id: ExpressOrderModel, v 0.1 2018/3/14 22:23 hzy24985 Exp $
 */
@Data
public class ExpressOrderModel implements Serializable {
    private static final long serialVersionUID = 3117917785075061553L;

    /** 快递公司类型 */
    private String            expressType;
    /** 商品id */
    private Long              commodityId;
    /** 商品名称或商品类型 */
    private String            commodityName;
    /** 商品总数量 */
    private Integer           commodityQuanity;
    /** 商品总重量 */
    private Integer           commodityWeight;
    /** 库存id */
    private Long              inventoryId;
    /** 首重价格，单位：分 */
    private Integer           firstWeightPrice;
    /** 续重价格，单位：分 */
    private Integer           additionalWeightPrice;
    /** 快递费总价，单位：分 */
    private int               totalPrice;
    /** 订单备注 */
    private String            remark;

    /** 寄件人姓名 */
    private Long              senderAddressId;

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
    /** 收件人公司 */
    private String            receiverCompany;
}
