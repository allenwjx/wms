package com.zeh.wms.biz.service.impl;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageUtils;
import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.UserMapper;
import com.zeh.wms.biz.model.UserAgentLinkVO;
import com.zeh.wms.biz.model.UserVO;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.service.UserService;
import com.zeh.wms.dal.daointerface.UserAgentLinkDAO;
import com.zeh.wms.dal.daointerface.UserDAO;
import com.zeh.wms.dal.dataobject.UserAgentLinkDO;
import com.zeh.wms.dal.dataobject.UserDO;
import com.zeh.wms.dal.operation.user.GetAllUserPageQuery;
import com.zeh.wms.dal.operation.user.UpdateByParsParameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author hzy24985
 * @version $Id: UserServiceImpl, v 0.1 2018/2/10 19:21 hzy24985 Exp $
 */
@Service
public class UserServiceImpl implements UserService {
    /** 错误工厂 */
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();

    @Resource
    private UserDAO                      userDAO;
    @Resource
    private UserAgentLinkDAO             userAgentLinkDAO;
    @Resource
    private UserMapper                   userMapper;

    @Override
    public PageList<UserVO> pageQueryUser(GetAllUserPageQuery userQuery) throws ServiceException {
        PageList<UserDO> userdos = userDAO.getAllUserPage(userQuery);
        Collection<UserVO> userVOS = userMapper.d2vs(userdos.getData());
        return PageUtils.createPageList(userVOS, userdos.getPaginator());
    }

    @Override
    public UserVO getUserDetailInfo(Long id) throws ServiceException {
        UserDO userDO = userDAO.queryById(id);
        return userMapper.d2v(userDO);
    }

    @Override
    public void updatePassword(String newPassword, Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException(ERROR_FACTORY.parameterEmptyError("id"));
        }

        if (StringUtils.isBlank(newPassword)) {
            throw new ServiceException(ERROR_FACTORY.parameterEmptyError("password"));
        }
        UpdateByParsParameter updateByParsParameter = new UpdateByParsParameter();
        // TODO: 2018/2/11 md5 password 
        updateByParsParameter.setPassword(newPassword);
        int count = userDAO.updateByPars(updateByParsParameter);
        if (count <= 0) {
            throw new ServiceException(ERROR_FACTORY.updateUserPasswordError());
        }
    }

    @Override
    public void updateType(UserAgentLinkVO linkVO) throws ServiceException {
        if (linkVO == null) {
            throw new ServiceException(ERROR_FACTORY.parameterEmptyError("agentLinkVO"));
        }

        if (linkVO.getUserId() == null || linkVO.getType() == null || StringUtils.isBlank(linkVO.getCode())) {
            throw new ServiceException(ERROR_FACTORY.parameterEmptyError("userId or type or code"));
        }
        UpdateByParsParameter updateByParsParameter = new UpdateByParsParameter();
        updateByParsParameter.setType(linkVO.getType().getCode());
        int count = userDAO.updateByPars(updateByParsParameter);
        if (count <= 0) {
            throw new ServiceException(ERROR_FACTORY.updateLinkError());
        }
        UserAgentLinkDO userAgentLinkDO = userAgentLinkDAO.queryByPar(linkVO.getUserId(), linkVO.getCode(), linkVO.getType().getCode());
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
            count = userAgentLinkDAO.update(userAgentLinkDO);
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
}
