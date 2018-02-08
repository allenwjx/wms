package com.zeh.wms.biz.constants;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author hzy24985
 * @version $Id: ExcelConstant.java, v 0.1 2018/2/8 15:29 hzy24985 Exp $
 */
public class ExcelConstant {
    public static final List<String> SF_EXCEL_HEADER = Lists.newArrayList("快递单号", "寄件人", "寄件人电话", "寄件人地址", "收件人邮编", "收件人", "收件人电话", "收件人地址", "寄件人邮编");
    public static final String SF_EXCEL_PROPERTY_NAMES = Joiner.on(",").join("orderNo", "senderName", "senderTel", "senderAddress",
    "senderZipCode", "receiverName", "receiverTel", "receiverAddress", "receiverZipCode");
}
