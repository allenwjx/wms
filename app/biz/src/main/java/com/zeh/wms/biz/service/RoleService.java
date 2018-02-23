package com.zeh.wms.biz.service;

import java.util.Collection;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.RoleVO;
import com.zeh.wms.biz.model.enums.StateEnum;

/**
 * @author allen
 * @create $ ID: RoleService, 18/2/23 15:33 allen Exp $
 * @since 1.0.0
 */
public interface RoleService {
    /**
     * 创建角色
     *
     * @param role 角色
     * @throws ServiceException 创建角色异常
     */
    void createRole(RoleVO role) throws ServiceException;

    /**
     * 更新角色信息
     *
     * @param role 角色
     * @throws ServiceException 角色更新异常
     */
    void updateRole(RoleVO role) throws ServiceException;

    /**
     * 根据角色ID查询角色信息
     *
     * @param id 角色ID
     * @return 角色信息
     * @throws ServiceException 角色查询异常
     */
    RoleVO findRoleById(long id) throws ServiceException;

    /**
     * 根据角色ID查询角色信息，含角色关联的权限信息
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    RoleVO findRoleDetailsById(long id) throws ServiceException;

    /**
     * 分页查询角色信息
     * @param role 角色查询条件
     * @param currentPage 查询起始页
     * @param size 每页数量
     * @return 角色信息
     * @throws ServiceException 分页查询异常
     */
    PageList<RoleVO> pageQueryRoles(RoleVO role, int currentPage, int size) throws ServiceException;

    /**
     * 查询所有有效角色信息
     *
     * @return 所有角色信息
     * @throws ServiceException 角色查询异常
     */
    Collection<RoleVO> findAllRoles() throws ServiceException;

    /**
     * 更新角色启用、禁用状态
     *
     * @param id       角色ID
     * @param modifyBy 修改人
     * @param enabled  状态
     * @throws ServiceException 角色状态更新异常
     */
    void updateRoleState(long id, String modifyBy, StateEnum enabled) throws ServiceException;
}
