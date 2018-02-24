package com.zeh.wms.web.form;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class QRCodeForm implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 二维码ID */
    private Long            id;
    /** 商品ID */
    private Long            commodityId;
    /** 批次号ID */
    private Long            batchId;
}
