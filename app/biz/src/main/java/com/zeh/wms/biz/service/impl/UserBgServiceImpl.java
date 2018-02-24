package com.zeh.wms.biz.service.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageUtils;
import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.UserBgMapper;
import com.zeh.wms.biz.model.RoleVO;
import com.zeh.wms.biz.model.UserBgVO;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.model.enums.UserTypeEnum;
import com.zeh.wms.biz.service.RoleService;
import com.zeh.wms.biz.service.UserBgService;
import com.zeh.wms.dal.daointerface.UserBgDAO;
import com.zeh.wms.dal.daointerface.UserRoleLinkDAO;
import com.zeh.wms.dal.dataobject.UserBgDO;
import com.zeh.wms.dal.dataobject.UserRoleLinkDO;
import com.zeh.wms.dal.operation.userbg.QueryByPageQuery;

/**
 * @author allen
 * @create $ ID: UserBgServiceImpl, 18/2/11 14:58 allen Exp $
 * @since 1.0.0
 */
@Service("userBgService")
public class UserBgServiceImpl implements UserBgService {
    /** 错误工厂 */
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();
    /** 后台用户mapper */
    @Resource
    private UserBgMapper                 mapper;
    /** 后台用户DAO */
    @Resource
    private UserBgDAO                    userBgDAO;
    /** User Role Relation Dao */
    @Resource
    private UserRoleLinkDAO              userRoleLinkDAO;
    /** 密码加解密服务 */
    @Resource
    private PasswordEncoder              passwordEncoder;
    /** 角色服务 */
    @Resource
    private RoleService                  roleService;

    /**
     * 创建后台用户
     *
     * @param userBg 后台用户
     * @throws ServiceException 创建后台用户异常
     */
    @Override
    public void createUserBg(UserBgVO userBg) throws ServiceException {
        if (userBg == null) {
            throw new ServiceException(ERROR_FACTORY.createUserBgError());
        }
        UserBgDO existUserBg = userBgDAO.queryByUsername(userBg.getUsername());
        if (existUserBg != null) {
            throw new ServiceException(ERROR_FACTORY.userBgNameExistError(userBg.getUsername()));
        }
        String encodedPassword = passwordEncoder.encode(userBg.getPassword());
        userBg.setPassword(encodedPassword);
        userBg.setEnabled(StateEnum.Y);
        UserBgDO userBgDO = mapper.vo2do(userBg);
        userBgDAO.insert(userBgDO);
    }

    /**
     * 更新后台用户信息
     *
     * @param userBg 后台用户
     * @throws ServiceException 后台用户更新异常
     */
    @Override
    public void updateUserBg(UserBgVO userBg) throws ServiceException {
        if (userBg == null || userBg.getId() <= 0) {
            throw new ServiceException(ERROR_FACTORY.updateUserBgError());
        }
        UserBgDO userBgDO = userBgDAO.queryById(userBg.getId());
        if (StringUtils.isNotBlank(userBg.getPassword())) {
            String encodedPassword = passwordEncoder.encode(userBg.getPassword());
            userBgDO.setPassword(encodedPassword);
        }
        userBgDO.setEnabled(userBg.getEnabled() != null ? userBg.getEnabled().getCode() : userBgDO.getEnabled());
        userBgDO.setModifyBy(userBg.getModifyBy());
        userBgDAO.update(userBgDO);
    }

    /**
     * 设置用户角色
     *
     * @param userBg 后台用户
     * @throws ServiceException 后台用户设置异常
     */
    @Override
    public void updateUserRoles(UserBgVO userBg) throws ServiceException {
        if (userBg == null || userBg.getId() <= 0) {
            throw new ServiceException(ERROR_FACTORY.updateUserBgError());
        }

        // update role
        UserBgDO userBgDO;
        userBgDO = userBgDAO.queryById(userBg.getId());
        userBgDO.setModifyBy(userBg.getModifyBy());
        userBgDAO.update(userBgDO);

        // update role authorization relation
        Collection<RoleVO> roles = userBg.getRoles();
        userRoleLinkDAO.deleteByUserId(userBg.getId());
        if (CollectionUtils.isNotEmpty(roles)) {
            for (RoleVO role : roles) {
                UserRoleLinkDO urld = new UserRoleLinkDO();
                urld.setUserId(userBg.getId());
                urld.setRoleId(role.getId());
                urld.setType(UserTypeEnum.B.getCode());
                userRoleLinkDAO.insert(urld);
            }
        }
    }

