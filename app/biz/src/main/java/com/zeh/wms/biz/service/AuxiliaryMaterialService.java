package com.zeh.wms.biz.service;

import java.util.Collection;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.AuxiliaryMaterialVO;
import com.zeh.wms.biz.model.enums.StateEnum;

/**
 * @author allen
 * @create $ ID: AuxiliaryMaterialService, 18/2/9 11:19 allen Exp $
 * @since 1.0.0
 */
public interface AuxiliaryMaterialService {
    /**
     * 创建辅材
     *
     * @param auxiliaryMaterial 商品
     * @throws ServiceException 创建商品异常
     */
    void createAuxiliaryMaterials(AuxiliaryMaterialVO auxiliaryMaterial) throws ServiceException;

    /**
     * 更新辅材信息
     *
     * @param auxiliaryMaterial 辅材
     * @throws ServiceException 辅材更新异常
     */
    void updateAuxiliaryMaterials(AuxiliaryMaterialVO auxiliaryMaterial) throws ServiceException;

    /**
     * 根据辅材ID查询辅材信息
     *
     * @param id 辅材ID
     * @return 辅材信息
     * @throws ServiceException 辅材查询异常
     */
    AuxiliaryMaterialVO findAuxiliaryMaterialsById(long id) throws ServiceException;

    /**
     * 根据辅材ID查询启用的辅材信息
     *
     * @param id 辅材ID
     * @return 辅材信息
     * @throws ServiceException 辅材查询异常
     */
    AuxiliaryMaterialVO findEnabledAuxiliaryMaterialsById(long id) throws ServiceException;

    /**
     * 分页查询辅材信息
     * @param auxiliaryMaterial 辅材查询条件
     * @param currentPage 查询起始页
     * @param size 每页数量
     * @return 辅材信息
     * @throws ServiceException 分页查询异常
     */
    PageList<AuxiliaryMaterialVO> pageQueryAuxiliaryMaterials(AuxiliaryMaterialVO auxiliaryMaterial, int currentPage, int size) throws ServiceException;

    /**
     * 查询所有辅材信息
     *
     * @return 所有辅材信息
     * @throws ServiceException 辅材查询异常
     */
    Collection<AuxiliaryMaterialVO> findAllAuxiliaryMaterials() throws ServiceException;

    /**
     * 查询商品关联的所有辅材信息
     *
     * @param commodityId 商品ID
     * @return 商品关联的辅材信息
     * @throws ServiceException 辅材查询异常
     */
    AuxiliaryMaterialVO findByCommodityId(Long commodityId) throws ServiceException;

    /**
     * 更新辅材启用、禁用状态
     *
     * @param id       辅材ID
     * @param modifyBy 修改人
     * @param enabled  状态
     * @throws ServiceException 辅材状态更新异常
     */
    void updateAuxiliaryMaterialsState(long id, String modifyBy, StateEnum enabled) throws ServiceException;
}
