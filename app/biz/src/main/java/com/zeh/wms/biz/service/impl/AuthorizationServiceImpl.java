package com.zeh.wms.biz.service.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageUtils;
import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.AuthorizationMapper;
import com.zeh.wms.biz.model.AuthorizationVO;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.service.AuthorizationService;
import com.zeh.wms.biz.utils.CodeGenerator;
import com.zeh.wms.dal.daointerface.AuthorizationDAO;
import com.zeh.wms.dal.dataobject.AuthorizationDO;
import com.zeh.wms.dal.operation.authorization.QueryByPageQuery;

/**
 * @author allen
 * @create $ ID: AuthorizationServiceImpl, 18/2/23 13:47 allen Exp $
 * @since 1.0.0
 */
@Service("authorizationService")
public class AuthorizationServiceImpl implements AuthorizationService {
    /** 错误工厂 */
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();
    /** AuthorizationMapper */
    @Resource
    private AuthorizationMapper          mapper;
    /** 资源权限DAO */
    @Resource
    private AuthorizationDAO             authorizationDAO;

    /**
     * 创建资源权限
     *
     * @param authorization 资源权限
     * @throws ServiceException 创建资源权限异常
     */
    @Override
    public void createAuthorization(AuthorizationVO authorization) throws ServiceException {
        if (authorization == null) {
            throw new ServiceException(ERROR_FACTORY.createAuthorizatonError());
        }
        AuthorizationDO ado = authorizationDAO.queryByName(authorization.getName());
        if (ado != null) {
            throw new ServiceException(ERROR_FACTORY.authorizatonNameExistError(authorization.getName()));
        }
        ado = authorizationDAO.queryByPath(authorization.getPath());
        if (ado != null) {
            throw new ServiceException(ERROR_FACTORY.authorizationResourceExistError(authorization.getPath()));
        }
        authorization.setCode(CodeGenerator.generateAuthCode());
        authorization.setEnabled(StateEnum.Y);
        AuthorizationDO authorizationDO = mapper.vo2do(authorization);
        authorizationDAO.insert(authorizationDO);
    }

    /**
     * 更新资源权限信息
     *
     * @param authorization 资源权限
     * @throws ServiceException 资源权限更新异常
     */
    @Override
    public void updateAuthorization(AuthorizationVO authorization) throws ServiceException {
        if (authorization == null || authorization.getId() <= 0) {
            throw new ServiceException(ERROR_FACTORY.updateAuthorizatonError());
        }

        AuthorizationDO authorizationDO;
        if (StringUtils.isNotBlank(authorization.getName())) {
            authorizationDO = authorizationDAO.queryByName(authorization.getName());
            if (authorizationDO != null && authorization.getId() != authorizationDO.getId()) {
                throw new ServiceException(ERROR_FACTORY.authorizatonNameExistError(authorization.getName()));
            }
        }
        if (StringUtils.isNotBlank(authorization.getPath())) {
            authorizationDO = authorizationDAO.queryByPath(authorization.getPath());
            if (authorizationDO != null && authorization.getId() != authorizationDO.getId()) {
                throw new ServiceException(ERROR_FACTORY.authorizationResourceExistError(authorization.getPath()));
            }
        }

        authorizationDO = authorizationDAO.queryById(authorization.getId());
        authorizationDO.setCode(StringUtils.isNotBlank(authorization.getCode()) ? authorization.getCode() : authorizationDO.getCode());
        authorizationDO.setPath(StringUtils.isNotBlank(authorization.getPath()) ? authorization.getPath() : authorizationDO.getPath());
        authorizationDO.setModifyBy(authorization.getModifyBy());
        authorizationDO.setName(StringUtils.isNotBlank(authorization.getName()) ? authorization.getName() : authorizationDO.getName());
        authorizationDO.setEnabled(authorization.getEnabled() != null ? authorization.getEnabled().getCode() : authorizationDO.getEnabled());
        authorizationDAO.update(authorizationDO);
    }

    /**
     * 根据资源权限ID查询资源权限信息
     *
     * @param id 资源权限ID
     * @return 资源权限信息
     * @throws ServiceException 资源权限查询异常
     */
    @Override
    public AuthorizationVO findAuthorizationById(long id) throws ServiceException {
        AuthorizationDO authorizationDO = authorizationDAO.queryById(id);
        return mapper.do2vo(authorizationDO);
    }

    /**
     * 根据资源权限ID查询资源权限信息
     *
     * @param authIds 资源权限ID集合
     * @return 资源权限信息
     * @throws ServiceException 资源权限查询异常
     */
    @Override
    public Collection<AuthorizationVO> findAuthorizationByIds(List<Long> authIds) throws ServiceException {
        if (CollectionUtils.isEmpty(authIds)) {
            return Lists.newArrayList();
        }
        List<AuthorizationDO> authorizationDOs = authorizationDAO.queryByIds(authIds, StateEnum.Y.getCode());
        return mapper.do2vos(authorizationDOs);
    }

    /**
     * 分页查询资源权限信息
     *
     * @param authorization 资源权限查询条件
     * @param currentPage   查询起始页
     * @param size          每页数量
     * @return 资源权限信息
     * @throws ServiceException 分页查询异常
     */
    @Override
    public PageList<AuthorizationVO> pageQueryAuthorizations(AuthorizationVO authorization, int currentPage, int size) throws ServiceException {
        if (authorization == null) {
            throw new ServiceException(ERROR_FACTORY.queryAuthorizatonError());
        }
        QueryByPageQuery query = new QueryByPageQuery();
        query.setEnabled(authorization.getEnabled() == null ? null : authorization.getEnabled().getCode());
        query.setName(authorization.getName());
        query.setCode(authorization.getCode());
        query.setPath(authorization.getPath());
        query.setPage(currentPage);
        query.setPageSize(size);
        PageList<AuthorizationDO> ret = authorizationDAO.queryByPage(query);
        Collection<AuthorizationVO> commodities = mapper.do2vos(ret.getData());
        return PageUtils.createPageList(commodities, ret.getPaginator());
    }

    /**
     * 查询所有有效资源权限信息
     *
     * @return 所有资源权限信息
     * @throws ServiceException 资源权限查询异常
     */
    @Override
    public Collection<AuthorizationVO> findAllAuthorizations() throws ServiceException {
        List<AuthorizationDO> authorizationDOs = authorizationDAO.queryAllEnabled();
        return mapper.do2vos(authorizationDOs);
    }

    /**
     * 更新资源权限启用、禁用状态
     *
     * @param id       资源权限ID
     * @param modifyBy 修改人
     * @param enabled  状态
     * @throws ServiceException 资源权限状态更新异常
     */
    @Override
    public void updateAuthorizationState(long id, String modifyBy, StateEnum enabled) throws ServiceException {
        AuthorizationDO authorizationDO = authorizationDAO.queryById(id);
        authorizationDO.setModifyBy(modifyBy);
        authorizationDO.setEnabled(enabled.getCode());
        authorizationDAO.update(authorizationDO);
    }

    /**
     * 根据资源查找权限信息
     *
     * @param resource 资源
     * @return 资源权限
     * @throws ServiceException 资源权限查询异常
     */
    @Override
    public AuthorizationVO findAuthorizationByResource(String resource) throws ServiceException {
        if (StringUtils.isBlank(resource)) {
            throw new IllegalArgumentException("Resource cannot be null");
        }
        AuthorizationDO authorizationDO = authorizationDAO.queryByPath(resource);
        if (authorizationDO == null) {
            throw new ServiceException(ERROR_FACTORY.authNotFoundByResourceError(resource));
        }
        return mapper.do2vo(authorizationDO);
    }
}