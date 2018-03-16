package com.zeh.wms.biz.mapper;

import com.zeh.wms.biz.model.PaymentOrderVO;
import com.zeh.wms.dal.dataobject.PaymentOrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.Collection;

/**
 * The interface Payment order mapper.
 * @author hzy24985
 * @version $Id : PaymentOrderMapper, v 0.1 2018/2/8 19:56 hzy24985 Exp $
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface PaymentOrderMapper extends AbstractMapper {

    /**
     * D 2 v payment order vo.
     *
     * @param dataObject the data object 
     * @return the payment order vo
     */
    PaymentOrderVO d2v(PaymentOrderDO dataObject);

    /**
     * D 2 vs collection.
     *
     * @param dataObjects the data objects 
     * @return the collection
     */
    Collection<PaymentOrderVO> d2vs(Collection<PaymentOrderDO> dataObjects);

    /**
     * V 2 d payment order do.
     *
     * @param dataObject the data object 
     * @return the payment order do
     */
    PaymentOrderDO v2d(PaymentOrderVO dataObject);
}
