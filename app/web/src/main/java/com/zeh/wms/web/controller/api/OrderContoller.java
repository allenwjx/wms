package com.zeh.wms.web.controller.api;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.FreightVO;
import com.zeh.wms.biz.service.FreightService;
import com.zeh.wms.biz.service.InventoryService;
import com.zeh.wms.biz.service.UserService;
import com.zeh.wms.dal.operation.inventory.GetInfoByMobileResult;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.controller.api.model.OrderCalculateModel;
import com.zeh.wms.web.controller.api.model.OrderCalculatePriceModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author hzy24985
 * @version $Id: OrderContoller, v 0.1 2018/2/24 14:01 hzy24985 Exp $
 */
@Api(value = "订单管理")
@Controller
@RequestMapping("/api/order")
public class OrderContoller extends BaseController{

    @Resource
    private UserService userService;

    @Resource
    private InventoryService inventoryService;
    @Resource
    private FreightService freightService;

    @ApiOperation(value = "获得订单列表", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "orders", method = RequestMethod.GET)
    @ResponseBody
    public String getOrderList() {
        return "OK";
    }

    @ApiOperation(value = "下单", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "order", method = RequestMethod.POST)
    @ResponseBody
    public String createOrder() {
        return "OK";
    }

    @ApiOperation(value = "订单详情", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "order", method = RequestMethod.GET)
    @ResponseBody
    public String orderDetail() {
        return "OK";
    }

    @ApiOperation(value = "生成支付单", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "pay", method = RequestMethod.POST)
    @ResponseBody
    public String pay() {
        return "OK";
    }

    @ApiOperation(value = "计算总费用", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "calculate", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult<OrderCalculatePriceModel> calculate(@RequestBody OrderCalculateModel calculateModel) throws ServiceException {
        getCurrentApiUser();

        BigDecimal discount = userService.getDiscount(getCurrentApiUserId(), calculateModel.getExpressType());

        GetInfoByMobileResult inventory = inventoryService.getInfoByMobileAndId(getCurrentApiUser().getMobile(), calculateModel.getCommodityId());

        BigDecimal wight = new BigDecimal(inventory.getWeight()).multiply(BigDecimal.valueOf(calculateModel.getCommodityQuantity()));

        FreightVO freightVO = freightService.findByProvinceName(calculateModel.getProvince());
        return createSuccessResult();
    }
}
