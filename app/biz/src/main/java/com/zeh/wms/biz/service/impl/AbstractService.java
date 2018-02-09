package com.zeh.wms.biz.service.impl;

import com.google.common.collect.Maps;
import com.zeh.wms.biz.error.BizErrorFactory;
import org.apache.commons.collections.MapUtils;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * @author hzy24985
 * @version $Id: AbstractService, v 0.1 2018/2/8 20:18 hzy24985 Exp $
 */
public abstract class AbstractService {
    /** 错误工厂 */
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();

    private static Logger logger = LoggerFactory.getLogger(AbstractService.class);

    protected ResponseEntity<byte[]> getExcel(String templatePath, String fileName, Object list) {
        Map<String, Object> pars = Maps.newHashMap();
        pars.put("lists", list);
        return getExcel(templatePath, fileName, pars);
    }

    protected ResponseEntity<byte[]> getExcel(String templatePath, String fileName, Map<String, Object> pars) {
        try (InputStream is = new FileInputStream(templatePath)) {
            try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {

                Context context = new Context();
                if (!MapUtils.isEmpty(pars)) {
                    pars.forEach((key, value) -> context.putVar(key, value));
                }
                JxlsHelper.getInstance().processTemplate(is, os, context);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentDispositionFormData("attachment", java.net.URLEncoder.encode(fileName, "UTF-8"));
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                return new ResponseEntity<>(os.toByteArray(), headers, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            logger.error("错误原因", e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
