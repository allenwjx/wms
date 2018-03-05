package com.zeh.wms.web.controller.api;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beust.jcommander.internal.Lists;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.AgentVO;
import com.zeh.wms.biz.service.AgentService;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.controller.api.model.AgentModel;

/**
 * @author allen
 * @create $ ID: AgentController, 18/2/26 16:56 allen Exp $
 * @since 1.0.0
 */
@Api(value = "代理人")
@Controller("apiAgentController")
@RequestMapping("/api/agent")
public class AgentController extends BaseController {
    @Resource
    private AgentService agentService;

    @ApiOperation(value = "代理人列表", httpMethod = "GET")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<List<AgentModel>> list() {
        try {
            Collection<AgentVO> agents = agentService.findAllAgents();
            List<AgentModel> agentModels = createAgentModels(agents);
            return createSuccessResult(agentModels);
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }

    @ApiOperation(value = "代理人过滤列表", httpMethod = "GET")
    @RequestMapping(value = "filterList", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<List<AgentModel>> filterList(String condition) {
        try {
            boolean isPhoneNumber = NumberUtils.isNumber(condition);
            Collection<AgentVO> agents;
            if (isPhoneNumber) {
                agents = agentService.findAllAgentsByMobile(condition);
            } else {
                agents = agentService.findAllAgentsByName(condition);
            }
            List<AgentModel> agentModels = createAgentModels(agents);
            return createSuccessResult(agentModels);
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }

    private List<AgentModel> createAgentModels(Collection<AgentVO> agents) {
        List<AgentModel> agentModels = Lists.newArrayList();
        for (AgentVO agent : agents) {
            AgentModel agentModel = new AgentModel();
            agentModel.setId(agent.getId());
            agentModel.setName(agent.getName());
            agentModel.setMobile(agent.getMobile());
            agentModels.add(agentModel);
        }
        return agentModels;
    }
}