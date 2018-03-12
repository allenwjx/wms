package com.zeh.wms.biz.service.impl;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.InventoryMapper;
import com.zeh.wms.biz.model.AgentVO;
import com.zeh.wms.biz.model.InventoryVO;
import com.zeh.wms.biz.service.AgentService;
import com.zeh.wms.biz.service.InventoryService;
import com.zeh.wms.dal.daointerface.InventoryDAO;
import com.zeh.wms.dal.daointerface.InventoryHistoryDAO;
import com.zeh.wms.dal.dataobject.InventoryDO;
import com.zeh.wms.dal.dataobject.InventoryHistoryDO;
import com.zeh.wms.dal.operation.inventory.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hzy24985
 * @version $Id: InventoryServiceImpl, v 0.1 2018/3/10 00:26 hzy24985 Exp $
 */
@Service
public class InventoryServiceImpl extends AbstractService implements InventoryService {
    private static Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Resource
    private InventoryDAO inventoryDAO;
    @Resource
    private InventoryHistoryDAO inventoryHistoryDAO;

    @Resource
    private AgentService agentService;
    @Resource
    private InventoryMapper inventoryMapper;

    @Override
    public PageList<FindPageResult> pageQueryInventory(FindPageQuery query) {
        return inventoryDAO.findPage(query);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public InventoryVO saveAgentAndInventory(AgentVO agent, InventoryVO inventory) throws ServiceException {
        agentService.saveOrUpdate(agent);

        return saveOrUpdate(inventory);
    }

    public InventoryVO saveOrUpdate(InventoryVO inventory) throws ServiceException {
        InventoryDO inventoryDO = inventoryDAO.queryByMobileAndCommodityId(inventory.getMobile(), inventory.getCommodityId());
        if (inventoryDO == null) {
            inventoryDO = inventoryMapper.vo2do(inventory);
            long id = inventoryDAO.insert(inventoryDO);
            checkInsert(id, "库存");
            inventory.setId(id);
        } else {
            AddAmountByMobileParameter parameter = new AddAmountByMobileParameter();
            parameter.setCommodityId(inventory.getCommodityId());
            parameter.setMobile(inventory.getMobile());
            parameter.setModifyBy(inventory.getModifyBy());
            parameter.setAmount(inventoryDO.getAmount() + inventory.getAmount());
            checkUpdate(inventoryDAO.addAmountByMobile(parameter), "库存");
        }

        InventoryHistoryDO historyDO = inventoryMapper.vo2HistoryDo(inventory);
        inventoryHistoryDAO.insert(historyDO);
        return inventory;
    }

    @Override
    public List<GetInfoByMobileResult> getInfoByMobileAndName(String mobile, String name) {
        return inventoryDAO.getInfoByMobile(new GetInfoByMobileQuery(mobile, null, name));
    }
}
