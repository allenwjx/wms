package com.zeh.wms.biz.service.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageUtils;
import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.RegionsMapper;
import com.zeh.wms.biz.model.RegionsVO;
import com.zeh.wms.biz.service.RegionsService;
import com.zeh.wms.dal.daointerface.RegionDAO;
import com.zeh.wms.dal.dataobject.RegionDO;
import com.zeh.wms.dal.operation.region.QueryByPageQuery;

/**
 * @author allen
 * @create $ ID: RegionsServiceImpl, 18/2/9 02:46 allen Exp $
 * @since 1.0.0
 */
@Service
public class RegionsServiceImpl implements RegionsService {
    /** 错误工厂 */
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();
    @Resource
    private RegionDAO                    regionDAO;
    @Resource
    private RegionsMapper                mapper;

    /**
     * 分页查询省市区
     *
     * @param query 查询条件
     * @return 省市区
     * @throws ServiceException 异常
     */
    @Override
    public PageList<RegionsVO> pageQueryRegions(QueryByPageQuery query) throws ServiceException {
        PageList<RegionDO> ret = regionDAO.queryByPage(query);
        Collection<RegionsVO> regions = mapper.do2vos(ret.getData());
        return PageUtils.createPageList(regions, ret.getPaginator());
    }

    /**
     * 查询省信息
     *
     * @return 省信息
     * @throws ServiceException 查询异常
     */
    @Override
    public Collection<RegionsVO> queryProvinces() throws ServiceException {
        List<RegionDO> regionDOs = regionDAO.queryByParentId(0L);
        return mapper.do2vos(regionDOs);
    }

    /**
     * 查询市信息
     *
     * @param provinceId 省ID
     * @return 市信息
     * @throws ServiceException 查询异常
     */
    @Override
    public Collection<RegionsVO> queryCities(Long provinceId) throws ServiceException {
        List<RegionDO> regionDOs = regionDAO.queryByParentId(provinceId);
        return mapper.do2vos(regionDOs);
    }

    /**
     * 查询县区信息
     *
     * @param cityId 市ID
     * @return 县区信息
     * @throws ServiceException 查询异常
     */
    @Override
    public Collection<RegionsVO> queryDistricts(Long cityId) throws ServiceException {
        List<RegionDO> regionDOs = regionDAO.queryByParentId(cityId);
        return mapper.do2vos(regionDOs);
    }
}