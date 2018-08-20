package com.learn.chapter.util;

import java.util.Random;

public class CommonUtil {

    public static final Random random = new Random();
    //模拟随机生成0.5~2.5秒的延迟方法
    public static void randomDelay() {
        int delay = 500 + random.nextInt(2000);
        System.out.println(delay);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        randomDelay();
    }
}
