package com.zeh.wms.web.controller.inventory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.AgentVO;
import com.zeh.wms.biz.model.InventoryVO;
import com.zeh.wms.biz.service.InventoryService;
import com.zeh.wms.dal.operation.inventory.FindPageQuery;
import com.zeh.wms.dal.operation.inventory.FindPageResult;
import com.zeh.wms.dal.operation.inventoryhistory.QueryHistoryQuery;
import com.zeh.wms.dal.operation.inventoryhistory.QueryHistoryResult;
import com.zeh.wms.web.constant.ExcelConstant;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.form.AgentImportModel;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.ReaderConfig;
import org.jxls.reader.XLSReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author hzy24985
 * @version $Id: InventoryController, v 0.1 2018/3/10 00:14 hzy24985 Exp $
 */
@Controller
@RequestMapping("/inventory")
public class InventoryController extends BaseController {

    private static Logger    logger = LoggerFactory.getLogger(InventoryController.class);

    @Resource
    private InventoryService inventoryService;

    /**
     * 分页查询
     *
     * @param query the query
     * @return page list
     * @throws ServiceException the service exception
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public PageList<FindPageResult> list(FindPageQuery query) throws ServiceException {
        return inventoryService.pageQueryInventory(query);
    }

    /**
     * 分页查询
     *
     * @param query the query
     * @return page list
     * @throws ServiceException the service exception
     */
    @RequestMapping(value = "history", method = RequestMethod.GET)
    @ResponseBody
    public PageList<QueryHistoryResult> historyList(QueryHistoryQuery query) throws ServiceException {
        return inventoryService.queryHistory(query);
    }

    /**
     * 导入供应商资源过滤配置数据
     *
     * @param file    导入文件
     * @param request the request
     * @return String single result
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult upload(@RequestParam("file_data") MultipartFile file, HttpServletRequest request, Long commodityId) {
        String configPath = getRealFileName(request, ExcelConstant.ANGENT_METADATE_PATH);

        try (InputStream xmlInputStream = new FileInputStream(configPath)) {
            ReaderConfig.getInstance().setSkipErrors(true);
            XLSReader reader = ReaderBuilder.buildFromXML(xmlInputStream);
            try (InputStream xlsInputStream = file.getInputStream()) {
                List<AgentImportModel> modelList = Lists.newArrayList();
                Map<String, Object> beans = Maps.newHashMap();
                beans.put("list", modelList);

                logger.info("Reading the data...");
                reader.read(xlsInputStream, beans);
                logger.info("Read " + modelList.size() + " model into `list` list");
                for (AgentImportModel model : modelList) {
                    AgentVO agentVO = new AgentVO();
                    agentVO.setAddress(model.getReceiverAddress());
                    agentVO.setExternalCode(model.getCode());
                    agentVO.setName(model.getReceiverName());
                    agentVO.setMobile(model.getTelPlainString());
                    agentVO.setCode(model.getCode());
                    agentVO.setModifyBy(getCurrentUserName());
                    agentVO.setCreateBy(getCurrentUserName());


                    InventoryVO inventory = new InventoryVO();
                    inventory.setAmount(model.getAmount());
                    inventory.setCommodityId(commodityId);
                    inventory.setMobile(agentVO.getMobile());
                    inventory.setModifyBy(getCurrentUserID());
                    inventory.setCreateBy(getCurrentUserID());
                    inventoryService.saveAgentAndInventory(agentVO, inventory);
                }
            }
        } catch (Exception e) {
            logger.error("导入失败", e);
            return createErrorResult(e);
        }
        return createSuccessResult();
    }
}
