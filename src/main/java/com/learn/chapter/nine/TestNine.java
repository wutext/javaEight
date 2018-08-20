package com.learn.chapter.nine;

public class TestNine {

    private Sized sized;

    public TestNine(Sized s) {
        this.sized = s;
    }
    public static void getSized() {

        TestNine testNine = new TestNine(new DefaultMethod());
        testNine.sized.isEmpty();
    }
}
