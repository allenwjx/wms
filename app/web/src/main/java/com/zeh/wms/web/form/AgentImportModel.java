package com.zeh.wms.web.form;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author hzy24985
 * @version $Id: AgentImportModel.java, v 0.1 2018/2/9 10:29 hzy24985 Exp $
 */
@Data
public class AgentImportModel implements Serializable {
    private static final String E = "E";
    /** 授权码 */
    private String              code;

    /** 收货人姓名 */
    private String              receiverName;

    /** 收货人电话 */
    private String              receiverTel;

    /** 数量 */
    private int                 amount;

    /** 收件人地址 */
    private String              receiverAddress;

    public String getTelPlainString() {
        if (StringUtils.isBlank(receiverTel)) {
            return StringUtils.EMPTY;
        }
        if (StringUtils.indexOf(receiverTel, E) > 0) {
            return new BigDecimal(receiverTel).toPlainString();
        }
        return receiverTel;
    }
}
