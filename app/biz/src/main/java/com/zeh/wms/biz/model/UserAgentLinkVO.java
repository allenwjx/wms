package com.zeh.wms.biz.model;

import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.model.enums.UserLinkTypeEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户关联模型
 *
 * @author allen
 * @create $ ID: UserAgentLinkVO, 18/2/6 16:28 allen Exp $
 * @since 1.0.0
 */
@Getter
@Setter
public class UserAgentLinkVO extends BaseVO {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 用户ID */
    private Long              userId;
    /** agent电话号码，或厂商编码（授权码）*/
    private String            code;
    /** 类型：代理商，厂商； 代理商：A； 厂商：B；*/
    private UserLinkTypeEnum  type;
    /** 关联关系状态：已关联, 已解除； 已关联：Y； 已解除：N；*/
    private StateEnum         linkStatus;
}