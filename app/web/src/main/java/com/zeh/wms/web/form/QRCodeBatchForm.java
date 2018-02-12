package com.zeh.wms.web.form;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author allen
 * @create $ ID: QRCodeBatchForm, 18/2/10 20:39 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class QRCodeBatchForm implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 批次号 */
    private String            batchSerial;
    /** 二维码ID */
    private String            qrcodeSerial;
    /** 批次状态； 0-未使用，1-已使用 */
    private Integer           state;
    /** 批次数量 */
    private Integer           amount;
}