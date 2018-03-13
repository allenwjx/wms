package com.zeh.wms.web.controller.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hzy24985
 * @version $Id: OrderCalculateModel, v 0.1 2018/3/14 00:19 hzy24985 Exp $
 */
@Data
public class OrderCalculateModel implements Serializable {

    private static final long serialVersionUID = -2766308915298721876L;

    private String            province;

    private Long              commodityId;

    private Double            commodityQuantity;

    private String            expressType;
}
