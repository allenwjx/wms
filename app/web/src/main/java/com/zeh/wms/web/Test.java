package com.zeh.wms.web;

import com.zeh.jungle.utils.serializer.FastJsonUtils;
import com.zeh.wms.biz.exception.QRCodeException;
import com.zeh.wms.biz.service.QRCodeService;
import com.zeh.wms.biz.service.impl.QRCodeServiceImpl;
import com.zeh.wms.web.form.QRCodeBindForm;

/**
 * @author allen
 * @create $ ID: Test, 18/2/25 21:38 allen Exp $
 * @since 1.0.0
 */
public class Test {


    public static void main(String[] args) throws QRCodeException {
        QRCodeBindForm f = new QRCodeBindForm();
        f.setAgentId(230L);
        f.setBindCommodityId(87L);
        f.setCommodityId(87L);
        f.setSerialNo("7abb322e60cd46fbb3d60ed00b8d841c");
        System.out.println(FastJsonUtils.toJSONString(f));
    }

}