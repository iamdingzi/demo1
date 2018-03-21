package com.example.demo.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * 根据当前时间
     * 格式化年月 2017-03-01 12:12:12 格式化为 20170301121201
     */
    public static String formatCurDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate = new Date();// 获取年月日
        return df.format(curDate);
    }
}
