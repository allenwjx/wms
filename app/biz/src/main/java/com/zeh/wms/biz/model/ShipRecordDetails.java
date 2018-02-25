package com.zeh.wms.biz.model;

import com.zeh.wms.biz.model.enums.StateEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author allen
 * @create $ ID: ShipRecordDetails, 18/2/25 20:42 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class ShipRecordDetails extends AbstractModel {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 客户编号 */
    private String            agentCode;
    /** 外部客户编号 */
    private String            agentExternalCode;
    /** 客户姓名 */
    private String            agentName;
    /** 代理人电话 */
    private String            agentMobile;
    /** 代理人收件地址 */
    private String            agentAddress;
    /** 厂商 */
    private String            manufacturer;
    /** 商品名称 */
    private String            commodityName;
    /** 商品单价，单位：分 */
    private Integer           commodityPrice;
    /** 商品单位，如：件，箱 */
    private String            commodityUnit;
    /** 商品重量（单件），单位：克 */
    private Integer           commodityWeight;
    /** 商品简介 */
    private String            commodityDescription;

}