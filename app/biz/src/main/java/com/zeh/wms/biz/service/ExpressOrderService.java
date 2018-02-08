package com.zeh.wms.biz.service;

import org.springframework.http.ResponseEntity;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.ExpressOrderVO;
import com.zeh.wms.dal.operation.expressorder.FindPageQuery;
import com.zeh.wms.dal.operation.expressorder.GetAllByParsQuery;

/**
 * 订单相关查询.
 *
 * @author hzy24985
 * @version $Id : ExpressOrderService, v 0.1 2018/2/7 21:45 hzy24985 Exp $
 */
public interface ExpressOrderService {

    /**
     * 分页查询厂商信息
     *
     * @param orderQuery 订单查询条件
     * @return 订单查询结果 page list
     * @throws ServiceException 分页查询异常
     */
    PageList<ExpressOrderVO> pageQueryExpressOrders(FindPageQuery orderQuery) throws ServiceException;

    /**
     * Gets order detail info.
     *
     * @param id the id
     * @return the order detail info
     * @throws ServiceException the service exception
     */
    ExpressOrderVO getOrderDetailInfo(Long id) throws ServiceException;

    ResponseEntity<byte[]> export(GetAllByParsQuery query) throws ServiceException;

    ResponseEntity<byte[]> export2(GetAllByParsQuery query, String templatePath) throws ServiceException;
}
