package com.zeh.wms.web.mapper;

import com.zeh.wms.biz.model.UserExpressDiscountVO;
import com.zeh.wms.web.form.UserExpressDiscountForm;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

/**
 * The interface User express discount form mapper.
 *
 * @author hzy24985
 * @version $Id : UserExpressDiscountFormMapper, v 0.1 2018/3/10 18:57 hzy24985 Exp $
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface UserExpressDiscountFormMapper {
    /**
     * Vo 2 form user express discount form.
     *
     * @param vo the vo
     * @return the user express discount form
     */
    UserExpressDiscountForm vo2form(UserExpressDiscountVO vo);

    /**
     * Vo 2 form user express discount form.
     *
     * @param vo the vo
     * @return the user express discount form
     */
    UserExpressDiscountVO form2vo(UserExpressDiscountForm vo);

    /**
     * Vos 2 forms list.
     *
     * @param vos the vos
     * @return the list
     */
    List<UserExpressDiscountForm> vos2forms(List<UserExpressDiscountVO> vos);
}
