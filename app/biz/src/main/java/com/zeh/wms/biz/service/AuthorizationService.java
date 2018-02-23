package com.zeh.wms.biz.service;

import java.util.Collection;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.AuthorizationVO;
import com.zeh.wms.biz.model.enums.StateEnum;

/**
 * @author allen
 * @create $ ID: AuthorizationService, 18/2/23 13:44 allen Exp $
 * @since 1.0.0
 */
public interface AuthorizationService {
    /**
     * 创建资源权限
     *
     * @param authorization 资源权限
     * @throws ServiceException 创建资源权限异常
     */
    void createAuthorization(AuthorizationVO authorization) throws ServiceException;

    /**
     * 更新资源权限信息
     *
     * @param authorization 资源权限
     * @throws ServiceException 资源权限更新异常
     */
    void updateAuthorization(AuthorizationVO authorization) throws ServiceException;

    /**
     * 根据资源权限ID查询资源权限信息
     *
     * @param id 资源权限ID
     * @return 资源权限信息
     * @throws ServiceException 资源权限查询异常
     */
    AuthorizationVO findAuthorizationById(long id) throws ServiceException;

    /**
     * 分页查询资源权限信息
     * @param authorization 资源权限查询条件
     * @param currentPage 查询起始页
     * @param size 每页数量
     * @return 资源权限信息
     * @throws ServiceException 分页查询异常
     */
    PageList<AuthorizationVO> pageQueryAuthorizations(AuthorizationVO authorization, int currentPage, int size) throws ServiceException;

    /**
     * 查询所有有效资源权限信息
     *
     * @return 所有资源权限信息
     * @throws ServiceException 资源权限查询异常
     */
    Collection<AuthorizationVO> findAllAuthorizations() throws ServiceException;

    /**
     * 更新资源权限启用、禁用状态
     *
     * @param id       资源权限ID
     * @param modifyBy 修改人
     * @param enabled  状态
     * @throws ServiceException 资源权限状态更新异常
     */
    void updateAuthorizationState(long id, String modifyBy, StateEnum enabled) throws ServiceException;
}
