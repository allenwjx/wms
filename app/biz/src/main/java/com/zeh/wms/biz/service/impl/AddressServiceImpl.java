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
        userAddresDAO.updateDefaultSettingByUserId(StateEnum.N.getCode(), address.getModifyBy(), address.getUserId(), address.getAddressType().getCode());
        address.setDefaultSetting(StateEnum.Y);
        UserAddresDO addressDO = userAddressMapper.vo2do(address);
        userAddresDAO.insert(addressDO);
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
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public boolean updateAddress(UserAddressVO address) throws ServiceException {
        UserAddresDO addresDO = userAddressMapper.vo2do(address);
        userAddresDAO.update(addresDO);
        //如果设置地址为默认的，则需要更新其他的地址
        if (address.getDefaultSetting() == StateEnum.Y) {
            setDefault(address.getUserId(), address.getId(), address.getAddressType().getCode(), address.getModifyBy());
        }
        return true;
    }

    @Override
    public boolean setDefault(long userId, long id, String type, String modify) throws ServiceException {
        userAddresDAO.updateDefaultByUserIdAndId(id, modify, userId, type);
        return true;
    }

    @Override
    public UserAddressVO getDefault(Long userId, AddressTypeEnum typeEnum) throws ServiceException {
        UserAddresDO userAddresDO = userAddresDAO.getDefault(userId, typeEnum.getCode());
        return userAddressMapper.do2vo(userAddresDO);
    }

    /**
     * 获取地址
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    @Override
    public UserAddressVO queryAddress(Long id) throws ServiceException {
        UserAddresDO userAddresDO = userAddresDAO.queryById(id);
        return userAddressMapper.do2vo(userAddresDO);
    }

    @Override
    public List<UserAddressVO> getList(Long userId, AddressTypeEnum typeEnum) throws ServiceException {
        List<UserAddresDO> list = userAddresDAO.getList(userId, typeEnum.getCode());
        return userAddressMapper.do2vos(list);
    }

    @Override
    public boolean delete(long id, long userId) throws ServiceException {
        userAddresDAO.delete(id, userId);
        return true;
    }
}
