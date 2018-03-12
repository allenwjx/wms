package com.zeh.wms.web.controller.api;

import com.beust.jcommander.internal.Lists;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.CommodityVO;
import com.zeh.wms.biz.service.CommodityService;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.controller.api.model.CommodityModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

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
    public SingleResult<List<CommodityModel>> list() {
        try {
            Collection<CommodityVO> commodities = commodityService.findAllCommodities();
            List<CommodityModel> commodityModels = createCommodityModels(commodities);
            return createSuccessResult(commodityModels);
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }

    @ApiOperation(value = "厂商商品列表", httpMethod = "GET")
    @RequestMapping(value = "filterList", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<List<CommodityModel>> filterList(String manufacturerId) {
        try {
            Collection<CommodityVO> commodities = commodityService.findByManufacturer(Long.valueOf(manufacturerId));
            List<CommodityModel> commodityModels = createCommodityModels(commodities);
            return createSuccessResult(commodityModels);
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }

    private List<CommodityModel> createCommodityModels(Collection<CommodityVO> commodities) {
        List<CommodityModel> commodityModels = Lists.newArrayList();
        for (CommodityVO commodity : commodities) {
            CommodityModel commodityModel = new CommodityModel();
            commodityModel.setCode(commodity.getCode());
            commodityModel.setDescription(commodity.getDescription());
            commodityModel.setId(commodity.getId());
            commodityModel.setManufacturerId(commodity.getManufacturerId());
            commodityModel.setName(commodity.getName());
            commodityModel.setPrice(commodity.getPrice());
            commodityModel.setUnit(commodity.getUnit());
            commodityModel.setWeight(commodity.getWeight());
            commodityModels.add(commodityModel);
        }
        return commodityModels;
    }
}