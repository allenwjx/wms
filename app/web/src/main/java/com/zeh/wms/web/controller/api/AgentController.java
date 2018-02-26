package com.zeh.wms.web.controller.api;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.AgentVO;
import com.zeh.wms.biz.service.AgentService;
import com.zeh.wms.web.controller.BaseController;

/**
 * @author allen
 * @create $ ID: AgentController, 18/2/26 16:56 allen Exp $
 * @since 1.0.0
 */
@Api(value = "代理人接口")
@Controller("apiAgentController")
@RequestMapping("/api/agent")
public class AgentController extends BaseController {
    @Resource
    private AgentService agentService;

    @ApiOperation(value = "代理人列表", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = AgentVO.class)
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<Collection<AgentVO>> list() {
        try {
            Collection<AgentVO> agents = agentService.findAllAgents();
            return createSuccessResult(agents);
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }
}