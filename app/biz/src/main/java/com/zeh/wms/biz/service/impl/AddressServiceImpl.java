package com.zeh.wms.biz.service.impl;

import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.UserAddressMapper;
import com.zeh.wms.biz.model.UserAddressVO;
import com.zeh.wms.biz.model.enums.AddressTypeEnum;
import com.zeh.wms.biz.service.AddressService;
import com.zeh.wms.dal.daointerface.UserAddresDAO;
import com.zeh.wms.dal.dataobject.UserAddresDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type Address service.
 *
 * @author hzy24985
 * @version $Id : AddressServiceImpl, v 0.1 2018/3/8 12:42 hzy24985 Exp $
 */
@Service
public class AddressServiceImpl extends AbstractService implements AddressService {
    /**
     * 用户地址数据操作
     */
    @Resource
    private UserAddresDAO     userAddresDAO;

    /**
     * 用户地址模型转换工具
     */
    @Resource
    private UserAddressMapper userAddressMapper;

    /**
     * Add address boolean.
     *
     * @param address the address
     * @return the boolean
     * @throws ServiceException the service exception
     */
    @Override
    public boolean addAddress(UserAddressVO address) throws ServiceException {
        UserAddresDO addresDO = userAddressMapper.vo2do(address);
        checkInsert(userAddresDAO.insert(addresDO), "地址");
        return true;
    }

    /**
     * Update address boolean.
     *
     * @param address the address
     * @return the boolean
     * @throws ServiceException the service exception
     */
    @Override
    public boolean updateAddress(UserAddressVO address) throws ServiceException {
        UserAddresDO addresDO = userAddressMapper.vo2do(address);
        checkUpdate(userAddresDAO.update(addresDO), "地址");
        return true;
    }

    @Override
    public UserAddressVO getDefault(Long userId, AddressTypeEnum typeEnum) throws ServiceException {
        UserAddresDO userAddresDO = userAddresDAO.getDefault(userId, typeEnum.getCode());
        return userAddressMapper.do2vo(userAddresDO);
    }

    @Override
    public List<UserAddressVO> getList(Long userId, AddressTypeEnum typeEnum) throws ServiceException {
        List<UserAddresDO> list = userAddresDAO.getList(userId, typeEnum.getCode());
        return userAddressMapper.do2vos(list);
    }

    @Override
    public boolean delete(long id, long userId) throws ServiceException {
        checkUpdate(userAddresDAO.delete(id, userId), "地址删除");
        return true;
    }
}
