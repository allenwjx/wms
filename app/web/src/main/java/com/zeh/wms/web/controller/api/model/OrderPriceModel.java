package com.zeh.wms.web.controller.api.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author allen
 * @create $ ID: OrderPriceModel, 18/3/15 19:25 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class OrderPriceModel implements Serializable {
    /** 支付方式：0-线上支付，1-线下现结，2-线下月结 */
    private String paymentType;
    /**  首重，单位（克）*/
    private int    firstWeight;
    /** 续重，单位：克 */
    private int    additionalWeight;
    /** 首重价格，单位：分 */
    private int    firstWeightPrice;
    /** 续重价格，单位：分 */
    private int    additionalWeightPrice;
    /** 快递费总价，单位：分 */
    private int    totalPrice;
}