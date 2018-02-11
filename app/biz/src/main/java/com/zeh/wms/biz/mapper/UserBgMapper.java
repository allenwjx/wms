package com.zeh.wms.biz.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.zeh.wms.biz.model.UserBgVO;
import com.zeh.wms.dal.dataobject.UserBgDO;

/**
 * @author allen
 * @create $ ID: UserBgMapper, 18/2/11 14:55 allen Exp $
 * @since 1.0.0
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface UserBgMapper extends AbstractMapper {
    UserBgVO do2vo(UserBgDO dataObject);

    UserBgDO vo2do(UserBgVO dateVO);

    Collection<UserBgVO> do2vos(Collection<UserBgDO> dataObjects);

    Collection<UserBgDO> vo2dos(Collection<UserBgVO> dateVOs);
}