package com.zsh.common.utils;

import java.util.Date;

/**
 * Created by zsh on 2023/4/30
 *
 * @author zsh
 */
public class DateUtils {

    /**
     * 返回天数差
     * @param date 日期
     * @return 当前日期距离date的天数
     */
    public static long dayDiff(Date date) {
        long diff = new Date().getTime() - date.getTime();
        return diff / (24 * 60 * 60 * 1000);
    }
}
