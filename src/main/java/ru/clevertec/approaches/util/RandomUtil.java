package ru.clevertec.approaches.util;

import lombok.experimental.UtilityClass;

import java.util.Random;


@UtilityClass
public class RandomUtil {

    private static final Random RANDOM = new Random();

    private static final int DEFAULT_BOUND = 20;

    public static int getRandom(int bound){
        return RANDOM.nextInt(bound);
    }

    public static int getRandom(){
        return getRandom(DEFAULT_BOUND);
    }

}
