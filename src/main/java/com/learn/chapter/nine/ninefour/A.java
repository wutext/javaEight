package com.learn.chapter.nine.ninefour;

public interface A {

    default void hello() {
        System.out.println("hello A");
    }
}
