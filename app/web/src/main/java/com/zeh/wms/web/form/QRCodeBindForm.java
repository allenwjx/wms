package com.zeh.wms.web.form;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author allen
 * @create $ ID: QRCodeBindForm, 18/2/25 19:53 allen Exp $
 * @since 1.0.0
 */
@ApiModel(description = "二维码请求信息")
@Getter
@Setter
public class QRCodeBindForm implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 二维码原始关联的商品ID */
    @ApiModelProperty(value = "二维码原始关联的商品ID", required = true)
    private Long              commodityId;
    /** 二维码流水号 */
    @ApiModelProperty(value = "二维码流水号", required = true)
    private String            serialNo;
    /** 要邦定的代理人ID */
    @ApiModelProperty(value = "要邦定的代理人ID", required = true)
    private Long              agentId;
    /** 要邦定的商品ID */
    @ApiModelProperty(value = "要邦定的商品ID", required = true)
    private Long              bindCommodityId;
}