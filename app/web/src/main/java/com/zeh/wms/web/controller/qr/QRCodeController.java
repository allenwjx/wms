package com.zeh.wms.web.controller.qr;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.Paginator;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.QrcodeVO;
import com.zeh.wms.biz.service.QRCodeService;
import com.zeh.wms.web.form.QRCodeForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping ("/qr/code")
public class QRCodeController {

    @Resource
    private QRCodeService qrCodeService;

    @RequestMapping ("list")
    @ResponseBody
    public PageList <QrcodeVO> list (QRCodeForm form, Paginator paginator) throws ServiceException {
        return qrCodeService.queryAll (paginator.getCurrentPage(), paginator.getPageSize());
    }
}
