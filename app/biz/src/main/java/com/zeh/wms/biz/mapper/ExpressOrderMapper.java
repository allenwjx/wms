package com.zeh.wms.biz.mapper;

import com.zeh.wms.biz.model.ExpressOrderVO;
import com.zeh.wms.dal.dataobject.ExpressOrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.Collection;

/**
 * The interface Express order mapper.
 *
 * @author hzy24985
 * @version $Id : ExpressOrderMapper, v 0.1 2018/2/7 21:51 hzy24985 Exp $
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ExpressOrderMapper extends AbstractMapper {
    /**
     * V 2 d express order do.
     *
     * @param dataVO the data vo
     * @return the express order do
     */
    ExpressOrderDO v2d(ExpressOrderVO dataVO);

    /**
     * D 2 v express order vo.
     *
     * @param dataObject the data object
     * @return the express order vo
     */
    ExpressOrderVO d2v(ExpressOrderDO dataObject);

    /**
     * D 2 vs collection.
     *
     * @param dataObjects the data objects
     * @return the collection
     */
    Collection<ExpressOrderVO> d2vs(Collection<ExpressOrderDO> dataObjects);
}
