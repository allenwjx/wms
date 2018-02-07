package com.zeh.wms.biz.service;

import com.zeh.jungle.dal.paginator.PageList;
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

    /**
     * 更新厂商信息
     * 
     * @param manufacturer 厂商
     * @throws ServiceException 厂商更新异常
     */
    void updateManufacturer(ManufacturerVO manufacturer) throws ServiceException;

    /**
     * 根据厂商编码查询厂商信息
     * 
     * @param code 厂商编码
     * @return 厂商
     * @throws ServiceException 厂商查询异常
     */
    ManufacturerVO findManufacturerByCode(String code) throws ServiceException;

    /**
     * 根据厂商ID查询厂商信息
     * 
     * @param id 厂商ID
     * @return 厂商
     * @throws ServiceException 厂商查询异常
     */
    ManufacturerVO findManufacturerById(long id) throws ServiceException;

    /**
     * 分页查询厂商信息
     *
     * @param manufacturer 厂商查询条件
     * @param currentPage 当前页
     * @param size 每页数据量
     * @return 厂商查询结果
     * @throws ServiceException 分页查询异常
     */
    PageList<ManufacturerVO> pageQueryManufacturers(ManufacturerVO manufacturer, int currentPage, int size) throws ServiceException;

}
