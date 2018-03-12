package com.zeh.wms.integration.wechat.client;

import com.zeh.wms.integration.exceptions.IntegrationException;
import com.zeh.wms.integration.wechat.model.RetrieveSessionRequest;
import com.zeh.wms.integration.wechat.model.RetrieveSessionResponse;

/**
 * @author allen
 * @create $ ID: WechatClient, 18/3/12 14:32 allen Exp $
 * @since 1.0.0
 */
public interface WechatClient {

    /**
     * 根据js_code获取微信侧用户的会话信息
     * 
     * @param request
     * @return
     * @throws IntegrationException
     */
    RetrieveSessionResponse retrieveWechatSession(RetrieveSessionRequest request) throws IntegrationException;
}
