package com.zeh.wms.biz.service;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.UserAgentLinkVO;
import com.zeh.wms.biz.model.UserVO;
import com.zeh.wms.dal.operation.user.GetAllUserPageQuery;

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
}
