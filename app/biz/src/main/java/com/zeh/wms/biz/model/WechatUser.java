package com.zeh.wms.biz.model;

import lombok.Getter;
import lombok.Setter;
import weixin.popular.bean.BaseResult;


/**
 *
 * 用户会话对象
 *
 * @author weijun
 * @create $ v 1.0.0 2018/3/10 下午12:40 Exp $
 */
@Getter @Setter public class WechatUser extends BaseResult {
    private String openid;
    private String session_key;
    private String unionid;
}
