package com.easy.ocr.core.core.util;


import java.util.Arrays;
import java.util.Random;

public class RandomUtil {
    public static int random(int max) {
        return new Random().nextInt(max-0)+0;
    }

    public static void main(String[] args) {

        System.out.println(RandomUtil.random(Arrays.asList(new java.lang.String[]{"1","2"}).size()));
    }
}
