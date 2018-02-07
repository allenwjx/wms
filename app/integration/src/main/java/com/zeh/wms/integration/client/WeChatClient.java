package com.zeh.wms.integration.client;

import com.zeh.wms.integration.dto.jscode2session.Jscode2SessionReqDto;
import com.zeh.wms.integration.dto.jscode2session.Jsoncode2SessionRespDto;
import com.zeh.wms.integration.dto.unifiedorder.UnifiedorderReqDto;
import com.zeh.wms.integration.dto.unifiedorder.UnifiedorderRespDto;
import com.zeh.wms.integration.exceptions.IntegrationException;

/**
 * The interface We chat client.
 *
 * @author hzy24985
 * @version $Id : WeChatClient, v 0.1 2018/2/6 17:02 hzy24985 Exp $
 */
public interface WeChatClient {

    /**
     * code 换sessionKey和openid
     *
     * @param reqDto the req dto
     * @return jsoncode 2 session resp dto
     * @throws IntegrationException the integration exception
     */
    Jsoncode2SessionRespDto jsoncode2Session(Jscode2SessionReqDto reqDto) throws IntegrationException;

    /**
     * 下支付单.
     *
     * @param reqDto the req dto
     * @return the unifiedorder resp dto
     * @throws IntegrationException the integration exception
     */
    UnifiedorderRespDto unifiedorder(UnifiedorderReqDto reqDto) throws IntegrationException;
}
