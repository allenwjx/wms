package com.zeh.wms.biz.model;

import lombok.Data;

/**
 * 二维码模型
 *
 * @author allen
 * @create $ ID: QrcodeVO, 18/2/6 16:12 allen Exp $
 * @since 1.0.0
 */
@Data
public class QrcodeVO extends BaseVO {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 二维码编号 */
    private String            serialNo;
    /** 商品id */
    private long              commodityId;
    /** 二维码图片数据，base64编码 */
    private String            data;
}