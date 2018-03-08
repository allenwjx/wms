package com.zeh.wms.web.mapper;

import com.zeh.wms.biz.model.UserAddressVO;
import com.zeh.wms.biz.model.enums.AddressTypeEnum;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.web.controller.api.model.AddressModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

/**
 * The interface Address form mapper.
 *
 * @author hzy24985
 * @version $Id : AddressFormMapper, v 0.1 2018/3/8 13:01 hzy24985 Exp $
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface AddressFormMapper {
    /**
     * Model to vo user address vo.
     *
     * @param model the model
     * @return the user address vo
     */
    UserAddressVO modelToVo(AddressModel model);

    /**
     * Vo to model address model.
     *
     * @param model the model
     * @return the address model
     */
    AddressModel voToModel(UserAddressVO model);

    /**
     * Vos to models list.
     *
     * @param models the models
     * @return the list
     */
    List<AddressModel> vosToModels(List<UserAddressVO> models);

    /**
     * StateEnum to code
     *
     * @param type
     * @return
     */
    default boolean stateEnumToCode(StateEnum type) {
        return type == StateEnum.Y;
    }

    /**
     * Code to StateEnum
     *
     * @param defaultSetting
     * @return
     */
    default StateEnum codeToStateEnum(boolean defaultSetting) {
        return defaultSetting ? StateEnum.Y : StateEnum.N;
    }

    /**
     * AddressTypeEnum to code
     *
     * @param type
     * @return
     */
    default String addressTypeEnumToCode(AddressTypeEnum type) {
        return type.getCode();
    }

    /**
     * Code to AddressTypeEnum
     *
     * @param code
     * @return
     */
    default AddressTypeEnum codeToAddressTypeEnum(String code) {
        return AddressTypeEnum.getEnumByCode(code);
    }
}
