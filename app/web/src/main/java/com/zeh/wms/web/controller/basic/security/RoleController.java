package com.zeh.wms.web.controller.basic.security;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Lists;
import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.Paginator;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.AuthorizationVO;
import com.zeh.wms.biz.model.RoleVO;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.service.RoleService;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.form.RoleForm;

/**
 * @author allen
 * @create $ ID: RoleController, 18/2/23 17:03 allen Exp $
 * @since 1.0.0
 */
@Controller
@RequestMapping("/basic/role")
public class RoleController extends BaseController {
    /** 角色服务 */
    @Resource
    private RoleService roleService;

    /**
     * 新增，编辑页面
     *
     * @param id 资源权限ID
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public RoleForm edit(Long id) throws ServiceException {
        RoleForm form = new RoleForm();
        if (id != null) {
            RoleVO role = roleService.findRoleDetailsById(id);
            form.setEnabled(role.getEnabled().getCode());
            form.setId(role.getId());
            form.setName(role.getName());
            for (AuthorizationVO authorization : role.getAuthorizations()) {
                form.getAuthorizations().add(String.valueOf(authorization.getId()));
            }
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
    public PageList<RoleVO> list(RoleForm form, Paginator paginator) throws ServiceException {
        RoleVO role = new RoleVO();
        role.setName(form.getName());
        role.setEnabled(form.getEnabled() == null ? null : StateEnum.getEnumByCode(form.getEnabled()));
        return roleService.pageQueryRoles(role, paginator.getCurrentPage(), paginator.getPageSize());
    }

    /**
     * 创建角色
     *§§
     * @param form
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult add(@RequestBody RoleForm form) {
        try {
            RoleVO role = new RoleVO();
            role.setName(form.getName());
            role.setCreateBy(getCurrentUserName());
            role.setModifyBy(getCurrentUserName());
            Collection<AuthorizationVO> authorizations = resolveAuthorizations(form);
            role.setAuthorizations(authorizations);
            roleService.createRole(role);
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
    public SingleResult update(@RequestBody RoleForm form) {
        try {
            RoleVO role = new RoleVO();
            role.setId(form.getId());
            role.setName(form.getName());
            role.setModifyBy(getCurrentUserName());
            Collection<AuthorizationVO> authorizations = resolveAuthorizations(form);
            role.setAuthorizations(authorizations);
            roleService.updateRole(role);
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
            return createErrorResult("角色权限ID不能为空");
        }
        try {
            roleService.updateRoleState(id, getCurrentUserName(), StateEnum.getEnumByCode(enabled));
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }

    /**
     * @param form
     * @return
     */
    private Collection<AuthorizationVO> resolveAuthorizations(RoleForm form) {
        Collection<AuthorizationVO> authorizations = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(form.getAuthorizations())) {
            for (String authorizationId : form.getAuthorizations()) {
                if (StringUtils.isNotBlank(authorizationId)) {
                    AuthorizationVO authorizationVO = new AuthorizationVO();
                    authorizationVO.setId(Long.valueOf(authorizationId));
                    authorizations.add(authorizationVO);
                }
            }
        }
        return authorizations;
    }
}