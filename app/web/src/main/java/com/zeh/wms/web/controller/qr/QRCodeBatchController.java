package com.zeh.wms.web.controller.qr;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.Paginator;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.QRCodeBatchVO;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.service.QRCodeBatchService;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.form.QRCodeBatchForm;

/**
 * @author allen
 * @create $ ID: QRCodeBatchController, 18/2/10 20:40 allen Exp $
 * @since 1.0.0
 */
@Controller
@RequestMapping("/qr/batch")
public class QRCodeBatchController extends BaseController {
    @Resource
    private QRCodeBatchService qrCodeBatchService;

    /**
     * 分页查询
     *
     * @param form
     * @param paginator
     * @return
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public PageList<QRCodeBatchVO> list(QRCodeBatchForm form, Paginator paginator) throws ServiceException {
        QRCodeBatchVO qrCodeBatch = new QRCodeBatchVO();
        qrCodeBatch.setQrcodeSerial(form.getQrcodeSerial());
        qrCodeBatch.setBatchSerial(form.getBatchSerial());
        qrCodeBatch.setState(form.getState() == null ? null : StateEnum.getEnumByCode(form.getState()));
        return qrCodeBatchService.pageQueryQRCodeBatchs(qrCodeBatch, paginator.getCurrentPage(), paginator.getPageSize());
    }

    /**
     * 更新批次状态
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "state/{id}/{enabled}", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult update(@PathVariable("id") Long id, @PathVariable("enabled") Integer enabled) {
        if (id == null || id == 0) {
            return createErrorResult("批次ID不能为空");
        }
        try {
            qrCodeBatchService.updateQRCodeBatchState(id, getCurrentUserName(), StateEnum.getEnumByCode(enabled));
            // TODO weijun 更新这个批次所有二维码状态
            return createSuccessResult();
        } catch (ServiceException e) {
            return createErrorResult(e);
        }
    }
}