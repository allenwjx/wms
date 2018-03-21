package com.zeh.wms.biz.mapper;

import com.zeh.wms.biz.model.ExpressOrderItemVO;
import com.zeh.wms.biz.model.ExpressOrderVO;
import com.zeh.wms.dal.dataobject.ExpressOrderDO;
import com.zeh.wms.dal.dataobject.ExpressOrderItemDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueMappingStrategy;

import java.util.Collection;
import java.util.List;

/**
 * The interface ExpressService order mapper.
 *
 * @author hzy24985
 * @version $Id : ExpressOrderMapper, v 0.1 2018/2/7 21:51 hzy24985 Exp $
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
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

    /**
     * Item do 2 vo express order item vo.
     *
     * @param dataObject the data object
     * @return the express order item vo
     */
    ExpressOrderItemVO itemDo2Vo(ExpressOrderItemDO dataObject);

    /**
     * Item dos 2 vos list.
     *
     * @param dataObjects the data objects
     * @return the list
     */
    List<ExpressOrderItemVO> itemDos2Vos(List<ExpressOrderItemDO> dataObjects);

    /**
     * Do 2 vo details express order vo.
     *
     * @param dataObject  the data object
     * @param dataObjects the data objects
     * @return the express order vo
     */
    @Mappings({@Mapping(target = "items", expression = "java(itemDos2Vos(dataObjects))")})
    ExpressOrderVO do2voDetails(ExpressOrderDO dataObject, List<ExpressOrderItemDO> dataObjects);
}
