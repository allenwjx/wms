package com.zeh.wms.biz.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author hzy24985
 * @version $Id: UserExpressDiscountVO, v 0.1 2018/3/10 18:43 hzy24985 Exp $
 */
@Getter
@Setter
public class UserExpressDiscountVO extends BaseVO {
    private Long       userId;

    private String     expressCode;

    private BigDecimal discount;
}
