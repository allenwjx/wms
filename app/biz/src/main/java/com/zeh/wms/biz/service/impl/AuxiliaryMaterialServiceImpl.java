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
import com.zeh.wms.biz.mapper.AuxiliaryMaterialMapper;
import com.zeh.wms.biz.model.AuxiliaryMaterialVO;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.service.AuxiliaryMaterialService;
import com.zeh.wms.dal.daointerface.AuxiliaryMaterialDAO;
import com.zeh.wms.dal.dataobject.AuxiliaryMaterialDO;
import com.zeh.wms.dal.operation.auxiliarymaterial.QueryByPageQuery;

/**
 * @author allen
 * @create $ ID: AuxiliaryMaterialServiceImpl, 18/2/9 11:24 allen Exp $
 * @since 1.0.0
 */
@Service
public class AuxiliaryMaterialServiceImpl implements AuxiliaryMaterialService {
    /** 错误工厂 */
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();
    /** Auxiliary Material mapper */
    @Resource
    private AuxiliaryMaterialMapper      mapper;
    /** 辅材DAO */
    @Resource
    private AuxiliaryMaterialDAO         auxiliaryMaterialDAO;

    /**
     * 创建辅材
     *
     * @param auxiliaryMaterial 商品
     * @throws ServiceException 创建商品异常
     */
    @Override
    public void createAuxiliaryMaterials(AuxiliaryMaterialVO auxiliaryMaterial) throws ServiceException {
        if (auxiliaryMaterial == null) {
            throw new ServiceException(ERROR_FACTORY.createAuxiliaryError());
        }
        AuxiliaryMaterialDO exist = auxiliaryMaterialDAO.queryByCommodityId(auxiliaryMaterial.getCommodityId());
        if (exist != null) {
            throw new ServiceException(ERROR_FACTORY.auxiliaryExistError(exist.getCommodityId(), exist.getId()));
        }
        auxiliaryMaterial.setEnabled(StateEnum.Y);
        AuxiliaryMaterialDO auxiliaryMaterialDO = mapper.vo2do(auxiliaryMaterial);
        auxiliaryMaterialDAO.insert(auxiliaryMaterialDO);
    }

    /**
     * 更新辅材信息
     *
     * @param auxiliaryMaterial 辅材
     * @throws ServiceException 辅材更新异常
     */
    @Override
    public void updateAuxiliaryMaterials(AuxiliaryMaterialVO auxiliaryMaterial) throws ServiceException {
        if (auxiliaryMaterial == null || auxiliaryMaterial.getId() <= 0) {
            throw new ServiceException(ERROR_FACTORY.updateAuxiliaryError());
        }

        if (auxiliaryMaterial.getCommodityId() != null) {
            AuxiliaryMaterialDO exist = auxiliaryMaterialDAO.queryByCommodityId(auxiliaryMaterial.getCommodityId());
            if (exist != null && exist.getId() != auxiliaryMaterial.getId()) {
                throw new ServiceException(ERROR_FACTORY.auxiliaryExistError(exist.getCommodityId(), exist.getId()));
            }
        }

        AuxiliaryMaterialDO auxiliaryMaterialDO = auxiliaryMaterialDAO.queryById(auxiliaryMaterial.getId());
        auxiliaryMaterialDO.setCommodityId(auxiliaryMaterial.getCommodityId() != null ? auxiliaryMaterial.getCommodityId() : auxiliaryMaterialDO.getCommodityId());
        auxiliaryMaterialDO.setName(StringUtils.isNotBlank(auxiliaryMaterial.getName()) ? auxiliaryMaterial.getName() : auxiliaryMaterialDO.getName());
        auxiliaryMaterialDO.setPrice(auxiliaryMaterial.getPrice() != null ? auxiliaryMaterial.getPrice() : auxiliaryMaterialDO.getPrice());
        auxiliaryMaterialDO.setQuantity(auxiliaryMaterial.getQuantity() != null ? auxiliaryMaterial.getQuantity() : auxiliaryMaterialDO.getQuantity());
        auxiliaryMaterialDO.setModifyBy(auxiliaryMaterial.getModifyBy());
        auxiliaryMaterialDO.setEnabled(auxiliaryMaterial.getEnabled() != null ? auxiliaryMaterial.getEnabled().getCode() : auxiliaryMaterialDO.getEnabled());
        auxiliaryMaterialDAO.update(auxiliaryMaterialDO);
    }

