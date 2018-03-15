package com.zeh.wms.biz.service;

import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.UserAddressVO;
import com.zeh.wms.biz.model.enums.AddressTypeEnum;

import java.util.List;

/**
 * The interface Address service.
 *
 * @author hzy24985
 * @version $Id : AddressService, v 0.1 2018/3/8 11:39 hzy24985 Exp $
 */
public interface AddressService {

    /**
     * Add address boolean.
     *
     * @param address the address
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addAddress(UserAddressVO address) throws ServiceException;

    /**
     * Update address boolean.
     *
     * @param address the address
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateAddress(UserAddressVO address) throws ServiceException;

    /**
     * Sets default.
     *
     * @param userId the user id
     * @param id     the id
     * @param type   the type
     * @param modify the modify
     * @return default
     * @throws ServiceException the service exception
     */
    boolean setDefault(long userId, long id, String type, String modify) throws ServiceException;

    /**
     * 获取默认地址。
     *
     * @param userId   the user id
     * @param typeEnum 地址类型.
     * @return 默认的地址. default
     * @throws ServiceException the service exception
     */
    UserAddressVO getDefault(Long userId, AddressTypeEnum typeEnum) throws ServiceException;

    /**
     * 获取地址
     *
     * @param id the id
     * @return user address vo
     * @throws ServiceException the service exception
     */
    UserAddressVO queryAddress(Long id) throws ServiceException;

    /**
     * 获取地址列表
     *
     * @param userId   the user id
     * @param typeEnum 地址类型.
     * @return 地址列表 list
     * @throws ServiceException the service exception
     */
    List<UserAddressVO> getList(Long userId, AddressTypeEnum typeEnum) throws ServiceException;

    /**
     * Delete boolean.
     *
     * @param id     the id
     * @param userId the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean delete(long id, long userId) throws ServiceException;

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws ServiceException the service exception
     */
    UserAddressVO getById(Long id) throws ServiceException;

}
