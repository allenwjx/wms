package com.zeh.wms.biz.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.zeh.wms.biz.model.RoleVO;
import com.zeh.wms.dal.dataobject.RoleDO;

/**
 * @author allen
 * @create $ ID: RoleMapper, 18/2/23 15:37 allen Exp $
 * @since 1.0.0
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface RoleMapper extends AbstractMapper {
    RoleVO do2vo(RoleDO dataObject);

    RoleDO vo2do(RoleVO dateVO);

    Collection<RoleVO> do2vos(Collection<RoleDO> dataObjects);

    Collection<RoleDO> vo2dos(Collection<RoleVO> dateVOs);
}
