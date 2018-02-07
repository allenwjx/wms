/**
 * LY.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.zeh.wms.web.controller;

import com.google.common.collect.Lists;
import com.zeh.jungle.web.basic.EnumUtil;
import com.zeh.jungle.web.basic.TextValue;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.ManufacturerVO;
import com.zeh.wms.biz.service.ManufacturerService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 下拉框Controller
 *
 * @author wyy2641
 * @version $Id : ComboController.java, v 0.1 2017年2月16日 下午2:30:27 wyy2641 Exp $
 */
@Controller
@RequestMapping("/combo")
public class ComboController extends BaseController {

    private static Logger       logger = LoggerFactory.getLogger(ComboController.class);

    @Resource
    private ManufacturerService manufacturerService;

    /**
     * 获取枚举作为下拉列表选项.
     *
     * @return list list
     */
    @RequestMapping(value = "/fromBizEnum", method = RequestMethod.GET)
    @ResponseBody
    public List<TextValue> fromBizEnum(String className) {
        List<TextValue> result = Lists.newArrayList();
        try {
            Class<?> clazz = Class.forName("com.zeh.wms.biz.model.enums." + className);
            result = EnumUtil.enumToList(clazz);
        } catch (Exception e) {
            logger.error("枚举类型转换异常", e);
        }
        return result;
    }

    /**
     * 获取所有有效代理商和大客户
     *
     * @return list list
     */
    @RequestMapping(value = "/allAgentsAndManus", method = RequestMethod.GET)
    @ResponseBody
    public List<TextValue> allAgentsAndManus() {
        List<TextValue> result = Lists.newArrayList();
        try {
            List<ManufacturerVO> list = manufacturerService.getAll();

            if (CollectionUtils.isNotEmpty(list)) {
                result.addAll(list.stream().map(item -> new TextValue(item.getCode(), item.getName())).collect(Collectors.toList()));
            }
        } catch (ServiceException e) {
            logger.error("异常", e);
        }
        return result;
    }

}
