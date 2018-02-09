package com.zeh.wms.web.controller.sale;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.Paginator;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.AuxiliaryMaterialVO;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.service.AuxiliaryMaterialService;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.form.AuxiliaryMaterialForm;

/**
 * @author allen
 * @create $ ID: AuxiliaryMaterialController, 18/2/9 13:36 allen Exp $
 * @since 1.0.0
 */
@Controller
@RequestMapping("/sale/auxiliary")
public class AuxiliaryMaterialController extends BaseController {
    /** 辅材服务 */
    @Resource
    private AuxiliaryMaterialService auxiliaryMaterialService;

    /**
     * 新增，编辑页面
     *
     * @param id 厂商ID
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public AuxiliaryMaterialForm edit(Long id) throws ServiceException {
        AuxiliaryMaterialForm form = new AuxiliaryMaterialForm();
        if (id != null) {
            AuxiliaryMaterialVO auxiliaryMaterial = auxiliaryMaterialService.findAuxiliaryMaterialsById(id);
            form.setCommodityId(auxiliaryMaterial.getCommodityId());
            form.setEnabled(auxiliaryMaterial.getEnabled().getCode());
            form.setName(auxiliaryMaterial.getName());
            form.setPrice(String.valueOf(auxiliaryMaterial.getPrice() / 100D));
            form.setQuantity(String.valueOf(auxiliaryMaterial.getQuantity()));
            form.setId(auxiliaryMaterial.getId());
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
    public PageList<AuxiliaryMaterialVO> list(AuxiliaryMaterialForm form, Paginator paginator) throws ServiceException {
        AuxiliaryMaterialVO auxiliaryMaterial = new AuxiliaryMaterialVO();
        auxiliaryMaterial.setName(StringUtils.isBlank(form.getName()) ? null : form.getName());
        auxiliaryMaterial.setCommodityId(form.getCommodityId() != null ? form.getCommodityId() : auxiliaryMaterial.getCommodityId());
        auxiliaryMaterial.setEnabled(form.getEnabled() == null ? null : StateEnum.getEnumByCode(form.getEnabled()));
        return auxiliaryMaterialService.pageQueryAuxiliaryMaterials(auxiliaryMaterial, paginator.getCurrentPage(), paginator.getPageSize());
    }

    /**
     * 创建辅材信息
     *§§
     * @param form
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult add(@RequestBody AuxiliaryMaterialForm form) {
        try {
            AuxiliaryMaterialVO auxiliaryMaterial = new AuxiliaryMaterialVO();
            auxiliaryMaterial.setCommodityId(form.getCommodityId());
            auxiliaryMaterial.setName(form.getName());
            if (StringUtils.isNotBlank(form.getPrice())) {
                auxiliaryMaterial.setPrice((int) (Double.valueOf(form.getPrice()) * 100));
            }
            if (StringUtils.isNotBlank(form.getQuantity())) {
                auxiliaryMaterial.setQuantity(Integer.valueOf(form.getQuantity()));
            }
            auxiliaryMaterial.setCreateBy(getCurrentUserName());
            auxiliaryMaterial.setModifyBy(getCurrentUserName());
            auxiliaryMaterialService.createAuxiliaryMaterials(auxiliaryMaterial);
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }

    /**
     * 更新辅材信息
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public SingleResult update(@RequestBody AuxiliaryMaterialForm form) {
        try {
            AuxiliaryMaterialVO auxiliaryMaterial = new AuxiliaryMaterialVO();
            auxiliaryMaterial.setId(form.getId());
            auxiliaryMaterial.setName(form.getName());
            auxiliaryMaterial.setCommodityId(form.getCommodityId());
            if (StringUtils.isNotBlank(form.getPrice())) {
                auxiliaryMaterial.setPrice((int) (Double.valueOf(form.getPrice()) * 100));
            }
            if (StringUtils.isNotBlank(form.getQuantity())) {
                auxiliaryMaterial.setQuantity(Integer.valueOf(form.getQuantity()));
            }
            auxiliaryMaterial.setModifyBy(getCurrentUserName());
            auxiliaryMaterialService.updateAuxiliaryMaterials(auxiliaryMaterial);
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }

    /**
     * 更新辅材状态
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/state/{id}/{enabled}", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult update(@PathVariable("id") Long id, @PathVariable("enabled") Integer enabled) {
        if (id == null || id == 0) {
            return createErrorResult("辅材ID不能为空");
        }
        try {
            auxiliaryMaterialService.updateAuxiliaryMaterialsState(id, getCurrentUserName(), StateEnum.getEnumByCode(enabled));
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }
}