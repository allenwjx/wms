package com.zeh.wms.web.form;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author allen
 * @create $ ID: AgentForm, 18/2/8 15:28 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class AgentForm implements Serializable {
    private static final long serialVersionUID = 1L;
    /** id */
    private long              id;
    /** 客户编号 */
    private String            code;
    /** 外部客户编号 */
    private String            externalCode;
    /** 客户姓名 */
    private String            name;
    /** 代理人电话 */
    private String            mobile;
    /** 代理人收件地址 */
    private String            address;
    /** 状态 */
    private Integer           enabled;
}