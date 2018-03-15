package com.zeh.wms.biz.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import com.zeh.jungle.utils.common.DateUtil;

/**
 * @author allen
 * @create $ ID: OrderSerialNoGenerator, 18/3/15 14:52 allen Exp $
 * @since 1.0.0
 */
public class OrderSerialNoGenerator {
    /** 用ip地址最后几个字节标示 */
    private long workerId;
    /** 可配置在properties中,启动时加载,此处默认先写成0 */
    private long datacenterId      = 0L;
    private long sequence          = 0L;
    /** 节点ID长度 */
    private long workerIdBits      = 8L;
    /** 数据中心ID长度,可根据时间情况设定位数 */
    private long datacenterIdBits  = 2L;
    /** 序列号12位 */
    private long sequenceBits      = 12L;
    /** 机器节点左移12位 */
    private long workerIdShift     = sequenceBits;
    /** 数据中心节点左移14位 */
    private long datacenterIdShift = sequenceBits + workerIdBits;
    /** 4095 */
    private long sequenceMask      = -1L ^ (-1L << sequenceBits);
    private long lastTimestamp     = -1L;

    OrderSerialNoGenerator() {
        workerId = 0x000000FF & getLastIP();
    }

    public static OrderSerialNoGenerator getInstance() {
        return OrderSerialNoGeneratorHolder.INSTANCE;
    }

    public synchronized String generate() {
        // 获取当前毫秒数
        long timestamp = System.currentTimeMillis();
        // 如果服务器时间有问题(时钟后退) 报错。
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        // 如果上次生成时间和当前时间相同,在同一毫秒内
        if (lastTimestamp == timestamp) {
            // sequence自增，因为sequence只有12bit，所以和sequenceMask相与一下，去掉高位
            sequence = (sequence + 1) & sequenceMask;
            // 判断是否溢出,也就是每毫秒内超过4095，当为4096时，与sequenceMask相与，sequence就等于0
            if (sequence == 0) {
                // 自旋等待到下一毫秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 如果和上次生成时间不同,重置sequence，就是下一毫秒开始，sequence计数重新从0开始累加
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        long suffix = (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
        String datePrefix = format(timestamp, "yyyyMMddHHMMssSSS");
        return datePrefix + suffix;
    }

    private String format(long timestamp, String format) {
        Date date = new Date(timestamp);
        String dateString = DateUtil.format(date, format);
        return dateString;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    private byte getLastIP() {
        byte lastip = 0;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            byte[] ipByte = ip.getAddress();
            lastip = ipByte[ipByte.length - 1];
            return lastip;
        } catch (UnknownHostException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static class OrderSerialNoGeneratorHolder {
        private static final OrderSerialNoGenerator INSTANCE = new OrderSerialNoGenerator();
    }
}