package com.zeh.wms.web.controller.sale;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.Paginator;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.FreightVO;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.service.FreightService;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.exception.WebException;
import com.zeh.wms.web.form.FreightForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
            form.setExpressCode(freight.getExpressCode());
            form.setAdditionalOriginalPrice(String.valueOf(freight.getAdditionalOriginalPrice() / 100D));
            form.setFirstOriginalPrice(String.valueOf(freight.getFirstOriginalPrice() / 100D));
            form.setFirstWeight(String.valueOf(freight.getFirstWeight() / 500D));

            form.setAdditionalCostPrice(String.valueOf(freight.getAdditionalCostPrice() / 100D));
            form.setFirstCostPrice(String.valueOf(freight.getFirstCostPrice() / 500D));
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
        if (form != null) {
            freight.setExpressCode(form.getExpressCode());
            freight.setEnabled(form.getEnabled() == null ? null : StateEnum.getEnumByCode(form.getEnabled()));
            freight.setProvinceCode(form.getProvinceCode());
        }
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
            validateForm(form);

            FreightVO freight = getFreightVO(form);

            freightService.createFreight(freight);
            return createSuccessResult();
        } catch (ServiceException | WebException e) {
            return createErrorResult(e);
        }
    }

    private FreightVO getFreightVO(@RequestBody FreightForm form) {
        FreightVO freight = new FreightVO();
        freight.setProvinceCode(form.getProvinceCode());
        freight.setExpressCode(form.getExpressCode());
        freight.setAdditionalOriginalPrice((int) (Double.valueOf(form.getAdditionalOriginalPrice()) * 100));
        freight.setFirstOriginalPrice((int) (Double.valueOf(form.getFirstOriginalPrice()) * 100));
        freight.setFirstWeight((int) (Double.valueOf(form.getFirstWeight()) * 500));
        freight.setFirstCostPrice((int) (Double.valueOf(form.getFirstCostPrice()) * 100));
        freight.setAdditionalCostPrice((int) (Double.valueOf(form.getAdditionalCostPrice()) * 100));

        freight.setCreateBy(getCurrentUserName());
        freight.setModifyBy(getCurrentUserName());
        return freight;
    }

    private void validateForm(@RequestBody FreightForm form) throws WebException {
        assertEmpty(form.getAdditionalOriginalPrice(), "续重原始价");
        assertEmpty(form.getFirstOriginalPrice(), "首重原始价");
        assertEmpty(form.getFirstWeight(), "首重");
        assertEmpty(form.getFirstCostPrice(), "首重成本价");
        assertEmpty(form.getAdditionalCostPrice(), "续重成本价");
        assertEmpty(form.getExpressCode(), "物流公司");
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
            validateForm(form);
            FreightVO freight = getFreightVO(form);
            freight.setId(form.getId());
            freight.setModifyBy(getCurrentUserName());
            freightService.updateFreight(freight);
            return createSuccessResult();
        } catch (ServiceException | WebException e) {
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