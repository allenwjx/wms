package com.zeh.wms.biz.service;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.ExpressOrderVO;
import com.zeh.wms.dal.operation.expressorder.*;

/**
 * 订单相关查询.
 *
 * @author hzy24985
 * @version $Id : ExpressOrderService, v 0.1 2018/2/7 21:45 hzy24985 Exp $
 */
public interface ExpressOrderService {

    /**
     * 创建订单
     *
     * @param expressOrder
     * @throws ServiceException
     */
    void createOrder(ExpressOrderVO expressOrder) throws ServiceException;

    /**
     * 更新订单状态
     * @param updateStatusParameter
     * @throws ServiceException
     */
    void updateOrderStatus(UpdateStatusParameter updateStatusParameter) throws ServiceException;

    /**
     * 更新用户订单价格，和包裹重量
     *
     * @param updateCommodityWeightAndPriceParameter
     * @throws ServiceException
     */
    void updateCommodityWeightAndPrice(UpdateCommodityWeightAndPriceParameter updateCommodityWeightAndPriceParameter) throws ServiceException;

    /**
     * 根据订单id，查询订单
     * 
     * @param id
     * @return
     * @throws ServiceException
     */
    ExpressOrderVO queryOrderById(Long id) throws ServiceException;

    /**
     * Gets order detail info.
     *
     * @param id the id
     * @return the order detail info
     * @throws ServiceException the service exception
     */
    ExpressOrderVO getOrderDetailInfo(Long id) throws ServiceException;

    /**
     * 根据用户ID查询用户订单
     *
     * @return
     * @throws ServiceException
     */
    Collection<ExpressOrderVO> queryOrderByUserId(QueryByUserIdQuery query) throws ServiceException;

    /**
     * 根据订单流水号查询订单
     *
     * @param orderSerialNo
     * @return
     * @throws ServiceException
     */
    ExpressOrderVO queryOrderByOrderSerialNo(String orderSerialNo) throws ServiceException;

    /**
     * 分页查询厂商信息
     *
     * @param orderQuery 订单查询条件
     * @return 订单查询结果 page list
     * @throws ServiceException 分页查询异常
     */
    PageList<ExpressOrderVO> pageQueryExpressOrders(FindPageQuery orderQuery) throws ServiceException;

    /**
     * 导出excel.
     *
     * @param query        查询条件
     * @param templatePath excel模板路径
     * @return the response entity
     * @throws ServiceException the service exception
     */
    ResponseEntity<byte[]> export(GetAllByParsQuery query, String templatePath) throws ServiceException;
}
