package com.zeh.wms.biz.service;

import java.util.Map;

import com.google.zxing.EncodeHintType;
import com.zeh.wms.biz.exception.QRCodeException;

/**
 * @author allen
 * @create $ ID: QRCodeService, 18/2/6 13:12 allen Exp $
 * @since 1.0.0
 */
public interface QRCodeService {

    /**
     * 生成二维码，默认配置参数，QR_CODE模式
     *
     * @param content   二维码内容
     * @param width     二维码宽度
     * @param height    二维码高度
     * @return 二维码图片Base64数据
     * @throws QRCodeException 二维码生成异常
     */
    String encode(String content, int width, int height) throws QRCodeException;

    /**
     * 生成二维码，QR_CODE模式
     *
     * @param content   二维码内容
     * @param width     二维码宽度
     * @param height    二维码高度
     * @param hints     配置参数
     * @return 二维码图片Base64数据
     * @throws QRCodeException 二维码生成异常
     */
    String encode(String content, int width, int height, Map<EncodeHintType, Object> hints) throws QRCodeException;

    /**
     * 解析二维码数据
     * 
     * @param qrCode    二维码图片Base64数据
     * @return          二维码数据
     * @throws QRCodeException 二维码解析异常
     */
    String decode(String qrCode) throws QRCodeException;
}
