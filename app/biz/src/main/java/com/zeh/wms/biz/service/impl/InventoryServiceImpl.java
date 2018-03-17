package com.zeh.wms.biz.service.impl;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageUtils;
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
import com.zeh.wms.dal.operation.inventoryhistory.QueryHistoryQuery;
import com.zeh.wms.dal.operation.inventoryhistory.QueryHistoryResult;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type Inventory service.
 *
 * @author hzy24985
 * @version $Id : InventoryServiceImpl, v 0.1 2018/3/10 00:26 hzy24985 Exp $
 */
@Service
public class InventoryServiceImpl extends AbstractService implements InventoryService {
    private static Logger       logger = LoggerFactory.getLogger(InventoryServiceImpl.class);
    /** 库存仓储 */
    @Resource
    private InventoryDAO        inventoryDAO;

    /** 库存历史仓储 */
    @Resource
    private InventoryHistoryDAO inventoryHistoryDAO;

    /** 代理商服务 */
    @Resource
    private AgentService        agentService;

    /** 库存模型转换工具 */
    @Resource
    private InventoryMapper     inventoryMapper;

    /**
     * 分页查询.
     * @param query the query
     * @return FindPageResult
     */
    @Override
    public PageList<FindPageResult> pageQueryInventory(FindPageQuery query) {
        PageList<FindPageResult> ret = inventoryDAO.findPage(query);
        return PageUtils.createPageList(ret.getData(), ret.getPaginator());
    }

    /**
     * 分页查询.
     * @param param the query
     * @return FindPageResult
     */
    @Override
    public PageList<QueryHistoryResult> queryHistory(QueryHistoryQuery param) {
        return inventoryHistoryDAO.queryHistory(param);
    }

    /**
     * 保存代理商和库存信息.
     * @param agent     the agent
     * @param inventory the inventory
     * @return the inventory vo
     * @throws ServiceException service exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public InventoryVO saveAgentAndInventory(AgentVO agent, InventoryVO inventory) throws ServiceException {
        agentService.saveOrUpdate(agent);

        return saveOrUpdate(inventory);
    }

    /**
     * 保存或更新库存，同时库存历史表插入记录.
     * @param inventory the inventory
     * @return the inventory vo
     * @throws ServiceException  service exception
     */
    @Override
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
            parameter.setAmount(inventory.getAmount());
            checkUpdate(inventoryDAO.addAmountByMobile(parameter), "库存");
        }

        InventoryHistoryDO historyDO = inventoryMapper.vo2HistoryDo(inventory);
        inventoryHistoryDAO.insert(historyDO);
        return inventory;
    }

    /**
     * 根据电话和代理人姓名查询库存信息.
     * @param mobile the mobile
     * @param name   the name
     * @return the inventory vo list
     */
    @Override
    public List<GetInfoByMobileResult> getInfoByMobileAndName(String mobile, String name) {
        return inventoryDAO.getInfoByMobile(new GetInfoByMobileQuery(mobile, null, name));
    }

    /**
     * 根据电话和库存id查询库存信息.
     * @param mobile the mobile
     * @param id     the id
     * @return the inventory vo
     */
    @Override
    public GetInfoByMobileResult getInfoByMobileAndId(String mobile, Long id) {
        List<GetInfoByMobileResult> list = inventoryDAO.getInfoByMobile(new GetInfoByMobileQuery(mobile, id, null));
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据电话和商品id，查询库存信息.
     * @param mobile      the mobile
     * @param commodityId the commodity id
     * @return the inventory vo
     * @throws ServiceException service exception
     */
    @Override
    public InventoryVO queryByMobileAndCommodityId(String mobile, Long commodityId) throws ServiceException {
        InventoryDO inventoryDO = inventoryDAO.queryByMobileAndCommodityId(mobile, commodityId);
        return inventoryMapper.do2vo(inventoryDO);
    }
}
