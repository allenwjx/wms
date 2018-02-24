package com.zeh.wms.web.controller.basic.security;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.Paginator;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.AuthorizationVO;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.service.AuthorizationService;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.form.AuthorizationForm;

/**
 * @author allen
 * @create $ ID: AuthorizationController, 18/2/23 14:38 allen Exp $
 * @since 1.0.0
 */
@Controller
@RequestMapping("/basic/auth")
public class AuthorizationController extends BaseController {
    /** 资源权限服务 */
    @Resource
    private AuthorizationService authorizationService;

    /**
     * 新增，编辑页面
     *
     * @param id 资源权限ID
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public AuthorizationForm edit(Long id) throws ServiceException {
        AuthorizationForm form = new AuthorizationForm();
        if (id != null) {
            AuthorizationVO authorization = authorizationService.findAuthorizationById(id);
            form.setCode(authorization.getCode());
            form.setEnabled(authorization.getEnabled().getCode());
            form.setId(authorization.getId());
            form.setName(authorization.getName());
            form.setPath(authorization.getPath());
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
    public PageList<AuthorizationVO> list(AuthorizationForm form, Paginator paginator) throws ServiceException {
        AuthorizationVO authorization = new AuthorizationVO();
        authorization.setCode(form.getCode());
        authorization.setName(form.getName());
        authorization.setPath(form.getPath());
        authorization.setEnabled(form.getEnabled() == null ? null : StateEnum.getEnumByCode(form.getEnabled()));
        return authorizationService.pageQueryAuthorizations(authorization, paginator.getCurrentPage(), paginator.getPageSize());
    }

    /**
     * 创建商品信息
     *§§
     * @param form
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult add(@RequestBody AuthorizationForm form) {
        try {
            AuthorizationVO authorization = new AuthorizationVO();
            authorization.setName(form.getName());
            authorization.setPath(form.getPath());
            authorization.setCreateBy(getCurrentUserName());
            authorization.setModifyBy(getCurrentUserName());
            authorizationService.createAuthorization(authorization);
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
    public SingleResult update(@RequestBody AuthorizationForm form) {
        try {
            AuthorizationVO authorization = new AuthorizationVO();
            authorization.setId(form.getId());
            authorization.setName(form.getName());
            authorization.setPath(form.getPath());
            authorization.setModifyBy(getCurrentUserName());
            authorizationService.updateAuthorization(authorization);
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
            return createErrorResult("资源权限ID不能为空");
        }
        try {
            authorizationService.updateAuthorizationState(id, getCurrentUserName(), StateEnum.getEnumByCode(enabled));
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }
}