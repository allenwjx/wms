package com.zeh.wms.web.form;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class QRCodeForm implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 二维码ID */
    private Long              id;
    /** 商品ID */
    private Long              commodityId;
    /** 批次号ID */
    private Long              batchId;
    /** 二维码状态 */
    private Integer           state;
    /** 二维码Base64数据 */
    private String            data;
}
