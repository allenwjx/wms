package com.zeh.wms.biz.service.impl;

import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.ManufacturerMapper;
import com.zeh.wms.biz.model.ManufacturerVO;
import com.zeh.wms.biz.service.ManufacturerService;
import com.zeh.wms.dal.daointerface.ManufacturerDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author allen
 * @create $ ID: ManufacturerServiceImpl, 18/2/6 16:53 allen Exp $
 * @since 1.0.0
 */
@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    /** 错误工厂 */
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();
    /** 厂商DAO */
    @Resource
    private ManufacturerDAO              manufacturerDAO;
    /** mapper */
    @Resource
    private ManufacturerMapper mapper;

    /**
     * 创建厂商
     *
     * @param manufacturer 厂商
     * @throws ServiceException 厂商创建异常
     */
    @Override
    public void createManufacturer(ManufacturerVO manufacturer) throws ServiceException {
        if (manufacturer == null) {
            throw new ServiceException(ERROR_FACTORY.createManufacturerError());
        }

    }
}