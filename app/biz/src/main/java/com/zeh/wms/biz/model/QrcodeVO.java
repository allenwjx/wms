package com.zeh.wms.biz.model;

import com.zeh.wms.biz.model.enums.StateEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 二维码模型
 *
 * @author allen
 * @create $ ID: QrcodeVO, 18/2/6 16:12 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class QrcodeVO extends BaseVO {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 二维码编号 */
    private String            serialNo;
    /** 商品id */
    private Long              commodityId;
    /** 批次号id*/
    private Long              batchId;
    /** 二维码图片数据，base64编码 */
    private String            data;
    /** 批次状态； 0-未关联，1-已关联 */
    private StateEnum         state;
}