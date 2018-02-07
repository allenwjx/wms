package com.zeh.wms.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/common")
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
    @RequestMapping("/env")
    @ResponseBody
    public WebResult isPredeploy() {
        String evn = jungleContext.getAppConfiguration().getPropertyValue("jungle-env", "default");
        return new WebResult(true, evn);
    }
}
