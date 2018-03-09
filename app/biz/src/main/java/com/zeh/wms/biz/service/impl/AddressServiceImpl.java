package com.zeh.wms.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.UserAddressMapper;
import com.zeh.wms.biz.model.UserAddressVO;
import com.zeh.wms.biz.model.enums.AddressTypeEnum;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.service.AddressService;
import com.zeh.wms.dal.daointerface.UserAddresDAO;
import com.zeh.wms.dal.dataobject.UserAddresDO;

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
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public boolean addAddress(UserAddressVO address) throws ServiceException {
        checkUpdate(userAddresDAO.updateDefaultSettingByUserId(StateEnum.N.getCode(), address.getModifyBy(), address.getUserId()), "地址是否默认");
        address.setDefaultSetting(StateEnum.Y);
        UserAddresDO addressDO = userAddressMapper.vo2do(address);
        checkInsert(userAddresDAO.insert(addressDO), "地址");
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

    /**
     * Set the address to the default
     *
     * @param address
     * @throws ServiceException
     */
    @Override
    public void setDefaultAddress(UserAddressVO address) throws ServiceException {
        checkUpdate(userAddresDAO.updateDefaultSettingByUserId(StateEnum.N.getCode(), address.getModifyBy(), address.getUserId()), "地址是否默认");
        UserAddresDO addressDO = userAddresDAO.queryById(address.getId());
        if (addressDO == null) {
            throw new RuntimeException("无效地址，ID：" + address.getId());
        }
        addressDO.setDefaultSetting(StateEnum.Y.getCode());
        addressDO.setModifyBy(address.getModifyBy());
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
