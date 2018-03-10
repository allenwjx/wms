package com.zeh.wms.web.form;

import lombok.Data;

import java.util.Date;

/**
 * @author hzy24985
 * @version $Id: InventoryForm, v 0.1 2018/3/10 00:20 hzy24985 Exp $
 */
@Data
public class InventoryForm {
    /**
     * id
     */
    private Long id;
    /**
     * 商品id
     */
    private Long commodityId;
    /**
     * userId
     */
    private Long userId;
    /**
     * amount
     */
    private Integer amount;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 修改人
     */
    private String modifyBy;

    /**
     * 用户昵称
     */
    private String nickName;

    private String tel;

    private String commodityName;
}
