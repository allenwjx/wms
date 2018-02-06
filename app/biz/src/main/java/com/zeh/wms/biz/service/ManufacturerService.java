package com.zeh.wms.biz.service;

import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.ManufacturerVO;

/**
 * 厂商服务
 *
 * @author allen
 * @create $ ID: ManufacturerService, 18/2/6 14:51 allen Exp $
 * @since 1.0.0
 */
public interface ManufacturerService {

    /**
     * 创建厂商
     *
     * @param manufacturer 厂商
     * @throws ServiceException 厂商创建异常
     */
    void createManufacturer(ManufacturerVO manufacturer) throws ServiceException;

}
