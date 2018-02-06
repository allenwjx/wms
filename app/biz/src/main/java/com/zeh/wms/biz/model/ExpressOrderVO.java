package com.zeh.wms.biz.model;

import java.util.List;

import com.google.common.collect.Lists;
import com.zeh.wms.biz.model.enums.ExpressOrderStateEnum;
import com.zeh.wms.biz.model.enums.ExpressTypeEnum;

import lombok.Data;

/**
 * 快递单模型
 *
 * @author allen
 * @create $ ID: ExpressOrderVO, 18/2/6 15:30 allen Exp $
 * @since 1.0.0
 */
@Data
public class ExpressOrderVO extends BaseVO {
    /**  */
    private static final long        serialVersionUID = 1L;
    /** 快递单号 */
    private String                   orderNo;
    /** 第三方订单号 */
    private String                   otherOrderNo;
    /** agent用户类型的下单：agent电话号码； 大客户类型的下单：厂商编码（授权码） 散客：空值 */
    private String                   code;
    /** 订单状态; WATI_PAY（待支付）； WAIT_PICKUP（待取件）； WAIT_SEND（待发货）； SENDED（已发货）； CANCEL（订单取消）*/
    private ExpressOrderStateEnum    status;
    /** 寄件人姓名 */
    private String                   senderName;
    /** 寄件人电话 */
    private String                   senderTel;
    /** 寄件人地址：省 */
    private ProvinceVO               senderProvince;
    /** 寄件人地址：城市 */
    private CityVO                   senderCity;
    /** 寄件人地址：区 */
    private DistrictVO               senderRegion;
    /** 寄件人地址明细 */
    private String                   senderAddressDetail;
    /** 寄件人邮编 */
    private String                   senderZipCode;
    /** 收件人姓名 */
    private String                   receiverName;
    /** 收件人电话 */
    private String                   receiverTel;
    /** 收件人地址：省 */
    private ProvinceVO               receiverProvince;
    /** 收件人地址：城市 */
    private CityVO                   receiverCity;
    /** 收件人地址：区 */
    private DistrictVO               receiverRegion;
    /** 收件人详细地址 */
    private String                   receiverAddressDetail;
    /** 收件人邮编 */
    private String                   receiverZipCode;
    /** 快递公司类型 TODO 快递公司需要配置为可数据库维护方式 */
    private ExpressTypeEnum          expressType;
    /** 快递费总价，单位：分 */
    private int                      totalPrice;
    /** 快递商品项 */
    private List<ExpressOrderItemVO> items            = Lists.newArrayList();
}