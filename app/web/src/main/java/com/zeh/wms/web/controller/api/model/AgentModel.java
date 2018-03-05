package com.zeh.wms.web.controller.api.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author allen
 * @create $ ID: AgentModel, 18/3/5 19:26 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class AgentModel implements Serializable {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 主键  */
    private long              id;
    /** 客户姓名 */
    private String            name;
    /** 代理人电话 */
    private String            mobile;
}