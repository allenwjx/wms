package com.zeh.wms.biz.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.zeh.wms.biz.model.AgentVO;
import com.zeh.wms.dal.dataobject.AgentDO;

/**
 * @author allen
 * @create $ ID: AgentMapper, 18/2/8 13:17 allen Exp $
 * @since 1.0.0
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface AgentMapper extends AbstractMapper {

    AgentVO do2vo(AgentDO dataObject);

    AgentDO vo2do(AgentVO dateVO);

    Collection<AgentVO> do2vos(Collection<AgentDO> dataObjects);

    Collection<AgentDO> vo2dos(Collection<AgentVO> dateVOs);
}