package com.zeh.wms.web.controller.api;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.AgentVO;
import com.zeh.wms.biz.model.CommodityVO;
import com.zeh.wms.biz.service.AgentService;
import com.zeh.wms.biz.service.CommodityService;
import com.zeh.wms.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author allen
 * @create $ ID: CommodityController, 18/2/26 17:01 allen Exp $
 * @since 1.0.0
 */
@Api(value = "代理人接口")
@Controller("apiCommodityController")
@RequestMapping("/api/commodity")
public class CommodityController extends BaseController {
    @Resource
    private CommodityService commodityService;

    @ApiOperation(value = "商品列表", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = CommodityVO.class)
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<Collection<CommodityVO>> list() {
        try {
            Collection<CommodityVO> agents = commodityService.findAllCommodities();
            return createSuccessResult(agents);
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }
}