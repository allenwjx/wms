package com.zeh.wms.web.controller;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.utils.common.LoggerUtils;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.ManufacturerVO;
import com.zeh.wms.biz.service.ManufacturerService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * The type Index controller.
 *
 * @author allen
 * @version $Id : SampleController.java, v 0.1 2016年7月11日 下午10:12:13 allen Exp $
 */
@Controller
public class IndexController {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    /**
     * The Manufacturer service.
     */
    @Resource
    private ManufacturerService manufacturerService;

    /**
     * Sample string.
     *
     * @return the string
     * @throws ServiceException the service exception
     */
    @RequestMapping("/index")
    public String sample() throws ServiceException {
        ManufacturerVO m = new ManufacturerVO();
        m.setCode("a12841232");

        PageList<ManufacturerVO> manufacturerPageList = manufacturerService.pageQueryManufacturers(m, 1, 10);
        LoggerUtils.info(LOGGER, "data: {}", manufacturerPageList.getData());
        return "index";
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
    public String page(@PathVariable("module") String module, @PathVariable("subModule") String subModule, @PathVariable("page") String page, HttpServletRequest request, Model model) {
        for (Object key : request.getParameterMap().keySet()) {
            String keyStr = key.toString();
            model.addAttribute(keyStr, request.getParameter(keyStr));
        }
        return StringUtils.join(module, "/", subModule, "/", page);
    }
}
