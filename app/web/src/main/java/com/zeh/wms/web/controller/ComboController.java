/**
 * LY.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.zeh.wms.web.controller;

import com.google.common.collect.Lists;
import com.zeh.jungle.web.basic.EnumUtil;
import com.zeh.jungle.web.basic.TextValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 下拉框Controller
 *
 * @author wyy2641
 * @version $Id : ComboController.java, v 0.1 2017年2月16日 下午2:30:27 wyy2641 Exp $
 */
@Controller
@RequestMapping("/combo")
public class ComboController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ComboController.class);


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
    @RequestMapping(value = "/enableStatus", method = RequestMethod.GET)
    @ResponseBody
    public List<TextValue> allAgentsAndManus() {
        List<TextValue> result = Lists.newArrayList();

        return result;
    }

}
