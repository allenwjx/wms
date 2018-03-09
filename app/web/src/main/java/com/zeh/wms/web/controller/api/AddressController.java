package com.zeh.wms.web.controller.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.UserAddressVO;
import com.zeh.wms.biz.model.enums.AddressTypeEnum;
import com.zeh.wms.biz.service.AddressService;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.controller.api.model.AddressModel;
import com.zeh.wms.web.exception.WebException;
import com.zeh.wms.web.mapper.AddressFormMapper;

/**
 * The type Address controller.
 *
 * @author hzy24985
 * @version $Id : AddressController, v 0.1 2018/2/24 13:52 hzy24985 Exp $
 */
@Api(value = "地址管理")
@Controller
@RequestMapping("/api/address")
public class AddressController extends BaseController {

    /**
     * The Address service.
     */
    @Resource
    private AddressService    addressService;
    /**
     * The Address form mapper.
     */
    @Resource
    private AddressFormMapper addressFormMapper;

    /**
     * Add address single result.
     *
     * @param address the address
     * @return the single result
     * @throws ServiceException the service exception
     */
    @ApiOperation(value = "新增地址", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public SingleResult addAddress(@ApiParam("地址模型") AddressModel address) throws ServiceException {
        UserAddressVO vo = addressFormMapper.modelToVo(address);
        vo.setUserId(getCurrentApiUserId());
        insertSecurityApiVO(vo);
        addressService.addAddress(vo);
        return createSuccessResult();
    }

    /**
     * Update address single result.
     *
     * @param address the address
     * @return the single result
     * @throws ServiceException the service exception
     */
    @ApiOperation(value = "修改地址", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult updateAddress(@ApiParam("地址模型") AddressModel address) throws ServiceException {
        UserAddressVO vo = addressFormMapper.modelToVo(address);
        vo.setUserId(getCurrentApiUserId());
        updateSecurityApiVO(vo);
        addressService.updateAddress(vo);
        return createSuccessResult();
    }

    /**
     * Add address single result.
     *
     * @param id the id of address
     * @return the single result
     * @throws ServiceException the service exception
     */
    @ApiOperation(value = "设置默认地址", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "/setDefault/{id}/{type}", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult setDefault(@PathVariable("id") @ApiParam("id") Long id,
                                   @PathVariable("type") @ApiParam(name = "type", allowableValues = "SENDER, RECEIVER") String type) throws ServiceException, WebException {
        assertNull(id, "地址id");
        assertEmpty(type, "地址类型");
        assertObjectNull(AddressTypeEnum.getEnumByCode(type), "地址类型枚举");
        addressService.setDefault(getCurrentApiUserId(), id, type, getCurrentApiUser().getNickName());
        return createSuccessResult();
    }

    /**
     * Gets address.
     *
     * @param id the type
     * @return the address
     * @throws ServiceException the service exception
     * @throws WebException     the web exception
     */
    @ApiOperation(value = "获取地址", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<AddressModel> getAddress(@PathVariable("id") @ApiParam(name = "id") Long id) throws ServiceException, WebException {
        assertObjectNull(id, "ID");

        UserAddressVO userAddressVO = addressService.queryAddress(id);
        if (userAddressVO == null) {
            return createErrorResult("未找到配置的地址");
        }
        return createSuccessResult(addressFormMapper.voToModel(userAddressVO));
    }

    /**
     * Gets default address.
     *
     * @param type the type
     * @return the default address
     * @throws ServiceException the service exception
     * @throws WebException     the web exception
     */
    @ApiOperation(value = "获取地址", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "/default/{type}", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<AddressModel> getDefaultAddress(@PathVariable("type") @ApiParam(name = "type", allowableValues = "SENDER, RECEIVER") String type) throws ServiceException,
                                                                                                                                                          WebException {
        assertEmpty(type, "地址类型");
        assertObjectNull(AddressTypeEnum.getEnumByCode(type), "地址类型枚举");

        UserAddressVO userAddressVO = addressService.getDefault(getCurrentApiUserId(), AddressTypeEnum.getEnumByCode(type));
        if (userAddressVO == null) {
            return createErrorResult("未找到默认配置的地址");
        }
        return createSuccessResult(addressFormMapper.voToModel(userAddressVO));
    }

    /**
     * Gets address list.
     *
     * @param type the type
     * @return the address list
     * @throws WebException     the web exception
     * @throws ServiceException the service exception
     */
    @ApiOperation(value = "获取地址", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "/list/{type}", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<List<AddressModel>> getAddressList(@PathVariable("type") @ApiParam(name = "type", allowableValues = "SENDER, RECEIVER") String type) throws WebException,
                                                                                                                                                             ServiceException {
        assertEmpty(type, "地址类型");
        assertObjectNull(AddressTypeEnum.getEnumByCode(type), "地址类型枚举");

        List<UserAddressVO> list = addressService.getList(getCurrentApiUserId(), AddressTypeEnum.getEnumByCode(type));
        return createSuccessResult(addressFormMapper.vosToModels(list));
    }

    /**
     * Gets address list.
     *
     * @param id the id
     * @return the address list
     * @throws WebException     the web exception
     * @throws ServiceException the service exception
     */
    @ApiOperation(value = "删除地址", httpMethod = "DELETE")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public SingleResult getAddressList(@ApiParam(name = "id") long id) throws WebException, ServiceException {
        assertNull(id, "地址id");

        addressService.delete(id, getCurrentApiUserId());
        return createSuccessResult();
    }
}
