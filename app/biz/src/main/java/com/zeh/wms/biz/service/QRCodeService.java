package com.zeh.wms.biz.service;

import com.google.zxing.EncodeHintType;
import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.QRCodeException;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.QrcodeVO;

import java.util.Map;

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

    /**
     * 查询全部数据
     *
     * @param currentPage
     * @param size
     * @return
     * @throws ServiceException
     */
    PageList <QrcodeVO> queryAll (int currentPage, int size) throws ServiceException;

    /**
     * 条件查询二维码列表
     *
     * @param vo        二维码查询对象（可包括：商品ID，批次号ID）
     * @param currentPage
     * @param size
     * @return
     * @throws QRCodeException
     */
    PageList <QrcodeVO> queryByConditions (QrcodeVO vo, int currentPage, int size) throws ServiceException;

    /**
     * 建立二维码与商品的绑定关系
     *
     * @param code_id   二维码ID
     * @param commodity_id  商品ID
     * @throws ServiceException
     */
    void bindCommodity (Long code_id, Long commodity_id) throws ServiceException;

    /**
     * 通过主键查询
     *
     * @param code_id   二维码ID
     * @return
     * @throws ServiceException
     */
    QrcodeVO queryById (Long code_id) throws ServiceException;
}
