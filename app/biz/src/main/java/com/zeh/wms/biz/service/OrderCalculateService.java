package com.zeh.wms.biz.service;

import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.calate.OrderCalculatePriceVO;

/**
 * The interface Order calculate service.
 *
 * @author hzy24985
 * @version $Id : OrderCalculateService, v 0.1 2018/3/14 21:36 hzy24985 Exp $
 */
public interface OrderCalculateService {

    /**
     * Inventory order price int.
     *
     * @param expressCode    the express code
     * @param targetProvince the target province
     * @param totalWeight    the total weight
     * @param userId         the user id
     * @return the order calculate price vo
     * @throws ServiceException the service exception
     */
    OrderCalculatePriceVO inventoryOrderPrice(String expressCode, String targetProvince, int totalWeight, long userId) throws ServiceException;
}
