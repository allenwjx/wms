package com.zeh.wms.integration.wechat.client;

import javax.annotation.Resource;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.zeh.jungle.client.RestTemplateClient;
import com.zeh.jungle.client.model.HttpRequest;
import com.zeh.jungle.client.model.HttpResponse;
import com.zeh.jungle.client.model.ParameterType;
import com.zeh.jungle.client.model.SerializeMethod;
import com.zeh.jungle.core.configuration.AppConfiguration;
import com.zeh.jungle.core.configuration.AppConfigurationAware;
import com.zeh.wms.integration.exceptions.IntegrationException;
import com.zeh.wms.integration.wechat.model.RetrieveSessionRequest;
import com.zeh.wms.integration.wechat.model.RetrieveSessionResponse;

/**
 * @author allen
 * @create $ ID: WechatClientImpl, 18/3/12 14:34 allen Exp $
 * @since 1.0.0
 */
@Service
public class WechatClientImpl implements WechatClient, AppConfigurationAware {

    private AppConfiguration   appConfiguration;
    @Resource
    private RestTemplateClient restTemplateClient;

    /**
     * 根据js_code获取微信侧用户的会话信息
     *
     * @param request
     * @return
     * @throws IntegrationException
     */
    @Override
    public RetrieveSessionResponse retrieveWechatSession(RetrieveSessionRequest request) throws IntegrationException {
        String jsCodeUrl = appConfiguration.getPropertyValue("jscode.url");
        HttpRequest<RetrieveSessionRequest> httpRequest = new HttpRequest<>();
        httpRequest.setRequest(request);
        httpRequest.setEndpoint(jsCodeUrl);
        httpRequest.setParameterType(ParameterType.URL_PIAR);
        httpRequest.setRequestMethod(HttpMethod.GET);
        httpRequest.setRequestSerializeMethod(SerializeMethod.NONE_SERIALIZER);
        httpRequest.setResponseSerializeMethod(SerializeMethod.FASTJSON_SERIALIZER);
        httpRequest.addHeader("Content-Type", "application/json");
        HttpResponse<RetrieveSessionResponse> httpResponse = restTemplateClient.invoke(httpRequest, RetrieveSessionResponse.class);
        return httpResponse.getResponse();
    }

    /**
     * 设置应用配置信息
     *
     * @param appConfiguration
     */
    @Override
    public void setAppConfiguration(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
    }
}