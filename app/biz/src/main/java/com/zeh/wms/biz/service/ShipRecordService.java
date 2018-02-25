package com.zeh.wms.biz.service;

import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.ShipRecordDetails;
import com.zeh.wms.biz.model.ShipRecordVO;

/**
 * @author allen
 * @create $ ID: ShipRecordService, 18/2/25 20:07 allen Exp $
 * @since 1.0.0
 */
public interface ShipRecordService {

    /**
     * 代理人，商品，二维码邦定
     * 
     * @param shipRecord
     * @throws ServiceException
     */
    void bind(ShipRecordVO shipRecord) throws ServiceException;

    /**
     * 根据二维码编号，查询代理人产品邦定详情
     * 
     * @param qrCodeSerialNo
     * @return
     * @throws ServiceException
     */
    ShipRecordDetails retrieveShipRecordDetails(String qrCodeSerialNo) throws ServiceException;

    /**
     * 根据二维码编号删除邦定记录
     * 
     * @param qrCodeSerialNo
     * @throws ServiceException
     */
    void deleteShipRecordByQRCode(String qrCodeSerialNo) throws ServiceException;

}
