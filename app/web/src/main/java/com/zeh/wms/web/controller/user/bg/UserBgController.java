package com.zeh.wms.web.controller.user.bg;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.Paginator;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.RoleVO;
import com.zeh.wms.biz.model.UserBgVO;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.service.UserBgService;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.form.UserBgForm;

/**
 * @author allen
 * @create $ ID: UserBgController, 18/2/11 15:59 allen Exp $
 * @since 1.0.0
 */
@Controller
@RequestMapping("/user/bg")
public class UserBgController extends BaseController {
    /** 后台用户服务 */
    @Resource
    private UserBgService userBgService;

    /**
     * 新增，编辑页面
     *
     * @param id 厂商ID
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public UserBgForm edit(Long id) throws ServiceException {
        UserBgForm form = new UserBgForm();
        if (id != null) {
            UserBgVO userBgVO = userBgService.findUserBgById(id);
            form.setId(userBgVO.getId());
            form.setUsername(userBgVO.getUsername());
        }
        return form;
    }

    /**
     * 权限设置页面
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    @RequestMapping("role")
    @ResponseBody
    public UserBgForm role(Long id) throws ServiceException {
        UserBgForm form = new UserBgForm();
        if (id != null) {
            UserBgVO userBgVO = userBgService.findUserBgDetailsById(id);
            form.setId(userBgVO.getId());
            form.setUsername(userBgVO.getUsername());
            for (RoleVO role : userBgVO.getRoles()) {
                form.getRoles().add(String.valueOf(role.getId()));
            }
        }
        return form;
    }

    @RequestMapping("changePwd")
    @ResponseBody
    public UserBgForm changePwd(Long id) throws ServiceException {
        UserBgForm form = new UserBgForm();
        if (id != null) {
            UserBgVO userBgVO = userBgService.findUserBgById(id);
            form.setUsername(userBgVO.getUsername());
            form.setId(userBgVO.getId());
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
    public PageList<UserBgVO> list(UserBgForm form, Paginator paginator) throws ServiceException {
        UserBgVO userBg = new UserBgVO();
        userBg.setUsername(form.getUsername());
        userBg.setEnabled(form.getEnabled() == null ? null : StateEnum.getEnumByCode(form.getEnabled()));
        return userBgService.pageQueryUserBgs(userBg, paginator.getCurrentPage(), paginator.getPageSize());
    }

    /**
     * 创建后台用户信息
     *§§
     * @param form
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult add(@RequestBody UserBgForm form) {
        if (!StringUtils.equals(form.getPassword(), form.getConfirmedPassword())) {
            return createErrorResult("两次输入的用户名密码不相同");
        }
        try {
            UserBgVO userBg = new UserBgVO();
            userBg.setUsername(form.getUsername());
            userBg.setPassword(form.getPassword());
            userBg.setCreateBy(getCurrentUserName());
            userBg.setModifyBy(getCurrentUserName());
            userBgService.createUserBg(userBg);
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }

    /**
     * 更新后台用户信息
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public SingleResult update(@RequestBody UserBgForm form) {
        if (!StringUtils.equals(form.getPassword(), form.getConfirmedPassword())) {
            return createErrorResult("两次输入的用户名密码不相同");
        }
        try {
            UserBgVO userBg = new UserBgVO();
            userBg.setId(form.getId());
            userBg.setUsername(form.getUsername());
            userBg.setPassword(form.getPassword());
            userBg.setModifyBy(getCurrentUserName());
            userBgService.updateUserBg(userBg);
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }

    /**
     * 设置用户权限
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/roleSetup", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult setupRoles(@RequestBody UserBgForm form) {
        try {
            UserBgVO userBg = new UserBgVO();
            userBg.setId(form.getId());
            userBg.setModifyBy(getCurrentUserName());
            if (CollectionUtils.isNotEmpty(form.getRoles())) {
                for (String roleId : form.getRoles()) {
                    RoleVO role = new RoleVO();
                    role.setId(Long.valueOf(roleId));
                    userBg.getRoles().add(role);
                }
            }
            userBgService.updateUserRoles(userBg);
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }

    /**
     * 更新后台用户状态
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/state/{id}/{enabled}", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult update(@PathVariable("id") Long id, @PathVariable("enabled") Integer enabled) {
        if (id == null || id == 0) {
            return createErrorResult("用户ID不能为空");
        }
        try {
            userBgService.updateUserBgState(id, getCurrentUserName(), StateEnum.getEnumByCode(enabled));
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }
}