package com.zeh.wms.web.controller.customer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.Paginator;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.jungle.utils.serializer.FastJsonUtils;
import com.zeh.jungle.web.basic.EnumUtil;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.AgentVO;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.service.AgentService;
import com.zeh.wms.web.constant.ExcelConstant;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.form.AgentForm;
import com.zeh.wms.web.form.AgentImportModel;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.ReaderConfig;
import org.jxls.reader.XLSReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author allen
 * @create $ ID: AgentController, 18/2/8 13:46 allen Exp $
 * @since 1.0.0
 */
@Controller
@RequestMapping("/customer/agent")
public class AgentController extends BaseController {
    /** 代理人服务 */
    @Resource
    private AgentService agentService;

    /**
     * 页面初始化
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        model.addAttribute("states", EnumUtil.enumToJson(StateEnum.class));
        return "customer/agent/index";
    }

    /**
     * 新增，编辑页面
     *
     * @param id 厂商ID
     * @param model
     * @return
     */
    @RequestMapping("edit")
    public String edit(Long id, Model model) throws ServiceException {
        AgentForm form = new AgentForm();
        if (id != null) {
            AgentVO agent = agentService.findAgentById(id);
            form.setId(agent.getId());
            form.setCode(agent.getCode());
            form.setAddress(agent.getAddress());
            form.setExternalCode(agent.getExternalCode());
            form.setMobile(agent.getMobile());
            form.setName(agent.getName());
        }
        String modelData = FastJsonUtils.toJSONString(form);
        model.addAttribute("modelData", modelData);
        return "customer/agent/edit";
    }

    /**
     * 分页查询
     *
     * @param form
     * @param paginator
     * @return
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public PageList<AgentVO> list(AgentForm form, Paginator paginator) throws ServiceException {
        AgentVO agent = new AgentVO();
        agent.setCode(form.getCode());
        agent.setName(form.getName());
        agent.setExternalCode(form.getExternalCode());
        agent.setMobile(form.getMobile());
        agent.setEnabled(form.getEnabled() == null ? null : StateEnum.getEnumByCode(form.getEnabled()));
        return agentService.pageQueryAgents(agent, paginator.getCurrentPage(), paginator.getPageSize());
    }

    /**
     * 创建代理人信息
     *§§
     * @param form
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult add(@RequestBody AgentForm form) {
        AgentVO agent = new AgentVO();
        agent.setName(form.getName());
        agent.setMobile(form.getMobile());
        agent.setExternalCode(form.getExternalCode());
        agent.setAddress(form.getAddress());
        agent.setCreateBy(getCurrentUserName());
        agent.setModifyBy(getCurrentUserName());
        try {
            agentService.createAgent(agent);
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }

    /**
     * 更新代理人信息
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public SingleResult update(@RequestBody AgentForm form) {
        AgentVO agent = new AgentVO();
        agent.setId(form.getId());
        agent.setName(form.getName());
        agent.setMobile(form.getMobile());
        agent.setExternalCode(form.getExternalCode());
        agent.setAddress(form.getAddress());
        agent.setModifyBy(getCurrentUserName());
        try {
            agentService.updateAgent(agent);
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }

    /**
     * 更新代理人状态
     * @param id
     * @return
     */
    @RequestMapping(value = "/state/{id}/{enabled}", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult update(@PathVariable("id") Long id, @PathVariable("enabled") Integer enabled) {
        if (id == null || id == 0) {
            return createErrorResult("代理人ID不能为空");
        }
        try {
            agentService.updateAgentState(id, getCurrentUserName(), StateEnum.getEnumByCode(enabled));
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }

    /**
     * 导入供应商资源过滤配置数据
     *
     * @param file 导入文件
     * @return String
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file_data") MultipartFile file, HttpServletRequest request) {
        String configPath = getRealFileName(request, ExcelConstant.ANGENT_METADATE_PATH);

        try(InputStream xmlInputStream = new FileInputStream(configPath)) {
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
                    logger.info(model.toString());
                    // TODO: 2018/2/9 导入到数据库里的逻辑。 
                }
            }
        } catch (Exception e) {
            logger.error("导入失败", e);
        }
        return "OK";
    }
}