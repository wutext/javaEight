package com.learn.chapter.fourteen;

import java.util.stream.IntStream;

public class StreamCount {

    public static IntStream getCount() {
        return IntStream.iterate(2, i -> i+1).limit(10);
    }

    public static void main(String[] args) {
        getCount().forEach(a -> System.out.println(a));
    }
}
