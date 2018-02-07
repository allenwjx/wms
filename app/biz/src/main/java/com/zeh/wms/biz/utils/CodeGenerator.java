package com.zeh.wms.biz.utils;

import com.zeh.jungle.utils.serializer.RandomGenerator;

/**
 * @author allen
 * @create $ ID: CodeGenerator, 18/2/7 22:23 allen Exp $
 * @since 1.0.0
 */
public class CodeGenerator {

    public static final String generateManufacturerCode() {
        RandomGenerator g = new RandomGenerator(6);
        String code = g.generate();
        return "MF" + code;
    }
}