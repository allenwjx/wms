package com.zeh.wms.web.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.utils.common.LoggerUtils;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.ManufacturerVO;
import com.zeh.wms.biz.service.ManufacturerService;

/**
 * 
 * @author allen
 * @version $Id: SampleController.java, v 0.1 2016年7月11日 下午10:12:13 allen Exp $
 */
@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private ManufacturerService manufacturerService;

    @RequestMapping("/index")
    public String sample() throws ServiceException {
        ManufacturerVO m = new ManufacturerVO();
        m.setCode("a12841232");

        PageList<ManufacturerVO> manufacturerPageList = manufacturerService.pageQueryManufacturers(m, 1, 10);
        LoggerUtils.info(LOGGER, "data: {}", manufacturerPageList.getData());
        return "index";
    }
}
