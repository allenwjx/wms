package com.zeh.wms.web.controller.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hzy24985
 * @version $Id: AddressModel, v 0.1 2018/3/8 12:17 hzy24985 Exp $
 */
@Data
@ApiModel("地址模型")
public class AddressModel implements Serializable {
    private static final long serialVersionUID = 5062328898860117698L;

    private long              id;
    /** 姓名 */
    private String            name;

    /** 电话 */
    private String            tel;

    /** 省 */
    private String            province;

    /** 市 */
    private String            city;

    /** 区 */
    private String            region;

    /** 地址明细 */
    private String            detail;
    /** 公司 */
    private String            company;
    @ApiModelProperty(name = "地址类型", allowableValues = "SENDER, RECEIVER")
    private String            addressType;

    /** 是否默认地址 */
    private boolean           defaultSetting;
}
