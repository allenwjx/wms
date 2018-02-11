package com.zeh.wms.web.controller.user;

import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.CommodityVO;
import com.zeh.wms.biz.model.UserBgVO;
import com.zeh.wms.biz.service.UserBgService;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.form.CommodityForm;
import com.zeh.wms.web.form.UserBgForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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
            form.setUsername(userBgVO.getUsername());
            form.setPassword(userBgVO.getPassword());
            form.setEnabled(userBgVO.getEnabled().getCode());
            form.setId(userBgVO.getId());
        }
        return form;
    }
}