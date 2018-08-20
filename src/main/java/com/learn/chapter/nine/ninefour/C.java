package com.learn.chapter.nine.ninefour;

public class C implements B,A {

    public static void main(String[] args) {
        new C().hello();
    }

    public void hello() {
        A.super.hello();
    }
}
