package com.zeh.wms.biz.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.zeh.wms.biz.model.RegionsVO;
import com.zeh.wms.dal.dataobject.RegionDO;

/**
 * @author allen
 * @create $ ID: RegionsMapper, 18/2/9 02:50 allen Exp $
 * @since 1.0.0
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface RegionsMapper extends AbstractMapper {
    RegionsVO do2vo(RegionDO dataObject);

    RegionDO vo2do(RegionsVO dateVO);

    Collection<RegionsVO> do2vos(Collection<RegionDO> dataObjects);

    Collection<RegionDO> vo2dos(Collection<RegionsVO> dateVOs);
}
