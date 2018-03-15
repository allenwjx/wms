package com.zeh.wms.web.controller.api;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.service.InventoryService;
import com.zeh.wms.dal.operation.inventory.GetInfoByMobileResult;
import com.zeh.wms.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hzy24985
 * @version $Id: InventoryController, v 0.1 2018/3/12 20:53 hzy24985 Exp $
 */
@Api(value = "地址管理")
@Controller
@RequestMapping("/api/inventory")
public class ApiInventoryController extends BaseController {

    @Resource
    private InventoryService inventoryService;

    @ApiOperation(value = "商品列表", httpMethod = "GET")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<List<GetInfoByMobileResult>> list(String name) {
        try {
            if (getCurrentApiUser() == null || StringUtils.isBlank(getCurrentApiUser().getMobile())) {
                return createSuccessResult();
            }
            List<GetInfoByMobileResult> result = inventoryService.getInfoByMobileAndName(getCurrentApiUser().getMobile(), name);
            return createSuccessResult(result);
        } catch (Exception e) {
            return createErrorResult(e);
        }
    }

    @ApiOperation(value = "商品详情", httpMethod = "GET")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<GetInfoByMobileResult> detail(Long id) {
        try {
            if (getCurrentApiUser() == null || StringUtils.isBlank(getCurrentApiUser().getMobile())) {
                return createSuccessResult();
            }
            GetInfoByMobileResult result = inventoryService.getInfoByMobileAndId(getCurrentApiUser().getMobile(), id);
            return createSuccessResult(result);
        } catch (Exception e) {
            return createErrorResult(e);
        }
    }
}
