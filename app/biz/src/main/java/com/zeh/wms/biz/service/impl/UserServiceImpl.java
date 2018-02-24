package com.zeh.wms.biz.service.impl;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageUtils;
import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.UserMapper;
import com.zeh.wms.biz.model.UserAgentLinkVO;
import com.zeh.wms.biz.model.UserVO;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.model.enums.UserLinkTypeEnum;
import com.zeh.wms.biz.service.UserService;
import com.zeh.wms.dal.daointerface.UserAgentLinkDAO;
import com.zeh.wms.dal.daointerface.UserDAO;
import com.zeh.wms.dal.dataobject.UserAgentLinkDO;
import com.zeh.wms.dal.dataobject.UserDO;
import com.zeh.wms.dal.operation.user.GetAllUserPageQuery;
import com.zeh.wms.dal.operation.user.UpdateByParsParameter;
import com.zeh.wms.dal.operation.useragentlink.QueryByParQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * The type User service.
 *
 * @author hzy24985
 * @version $Id : UserServiceImpl, v 0.1 2018/2/10 19:21 hzy24985 Exp $
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * 错误工厂
     */
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();

    /**
     * The User dao.
     */
    @Resource
    private UserDAO                      userDAO;
    /**
     * The User agent link dao.
     */
    @Resource
    private UserAgentLinkDAO             userAgentLinkDAO;
    /**
     * The User mapper.
     */
    @Resource
    private UserMapper                   userMapper;
    /**
     * The Password encoder.
     */
    @Resource
    private PasswordEncoder              passwordEncoder;

    /**
     * Page query user page list.
     *
     * @param userQuery the user query
     * @return the page list
     * @throws ServiceException the service exception
     */
    @Override
    public PageList<UserVO> pageQueryUser(GetAllUserPageQuery userQuery) throws ServiceException {
        PageList<UserDO> userDos = userDAO.getAllUserPage(userQuery);
        Collection<UserVO> userVOS = userMapper.d2vs(userDos.getData());
        return PageUtils.createPageList(userVOS, userDos.getPaginator());
    }

    /**
     * Gets user detail info.
     *
     * @param id the id
     * @return the user detail info
     * @throws ServiceException the service exception
     */
    @Override
    public UserVO getUserDetailInfo(Long id) throws ServiceException {
        UserDO userDO = userDAO.queryById(id);
        return userMapper.d2v(userDO);
    }

    /**
     * Update password.
     *
     * @param newPassword the new password
     * @param id          the id
     * @throws ServiceException the service exception
     */
    @Override
    public void updatePassword(String newPassword, Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException(ERROR_FACTORY.parameterEmptyError("id"));
        }

        if (StringUtils.isBlank(newPassword)) {
            throw new ServiceException(ERROR_FACTORY.parameterEmptyError("password"));
        }
        UpdateByParsParameter updateByParsParameter = new UpdateByParsParameter();
        updateByParsParameter.setId(id);

        updateByParsParameter.setPassword(passwordEncoder.encode(newPassword));
        int count = userDAO.updateByPars(updateByParsParameter);
        if (count <= 0) {
            throw new ServiceException(ERROR_FACTORY.updateUserPasswordError());
        }
    }

    /**
     * Update type.
     *
     * @param linkVO the link vo
     * @throws ServiceException the service exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateType(UserAgentLinkVO linkVO) throws ServiceException {
        //校验参数
        checkUpdateTypePars(linkVO);
        //更新用户类型。
        updateUserType(linkVO);
        //更新link表。
        updateUserAgentLinkCode(linkVO);
    }

    /**
     * 校验参数
     *
     * @param linkVO the link vo
     * @throws ServiceException the service exception
     */
    private void checkUpdateTypePars(UserAgentLinkVO linkVO) throws ServiceException {
        if (linkVO == null) {
            throw new ServiceException(ERROR_FACTORY.parameterEmptyError("agentLinkVO"));
        }

        if (linkVO.getUserId() == null || linkVO.getType() == null || StringUtils.isBlank(linkVO.getCode())) {
            throw new ServiceException(ERROR_FACTORY.parameterEmptyError("userId or type or code"));
        }
    }

    /**
     * 更新link表。
     *
     * @param linkVO the link vo
     * @throws ServiceException the service exception
     */
    private void updateUserAgentLinkCode(UserAgentLinkVO linkVO) throws ServiceException {
        QueryByParQuery query = new QueryByParQuery();
        query.setLinkStatus(StateEnum.Y.getCode());
        query.setUserId(linkVO.getUserId());

        UserAgentLinkDO userAgentLinkDO = userAgentLinkDAO.queryByPar(query);
        if (userAgentLinkDO == null) {
            userAgentLinkDO = new UserAgentLinkDO();
            userAgentLinkDO.setCreateBy(linkVO.getModifyBy());
            userAgentLinkDO.setLinkStatus(StateEnum.Y.getCode());

        }
        userAgentLinkDO.setCode(linkVO.getCode());
        userAgentLinkDO.setType(linkVO.getType().getCode());
        userAgentLinkDO.setUserId(linkVO.getUserId());
        userAgentLinkDO.setModifyBy(linkVO.getModifyBy());

        if (userAgentLinkDO.getId() > 0) {
            int count = userAgentLinkDAO.update(userAgentLinkDO);
            if (count <= 0) {
                throw new ServiceException(ERROR_FACTORY.updateLinkError());
            }
        } else {
            long newId = userAgentLinkDAO.insert(userAgentLinkDO);
            if (newId <= 0) {
                throw new ServiceException(ERROR_FACTORY.updateLinkError());
            }
        }
    }

    /**
     * 更新用户类型
     *
     * @param linkVO the link vo
     * @throws ServiceException the service exception
     */
    private void updateUserType(UserAgentLinkVO linkVO) throws ServiceException {
        UpdateByParsParameter updateByParsParameter = new UpdateByParsParameter();
        updateByParsParameter.setId(linkVO.getUserId());
        updateByParsParameter.setType(linkVO.getType().getCode());
        int count = userDAO.updateByPars(updateByParsParameter);
        if (count <= 0) {
            throw new ServiceException(ERROR_FACTORY.updateLinkError());
        }
    }

    /**
     * 根据user id获取userlinkvo
     *
     * @param id the id user id
     * @return link vo by user id
     * @throws ServiceException the service exception
     */
    @Override
    public UserAgentLinkVO getLinkVOByUserId(Long id) throws ServiceException {
        UserAgentLinkDO linkDO = userAgentLinkDAO.queryByUserId(id, StateEnum.Y.getCode());
        return userMapper.linkDo2Vo(linkDO);
    }

    /**
     * 使用户关联的商户无效.
     *
     * @param id       User的主键
     * @param modifyBy 修改人。
     * @throws ServiceException the service exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disabledUserLinkByUserId(Long id, String modifyBy) throws ServiceException {
        UserAgentLinkVO vo = new UserAgentLinkVO();
        vo.setModifyBy(modifyBy);
        vo.setUserId(id);
        vo.setType(UserLinkTypeEnum.C);
        updateUserType(vo);

        UserAgentLinkDO linkDO = userAgentLinkDAO.queryByUserId(id, StateEnum.Y.getCode());
        if (linkDO == null) {
            return;
        }
        linkDO.setLinkStatus(StateEnum.N.getCode());
        linkDO.setModifyBy(modifyBy);
        int count = userAgentLinkDAO.update(linkDO);
        if (count <= 0) {
            throw new ServiceException(ERROR_FACTORY.updateLinkError());
        }
    }
}
