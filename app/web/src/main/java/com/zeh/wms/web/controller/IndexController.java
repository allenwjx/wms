package com.zeh.wms.web.controller;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.utils.common.LoggerUtils;
import com.zeh.wms.dal.daointerface.ManufacturerDAO;
import com.zeh.wms.dal.dataobject.ManufacturerDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 
 * @author allen
 * @version $Id: SampleController.java, v 0.1 2016年7月11日 下午10:12:13 allen Exp $
 */
@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private ManufacturerDAO     manufacturerDAO;

    @RequestMapping("/index")
    public String sample() {
        PageList<ManufacturerDO> manufacturerPageList = manufacturerDAO.findPage(20, 1);
        LoggerUtils.info(LOGGER, "data: {}", manufacturerPageList.getData());
        return "index";
    }
}
