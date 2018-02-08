package com.zeh.wms.biz.mapper;

import com.zeh.wms.biz.model.PaymentOrderVO;
import com.zeh.wms.dal.dataobject.PaymentOrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.Collection;

/**
 * @author hzy24985
 * @version $Id: PaymentOrderMapper, v 0.1 2018/2/8 19:56 hzy24985 Exp $
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface PaymentOrderMapper extends AbstractMapper {

    PaymentOrderVO d2v(PaymentOrderDO dataObject);

    Collection<PaymentOrderVO> d2vs(Collection<PaymentOrderDO> dataObjects);
}
