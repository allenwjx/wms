package com.zeh.wms.biz.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author hzy24985
 * @version $Id: InventoryCommodityVO, v 0.1 2018/3/12 20:56 hzy24985 Exp $
 */
@Getter
@Setter
public class InventoryCommodityVO extends InventoryVO {

    private CommodityVO commodity;

    private AgentVO     agent;

    private UserVO      user;
}
