package com.zeh.wms.biz.mapper;

import com.zeh.wms.biz.model.UserAgentLinkVO;
import com.zeh.wms.biz.model.UserVO;
import com.zeh.wms.dal.dataobject.UserAgentLinkDO;
import com.zeh.wms.dal.dataobject.UserDO;
import com.zeh.wms.dal.operation.useragentlink.QueryByParQuery;
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

    UserDO v2d(UserVO v);

    UserAgentLinkVO linkDo2Vo(UserAgentLinkDO dataObject);

    QueryByParQuery linkVo2Query(UserAgentLinkVO dataObject);
}
