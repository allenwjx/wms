package com.zeh.wms.biz.service;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.AgentVO;
import com.zeh.wms.biz.model.InventoryVO;
import com.zeh.wms.dal.operation.inventory.FindPageQuery;
import com.zeh.wms.dal.operation.inventory.FindPageResult;

/**
 * The interface Inventory service.
 *
 * @author hzy24985
 * @version $Id : InventoryService, v 0.1 2018/3/10 00:25 hzy24985 Exp $
 */
public interface InventoryService {

    /**
     * Page query inventory page list.
     *
     * @param query the query
     * @return the page list
     */
    PageList<FindPageResult> pageQueryInventory(FindPageQuery query);

    InventoryVO saveAgentAndInventory(AgentVO agent, InventoryVO inventory)  throws ServiceException;
}
