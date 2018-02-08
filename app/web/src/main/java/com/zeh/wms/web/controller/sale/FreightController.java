package com.zeh.wms.web.controller.sale;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.Paginator;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.FreightVO;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.service.FreightService;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.form.FreightForm;

/**
 * @author allen
 * @create $ ID: FreightController, 18/2/9 01:14 allen Exp $
 * @since 1.0.0
 */
@Controller
@RequestMapping("/sale/freight")
public class FreightController extends BaseController {
    /** 运价服务 */
    @Resource
    private FreightService freightService;

    /**
     * 新增，编辑页面
     *
     * @param id 厂商ID
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public FreightForm edit(Long id) throws ServiceException {
        FreightForm form = new FreightForm();
        if (id != null) {
            FreightVO freight = freightService.findFreightById(id);
            form.setAdditionalPrice(String.valueOf(freight.getAdditionalPrice() / 100D));
            form.setFirstPrice(String.valueOf(freight.getFirstPrice() / 100D));
            form.setFirstWeight(String.valueOf(freight.getFirstWeight() / 500D));
            form.setProvinceCode(freight.getProvinceCode());
            form.setEnabled(freight.getEnabled().getCode());
            form.setId(freight.getId());
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
    public PageList<FreightVO> list(FreightForm form, Paginator paginator) throws ServiceException {
        FreightVO freight = new FreightVO();
        freight.setProvinceCode(form.getProvinceCode());
        freight.setEnabled(form.getEnabled() == null ? null : StateEnum.getEnumByCode(form.getEnabled()));
        return freightService.pageQueryFreights(freight, paginator.getCurrentPage(), paginator.getPageSize());
    }

    /**
     * 创建运价信息
     *§§
     * @param form
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult add(@RequestBody FreightForm form) {
        try {
            FreightVO freight = new FreightVO();
            freight.setProvinceCode(form.getProvinceCode());
            if (StringUtils.isNotBlank(form.getAdditionalPrice())) {
                freight.setAdditionalPrice((int) (Double.valueOf(form.getAdditionalPrice()) * 100));
            }
            if (StringUtils.isNotBlank(form.getFirstPrice())) {
                freight.setFirstPrice((int) (Double.valueOf(form.getFirstPrice()) * 100));
            }
            if (StringUtils.isNotBlank(form.getFirstWeight())) {
                freight.setFirstWeight((int) (Double.valueOf(form.getFirstWeight()) * 500));
            }
            freight.setCreateBy(getCurrentUserName());
            freight.setModifyBy(getCurrentUserName());
            freightService.createFreight(freight);
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }

    /**
     * 更新运价信息
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public SingleResult update(@RequestBody FreightForm form) {
        try {
            FreightVO freight = new FreightVO();
            freight.setId(form.getId());
            freight.setProvinceCode(form.getProvinceCode());
            if (StringUtils.isNotBlank(form.getAdditionalPrice())) {
                freight.setAdditionalPrice((int) (Double.valueOf(form.getAdditionalPrice()) * 100));
            }
            if (StringUtils.isNotBlank(form.getFirstPrice())) {
                freight.setFirstPrice((int) (Double.valueOf(form.getFirstPrice()) * 100));
            }
            if (StringUtils.isNotBlank(form.getFirstWeight())) {
                freight.setFirstWeight((int) (Double.valueOf(form.getFirstWeight()) * 500));
            }
            freight.setModifyBy(getCurrentUserName());
            freightService.updateFreight(freight);
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }

    /**
     * 更新运价状态
     * @param id
     * @return
     */
    @RequestMapping(value = "/state/{id}/{enabled}", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult update(@PathVariable("id") Long id, @PathVariable("enabled") Integer enabled) {
        if (id == null || id == 0) {
            return createErrorResult("运价ID不能为空");
        }
        try {
            freightService.updateFreightState(id, getCurrentUserName(), StateEnum.getEnumByCode(enabled));
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }
}