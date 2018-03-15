package com.zeh.wms.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.zeh.wms.biz.model.ExpressOrderVO;

/**
 * @author hzy24985
 * @version $Id: PaymentFormMapper, v 0.1 2018/2/8 21:32 hzy24985 Exp $
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface OrderModelMapper {

    ExpressOrderVO model2Vo(ExpressOrderModel model);
}
