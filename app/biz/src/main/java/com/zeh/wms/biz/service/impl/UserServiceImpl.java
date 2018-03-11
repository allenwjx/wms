package com.zeh.wms.biz.service.impl;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageUtils;
import com.zeh.jungle.utils.common.UUID;
import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.UserMapper;
import com.zeh.wms.biz.model.UserAgentLinkVO;
import com.zeh.wms.biz.model.UserExpressDiscountVO;
import com.zeh.wms.biz.model.UserVO;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.model.enums.UserLinkTypeEnum;
import com.zeh.wms.biz.service.UserService;
import com.zeh.wms.dal.daointerface.UserAgentLinkDAO;
import com.zeh.wms.dal.daointerface.UserDAO;
import com.zeh.wms.dal.daointerface.UserExpressDiscountDAO;
import com.zeh.wms.dal.dataobject.UserAgentLinkDO;
import com.zeh.wms.dal.dataobject.UserDO;
import com.zeh.wms.dal.dataobject.UserExpressDiscountDO;
import com.zeh.wms.dal.operation.user.GetAllUserPageQuery;
import com.zeh.wms.dal.operation.user.UpdateByParsParameter;
import com.zeh.wms.dal.operation.useragentlink.QueryByParQuery;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * The type User service.
 *
 * @author hzy24985
 * @version $Id : UserServiceImpl, v 0.1 2018/2/10 19:21 hzy24985 Exp $
 */
@Service("userService")
public class UserServiceImpl extends AbstractService implements UserService {
    /**
     * 错误工厂
     */
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();
    
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
     * 折扣信息.
     */
    @Resource
    private UserExpressDiscountDAO       expressDiscountDAO;

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

    @Override
    public UserVO queryByOpenId(String openId) throws ServiceException {
        UserDO userDO = userDAO.queryByOpenId(openId);
        return userMapper.d2v(userDO);
    }

    @Override
    public UserVO queryByUserId(String userId) throws ServiceException {
        UserDO userDO = userDAO.queryByUserId(userId);
        return userMapper.d2v(userDO);
    }

    /**
     * 创建用户
     * @param userVO
     * @return
     * @throws ServiceException
     */
    @Override
    public UserVO createUser(UserVO userVO) throws ServiceException {
        if (userVO == null || userVO.getId() > 0) {
            throw new ServiceException(ERROR_FACTORY.createUserError("参数为空"));
        }
        userVO.setGmtCreate(new Date());
        userVO.setGmtModified(new Date());
        if (StringUtils.isBlank(userVO.getCreateBy())) {
            userVO.setCreateBy("系统");
        }
        if (StringUtils.isBlank(userVO.getModifyBy())) {
            userVO.setModifyBy("系统");
        }
        if (StringUtils.isBlank(userVO.getPassword())) {
            userVO.setPassword("111111");
        }
        userVO.setUserId(UUID.generateRandomUUID());

        Long userId = userDAO.insert(userMapper.v2d(userVO));
        if (userId == null || userId <= 0) {
            throw new ServiceException(ERROR_FACTORY.createUserError("数据库创建失败"));
        }

        return userMapper.d2v(userDAO.queryById(userId));
    }

    @Override
    public void updateUser(UserVO userVO) throws ServiceException {
        if (userVO == null || userVO.getId() <= 0) {
            throw new ServiceException(ERROR_FACTORY.updateUserError("参数不能为空"));
        }

        UserDO dbUser = userDAO.queryById(userVO.getId());
        if (dbUser == null || dbUser.getId() <= 0) {
            throw new ServiceException(ERROR_FACTORY.updateUserError("指定用户不存在"));
        }

        if (StringUtils.isNotBlank(userVO.getNickName())) {
            dbUser.setNickName(userVO.getNickName());
        }
        if (StringUtils.isNotBlank(userVO.getOpenId())) {
            dbUser.setOpenId(userVO.getOpenId());
        }
        if (StringUtils.isNotBlank(userVO.getPassword())) {
            dbUser.setPassword(userVO.getPassword());
        }
        if (StringUtils.isNotBlank(userVO.getMobile())) {
            dbUser.setMobile(userVO.getMobile());
        }

        dbUser.setGmtModified(new Date());

        userDAO.update(dbUser);
    }

    @Override
    public List<UserExpressDiscountVO> getUserDiscount(Long userId) throws ServiceException {
        com.zeh.wms.dal.operation.userexpressdiscount.QueryByParQuery query = new com.zeh.wms.dal.operation.userexpressdiscount.QueryByParQuery();
        query.setUserId(userId);
        List<UserExpressDiscountDO> list = expressDiscountDAO.queryByPar(query);

        return userMapper.discountDos2Vos(list);
    }

    @Override
    public void deleteDiscount(Long id) throws ServiceException {
        checkUpdate(expressDiscountDAO.delete(id), "折扣信息");
    }

    @Override
    public void addDiscount(UserExpressDiscountVO vo) throws ServiceException {
        UserExpressDiscountDO discountDO = userMapper.discountVo2Do(vo);
        com.zeh.wms.dal.operation.userexpressdiscount.QueryByParQuery query = new com.zeh.wms.dal.operation.userexpressdiscount.QueryByParQuery();
        query.setUserId(vo.getUserId());
        query.setExpressCode(vo.getExpressCode());
        List<UserExpressDiscountDO> list = expressDiscountDAO.queryByPar(query);
        if (CollectionUtils.isNotEmpty(list)) {
            list.stream().forEach(item -> {
                try {
                    deleteDiscount(item.getId());
                } catch (ServiceException e) {
                    logger.error("delete", e);
                }
            });
        }
        checkInsert(expressDiscountDAO.insert(discountDO), "折扣信息");
    }

    @Override
    public BigDecimal getDiscount(Long userId, String expressCode) {
        com.zeh.wms.dal.operation.userexpressdiscount.QueryByParQuery query = new com.zeh.wms.dal.operation.userexpressdiscount.QueryByParQuery();
        query.setUserId(userId);
        query.setExpressCode(expressCode);
        List<UserExpressDiscountDO> list = expressDiscountDAO.queryByPar(query);
        if (CollectionUtils.isNotEmpty(list)) {
            return new BigDecimal(list.get(0).getDiscount());
        }
        return new BigDecimal(1);
    }
}
