package com.zeh.wms.web.form;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author allen
 * @create $ ID: QRCodeBindForm, 18/2/25 19:53 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class QRCodeBindForm implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 二维码原始关联的商品ID */
    private Long              commodityId;
    /** 二维码流水号 */
    private String            serialNo;
    /** 要邦定的代理人ID */
    private Long              agentId;
    /** 要邦定的商品ID */
    private Long              bindCommodityId;
}