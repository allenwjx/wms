package com.zeh.wms.biz.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.zeh.wms.biz.model.ShipRecordVO;
import com.zeh.wms.dal.dataobject.ShipRecordDO;

/**
 * @author allen
 * @create $ ID: ShipRecordMapper, 18/2/25 20:14 allen Exp $
 * @since 1.0.0
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ShipRecordMapper extends AbstractMapper {
    ShipRecordVO do2vo(ShipRecordDO dataObject);

    ShipRecordDO vo2do(ShipRecordVO dateVO);

    Collection<ShipRecordVO> do2vos(Collection<ShipRecordDO> dataObjects);

    Collection<ShipRecordDO> vo2dos(Collection<ShipRecordVO> dateVOs);
}