package com.zeh.wms.biz.mapper;

import com.zeh.wms.biz.model.UserAddressVO;
import com.zeh.wms.dal.dataobject.UserAddresDO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

/**
 * The interface User address mapper.
 *
 * @author hzy24985
 * @version $Id : UserAddressMapper, v 0.1 2018/3/8 12:43 hzy24985 Exp $
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface UserAddressMapper extends AbstractMapper {
    /**
     * Do 2 vo user address vo.
     *
     * @param dataObject the data object
     * @return the user address vo
     */
    UserAddressVO do2vo(UserAddresDO dataObject);

    /**
     * Vo 2 do user addres do.
     *
     * @param dateVO the date vo
     * @return the user addres do
     */
    UserAddresDO vo2do(UserAddressVO dateVO);

    /**
     * Do 2 vos list.
     *
     * @param dataObjects the data objects
     * @return the list
     */
    List<UserAddressVO> do2vos(List<UserAddresDO> dataObjects);

    /**
     * Vo 2 dos list.
     *
     * @param dateVOs the date v os
     * @return the list
     */
    List<UserAddresDO> vo2dos(List<UserAddressVO> dateVOs);
}
