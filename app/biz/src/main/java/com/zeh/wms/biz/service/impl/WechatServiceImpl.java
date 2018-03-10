package com.zeh.wms.biz.service.impl;

import com.zeh.jungle.core.configuration.AppConfiguration;
import com.zeh.jungle.core.configuration.AppConfigurationAware;
import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.WechatAccessToken;
import com.zeh.wms.biz.model.WechatUser;
import com.zeh.wms.biz.service.WechatService;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.springframework.stereotype.Service;
import weixin.popular.client.LocalHttpClient;

/**
 *
 * @author weijun
 * @create $ v 1.0.0 2018/3/10 下午1:24 Exp $
 */
@Service
public class WechatServiceImpl implements WechatService, AppConfigurationAware {
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();

    private String           wechat_js_code_url  = "https://api.weixin.qq.com/sns/jscode2session";
    private String           wechat_access_token = "https://api.weixin.qq.com/cgi-bin/token";
    private AppConfiguration appConfiguration    = null;

    private WechatAccessToken wechatAccessToken = null;

    private String wechatAppId     = null;
    private String wechatAppSecret = null;

    @Override public void setAppConfiguration(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
        this.wechatAppId = this.appConfiguration.getPropertyValue("wechat.appId");
        this.wechatAppSecret = this.appConfiguration.getPropertyValue("wechat.appSecret");
    }

    @Override public WechatAccessToken getAccessToken(Boolean isRenew) throws ServiceException {
        if (isRenew) {
            HttpUriRequest httpUriRequest = RequestBuilder.get().setUri(wechat_access_token).addParameter("appid", this.wechatAppId).addParameter("secret", this.wechatAppSecret)
                    .addParameter("grant_type", "client_credential").build();

            this.wechatAccessToken = LocalHttpClient.executeJsonResult(httpUriRequest, WechatAccessToken.class);
        }

        if (this.wechatAccessToken == null) {
            throw new ServiceException(ERROR_FACTORY.getWechatAccessTokenError("获取失败"));
        }

        return this.wechatAccessToken;
    }

    @Override public WechatUser getUserByJSCode(String jsCode) throws ServiceException {
        // 从微信获取对应信息
        HttpUriRequest httpUriRequest = RequestBuilder.get ()
                .setUri (wechat_js_code_url)
                .addParameter ("appid", this.wechatAppId)
                .addParameter ("secret", this.wechatAppSecret)
                .addParameter ("js_code", jsCode)
                .addParameter ("grant_type", "authorization_code")
                .build ();
        WechatUser wechat_app_user = LocalHttpClient.executeJsonResult (httpUriRequest, WechatUser.class);
        return wechat_app_user;
    }
}
