package com.zeh.wms.web.controller.qr;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.Paginator;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.ExpressOrderVO;
import com.zeh.wms.biz.model.QrcodeVO;
import com.zeh.wms.biz.service.QRCodeService;
import com.zeh.wms.dal.dataobject.QrcodeDO;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.form.QRCodeForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping ("/qr/code")
public class QRCodeController extends BaseController {

    @Resource
    private QRCodeService qrCodeService;

    @RequestMapping ("list")
    @ResponseBody
    public PageList <QrcodeVO> list (QRCodeForm form, Paginator paginator) throws ServiceException {
        PageList <QrcodeVO> result = qrCodeService.queryAll (paginator.getCurrentPage(), paginator.getPageSize());
        return result;
    }

    @RequestMapping ("one")
    @ResponseBody
    public QrcodeVO one (Long id) throws ServiceException {
        QrcodeVO qrcodeVO = new QrcodeVO();
        if (id != null) {
            qrcodeVO = qrCodeService.queryById (id);
        }
        return qrcodeVO;
    }

    @RequestMapping ("{codeId}/bind/{commodityId}")
    @ResponseBody
    public SingleResult bindCommodity (@PathVariable ("codeId") Long codeId, @PathVariable ("commodityId") Long commodityId) throws ServiceException {
        qrCodeService.bindCommodity (codeId, commodityId);
        return createSuccessResult ();
    }
}
