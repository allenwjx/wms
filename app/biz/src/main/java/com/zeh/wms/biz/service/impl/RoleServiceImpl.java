package com.zeh.wms.biz.service.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageUtils;
import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.RoleMapper;
import com.zeh.wms.biz.model.AuthorizationVO;
import com.zeh.wms.biz.model.RoleVO;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.service.AuthorizationService;
import com.zeh.wms.biz.service.RoleService;
import com.zeh.wms.dal.daointerface.RoleAuthorizationLinkDAO;
import com.zeh.wms.dal.daointerface.RoleDAO;
import com.zeh.wms.dal.dataobject.RoleAuthorizationLinkDO;
import com.zeh.wms.dal.dataobject.RoleDO;
import com.zeh.wms.dal.operation.role.QueryByPageQuery;

/**
 * @author allen
 * @create $ ID: RoleServiceImpl, 18/2/23 15:35 allen Exp $
 * @since 1.0.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    /** 错误工厂 */
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();
    /** Role Dao */
    @Resource
    private RoleDAO                      roleDAO;
    /** Role relation Dao */
    @Resource
    private RoleAuthorizationLinkDAO     roleAuthorizationLinkDAO;
    /** Authorization Service */
    @Resource
    private AuthorizationService         authorizationService;
    /** Role mapper */
    @Resource
    private RoleMapper                   mapper;

    /**
     * 创建角色
     *
     * @param role 角色
     * @throws ServiceException 创建角色异常
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createRole(RoleVO role) throws ServiceException {
        if (role == null) {
            throw new ServiceException(ERROR_FACTORY.createRoleError());
        }
        RoleDO roleDO = roleDAO.queryByName(role.getName());
        if (roleDO != null) {
            throw new ServiceException(ERROR_FACTORY.roleExistError(role.getName()));
        }

        // create role
        role.setEnabled(StateEnum.Y);
        roleDO = mapper.vo2do(role);
        long roleId = roleDAO.insert(roleDO);

        // create role authorization relations
        Collection<AuthorizationVO> authorizations = role.getAuthorizations();
        if (CollectionUtils.isNotEmpty(authorizations)) {
            for (AuthorizationVO authorization : authorizations) {
                RoleAuthorizationLinkDO rado = new RoleAuthorizationLinkDO();
                rado.setRoleId(roleId);
                rado.setAuthId(authorization.getId());
                roleAuthorizationLinkDAO.insert(rado);
            }
        }
    }

    /**
     * 更新角色信息
     *
     * @param role 角色
     * @throws ServiceException 角色更新异常
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRole(RoleVO role) throws ServiceException {
        if (role == null || role.getId() <= 0) {
            throw new ServiceException(ERROR_FACTORY.updateRoleError());
        }

        // update role
        RoleDO roleDO;
        if (StringUtils.isNotBlank(role.getName())) {
            roleDO = roleDAO.queryByName(role.getName());
            if (roleDO != null && role.getId() != roleDO.getId()) {
                throw new ServiceException(ERROR_FACTORY.roleExistError(role.getName()));
            }
        }
        roleDO = roleDAO.queryById(role.getId());
        roleDO.setModifyBy(role.getModifyBy());
        roleDO.setName(StringUtils.isNotBlank(role.getName()) ? role.getName() : roleDO.getName());
        roleDO.setEnabled(role.getEnabled() != null ? role.getEnabled().getCode() : roleDO.getEnabled());
        roleDAO.update(roleDO);

        // update role authorization relation
        Collection<AuthorizationVO> authorizations = role.getAuthorizations();
        roleAuthorizationLinkDAO.deleteByRoleId(role.getId());
        if (CollectionUtils.isNotEmpty(authorizations)) {
            for (AuthorizationVO authorization : authorizations) {
                RoleAuthorizationLinkDO rado = new RoleAuthorizationLinkDO();
                rado.setAuthId(authorization.getId());
                rado.setRoleId(role.getId());
                roleAuthorizationLinkDAO.insert(rado);
            }
        }
    }

    /**
     * 根据角色ID查询角色信息
     *
     * @param id 角色ID
     * @return 角色信息
     * @throws ServiceException 角色查询异常
     */
    @Override
    public RoleVO findRoleById(long id) throws ServiceException {
        RoleDO roleDO = roleDAO.queryById(id);
        return mapper.do2vo(roleDO);
    }

    /**
     * 根据角色ID集合查询角色信息
     *
     * @param roleIds 角色ID集合
     * @return 角色信息集合
     * @throws ServiceException 角色查询异常
     */
    @Override
    public Collection<RoleVO> findRoleByIds(List<Long> roleIds) throws ServiceException {
        if (CollectionUtils.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        List<RoleDO> roleDOs = roleDAO.queryByIds(roleIds, StateEnum.Y.getCode());
        return mapper.do2vos(roleDOs);
    }

    /**
     * 根据角色ID查询角色信息，含角色关联的权限信息
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    @Override
    public RoleVO findRoleDetailsById(long id) throws ServiceException {
        RoleDO roleDO = roleDAO.queryById(id);
        RoleVO role = mapper.do2vo(roleDO);
        List<Long> authIds = roleAuthorizationLinkDAO.queryByRoleId(role.getId());
        Collection<AuthorizationVO> authorizations = authorizationService.findAuthorizationByIds(authIds);
        role.setAuthorizations(authorizations);
        return role;
    }

    /**
     * 分页查询角色信息
     *
     * @param role        角色查询条件
     * @param currentPage 查询起始页
     * @param size        每页数量
     * @return 角色信息
     * @throws ServiceException 分页查询异常
     */
    @Override
    public PageList<RoleVO> pageQueryRoles(RoleVO role, int currentPage, int size) throws ServiceException {
        if (role == null) {
            throw new ServiceException(ERROR_FACTORY.queryRoleError());
        }
        QueryByPageQuery query = new QueryByPageQuery();
        query.setEnabled(role.getEnabled() == null ? null : role.getEnabled().getCode());
        query.setName(role.getName());
        query.setPage(currentPage);
        query.setPageSize(size);
        PageList<RoleDO> ret = roleDAO.queryByPage(query);
        Collection<RoleVO> roles = mapper.do2vos(ret.getData());
        return PageUtils.createPageList(roles, ret.getPaginator());
    }

    /**
     * 查询所有有效角色信息
     *
     * @return 所有角色信息
     * @throws ServiceException 角色查询异常
     */
    @Override
    public Collection<RoleVO> findAllRoles() throws ServiceException {
        List<RoleDO> roleDOs = roleDAO.queryAllEnabled();
        return mapper.do2vos(roleDOs);
    }

    /**
     * 更新角色启用、禁用状态
     *
     * @param id       角色ID
     * @param modifyBy 修改人
     * @param enabled  状态
     * @throws ServiceException 角色状态更新异常
     */
    @Override
    public void updateRoleState(long id, String modifyBy, StateEnum enabled) throws ServiceException {
        RoleDO roleDO = roleDAO.queryById(id);
        roleDO.setModifyBy(modifyBy);
        roleDO.setEnabled(enabled.getCode());
        roleDAO.update(roleDO);
    }
}