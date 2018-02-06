package com.zeh.wms.biz.model;

import lombok.Data;

/**
 * 发货单模型
 * 
 * @author allen
 * @create $ ID: ShipRecordVO, 18/2/6 16:21 allen Exp $
 * @since 1.0.0
 */
@Data
public class ShipRecordVO extends BaseVO {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 用户ID（代理商）*/
    private long              agentId;
    /** 商品ID  */
    private long              commodityId;
    /** 二维码编号 */
    private String            qrcodeNo;
}