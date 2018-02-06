package com.zeh.wms.integration.client;

import com.zeh.wms.integration.dto.jscode2session.Jscode2SessionReqDto;
import com.zeh.wms.integration.dto.jscode2session.Jsoncode2SessionRespDto;

/**
 * @author hzy24985
 * @version $Id: WeChatClient, v 0.1 2018/2/6 17:02 hzy24985 Exp $
 */
public interface WeChatClient {

    Jsoncode2SessionRespDto jsoncode2Session(Jscode2SessionReqDto reqDto);
}
