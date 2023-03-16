package com.zsh.msm.utils;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * 获取随机数
 *
 * @author zsh
 */
public class RandomUtil {

    public static String getFourBitRandom() {
        return new DecimalFormat("0000").format(new Random().nextInt(10000));
    }

    public static String getSixBitRandom() {
        return new DecimalFormat("000000").format(new Random().nextInt(1000000));
    }

}
