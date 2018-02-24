package com.zeh.wms.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeh.jungle.core.context.JungleContext;
import com.zeh.jungle.web.basic.WebResult;

/**
 * The type Common controller.
 *
 * @author hxy43938
 * @version Id : CommonController, v 0.1 2017/2/17 16:40 hxy43938 Exp $
 */
@Controller
public class CommonController extends BaseController {

    /**
     * The constant PERMISSION_DESC.
     */
    private static final String PERMISSION_DESC = "管理员";

    /**
     * Jungle 上下文
     */
    @Resource
    private JungleContext       jungleContext;

    /**
     * Method isPredeploy ...
     *
     * @return ResultMsg result msg
     */
    @RequestMapping("/common/env")
    @ResponseBody
    public WebResult isPredeploy() {
        String evn = jungleContext.getAppConfiguration().getPropertyValue("jungle-env", "default");
        return new WebResult(true, evn);
    }

    /**
     * 用页面跳转.适用于不需要传参数的纯页面.
     * 在前端调试时，不需要写controller,直接写页面即可.
     *
     * @param module  the module
     * @param page    the page
     * @param request the request
     * @param model   the model
     * @return the string
     */
    @RequestMapping(value = "/page/{module}/{page}", method = RequestMethod.GET)
    public String page(@PathVariable("module") String module, @PathVariable("page") String page, HttpServletRequest request, Model model) {
        for (Object key : request.getParameterMap().keySet()) {
            String keyStr = key.toString();
            model.addAttribute(keyStr, request.getParameter(keyStr));
        }
        return StringUtils.join(module, "/", page);
    }

    /**
     * 用页面跳转.适用于不需要传参数的纯页面.
     * 在前端调试时，不需要写controller,直接写页面即可.
     *
     * @param module    the module
     * @param subModule the sub module
     * @param page      the page
     * @param request   the request
     * @param model     the model
     * @return the string
     */
    @RequestMapping(value = "/page/{module}/{subModule}/{page}", method = RequestMethod.GET)
    public String page(@PathVariable("module") String module, @PathVariable("subModule") String subModule, @PathVariable("page") String page, HttpServletRequest request,
                       Model model) {
        for (Object key : request.getParameterMap().keySet()) {
            String keyStr = key.toString();
            model.addAttribute(keyStr, request.getParameter(keyStr));
        }
        return StringUtils.join(module, "/", subModule, "/", page);
    }
}
