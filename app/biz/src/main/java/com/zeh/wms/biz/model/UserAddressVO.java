package com.zeh.wms.biz.model;

import com.zeh.wms.biz.model.enums.AddressTypeEnum;
import com.zeh.wms.biz.model.enums.StateEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 发货、收获地址模型
 * 
 * @author allen
 * @create $ ID: UserAddressVO, 18/2/6 16:23 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class UserAddressVO extends BaseVO {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 姓名 */
    private String            name;
    /** 电话 */
    private String            tel;
    /** 邮编 */
    private String            zipCode;
    /** 省 */
    private String            province;
    /** 市 */
    private String            city;
    /** 区 */
    private String            region;
    /** 地址明细 */
    private String            detail;
    /** 类型：SENDER：发件人; RECEIVER：寄件人; */
    private AddressTypeEnum   addressType;
    /** 关联用户 */
    private long              userId;
    /** 是否默认地址 */
    private StateEnum         defaultSetting;
}