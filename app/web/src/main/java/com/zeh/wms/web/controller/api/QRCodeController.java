package com.zeh.wms.web.controller.api;

import com.wordnik.swagger.annotations.Api;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author allen
 * @create $ ID: QRCodeController, 18/2/25 18:56 allen Exp $
 * @since 1.0.0
 */
@Api(value = "二维码")
@Controller("apiQRCodeController")
@RequestMapping("/api/qrcode")
public class QRCodeController extends BaseController {

    @RequestMapping(params = "action=bind")
    @ResponseBody
    public SingleResult bind(@RequestParam("serialNo") String serialNo, @RequestParam("commodityId") Long commodityId) {
        // TODO 处理绑定信息，生成商品，二维码，代理人邦定关系
        return createSuccessResult();
    }

    @RequestMapping(params = "action=view")
    @ResponseBody
    public SingleResult view(@RequestParam("serialNo") String serialNo, @RequestParam("commodityId") Long commodityId) {
        // TODO 查询二维码关联的商品，代理人信息
        return createSuccessResult();
    }
}