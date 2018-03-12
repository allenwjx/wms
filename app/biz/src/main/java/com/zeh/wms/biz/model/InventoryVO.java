package com.zeh.wms.biz.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author hzy24985
 * @version $Id: InventoryVO, v 0.1 2018/3/10 00:16 hzy24985 Exp $
 */
@Getter
@Setter
public class InventoryVO extends BaseVO {
    /**
     * 商品id
     */
    private Long    commodityId;
    /**
     * 电话
     */
    private String  mobile;
    /**
     * amount
     */
    private Integer amount;
}
