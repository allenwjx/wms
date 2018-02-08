package com.zeh.wms.biz.service;

import java.util.Collection;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.CommodityVO;
import com.zeh.wms.biz.model.enums.StateEnum;

/**
 * @author allen
 * @create $ ID: CommodityService, 18/2/8 18:14 allen Exp $
 * @since 1.0.0
 */
public interface CommodityService {

    /**
     * 创建商品
     *
     * @param commodity 商品
     * @throws ServiceException 创建商品异常
     */
    void createCommodity(CommodityVO commodity) throws ServiceException;

    /**
     * 更新商品信息
     *
     * @param commodity 商品
     * @throws ServiceException 商品更新异常
     */
    void updateCommodity(CommodityVO commodity) throws ServiceException;

    /**
     * 根据商品编号查询商品信息
     *
     * @param code 商品编号
     * @return 商品信息
     * @throws ServiceException 商品查询异常
     */
    CommodityVO findCommodityByCode(String code) throws ServiceException;

    /**
     * 根据商品ID查询商品信息
     *
     * @param id 商品ID
     * @return 商品信息
     * @throws ServiceException 商品查询异常
     */
    CommodityVO findCommodityById(long id) throws ServiceException;

    /**
     * 分页查询商品信息
     * @param commodity 商品查询条件
     * @param currentPage 查询起始页
     * @param size 每页数量
     * @return 商品信息
     * @throws ServiceException 分页查询异常
     */
    PageList<CommodityVO> pageQueryCommodities(CommodityVO commodity, int currentPage, int size) throws ServiceException;

    /**
     * 查询所有商品信息
     *
     * @return 所有商品信息
     * @throws ServiceException 商品查询异常
     */
    Collection<CommodityVO> findAllCommodities() throws ServiceException;

    /**
     * 查询厂商所有商品信息
     *
     * @param manufacturerId 厂商ID
     * @return 厂商所有商品信息
     * @throws ServiceException 商品查询异常
     */
    Collection<CommodityVO> findByManufacturer(Long manufacturerId) throws ServiceException;

    /**
     * 更新商品启用、禁用状态
     *
     * @param id       商品ID
     * @param modifyBy 修改人
     * @param enabled  状态
     * @throws ServiceException 商品状态更新异常
     */
    void updateCommodityState(long id, String modifyBy, StateEnum enabled) throws ServiceException;

    /**
     * 删除商品信息，不能恢复
     *
     * @param id 商品ID
     * @throws ServiceException 商品删除异常
     */
    void deleteCommodity(long id) throws ServiceException;
}
