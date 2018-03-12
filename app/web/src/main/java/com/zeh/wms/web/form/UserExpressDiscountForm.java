package com.zeh.wms.web.form;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hzy24985
 * @version $Id: UserExpressDiscountForm, v 0.1 2018/3/10 18:45 hzy24985 Exp $
 */
@Data
public class UserExpressDiscountForm implements Serializable {

    private Long   id;
    /**
     * 前端用户主键
     */
    private Long   userId;
    /**
     * 快递公司code
     */
    private String expressCode;
    /**
     * 折扣，原始价格*discount的价格。0< discount <= 1
     */
    private String discount;

    private Date   gmtCreate;
    /**
     * 修改时间
     */
    private Date   gmtModified;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 修改人
     */
    private String modifyBy;
}
