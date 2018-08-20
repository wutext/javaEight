package com.learn.chapter.nine;

public interface Sized {

    int getSize();
    int returnSum();

    default boolean isEmpty() {
        return getSize()==0;
    }
}
