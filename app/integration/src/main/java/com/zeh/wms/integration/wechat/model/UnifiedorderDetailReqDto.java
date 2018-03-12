package com.zeh.wms.integration.wechat.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author hzy24985
 * @version $Id: UnifiedorderDetailReqDto, v 0.1 2018/2/7 15:53 hzy24985 Exp $
 */
@Data
public class UnifiedorderDetailReqDto implements Serializable{
    private static final long serialVersionUID = 811462181120915829L;

    /**
     * 订单原价.
     * 1.商户侧一张小票订单可能被分多次支付，订单原价用于记录整张小票的交易金额。
     * 2.当订单原价与支付金额不相等，则不享受优惠。
     * 3.该字段主要用于防止同一张小票分多次支付，以享受多次优惠的情况，正常支付订单不必上传此参数。
     */
    @JSONField(name = "cost_price")
    private int costPrice;

    @JSONField(name = "receipt_id")
    private String receiptId;

    @JSONField(name = "goods_detail")
    private List<UnifiedorderGoodsDetailReqDto> goodsDetail;


}
