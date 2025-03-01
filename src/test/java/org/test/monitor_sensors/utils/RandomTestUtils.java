package org.test.monitor_sensors.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomTestUtils {
    private static final Random RANDOM_INSTANT = new Random();
    public static int getRandomInt(int count){
        return Integer.parseInt(RandomStringUtils.secure().nextNumeric(count));
    }
    public static String getRandomString(int count){
        return RandomStringUtils.secure().nextAlphabetic(count);
    }
    public static <T> T getRandomEnum(T[] values){
        return values[RANDOM_INSTANT.nextInt(values.length)];
    }
}
