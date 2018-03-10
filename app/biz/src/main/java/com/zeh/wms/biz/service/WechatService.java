package com.zeh.wms.biz.service;

import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.WechatAccessToken;
import com.zeh.wms.biz.model.WechatUser;

/**
 *
 * @author weijun
 * @create $ v 1.0.0 2018/3/10 下午12:31 Exp $
 */
public interface WechatService {

    /**
     * 获取缓存的微信应用accesstoken
     * @return
     */
    WechatAccessToken getAccessToken(Boolean is_renew) throws ServiceException;

    /**
     * 通过jscode换取微信用户对象
     *
     * @param jsCode
     * @return
     * @throws ServiceException
     */
    WechatUser getUserByJSCode(String jsCode) throws ServiceException;
}
