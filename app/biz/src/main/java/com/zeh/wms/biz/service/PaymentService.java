package com.zeh.wms.biz.service;

import org.springframework.http.ResponseEntity;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.ExpressOrderVO;
import com.zeh.wms.biz.model.PaymentOrderVO;
import com.zeh.wms.biz.model.UserVO;
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
     *
     * @param xmlData the xml data
     * @return string
     * @throws ServiceException
     */
    String payCallback(String xmlData) ;

    /**
     * 创建支付单.
     *
     * @param paymentOrderVO payment order vo.
     * @return payment order vo.
     * @throws ServiceException the service exception
     */
    PaymentOrderVO createPayOrder(PaymentOrderVO paymentOrderVO) throws ServiceException;

    /**
     * 生成微信统一订单，返回签名信息。
     *
     * @param order          the order
     * @param paymentOrderVO the payment order vo
     * @return 返回签名信息 ，用于微信支付.
     * @throws ServiceException service exception.
     */
    WxPayMpOrderResult createWechatOrder(ExpressOrderVO order, PaymentOrderVO paymentOrderVO) throws ServiceException;


    /**
     * 创建支付单，同时创建微信统一订单。
     * 每次创建都重新生成新的支付单。
     *
     * @param orderNo     订单流水号
     * @param currentUser the current user
     * @return wx pay mp order result
     * @throws ServiceException the service exception
     */
    WxPayMpOrderResult goPay(String orderNo, UserVO currentUser) throws ServiceException;
}
