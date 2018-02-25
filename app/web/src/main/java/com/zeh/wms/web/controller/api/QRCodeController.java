package com.zeh.wms.web.controller.api;

import javax.annotation.Resource;

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

    @RequestMapping(params = "action=bind", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult bind(@RequestBody QRCodeBindForm form) {
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

    @RequestMapping(params = "action=view", method = RequestMethod.GET)
    @ResponseBody
    public SingleResult view(@RequestParam("serialNo") String serialNo, @RequestParam("commodityId") Long commodityId) {
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

    @RequestMapping(value = "/{serialNo}", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult delete(@PathVariable("serialNo") String serialNo) {
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