package com.zeh.wms.biz.mapper;

import com.zeh.wms.biz.model.ManufacturerVO;
import com.zeh.wms.dal.dataobject.ManufacturerDO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.Collection;
import java.util.List;

/**
 * @author allen
 * @create $ ID: ManufacturerMapper, 18/2/6 16:58 allen Exp $
 * @since 1.0.0
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ManufacturerMapper extends AbstractMapper {
    ManufacturerDO v2d(ManufacturerVO dataVO);

    ManufacturerVO d2v(ManufacturerDO dataObject);

    Collection<ManufacturerVO> d2vs(Collection<ManufacturerDO> dataObjects);

    List<ManufacturerVO> do2vos(List<ManufacturerDO> dataObjects);
}
