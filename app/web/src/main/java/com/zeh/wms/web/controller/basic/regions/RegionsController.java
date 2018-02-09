package com.zeh.wms.web.controller.basic.regions;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.Paginator;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.service.RegionsService;
import com.zeh.wms.dal.operation.region.QueryByPageQuery;
import com.zeh.wms.dal.operation.region.QueryByPageResult;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.form.RegionsForm;

/**
 * @author allen
 * @create $ ID: RegionsController, 18/2/9 15:17 allen Exp $
 * @since 1.0.0
 */
@Controller
@RequestMapping("/basic/regions")
public class RegionsController extends BaseController {
    /** 地区服务 */
    @Resource
    private RegionsService regionsService;

    /**
     * 分页查询
     *
     * @param form
     * @param paginator
     * @return
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public PageList<QueryByPageResult> list(RegionsForm form, Paginator paginator) throws ServiceException {
        QueryByPageQuery query = new QueryByPageQuery();
        query.setCode(form.getCode());
        query.setName(form.getName());
        query.setPage(paginator.getCurrentPage());
        query.setPageSize(paginator.getPageSize());
        if (form.getDistrictId() != null) {
            query.setParentId(form.getDistrictId());
        } else if (form.getCityId() != null) {
            query.setParentId(form.getCityId());
        } else if (form.getProvinceId() != null) {
            query.setParentId(form.getProvinceId());
        }
        return regionsService.pageQueryRegions(query);
    }
}