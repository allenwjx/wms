package com.zeh.wms.biz.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.zeh.wms.biz.model.CommodityVO;
import com.zeh.wms.dal.dataobject.CommodityDO;

/**
 * @author allen
 * @create $ ID: CommodityMapper, 18/2/8 18:24 allen Exp $
 * @since 1.0.0
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface CommodityMapper extends AbstractMapper {
    CommodityVO do2vo(CommodityDO dataObject);

    CommodityDO vo2do(CommodityVO dateVO);

    Collection<CommodityVO> do2vos(Collection<CommodityDO> dataObjects);

    Collection<CommodityDO> vo2dos(Collection<CommodityVO> dateVOs);
}
