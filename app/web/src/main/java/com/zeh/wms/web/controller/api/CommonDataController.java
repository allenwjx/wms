package com.zeh.wms.web.controller.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.jungle.web.basic.TextValue;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.ExpressVO;
import com.zeh.wms.biz.service.ExpressService;
import com.zeh.wms.web.controller.BaseController;

/**
 * @author hzy24985
 * @version $Id: CommonDataController, v 0.1 2018/2/24 14:13 hzy24985 Exp $
 */
@Api(value = "通用数据")
@Controller
@RequestMapping("/api/data")
public class CommonDataController extends BaseController {
    private static Logger  logger = LoggerFactory.getLogger(CommonDataController.class);

    @Resource
    private ExpressService expressService;

    @ApiOperation(value = "快递公司信息", httpMethod = "GET")
    @RequestMapping(value = "express", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<List<TextValue>> getExpress() {
        List<TextValue> result = Lists.newArrayList();
        try {
            List<ExpressVO> expressVOS = expressService.getAllExpress();
            if (CollectionUtils.isNotEmpty(expressVOS)) {
                result.addAll(expressVOS.stream().map(item -> new TextValue(item.getCode(), item.getName())).collect(Collectors.toList()));
            }
            return createSuccessResult(result);
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }
}
