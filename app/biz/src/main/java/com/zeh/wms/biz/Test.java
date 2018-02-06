package com.zeh.wms.biz;

import com.zeh.wms.biz.exception.QRCodeException;
import com.zeh.wms.biz.service.QRCodeService;
import com.zeh.wms.biz.service.impl.QRCodeServiceImpl;

/**
 * @author allen
 * @create $ ID: Test, 18/2/6 14:17 allen Exp $
 * @since 1.0.0
 */
public class Test {

    public static void main(String[] args) throws QRCodeException {
        QRCodeService s = new QRCodeServiceImpl();
        String content = "http://127.0.0.1:8080/wms/index?noframe=false";
        String data = s.encode(content, 90, 90);
        String c = s.decode(data);
        System.out.println(c);
    }
}