    /**
     * 根据后台用户编号查询后台用户信息
     *
     * @param username 后台用户名
     * @return 后台用户信息
     * @throws ServiceException 后台用户查询异常
     */
    @Override
    public UserBgVO findUserBgByUsername(String username) throws ServiceException {
        if (StringUtils.isBlank(username)) {
            throw new ServiceException(ERROR_FACTORY.queryUserBgError());
        }
        UserBgDO userBgDO = userBgDAO.queryByUsername(username);
        if (userBgDO == null) {
            throw new ServiceException(ERROR_FACTORY.usernameInvalid());
        }
        return mapper.do2vo(userBgDO);
    }

    /**
     * 根据后台用户ID查询后台用户信息
     *
     * @param id 后台用户ID
     * @return 后台用户信息
     * @throws ServiceException 后台用户查询异常
     */
    @Override
    public UserBgVO findUserBgById(long id) throws ServiceException {
        UserBgDO userBgDO = userBgDAO.queryById(id);
        return mapper.do2vo(userBgDO);
    }

    /**
     * 分页查询后台用户信息
     *
     * @param userBg      后台用户查询条件
     * @param currentPage 查询起始页
     * @param size        每页数量
     * @return 后台用户信息
     * @throws ServiceException 分页查询异常
     */
    @Override
    public PageList<UserBgVO> pageQueryUserBgs(UserBgVO userBg, int currentPage, int size) throws ServiceException {
        if (userBg == null) {
            throw new ServiceException(ERROR_FACTORY.queryUserBgError());
        }
        QueryByPageQuery query = new QueryByPageQuery();
        query.setUsername(userBg.getUsername());
        query.setEnabled(userBg.getEnabled() == null ? null : userBg.getEnabled().getCode());
        query.setPage(currentPage);
        query.setPageSize(size);
        PageList<UserBgDO> ret = userBgDAO.queryByPage(query);
        Collection<UserBgVO> userBgDOs = mapper.do2vos(ret.getData());
        return PageUtils.createPageList(userBgDOs, ret.getPaginator());
    }

    /**
     * 更新后台用户启用、禁用状态
     *
     * @param id       后台用户ID
     * @param modifyBy 修改人
     * @param enabled  状态
     * @throws ServiceException 后台用户状态更新异常
     */
    @Override
    public void updateUserBgState(long id, String modifyBy, StateEnum enabled) throws ServiceException {
        UserBgVO userBg = new UserBgVO();
        userBg.setId(id);
        userBg.setModifyBy(modifyBy);
        userBg.setEnabled(enabled);
        updateUserBg(userBg);
    }

    /**
     * 根据后台用户名查询后台用户信息，含用户角色信息
     *
     * @param username 后台用户名
     * @return 后台用户信息
     * @throws ServiceException 后台用户查询异常
     */
    @Override
    public UserBgVO findUserBgDetailsByUsername(String username) throws ServiceException {
        UserBgVO user = findUserBgByUsername(username);
        if (user == null) {
            throw new ServiceException(ERROR_FACTORY.usernameInvalid());
        }
        List<Long> roleIds = userRoleLinkDAO.queryByUserId(user.getId());
        Collection<RoleVO> roles = roleService.findRoleDetailsByIds(roleIds);
        user.setRoles(roles);
        return user;
    }

    /**
     * 根据后台用户ID查询后台用户信息，含用户角色信息
     *
     * @param id 后台用户ID
     * @return 后台用户信息
     * @throws ServiceException 后台用户查询异常
     */
    @Override
    public UserBgVO findUserBgDetailsById(long id) throws ServiceException {
        UserBgVO user = findUserBgById(id);
        if (user == null) {
            throw new ServiceException(ERROR_FACTORY.usernameInvalid());
        }
        List<Long> roleIds = userRoleLinkDAO.queryByUserId(user.getId());
        Collection<RoleVO> roles = roleService.findRoleByIds(roleIds);
        user.setRoles(roles);
        return user;
    }
}