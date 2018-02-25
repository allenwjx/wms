package com.zeh.wms.web.controller.qr;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.Paginator;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.QRCodeMapper;
import com.zeh.wms.biz.model.QrcodeVO;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.service.QRCodeService;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.form.QRCodeForm;

@Controller
@RequestMapping("/qr/code")
public class QRCodeController extends BaseController {

    @Resource
    private QRCodeService qrCodeService;

    @Resource
    private QRCodeMapper  mapper;

    @RequestMapping("list")
    @ResponseBody
    public PageList<QrcodeVO> list(QRCodeForm form, Paginator paginator) throws ServiceException {
        QrcodeVO qrcode = new QrcodeVO();
        qrcode.setCommodityId(form.getCommodityId());
        qrcode.setBatchId(form.getBatchId());
        qrcode.setState(form.getState() == null ? null : StateEnum.getEnumByCode(form.getState()));
        return qrCodeService.queryByConditions(qrcode, paginator.getCurrentPage(), paginator.getPageSize());
    }

    @RequestMapping("viewCode")
    @ResponseBody
    public QRCodeForm edit(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        QrcodeVO qrcode = qrCodeService.queryById(id);
        if (qrcode == null) {
            throw new IllegalArgumentException("无法找到二维码信息，ID" + id);
        }
        QRCodeForm form = new QRCodeForm();
        form.setData(qrcode.getData());
        return form;
    }

}
