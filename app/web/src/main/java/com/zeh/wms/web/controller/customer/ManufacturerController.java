package com.zeh.wms.web.controller.customer;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.Paginator;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.jungle.utils.serializer.FastJsonUtils;
import com.zeh.jungle.web.basic.EnumUtil;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.ManufacturerVO;
import com.zeh.wms.biz.model.enums.ExpressTypeEnum;
import com.zeh.wms.biz.model.enums.SettleTypeEnum;
import com.zeh.wms.biz.service.ManufacturerService;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.form.ManufacturerForm;

/**
 * @author allen
 * @create $ ID: ManufacturerController, 18/2/7 17:37 allen Exp $
 * @since 1.0.0
 */
@Controller
@RequestMapping("/customer/manufacturer")
public class ManufacturerController extends BaseController {
    /** 厂商服务 */
    @Resource
    private ManufacturerService manufacturerService;

    /**
     * 页面初始化
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        model.addAttribute("settleTypes", EnumUtil.enumToJson(SettleTypeEnum.class));
        model.addAttribute("expresses", EnumUtil.enumToJson(ExpressTypeEnum.class));
        return "customer/manufacturer/index";
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
        model.addAttribute("settleTypes", EnumUtil.enumToJson(SettleTypeEnum.class));
        model.addAttribute("expresses", EnumUtil.enumToJson(ExpressTypeEnum.class));
        ManufacturerForm form = new ManufacturerForm();
        if (id != null) {
            ManufacturerVO manufacturer = manufacturerService.findManufacturerById(id);
            form.setId(manufacturer.getId());
            form.setCode(manufacturer.getCode());
            form.setName(manufacturer.getName());
            form.setExpress(manufacturer.getExpress().getCode());
            form.setSettleType(manufacturer.getSettleType().getCode());
        }
        String modelData = FastJsonUtils.toJSONString(form);
        model.addAttribute("modelData", modelData);
        return "customer/manufacturer/edit";
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
    public PageList<ManufacturerVO> list(ManufacturerForm form, Paginator paginator) throws ServiceException {
        ManufacturerVO manufacturer = new ManufacturerVO();
        manufacturer.setCode(form.getCode());
        manufacturer.setName(form.getName());
        manufacturer.setSettleType(SettleTypeEnum.getEnumByCode(form.getSettleType()));
        manufacturer.setExpress(ExpressTypeEnum.getEnumByCode(form.getExpress()));
        return manufacturerService.pageQueryManufacturers(manufacturer, paginator.getCurrentPage(), paginator.getPageSize());
    }

    /**
     * 创建厂商信息
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult add(@RequestBody ManufacturerForm form) {
        ManufacturerVO manufacturer = new ManufacturerVO();
        manufacturer.setExpress(ExpressTypeEnum.getEnumByCode(form.getExpress()));
        manufacturer.setSettleType(SettleTypeEnum.getEnumByCode(form.getSettleType()));
        manufacturer.setName(form.getName());
        manufacturer.setCreateBy(getCurrentUserName());
        manufacturer.setModifyBy(getCurrentUserName());
        try {
            manufacturerService.createManufacturer(manufacturer);
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }

    }

    /**
     * 更新厂商信息
     * 
     * @param form
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public SingleResult update(@RequestBody ManufacturerForm form) {
        ManufacturerVO manufacturer = new ManufacturerVO();
        manufacturer.setId(form.getId());
        manufacturer.setExpress(ExpressTypeEnum.getEnumByCode(form.getExpress()));
        manufacturer.setSettleType(SettleTypeEnum.getEnumByCode(form.getSettleType()));
        manufacturer.setName(form.getName());
        manufacturer.setModifyBy(getCurrentUserName());
        try {
            manufacturerService.updateManufacturer(manufacturer);
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult update(@PathVariable Long id) {
        if (id == null || id == 0) {
            return createErrorResult("厂商ID不能为空");
        }
        try {
            manufacturerService.deleteManufacturer(id);
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }

    }

}