package com.zeh.wms.web.form;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author allen
 * @create $ ID: ManufacturerFrom, 18/2/7 19:28 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class ManufacturerFrom implements Serializable {
    /** id */
    private long   id;
    /** 厂商编号 */
    private String code;
    /** 厂商名称 */
    private String name;
    /** 结算方式； real：实时线上结算 monthly：月结算 */
    private String settleType;
    /** 快递公司类型，SF-顺丰，DEPPON-德邦 */
    private String express;
}