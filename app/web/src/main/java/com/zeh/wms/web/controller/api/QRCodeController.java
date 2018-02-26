package com.zeh.wms.web.controller.api;

import javax.annotation.Resource;

import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.wordnik.swagger.annotations.Api;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.ShipRecordDetails;
import com.zeh.wms.biz.model.ShipRecordVO;
import com.zeh.wms.biz.service.ShipRecordService;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.form.QRCodeBindForm;

/**
 * @author allen
 * @create $ ID: QRCodeController, 18/2/25 18:56 allen Exp $
 * @since 1.0.0
 */
@Api(value = "二维码")
@Controller("apiQRCodeController")
@RequestMapping("/api/qrcode")
public class QRCodeController extends BaseController {

    @Resource
    private ShipRecordService shipRecordService;

    @ApiOperation(value = "二维码绑定商品", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(params = "action=bind", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult<String> bind(@ApiParam("请求订单参数") @RequestBody QRCodeBindForm form) {
        if (form.getAgentId() == null || form.getAgentId() <= 0) {
            return createErrorResult(ERROR_FACTORY.parameterEmptyError("agentId"));
        }
        if (form.getBindCommodityId() == null || form.getBindCommodityId() <= 0) {
            return createErrorResult(ERROR_FACTORY.parameterEmptyError("bindCommodityId"));
        }
        if (StringUtils.isBlank(form.getSerialNo())) {
            return createErrorResult(ERROR_FACTORY.parameterEmptyError("serialNo"));
        }
        if (form.getCommodityId() == null || form.getCommodityId() <= 0) {
            return createErrorResult(ERROR_FACTORY.parameterEmptyError("commodityId"));
        }
        if (!form.getCommodityId().equals(form.getBindCommodityId())) {
            return createErrorResult(ERROR_FACTORY.qrcodeCommodityIdNotEqualsError());
        }
        // 处理绑定信息，生成商品，二维码，代理人邦定关系
        ShipRecordVO shipRecord = new ShipRecordVO();
        shipRecord.setAgentId(form.getAgentId());
        shipRecord.setCommodityId(form.getCommodityId());
        shipRecord.setQrcodeNo(form.getSerialNo());
        shipRecord.setCreateBy("TODO");
        shipRecord.setModifyBy("TODO");
        try {
            shipRecordService.bind(shipRecord);
            return createSuccessResult(shipRecord.getQrcodeNo());
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }

    @ApiOperation(value = "二维码绑定商品", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = ShipRecordDetails.class)
    @RequestMapping(params = "action=view", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult<ShipRecordDetails> view(@ApiParam("快递单号") @RequestParam("serialNo") String serialNo,
    @ApiParam("二维码原始关联的商品ID") @RequestParam("commodityId") Long  commodityId) {
        if (StringUtils.isBlank(serialNo)) {
            return createErrorResult(ERROR_FACTORY.parameterEmptyError("serialNo"));
        }
        // 查询二维码关联的商品，代理人信息
        try {
            ShipRecordDetails details = shipRecordService.retrieveShipRecordDetails(serialNo);
            return createSuccessResult(details);
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }

    @ApiOperation(value = "根据二维码编号删除邦定记录", httpMethod = "POST")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping(value = "/{serialNo}", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult<String> delete(@PathVariable("serialNo") String serialNo) {
        if (StringUtils.isBlank(serialNo)) {
            return createErrorResult(ERROR_FACTORY.parameterEmptyError("serialNo"));
        }
        try {
            shipRecordService.deleteShipRecordByQRCode(serialNo);
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }
}