package com.zeh.wms.biz.mapper;

import com.zeh.wms.biz.model.ExpressVO;
import com.zeh.wms.dal.dataobject.ExpresDO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

/**
 * The interface Inventory mapper.
 *
 * @author hzy24985
 * @version $Id : InventoryMapper, v 0.1 2018/3/10 12:48 hzy24985 Exp $
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ExpressMapper extends AbstractMapper{

    /**
     * Do 2 vo inventory vo.
     *
     * @param data the data
     * @return the inventory vo
     */
    ExpressVO do2vo(ExpresDO data);

    /**
     * Vo 2 do inventory do.
     *
     * @param vo the vo
     * @return the inventory do
     */
    ExpresDO vo2do(ExpressVO vo);

    List<ExpressVO> dos2vos(List<ExpresDO> data);
}
