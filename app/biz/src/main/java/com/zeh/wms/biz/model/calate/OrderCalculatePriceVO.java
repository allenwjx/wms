package com.zeh.wms.biz.model.calate;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author hzy24985
 * @version $Id: OrderCalculatePriceModel, v 0.1 2018/3/14 00:27 hzy24985 Exp $
 */
@Data
public class OrderCalculatePriceVO implements Serializable {
    private static final long serialVersionUID = 1437650158430613632L;

    private Integer firstPrice;

    private Integer additionalPrice;

    private Integer total;

    public String getFirstPriceRmb() {
        return getRmb(firstPrice);
    }

    public String getAdditionalPriceRmb() {
        return getRmb(additionalPrice);
    }

    public String getTotalRmb() {
        return getRmb(total);
    }

    private String getRmb(Integer price) {
        if (price == null) {
            return "0.00";
        }
        BigDecimal bd = new BigDecimal(price).divide(new BigDecimal(100), BigDecimal.ROUND_UP).setScale(2, BigDecimal.ROUND_UP);
        return bd.toString();
    }
}
