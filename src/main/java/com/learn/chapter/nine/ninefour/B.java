package com.learn.chapter.nine.ninefour;

public interface B{

    default void hello() {
        System.out.println("hello B");
    }
}
