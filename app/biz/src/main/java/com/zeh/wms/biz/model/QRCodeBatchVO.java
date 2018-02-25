package com.zeh.wms.biz.model;

import com.zeh.wms.biz.model.enums.StateEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * @author allen
 * @create $ ID: QRCodeBatchVO, 18/2/10 20:11 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class QRCodeBatchVO extends BaseVO {

    /**  */
    private static final long serialVersionUID = 1L;
    /** 批次号 */
    private String            batchSerial;
    /** 数量 */
    private Integer           amount;
    /** 批次状态； 0-未使用，1-已使用 */
    private StateEnum         state;
    /** 商品ID */
    private Long              commodityId;
}