package com.zeh.wms.web.form;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hzy24985
 * @version $Id: DeleteForm.java, v 0.1 2018/2/13 16:30 hzy24985 Exp $
 */
@ApiModel(description = "订单查询请求信息")
@Data
public class DeleteForm implements Serializable {
    private static final long serialVersionUID = -5216457518046898601L;

    @ApiModelProperty("订单创建时间范围起始")
    private String            createTimeStart;

    @ApiModelProperty("订单创建时间范围结束")
    private String            createTimeEnd;
}
