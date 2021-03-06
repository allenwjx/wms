package com.zeh.wms.biz.service;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.UserAgentLinkVO;
import com.zeh.wms.biz.model.UserExpressDiscountVO;
import com.zeh.wms.biz.model.UserVO;
import com.zeh.wms.dal.operation.user.GetAllUserPageQuery;

import java.math.BigDecimal;
import java.util.List;

/**
 * The interface User service.
 *
 * @author hzy24985
 * @version $Id : UserService, v 0.1 2018/2/10 19:18 hzy24985 Exp $
 */
public interface UserService {
    /**
     * 分页查询厂商信息
     *
     * @param userQuery 用户查询条件
     * @return 订单查询结果 page list
     * @throws ServiceException 分页查询异常
     */
    PageList<UserVO> pageQueryUser(GetAllUserPageQuery userQuery) throws ServiceException;

    /**
     * Gets order detail info.
     *
     * @param id the id
     * @return the order detail info
     * @throws ServiceException the service exception
     */
    UserVO getUserDetailInfo(Long id) throws ServiceException;

    /**
     * Update password.
     *
     * @param newPassword the new password
     * @param id          the id
     * @throws ServiceException the service exception
     */
    void updatePassword(String newPassword, Long id) throws ServiceException;

    /**
     * 更新用户对商户的类别.
     *
     * @param linkVO the link vo
     * @throws ServiceException the service exception
     */
    void updateType(UserAgentLinkVO linkVO) throws ServiceException;

    /**
     * 根据User表主键id，查询已关联状态的link表数据。
     *
     * @param id the id
     * @return link vo by user id
     * @throws ServiceException the service exception
     */
    UserAgentLinkVO getLinkVOByUserId(Long id) throws ServiceException;

    /**
     * 使link无效
     * @param id User的主键
     * @param modifyBy 修改人。
     * @throws ServiceException 更新失败。
     */
    void disabledUserLinkByUserId(Long id, String modifyBy) throws ServiceException;

    /**
     * 通过用户的微信标识查询用户
     *
     * @param openId
     * @return
     * @throws ServiceException
     */
    UserVO queryByOpenId(String openId) throws ServiceException;

    /**
     * 通过 userId 查询用户
     *
     * @param userId
     * @return
     * @throws ServiceException
     */
    UserVO queryByUserId(String userId) throws ServiceException;

    /**
     * 创建一个用户
     *
     * @param userVO
     * @return
     * @throws ServiceException
     */
    UserVO createUser(UserVO userVO) throws ServiceException;

    /**
     * 更新用户
     *
     * @param userVO
     * @throws ServiceException
     */
    void updateUser (UserVO userVO) throws ServiceException;


    /**
     * 获取用户的折扣信息.
     * @param userId 用户id
     * @return 折扣信息
     * @throws ServiceException
     */
    List<UserExpressDiscountVO> getUserDiscount(Long userId) throws ServiceException;

    /**
     * 删除用户的折扣信息.
     * @param id id
     * @return 折扣信息
     * @throws ServiceException
     */
    void deleteDiscount(Long id) throws ServiceException;

    /**
     * 添加用户的折扣信息.
     * @param vo 折扣vo
     * @return 折扣信息
     * @throws ServiceException
     */
    void addDiscount(UserExpressDiscountVO vo) throws ServiceException;

    /**
     * 获取用户的折扣数据
     * @param userId 用户id
     * @param expressCode 快递公司code
     * @return 折扣，会捕获异常，如果没有配置，则返回1：没有折扣
     */
    BigDecimal getDiscount(Long userId, String expressCode);
}
