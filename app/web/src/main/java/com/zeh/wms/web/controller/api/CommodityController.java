package com.zeh.wms.web.controller.api;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beust.jcommander.internal.Lists;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.CommodityVO;
import com.zeh.wms.biz.service.CommodityService;
import com.zeh.wms.web.controller.BaseController;

/**
 * @author allen
 * @create $ ID: CommodityController, 18/2/26 17:01 allen Exp $
 * @since 1.0.0
 */
@Api(value = "商品")
@Controller("apiCommodityController")
@RequestMapping("/api/commodity")
public class CommodityController extends BaseController {
    @Resource
    private CommodityService commodityService;

    @ApiOperation(value = "商品列表", httpMethod = "GET")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<List<CommodityVO>> list() {
        try {
            Collection<CommodityVO> commodities = commodityService.findAllCommodities();
            return createSuccessResult(Lists.newArrayList(commodities));
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }
}