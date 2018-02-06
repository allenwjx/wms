package com.zeh.wms.biz.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 代理商模型
 *
 * @author allen
 * @create $ ID: AgentVO, 18/2/6 15:03 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class AgentVO extends BaseVO {
    /**  */
    private static final long serialVersionUID = 1L;
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
}