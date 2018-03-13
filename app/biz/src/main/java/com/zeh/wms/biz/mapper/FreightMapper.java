package com.zeh.wms.biz.mapper;

import com.zeh.wms.biz.model.FreightVO;
import com.zeh.wms.dal.dataobject.FreightDO;
import com.zeh.wms.dal.operation.freight.GetPriceByProvinceNameResult;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.Collection;

/**
 * @author allen
 * @create $ ID: FreightMapper, 18/2/9 00:41 allen Exp $
 * @since 1.0.0
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface FreightMapper extends AbstractMapper {
    FreightVO do2vo(FreightDO dataObject);

    FreightDO vo2do(FreightVO dateVO);

    Collection<FreightVO> do2vos(Collection<FreightDO> dataObjects);

    Collection<FreightDO> vo2dos(Collection<FreightVO> dateVOs);

    FreightVO result2VO(GetPriceByProvinceNameResult result);
}