package com.zeh.wms.biz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.zeh.wms.biz.model.InventoryVO;
import com.zeh.wms.dal.dataobject.InventoryDO;

/**
 * The interface Inventory mapper.
 *
 * @author hzy24985
 * @version $Id : InventoryMapper, v 0.1 2018/3/10 12:48 hzy24985 Exp $
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface InventoryMapper extends AbstractMapper{

    /**
     * Do 2 vo inventory vo.
     *
     * @param data the data
     * @return the inventory vo
     */
    InventoryVO do2vo(InventoryVO data);

    /**
     * Vo 2 do inventory do.
     *
     * @param vo the vo
     * @return the inventory do
     */
    InventoryDO vo2do(InventoryVO vo);
}