    /**
     * 根据辅材ID查询辅材信息
     *
     * @param id 辅材ID
     * @return 辅材信息
     * @throws ServiceException 辅材查询异常
     */
    @Override
    public AuxiliaryMaterialVO findAuxiliaryMaterialsById(long id) throws ServiceException {
        AuxiliaryMaterialDO auxiliaryMaterial = auxiliaryMaterialDAO.queryById(id);
        return mapper.do2vo(auxiliaryMaterial);
    }

    /**
     * 根据辅材ID查询启用的辅材信息
     *
     * @param id 辅材ID
     * @return 辅材信息
     * @throws ServiceException 辅材查询异常
     */
    @Override
    public AuxiliaryMaterialVO findEnabledAuxiliaryMaterialsById(long id) throws ServiceException {
        AuxiliaryMaterialDO auxiliaryMaterial = auxiliaryMaterialDAO.queryEnabledById(id);
        return mapper.do2vo(auxiliaryMaterial);
    }

    /**
     * 分页查询辅材信息
     *
     * @param auxiliaryMaterial 辅材查询条件
     * @param currentPage       查询起始页
     * @param size              每页数量
     * @return 辅材信息
     * @throws ServiceException 分页查询异常
     */
    @Override
    public PageList<AuxiliaryMaterialVO> pageQueryAuxiliaryMaterials(AuxiliaryMaterialVO auxiliaryMaterial, int currentPage, int size) throws ServiceException {
        if (auxiliaryMaterial == null) {
            throw new ServiceException(ERROR_FACTORY.queryAuxiliaryError());
        }
        QueryByPageQuery query = new QueryByPageQuery();
        query.setCommodityId(auxiliaryMaterial.getCommodityId() != null ? auxiliaryMaterial.getCommodityId() : null);
        query.setName(StringUtils.isNotBlank(auxiliaryMaterial.getName()) ? auxiliaryMaterial.getName() : null);
        query.setEnabled(auxiliaryMaterial.getEnabled() == null ? null : auxiliaryMaterial.getEnabled().getCode());
        query.setPage(currentPage);
        query.setPageSize(size);
        PageList<AuxiliaryMaterialDO> ret = auxiliaryMaterialDAO.queryByPage(query);
        Collection<AuxiliaryMaterialVO> auxiliaryMaterials = mapper.do2vos(ret.getData());
        return PageUtils.createPageList(auxiliaryMaterials, ret.getPaginator());
    }

    /**
     * 查询所有辅材信息
     *
     * @return 所有辅材信息
     * @throws ServiceException 辅材查询异常
     */
    @Override
    public Collection<AuxiliaryMaterialVO> findAllAuxiliaryMaterials() throws ServiceException {
        List<AuxiliaryMaterialDO> auxiliaryMaterials = auxiliaryMaterialDAO.queryAllEnabled();
        return mapper.do2vos(auxiliaryMaterials);
    }

    /**
     * 查询商品关联的所有辅材信息
     *
     * @param commodityId 商品ID
     * @return 商品关联的辅材信息
     * @throws ServiceException 辅材查询异常
     */
    @Override
    public AuxiliaryMaterialVO findByCommodityId(Long commodityId) throws ServiceException {
        AuxiliaryMaterialDO auxiliaryMaterial = auxiliaryMaterialDAO.queryByCommodityId(commodityId);
        return mapper.do2vo(auxiliaryMaterial);
    }

    /**
     * 更新辅材启用、禁用状态
     *
     * @param id       辅材ID
     * @param modifyBy 修改人
     * @param enabled  状态
     * @throws ServiceException 辅材状态更新异常
     */
    @Override
    public void updateAuxiliaryMaterialsState(long id, String modifyBy, StateEnum enabled) throws ServiceException {
        AuxiliaryMaterialVO auxiliaryMaterial = new AuxiliaryMaterialVO();
        auxiliaryMaterial.setId(id);
        auxiliaryMaterial.setModifyBy(modifyBy);
        auxiliaryMaterial.setEnabled(enabled);
        updateAuxiliaryMaterials(auxiliaryMaterial);
    }
}