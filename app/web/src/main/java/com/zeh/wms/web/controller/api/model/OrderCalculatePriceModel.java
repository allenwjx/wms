package com.zeh.wms.web.controller.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hzy24985
 * @version $Id: OrderCalculatePriceModel, v 0.1 2018/3/14 00:27 hzy24985 Exp $
 */
@Data
public class OrderCalculatePriceModel implements Serializable {
    private static final long serialVersionUID = 1437650158430613632L;

    private String firstPrice;

    private String additionalPrice;

    private double total;
}
