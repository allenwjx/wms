package com.zeh.wms.biz.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.zeh.wms.biz.model.AuthorizationVO;
import com.zeh.wms.dal.dataobject.AuthorizationDO;

/**
 * @author allen
 * @create $ ID: AuthorizationMapper, 18/2/23 13:49 allen Exp $
 * @since 1.0.0
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface AuthorizationMapper extends AbstractMapper {
    AuthorizationVO do2vo(AuthorizationDO dataObject);

    AuthorizationDO vo2do(AuthorizationVO dateVO);

    Collection<AuthorizationVO> do2vos(Collection<AuthorizationDO> dataObjects);

    Collection<AuthorizationDO> vo2dos(Collection<AuthorizationVO> dateVOs);
}