package com.zeh.wms.biz.mapper;

import com.zeh.wms.biz.model.UserVO;
import com.zeh.wms.dal.dataobject.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.Collection;

/**
 * @author hzy24985
 * @version $Id: UserMapper, v 0.1 2018/2/10 19:23 hzy24985 Exp $
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface UserMapper extends AbstractMapper {

    UserVO d2v(UserDO dataObject);

    Collection<UserVO> d2vs(Collection<UserDO> dataObjects);
}
