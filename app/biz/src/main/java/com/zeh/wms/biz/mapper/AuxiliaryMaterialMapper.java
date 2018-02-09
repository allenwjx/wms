package com.zeh.wms.biz.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.zeh.wms.biz.model.AuxiliaryMaterialVO;
import com.zeh.wms.dal.dataobject.AuxiliaryMaterialDO;

/**
 * @author allen
 * @create $ ID: AuxiliaryMaterialMapper, 18/2/9 11:29 allen Exp $
 * @since 1.0.0
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface AuxiliaryMaterialMapper extends AbstractMapper {
    AuxiliaryMaterialVO do2vo(AuxiliaryMaterialDO dataObject);

    AuxiliaryMaterialDO vo2do(AuxiliaryMaterialVO dateVO);

    Collection<AuxiliaryMaterialVO> do2vos(Collection<AuxiliaryMaterialDO> dataObjects);

    Collection<AuxiliaryMaterialDO> vo2dos(Collection<AuxiliaryMaterialVO> dateVOs);
}
