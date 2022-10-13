package com.caiyucong.smsservice.utils;

import java.text.DecimalFormat;
import java.util.Random;

public class RandomUtil {

    private static final Random RANDOM = new Random();

    private static final DecimalFormat FOUR_DF = new DecimalFormat("0000");

    private static final DecimalFormat SIX_DF = new DecimalFormat("000000");

    public static String getFourBitRandom() {
        return FOUR_DF.format(RANDOM.nextInt(10000));
    }

    public static String getSixBitRandom() {
        return SIX_DF.format(RANDOM.nextInt(1000000));
    }

}
