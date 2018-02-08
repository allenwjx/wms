package com.zeh.wms.biz.service;

import java.util.Collection;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.RegionsVO;
import com.zeh.wms.dal.operation.region.QueryByPageQuery;

/**
 * @author allen
 * @create $ ID: RegionsService, 18/2/9 02:44 allen Exp $
 * @since 1.0.0
 */
public interface RegionsService {

    /**
     * 分页查询省市区
     * 
     * @param query 查询条件
     * @return 省市区
     * @throws ServiceException 异常
     */
    PageList<RegionsVO> pageQueryRegions(QueryByPageQuery query) throws ServiceException;

    /**
     * 查询省信息
     * 
     * @return 省信息
     * @throws ServiceException 查询异常
     */
    Collection<RegionsVO> queryProvinces() throws ServiceException;

    /**
     * 查询市信息
     * 
     * @param provinceId 省ID
     * @return 市信息
     * @throws ServiceException 查询异常
     */
    Collection<RegionsVO> queryCities(Long provinceId) throws ServiceException;

    /**
     * 查询县区信息
     * 
     * @param cityId 市ID
     * @return 县区信息
     * @throws ServiceException 查询异常
     */
    Collection<RegionsVO> queryDistricts(Long cityId) throws ServiceException;

}