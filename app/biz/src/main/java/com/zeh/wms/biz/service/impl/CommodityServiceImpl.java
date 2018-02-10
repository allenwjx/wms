package com.zeh.wms.biz.service.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageUtils;
import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.CommodityMapper;
import com.zeh.wms.biz.model.CommodityVO;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.service.CommodityService;
import com.zeh.wms.biz.utils.CodeGenerator;
import com.zeh.wms.dal.daointerface.CommodityDAO;
import com.zeh.wms.dal.dataobject.CommodityDO;
import com.zeh.wms.dal.operation.commodity.QueryByPageQuery;

/**
 * @author allen
 * @create $ ID: CommodityServiceImpl, 18/2/8 18:23 allen Exp $
 * @since 1.0.0
 */
@Service
public class CommodityServiceImpl implements CommodityService {
    /** 错误工厂 */
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();
    /** Commidty mapper */
    @Resource
    private CommodityMapper              mapper;
    /** 商品DAO */
    @Resource
    private CommodityDAO                 commodityDAO;

    /**
     * 创建商品
     *
     * @param commodity 商品
     * @throws ServiceException 创建商品异常
     */
    @Override
    public void createCommodity(CommodityVO commodity) throws ServiceException {
        if (commodity == null) {
            throw new ServiceException(ERROR_FACTORY.createCommodityError());
        }
        commodity.setCode(CodeGenerator.generateCommodityCode());
        commodity.setEnabled(StateEnum.Y);
        CommodityDO commodityDO = mapper.vo2do(commodity);
        commodityDAO.insert(commodityDO);
    }

    /**
     * 更新商品信息
     *
     * @param commodity 商品
     * @throws ServiceException 商品更新异常
     */
    @Override
    public void updateCommodity(CommodityVO commodity) throws ServiceException {
        if (commodity == null || commodity.getId() <= 0) {
            throw new ServiceException(ERROR_FACTORY.updateCommodityError());
        }
        CommodityDO commodityDO = commodityDAO.queryById(commodity.getId());
        commodityDO.setDescription(StringUtils.isNoneBlank(commodity.getDescription()) ? commodity.getDescription() : commodityDO.getDescription());
        commodityDO.setManufacturerId(commodity.getManufacturerId() != null ? commodity.getManufacturerId() : commodityDO.getManufacturerId());
        commodityDO.setPrice(commodity.getPrice() != null ? commodity.getPrice() : commodityDO.getPrice());
        commodityDO.setUnit(StringUtils.isNotBlank(commodity.getUnit()) ? commodity.getUnit() : commodityDO.getUnit());
        commodityDO.setWeight(commodity.getWeight() != null ? commodity.getWeight() : commodityDO.getWeight());
        commodityDO.setModifyBy(commodity.getModifyBy());
        commodityDO.setName(StringUtils.isNotBlank(commodity.getName()) ? commodity.getName() : commodityDO.getName());
        commodityDO.setEnabled(commodity.getEnabled() != null ? commodity.getEnabled().getCode() : commodityDO.getEnabled());
        commodityDAO.update(commodityDO);
    }

    /**
     * 根据商品编号查询商品信息
     *
     * @param code 商品编号
     * @return 商品信息
     * @throws ServiceException 商品查询异常
     */
    @Override
    public CommodityVO findCommodityByCode(String code) throws ServiceException {
        if (StringUtils.isBlank(code)) {
            throw new ServiceException(ERROR_FACTORY.queryCommodityError());
        }
        CommodityDO commodityDO = commodityDAO.queryByCode(code);
        return mapper.do2vo(commodityDO);
    }

    /**
     * 根据商品ID查询商品信息
     *
     * @param id 商品ID
     * @return 商品信息
     * @throws ServiceException 商品查询异常
     */
    @Override
    public CommodityVO findCommodityById(long id) throws ServiceException {
        CommodityDO commodityDO = commodityDAO.queryById(id);
        return mapper.do2vo(commodityDO);
    }

    /**
     * 分页查询商品信息
     *
     * @param commodity   商品查询条件
     * @param currentPage 查询起始页
     * @param size        每页数量
     * @return 商品信息
     * @throws ServiceException 分页查询异常
     */
    @Override
    public PageList<CommodityVO> pageQueryCommodities(CommodityVO commodity, int currentPage, int size) throws ServiceException {
        if (commodity == null) {
            throw new ServiceException(ERROR_FACTORY.queryCommodityError());
        }
        QueryByPageQuery query = new QueryByPageQuery();
        query.setEnabled(commodity.getEnabled() == null ? null : commodity.getEnabled().getCode());
        query.setName(commodity.getName());
        query.setCode(commodity.getCode());
        query.setManufacturerId(commodity.getManufacturerId());
        query.setPage(currentPage);
        query.setPageSize(size);
        PageList<CommodityDO> ret = commodityDAO.queryByPage(query);
        Collection<CommodityVO> commodities = mapper.do2vos(ret.getData());
        return PageUtils.createPageList(commodities, ret.getPaginator());
    }

    /**
     * 查询所有商品信息
     *
     * @return 所有商品信息
     * @throws ServiceException 商品查询异常
     */
    @Override
    public Collection<CommodityVO> findAllCommodities() throws ServiceException {
        List<CommodityDO> commodities = commodityDAO.queryAllEnabled();
        return mapper.do2vos(commodities);
    }

    /**
     * 查询厂商所有商品信息
     *
     * @param manufacturerId 厂商ID
     * @return 厂商所有商品信息
     * @throws ServiceException 商品查询异常
     */
    @Override
    public Collection<CommodityVO> findByManufacturer(Long manufacturerId) throws ServiceException {
        if (manufacturerId == null) {
            throw new ServiceException(ERROR_FACTORY.queryCommodityError());
        }
        List<CommodityDO> commodityDOs = commodityDAO.queryByManufacturerId(manufacturerId);
        return mapper.do2vos(commodityDOs);
    }

    /**
     * 更新商品启用、禁用状态
     *
     * @param id       商品ID
     * @param modifyBy 修改人
     * @param enabled  状态
     * @throws ServiceException 商品状态更新异常
     */
    @Override
    public void updateCommodityState(long id, String modifyBy, StateEnum enabled) throws ServiceException {
        CommodityVO commodity = new CommodityVO();
        commodity.setId(id);
        commodity.setModifyBy(modifyBy);
        commodity.setEnabled(enabled);
        updateCommodity(commodity);
    }

    /**
     * 删除商品信息，不能恢复
     *
     * @param id 商品ID
     * @throws ServiceException 商品删除异常
     */
    @Override
    public void deleteCommodity(long id) throws ServiceException {
        commodityDAO.delete(id);
    }
}