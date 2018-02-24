package com.zeh.wms.biz.service;

import org.springframework.http.ResponseEntity;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.PaymentOrderVO;
import com.zeh.wms.dal.operation.paymentorder.GetAllDataQuery;
import com.zeh.wms.dal.operation.paymentorder.GetPageDataQuery;

/**
 * The interface Payment service.
 *
 * @author hzy24985
 * @version $Id : PaymentService, v 0.1 2018/2/8 19:44 hzy24985 Exp $
 */
public interface PaymentService {

    /**
     * 分页订单信息。
     *
     * @param orderQuery 订单查询条件
     * @return 订单查询结果 page list
     * @throws ServiceException 分页查询异常
     */
    PageList<PaymentOrderVO> pageQueryPaymentOrders(GetPageDataQuery orderQuery) throws ServiceException;

    /**
     * Gets order detail info.
     *
     * @param id the id
     * @return the order detail info
     * @throws ServiceException the service exception
     */
    PaymentOrderVO getPaymentDetailInfo(Long id) throws ServiceException;

    /**
     * Export response entity.
     *
     * @param query        the query
     * @param templatePath the template path
     * @return the response entity
     * @throws ServiceException the service exception
     */
    ResponseEntity<byte[]> export(GetAllDataQuery query, String templatePath) throws ServiceException;

    /**
     * 微信支付回调解析
     * @param xmlData
     * @return
     * @throws ServiceException
     */
    String payCallback(String xmlData) ;
}
