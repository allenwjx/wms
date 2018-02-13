package com.zeh.wms.web.controller.qr;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.Paginator;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.jungle.utils.serializer.FastJsonUtils;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.QRCodeMapper;
import com.zeh.wms.biz.model.QrcodeVO;
import com.zeh.wms.biz.service.QRCodeService;
import com.zeh.wms.dal.dataobject.QrcodeDO;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.form.QRCodeForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping ("/qr/code")
public class QRCodeController extends BaseController {

    @Resource
    private QRCodeService qrCodeService;

    @Resource
    private QRCodeMapper mapper;

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

    @RequestMapping ("bind")
    public String bind (Long id, Model model) throws ServiceException {
        QrcodeVO qrcodeVO = null;
        if (id != null) {
            qrcodeVO = qrCodeService.queryById (id);

            QRCodeForm form = new QRCodeForm ();
            form.setId (qrcodeVO.getId ());

            String modelData = FastJsonUtils.toJSONString (form);
            model.addAttribute ("modelData", modelData);
        }
        return "qr/code/view";
    }

    @RequestMapping ("doBind")
    @ResponseBody
    public QrcodeVO bindCommodity (QRCodeForm form) throws ServiceException {
        qrCodeService.bindCommodity (form.getId (), form.getCommodityId ());
        return qrCodeService.queryById (form.getId ());
    }
}
