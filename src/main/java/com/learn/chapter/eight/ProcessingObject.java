package com.learn.chapter.eight;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ProcessingObject {

    public static void innerJoin() {
        UnaryOperator<String> head = (String s) -> "hello world";
        UnaryOperator<String> second = (String s) -> "yes, go on";
        Function<String,String> function = head.andThen(second);
        String result = function.apply("already here warting you");
        System.out.println(result);
    }

    public static void main(String[] args) {
        innerJoin();
    }
}
