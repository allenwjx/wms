package com.zeh.wms.web.controller.api;

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
import com.zeh.wms.biz.model.ManufacturerVO;
import com.zeh.wms.biz.service.ManufacturerService;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.controller.api.model.ManufacturerModel;

/**
 * @author allen
 * @create $ ID: ManufacturerController, 18/2/26 17:01 allen Exp $
 * @since 1.0.0
 */
@Api(value = "厂商")
@Controller("apiManufacturerController")
@RequestMapping("/api/manufacturer")
public class ManufacturerController extends BaseController {
    @Resource
    private ManufacturerService manufacturerService;

    @ApiOperation(value = "厂商列表", httpMethod = "GET")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<List<ManufacturerModel>> list() {
        try {
            List<ManufacturerVO> manufacturers = manufacturerService.getAll();
            List<ManufacturerModel> manufacturerModels = createManufacturerModels(manufacturers);
            return createSuccessResult(manufacturerModels);
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }

    private List<ManufacturerModel> createManufacturerModels(List<ManufacturerVO> manufacturers) {
        List<ManufacturerModel> manufacturerModels = Lists.newArrayList();
        for (ManufacturerVO manufacturer : manufacturers) {
            ManufacturerModel manufacturerModel = new ManufacturerModel();
            manufacturerModel.setCode(manufacturer.getCode());
            manufacturerModel.setId(manufacturer.getId());
            manufacturerModel.setName(manufacturer.getName());
            manufacturerModels.add(manufacturerModel);
        }
        return manufacturerModels;
    }
}