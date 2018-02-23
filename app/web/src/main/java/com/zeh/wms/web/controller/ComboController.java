/**
 * LY.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.zeh.wms.web.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.zeh.jungle.web.basic.EnumUtil;
import com.zeh.jungle.web.basic.TextValue;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.*;
import com.zeh.wms.biz.service.*;

/**
 * 下拉框Controller
 *
 * @author wyy2641
 * @version $Id : ComboController.java, v 0.1 2017年2月16日 下午2:30:27 wyy2641 Exp $
 */
@Controller
@RequestMapping("/combo")
public class ComboController extends BaseController {
    private static Logger        logger = LoggerFactory.getLogger(ComboController.class);
    @Resource
    private ManufacturerService  manufacturerService;
    @Resource
    private AgentService         agentService;
    @Resource
    private RegionsService       regionsService;
    @Resource
    private CommodityService     commodityService;
    @Resource
    private AuthorizationService authorizationService;
    @Resource
    private RoleService          roleService;

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
     * text: name; value: code
     *
     * @return list list
     */
    @RequestMapping(value = "/allAgentsAndManus", method = RequestMethod.GET)
    @ResponseBody
    public List<TextValue> allAgentsAndManus() {
        List<TextValue> result = Lists.newArrayList();
        try {
            List<ManufacturerVO> list = manufacturerService.getAll();
            Collection<AgentVO> agentVOS = agentService.findAllAgents();

            if (CollectionUtils.isNotEmpty(list)) {
                result.addAll(list.stream().map(item -> new TextValue(item.getCode(), item.getName())).collect(Collectors.toList()));
            }

            if (CollectionUtils.isNotEmpty(agentVOS)) {
                result.addAll(agentVOS.stream().map(item -> new TextValue(item.getCode(), item.getName())).collect(Collectors.toList()));
            }
        } catch (ServiceException e) {
            logger.error("异常", e);
        }
        return result;
    }

    /**
     * 获取所有有效大客户
     * text: name; value: code
     *
     * @return list list
     */
    @RequestMapping(value = "/allManus", method = RequestMethod.GET)
    @ResponseBody
    public List<TextValue> allManus() {
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

    /**
     * 获取所有有效代理商
     * text: name; value: code
     *
     * @return list list
     */
    @RequestMapping(value = "/allAgents", method = RequestMethod.GET)
    @ResponseBody
    public List<TextValue> allAgent() {
        List<TextValue> result = Lists.newArrayList();
        try {
            Collection<AgentVO> list = agentService.findAllAgents();

            if (CollectionUtils.isNotEmpty(list)) {
                result.addAll(list.stream().map(item -> new TextValue(item.getCode(), item.getName())).collect(Collectors.toList()));
            }
        } catch (ServiceException e) {
            logger.error("异常", e);
        }
        return result;
    }

    /**
     * 获取所有有效大客户，text：name; value: id
     *
     * @return list list
     */
    @RequestMapping(value = "/loadManus", method = RequestMethod.GET)
    @ResponseBody
    public List<TextValue> loadManus() {
        List<TextValue> result = Lists.newArrayList();
        try {
            List<ManufacturerVO> list = manufacturerService.getAll();

            if (CollectionUtils.isNotEmpty(list)) {
                result.addAll(list.stream().map(item -> new TextValue(item.getId(), item.getName())).collect(Collectors.toList()));
            }
        } catch (ServiceException e) {
            logger.error("异常", e);
        }
        return result;
    }

    /**
     * 获取所有省份信息
     *
     * @return list list
     */
    @RequestMapping(value = "/provinces", method = RequestMethod.GET)
    @ResponseBody
    public List<TextValue> loadProvinces() {
        List<TextValue> result = Lists.newArrayList();
        try {
            Collection<RegionsVO> provinces = regionsService.queryProvinces();

            if (CollectionUtils.isNotEmpty(provinces)) {
                result.addAll(provinces.stream().map(item -> new TextValue(item.getId(), item.getName())).collect(Collectors.toList()));
            }
        } catch (ServiceException e) {
            logger.error("异常", e);
        }
        return result;
    }

    /**
     * 获取市信息
     *
     * @return list list
     */
    @RequestMapping(value = "/cities/{provinceId}", method = RequestMethod.GET)
    @ResponseBody
    public List<TextValue> loadCities(@PathVariable("provinceId") String provinceId) {
        List<TextValue> result = Lists.newArrayList();
        try {
            Collection<RegionsVO> cities = regionsService.queryCities(Long.valueOf(provinceId));
            if (CollectionUtils.isNotEmpty(cities)) {
                result.addAll(cities.stream().map(item -> new TextValue(item.getId(), item.getName())).collect(Collectors.toList()));
            }
        } catch (ServiceException e) {
            logger.error("异常", e);
        }
        return result;
    }

    /**
     * 获取市信息
     *
     * @return list list
     */
    @RequestMapping(value = "/districts/{cityId}", method = RequestMethod.GET)
    @ResponseBody
    public List<TextValue> loadDistricts(@PathVariable("cityId") String cityId) {
        List<TextValue> result = Lists.newArrayList();
        try {
            Collection<RegionsVO> districts = regionsService.queryDistricts(Long.valueOf(cityId));
            if (CollectionUtils.isNotEmpty(districts)) {
                result.addAll(districts.stream().map(item -> new TextValue(item.getId(), item.getName())).collect(Collectors.toList()));
            }
        } catch (ServiceException e) {
            logger.error("异常", e);
        }
        return result;
    }

    /**
     * 获取有效商品信息
     *
     * @return list list
     */
    @RequestMapping(value = "/commodities", method = RequestMethod.GET)
    @ResponseBody
    public List<TextValue> loadCommodities() {
        List<TextValue> result = Lists.newArrayList();
        try {
            Collection<CommodityVO> commodities = commodityService.findAllCommodities();
            if (CollectionUtils.isNotEmpty(commodities)) {
                result.addAll(commodities.stream().map(item -> new TextValue(item.getId(), item.getName())).collect(Collectors.toList()));
            }
        } catch (ServiceException e) {
            logger.error("异常", e);
        }
        return result;
    }

    /**
     * 获取有效权限信息
     *
     * @return list list
     */
    @RequestMapping(value = "/authorizations", method = RequestMethod.GET)
    @ResponseBody
    public List<TextValue> loadAuthorizations() {
        List<TextValue> result = Lists.newArrayList();
        try {
            Collection<AuthorizationVO> auths = authorizationService.findAllAuthorizations();
            if (CollectionUtils.isNotEmpty(auths)) {
                result.addAll(auths.stream().map(item -> new TextValue(String.valueOf(item.getId()), item.getName())).collect(Collectors.toList()));
            }
        } catch (ServiceException e) {
            logger.error("异常", e);
        }
        return result;
    }

    /**
     * 获取有效角色信息
     *
     * @return list list
     */
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    @ResponseBody
    public List<TextValue> loadRoles() {
        List<TextValue> result = Lists.newArrayList();
        try {
            Collection<RoleVO> roles = roleService.findAllRoles();
            if (CollectionUtils.isNotEmpty(roles)) {
                result.addAll(roles.stream().map(item -> new TextValue(String.valueOf(item.getId()), item.getName())).collect(Collectors.toList()));
            }
        } catch (ServiceException e) {
            logger.error("异常", e);
        }
        return result;
    }
}
