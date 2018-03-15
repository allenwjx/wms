package com.zeh.wms.biz.service;

import java.util.Collection;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.FreightVO;
import com.zeh.wms.biz.model.enums.StateEnum;

/**
 * @author allen
 * @create $ ID: FreightService, 18/2/9 00:11 allen Exp $
 * @since 1.0.0
 */
public interface FreightService {
    /**
     * 创建运价
     *
     * @param freight 运价
     * @throws ServiceException 创建运价异常
     */
    void createFreight(FreightVO freight) throws ServiceException;

    /**
     * 更新运价信息
     *
     * @param freight 运价
     * @throws ServiceException 运价更新异常
     */
    void updateFreight(FreightVO freight) throws ServiceException;

    /**
     * 根据运价ID查询运价信息
     *
     * @param id 运价ID
     * @return 运价信息
     * @throws ServiceException 运价查询异常
     */
    FreightVO findFreightById(long id) throws ServiceException;

    /**
     * 分页查询运价信息
     *
     * @param freight 运价查询条件
     * @param currentPage 查询起始页
     * @param size 每页数量
     * @return 运价信息
     * @throws ServiceException 分页查询异常
     */
    PageList<FreightVO> pageQueryFreights(FreightVO freight, int currentPage, int size) throws ServiceException;

    /**
     * 查询所有运价信息
     *
     * @return 所有运价信息
     * @throws ServiceException 运价查询异常
     */
    Collection<FreightVO> findAllFreights() throws ServiceException;

    /**
     * 查询一家物流公司某个身份的运价
     * 
     * @param expressCode
     * @param provinceName
     * @return
     */
    FreightVO queryFreightByExpressProvince(String expressCode, String provinceName);

    /**
     * 更新运价启用、禁用状态
     *
     * @param id       运价ID
     * @param modifyBy 修改人
     * @param enabled  状态
     * @throws ServiceException 运价状态更新异常
     */
    void updateFreightState(long id, String modifyBy, StateEnum enabled) throws ServiceException;
}
