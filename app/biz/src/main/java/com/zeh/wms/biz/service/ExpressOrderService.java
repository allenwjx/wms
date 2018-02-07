package com.zeh.wms.biz.service;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.ExpressOrderVO;
import com.zeh.wms.dal.operation.expressorder.FindPageQuery;

/**
 * 订单相关查询.
 * @author hzy24985
 * @version $Id: ExpressOrderService, v 0.1 2018/2/7 21:45 hzy24985 Exp $
 */
public interface ExpressOrderService {

    /**
     * 分页查询厂商信息
     *
     * @param orderQuery 订单查询条件
     * @return 订单查询结果
     * @throws ServiceException 分页查询异常
     */
    PageList<ExpressOrderVO> pageQueryExpressOrders(FindPageQuery orderQuery) throws ServiceException;
}
