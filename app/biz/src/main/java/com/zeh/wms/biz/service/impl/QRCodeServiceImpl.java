package com.zeh.wms.biz.service.impl;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageUtils;
import com.zeh.jungle.utils.common.FastBase64;
import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.QRCodeException;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.QRCodeMapper;
import com.zeh.wms.biz.model.QrcodeVO;
import com.zeh.wms.biz.service.QRCodeService;
import com.zeh.wms.dal.daointerface.QrcodeDAO;
import com.zeh.wms.dal.dataobject.QrcodeDO;
import com.zeh.wms.dal.operation.qrcode.QueryPageByConditionsQuery;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author allen
 * @create $ ID: QRCodeServiceImpl, 18/2/6 13:21 allen Exp $
 * @since 1.0.0
 */
@Service
public class QRCodeServiceImpl implements QRCodeService {
    /** ERROR Factory */
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();

    @Resource
    private QrcodeDAO qrcodeDAO;

    @Resource
    private QRCodeMapper mapper;

    /**
     * 生成二维码，默认配置参数
     *
     * @param content 二维码内容
     * @param width   二维码宽度
     * @param height  二维码高度
     * @return 二维码图片Base64数据
     */
    @Override
    public String encode(String content, int width, int height) throws QRCodeException {
        Map<EncodeHintType, Object> hints = new HashMap<>(8);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        return encode(content, width, height, hints);
    }

    /**
     * 生成二维码
     *
     * @param content 二维码内容
     * @param width   二维码宽度
     * @param height  二维码高度
     * @param hints   配置参数
     * @return 二维码图片Base64数据
     */
    @Override
    public String encode(String content, int width, int height, Map<EncodeHintType, Object> hints) throws QRCodeException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ImageIO.write(bufferedImage, "png", os);
            String data = FastBase64.encodeToString(os.toByteArray(), true);
            return data;
        } catch (Exception e) {
            throw new QRCodeException(ERROR_FACTORY.encodeQRCodeError(content), e);
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

    /**
     * 解析二维码数据
     *
     * @param qrCode
     * @return 二维码图片Base64数据
     * @throws QRCodeException 二维码解析异常
     */
    @Override
    public String decode(String qrCode) throws QRCodeException {
        if (StringUtils.isBlank(qrCode)) {
            throw new IllegalArgumentException("QRCode content is null");
        }

        byte[] data = FastBase64.decode(qrCode);
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        try {
            BufferedImage image = ImageIO.read(in);
            if (image == null) {
                throw new QRCodeException(ERROR_FACTORY.decodeQRCodeError(qrCode));
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Map<DecodeHintType, Object> hints = new HashMap<>(8);
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            Result result = new MultiFormatReader().decode(bitmap, hints);
            return result.getText();
        } catch (Exception e) {
            throw new QRCodeException(ERROR_FACTORY.decodeQRCodeError(qrCode));
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    @Override
    public PageList <QrcodeVO> queryAll (int currentPage, int size) throws ServiceException {
        PageList <QrcodeDO> dos = qrcodeDAO.queryPageAll (currentPage, size);
        return PageUtils.createPageList (mapper.d2vs (dos.getData ()), dos.getPaginator ());
    }

    @Override
    public PageList <QrcodeVO> queryByConditions (QrcodeVO vo, int currentPage, int size) throws ServiceException {
        if (currentPage <= 0)
            currentPage = 1;
        if (size <= 0)
            size = 20;
        QueryPageByConditionsQuery query = new QueryPageByConditionsQuery ();
        if (vo != null) {
            if (vo.getCommodityId () != null)
                query.setCommodityId (vo.getCommodityId ());
            if (vo.getBatchId () != null)
                query.setBatchId (vo.getBatchId ());
        }
        query.setPage (currentPage);
        query.setPageSize (size);

        PageList <QrcodeDO> dos = qrcodeDAO.queryPageByConditions (query);
        return PageUtils.createPageList (mapper.d2vs (dos.getData ()), dos.getPaginator ());
    }
}
