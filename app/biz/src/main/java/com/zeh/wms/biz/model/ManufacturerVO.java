package com.zeh.wms.biz.model;

import java.util.List;

import com.google.common.collect.Lists;
import com.zeh.wms.biz.model.enums.ExpressTypeEnum;
import com.zeh.wms.biz.model.enums.SettleTypeEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 厂商模型
 * 
 * @author allen
 * @create $ ID: ManufacturerVO, 18/2/6 14:52 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class ManufacturerVO extends BaseVO {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 厂商编号 */
    private String            code;
    /** 厂商名称 */
    private String            name;
    /** 结算方式； real：实时线上结算 monthly：月结算 */
    private SettleTypeEnum    settleType;
    /** 快递公司类型，SF-顺丰，DEPPON-德邦 */
    private ExpressTypeEnum   express;
    /** 厂商售卖商品信息 */
    private List<CommodityVO> commodities      = Lists.newArrayList();
}