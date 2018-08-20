package com.learn.chapter.fourteen.lazymethod;

public class TestLazy {

    public static void main(String[] args) {
        MyList<Integer> myList =
            new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));
    }
}
