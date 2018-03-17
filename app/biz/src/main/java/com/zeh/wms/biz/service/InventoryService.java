package com.zeh.wms.biz.service;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.AgentVO;
import com.zeh.wms.biz.model.InventoryVO;
import com.zeh.wms.dal.operation.inventory.FindPageQuery;
import com.zeh.wms.dal.operation.inventory.FindPageResult;
import com.zeh.wms.dal.operation.inventory.GetInfoByMobileResult;
import com.zeh.wms.dal.operation.inventoryhistory.QueryHistoryQuery;
import com.zeh.wms.dal.operation.inventoryhistory.QueryHistoryResult;

import java.util.List;

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

    /**
     * Query history page list.
     *
     * @param param the param
     * @return the page list
     */
    PageList<QueryHistoryResult> queryHistory(QueryHistoryQuery param);

    /**
     * Save agent and inventory inventory vo.
     *
     * @param agent     the agent
     * @param inventory the inventory
     * @return the inventory vo
     * @throws ServiceException the service exception
     */
    InventoryVO saveAgentAndInventory(AgentVO agent, InventoryVO inventory) throws ServiceException;

    /**
     * Get info by mobile and name list.
     *
     * @param mobile the mobile
     * @param name   the name
     * @return the list
     */
    List<GetInfoByMobileResult> getInfoByMobileAndName(String mobile, String name);

    /**
     * Gets info by mobile and id.
     *
     * @param mobile the mobile
     * @param id     the id
     * @return the info by mobile and id
     */
    GetInfoByMobileResult getInfoByMobileAndId(String mobile, Long id);

    /**
     * Save or update inventory vo.
     *
     * @param inventory the inventory
     * @return the inventory vo
     * @throws ServiceException the service exception
     */
    InventoryVO saveOrUpdate(InventoryVO inventory) throws ServiceException;

    /**
     * Query by mobile and commodity id inventory vo.
     *
     * @param mobile      the mobile
     * @param commodityId the commodity id
     * @return the inventory vo
     * @throws ServiceException the service exception
     */
    InventoryVO queryByMobileAndCommodityId(String mobile, Long commodityId) throws ServiceException;
}
