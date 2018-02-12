package com.zeh.wms.biz.service;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.UserBgVO;
import com.zeh.wms.biz.model.enums.StateEnum;

/**
 * @author allen
 * @create $ ID: UserBgService, 18/2/11 14:56 allen Exp $
 * @since 1.0.0
 */
public interface UserBgService {
    /**
     * 创建后台用户
     *
     * @param userBg 后台用户
     * @throws ServiceException 创建后台用户异常
     */
    void createUserBg(UserBgVO userBg) throws ServiceException;

    /**
     * 更新后台用户信息
     *
     * @param userBg 后台用户
     * @throws ServiceException 后台用户更新异常
     */
    void updateUserBg(UserBgVO userBg) throws ServiceException;

    /**
     * 根据后台用户名查询后台用户信息
     *
     * @param username 后台用户名
     * @return 后台用户信息
     * @throws ServiceException 后台用户查询异常
     */
    UserBgVO findUserBgByUsername(String username) throws ServiceException;

    /**
     * 根据后台用户ID查询后台用户信息
     *
     * @param id 后台用户ID
     * @return 后台用户信息
     * @throws ServiceException 后台用户查询异常
     */
    UserBgVO findUserBgById(long id) throws ServiceException;

    /**
     * 分页查询后台用户信息
     * @param userBg 后台用户查询条件
     * @param currentPage 查询起始页
     * @param size 每页数量
     * @return 后台用户信息
     * @throws ServiceException 分页查询异常
     */
    PageList<UserBgVO> pageQueryUserBgs(UserBgVO userBg, int currentPage, int size) throws ServiceException;

    /**
     * 更新后台用户启用、禁用状态
     *
     * @param id       后台用户ID
     * @param modifyBy 修改人
     * @param enabled  状态
     * @throws ServiceException 后台用户状态更新异常
     */
    void updateUserBgState(long id, String modifyBy, StateEnum enabled) throws ServiceException;
}
