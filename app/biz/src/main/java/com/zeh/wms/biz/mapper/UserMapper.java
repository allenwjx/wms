package com.zeh.wms.biz.mapper;

import com.zeh.wms.biz.model.UserAgentLinkVO;
import com.zeh.wms.biz.model.UserExpressDiscountVO;
import com.zeh.wms.biz.model.UserVO;
import com.zeh.wms.dal.dataobject.UserAgentLinkDO;
import com.zeh.wms.dal.dataobject.UserDO;
import com.zeh.wms.dal.dataobject.UserExpressDiscountDO;
import com.zeh.wms.dal.operation.useragentlink.QueryByParQuery;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.Collection;
import java.util.List;

/**
 * The interface User mapper.
 *
 * @author hzy24985
 * @version $Id : UserMapper, v 0.1 2018/2/10 19:23 hzy24985 Exp $
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface UserMapper extends AbstractMapper {

    /**
     * D 2 v user vo.
     *
     * @param dataObject the data object
     * @return the user vo
     */
    UserVO d2v(UserDO dataObject);

    /**
     * D 2 vs collection.
     *
     * @param dataObjects the data objects
     * @return the collection
     */
    Collection<UserVO> d2vs(Collection<UserDO> dataObjects);

    /**
     * V 2 d user do.
     *
     * @param v the v
     * @return the user do
     */
    UserDO v2d(UserVO v);

    /**
     * Link do 2 vo user agent link vo.
     *
     * @param dataObject the data object
     * @return the user agent link vo
     */
    UserAgentLinkVO linkDo2Vo(UserAgentLinkDO dataObject);

    /**
     * Link vo 2 query query by par query.
     *
     * @param dataObject the data object
     * @return the query by par query
     */
    QueryByParQuery linkVo2Query(UserAgentLinkVO dataObject);

    /**
     * Discount do 2 vo user express discount vo.
     *
     * @param data the data
     * @return the user express discount vo
     */
    UserExpressDiscountVO discountDo2Vo(UserExpressDiscountDO data);

    /**
     * Discount vo 2 do user express discount do.
     *
     * @param vo the vo
     * @return the user express discount do
     */
    UserExpressDiscountDO discountVo2Do(UserExpressDiscountVO vo);

    /**
     * Discount dos 2 vos list.
     *
     * @param datas the datas
     * @return the list
     */
    List<UserExpressDiscountVO> discountDos2Vos(List<UserExpressDiscountDO> datas);
}
