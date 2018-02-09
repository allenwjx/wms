package com.zeh.wms.web.controller.sale;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.Paginator;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.CommodityVO;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.service.CommodityService;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.form.CommodityForm;

/**
 * @author allen
 * @create $ ID: CommodityController, 18/2/8 18:53 allen Exp $
 * @since 1.0.0
 */
@Controller
@RequestMapping("/sale/commodity")
public class CommodityController extends BaseController {
    /** 代理人服务 */
    @Resource
    private CommodityService commodityService;

    /**
     * 新增，编辑页面
     *
     * @param id 厂商ID
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public CommodityForm edit(Long id) throws ServiceException {
        CommodityForm form = new CommodityForm();
        if (id != null) {
            CommodityVO commodity = commodityService.findCommodityById(id);
            form.setCode(commodity.getCode());
            form.setDescription(commodity.getDescription());
            form.setEnabled(commodity.getEnabled().getCode());
            form.setId(commodity.getId());
            form.setManufacturerId(commodity.getManufacturerId());
            form.setName(commodity.getName());
            form.setPrice(String.valueOf(commodity.getPrice() / 100D));
            form.setUnit(commodity.getUnit());
            form.setWeight(String.valueOf(commodity.getWeight() / 1000D));
        }
        return form;
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
    public PageList<CommodityVO> list(CommodityForm form, Paginator paginator) throws ServiceException {
        CommodityVO commodity = new CommodityVO();
        commodity.setCode(form.getCode());
        commodity.setName(form.getName());
        commodity.setEnabled(form.getEnabled() == null ? null : StateEnum.getEnumByCode(form.getEnabled()));
        commodity.setManufacturerId(form.getManufacturerId());
        return commodityService.pageQueryCommodities(commodity, paginator.getCurrentPage(), paginator.getPageSize());
    }

    /**
     * 创建商品信息
     *§§
     * @param form
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult add(@RequestBody CommodityForm form) {
        try {
            CommodityVO commodity = new CommodityVO();
            commodity.setName(form.getName());
            commodity.setManufacturerId(form.getManufacturerId());
            commodity.setDescription(form.getDescription());
            if (StringUtils.isNotBlank(form.getPrice())) {
                commodity.setPrice((int) (Double.valueOf(form.getPrice()) * 100));
            }
            commodity.setUnit(form.getUnit());
            if (StringUtils.isNotBlank(form.getWeight())) {
                commodity.setWeight((int) (Double.valueOf(form.getWeight()) * 1000));
            }
            commodity.setCreateBy(getCurrentUserName());
            commodity.setModifyBy(getCurrentUserName());
            commodityService.createCommodity(commodity);
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }

    /**
     * 更新商品信息
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public SingleResult update(@RequestBody CommodityForm form) {
        try {
            CommodityVO commodity = new CommodityVO();
            commodity.setId(form.getId());
            commodity.setName(form.getName());
            commodity.setManufacturerId(form.getManufacturerId());
            commodity.setDescription(form.getDescription());
            if (StringUtils.isNotBlank(form.getPrice())) {
                commodity.setPrice((int) (Double.valueOf(form.getPrice()) * 100));
            }
            commodity.setUnit(form.getUnit());
            if (StringUtils.isNotBlank(form.getWeight())) {
                commodity.setWeight((int) (Double.valueOf(form.getWeight()) * 1000));
            }
            commodity.setModifyBy(getCurrentUserName());
            commodityService.updateCommodity(commodity);
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }

    /**
     * 更新商品状态
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/state/{id}/{enabled}", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult update(@PathVariable("id") Long id, @PathVariable("enabled") Integer enabled) {
        if (id == null || id == 0) {
            return createErrorResult("商品ID不能为空");
        }
        try {
            commodityService.updateCommodityState(id, getCurrentUserName(), StateEnum.getEnumByCode(enabled));
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }
}