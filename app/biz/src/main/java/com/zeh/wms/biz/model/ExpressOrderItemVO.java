package com.zeh.wms.biz.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 快递商品项模型
 *
 * @author allen
 * @create $ ID: ExpressOrderItemVO, 18/2/6 15:46 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class ExpressOrderItemVO extends BaseVO {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 关联快递单号 */
    private String            orderNo;
    /** 商品名称 */
    private String            itemName;
    /** 商品code */
    private String            itemCode;
    /** agent用户类型的下单：agent电话号码； 大客户类型的下单：厂商编码（授权码） 散客：空值 */
    private String            relationCode;
    /** 商品数量 */
    private int               quantity;
    /** * 商品单位 */
    private String            unit;
    /** 商品重量（单件），单位：克 */
    private int               unitWeight;
    /** 商品总重量，单位：克 */
    private int               totalWeight;
    /** 商品物流单价，单位分 */
    private int               unitPrice;
    /** 商品快递费总价，单位：分 */
    private int               totalPrice;
}