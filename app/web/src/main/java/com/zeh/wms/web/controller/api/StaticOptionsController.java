package com.zeh.wms.web.controller.api;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.model.enums.*;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.controller.api.model.StaticOptionsModel;

/**
 * @author allen
 * @create $ ID: StaticOptionsController, 18/3/7 15:56 allen Exp $
 * @since 1.0.0
 */
@Api(value = "静态选项资源")
@Controller("apiStaticOptionsController")
@RequestMapping("/api/opt")
public class StaticOptionsController extends BaseController {
    private static final Map<String, List<StaticOptionsModel>> CACHE = Maps.newConcurrentMap();
    private static final Map<String, Class<?>>                 ENUMS = Maps.newHashMap();
    static {
        ENUMS.put(AddressTypeEnum.class.getSimpleName(), AddressTypeEnum.class);
        ENUMS.put(ExpressOrderStateEnum.class.getSimpleName(), ExpressOrderStateEnum.class);
        ENUMS.put(ExpressTypeEnum.class.getSimpleName(), ExpressTypeEnum.class);
        ENUMS.put(LogTypeEnum.class.getSimpleName(), LogTypeEnum.class);
        ENUMS.put(PaymentChannelEnum.class.getSimpleName(), PaymentChannelEnum.class);
        ENUMS.put(PaymentStateEnum.class.getSimpleName(), PaymentStateEnum.class);
        ENUMS.put(SettleTypeEnum.class.getSimpleName(), SettleTypeEnum.class);
        ENUMS.put(StateEnum.class.getSimpleName(), StateEnum.class);
        ENUMS.put(UserLinkTypeEnum.class.getSimpleName(), UserLinkTypeEnum.class);
        ENUMS.put(UserTypeEnum.class.getSimpleName(), UserTypeEnum.class);
    }

    @ApiOperation(value = "枚举资源", httpMethod = "GET")
    @RequestMapping(value = "enum/{type}", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<List<StaticOptionsModel>> bind(@ApiParam("资源类型") @PathVariable("type") String type) {
        try {
            Class<?> clazz = ENUMS.get(type);
            if (clazz == null) {
                return createSuccessResult();
            }
            List<StaticOptionsModel> options = toList(clazz);
            return createSuccessResult(options);
        } catch (Exception e) {
            return createErrorResult(e);
        }
    }

    /**
     *
     * @param <T> the type parameter
     * @param t   the t
     * @return the list
     * @throws RuntimeException
     */
    private static <T> List<StaticOptionsModel> toList(Class<T> t) {
        List<StaticOptionsModel> result = CACHE.get(t.getName());
        if (result != null) {
            return result;
        }

        try {
            List<StaticOptionsModel> options = Lists.newArrayList();
            if (t.isEnum()) {
                T[] emArr = t.getEnumConstants();
                for (int i = 0; i < emArr.length; i++) {
                    Enum<?> e = (Enum<?>) emArr[i];
                    Object value = e.getDeclaringClass().getDeclaredMethod("getCode").invoke(e);
                    String name = (String) (e.getDeclaringClass().getDeclaredMethod("getDesc").invoke(e));
                    StaticOptionsModel option = new StaticOptionsModel();
                    option.setName(name);
                    option.setValue(value);
                    if (i == 0) {
                        option.setChecked(true);
                    }
                    options.add(option);
                }
                CACHE.put(t.getName(), options);
            }
            return options;
        } catch (Exception e) {
            throw new RuntimeException("资源解析错误, " + e.getMessage());
        }
    }
}