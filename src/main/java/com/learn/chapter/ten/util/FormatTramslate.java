package com.learn.chapter.ten.util;

import java.util.Optional;

/**
 * 工具类
 */
public class FormatTramslate {

    /**
     * 将string转换为int,当string值为字符串时将返回empty
     * @param s
     * @return
     */
    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.valueOf(s));
        }catch(NumberFormatException e) {
            return Optional.empty();
        }
    }
}
