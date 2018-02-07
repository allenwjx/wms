package com.zeh.wms.web.controller.customer;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.Paginator;
import com.zeh.jungle.web.basic.EnumUtil;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.ManufacturerVO;
import com.zeh.wms.biz.model.enums.ExpressTypeEnum;
import com.zeh.wms.biz.model.enums.SettleTypeEnum;
import com.zeh.wms.biz.service.ManufacturerService;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.form.ManufacturerFrom;

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
     * 分页查询
     *
     * @param form
     * @param paginator
     * @return
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public PageList<ManufacturerVO> list(ManufacturerFrom form, Paginator paginator) throws ServiceException {
        ManufacturerVO manufacturer = new ManufacturerVO();
        //        manufacturer.setCode(form.get);
        return manufacturerService.pageQueryManufacturers(manufacturer, paginator.getCurrentPage(), paginator.getPageSize());
    }

}