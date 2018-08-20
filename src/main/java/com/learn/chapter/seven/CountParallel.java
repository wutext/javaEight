package com.learn.chapter.seven;

public class CountParallel {

    private final int count;
    private final boolean isWhiteSpace;

    public CountParallel(int count, boolean isWhiteSpace) {
        this.count = count;
        this.isWhiteSpace = isWhiteSpace;
    }

    public CountParallel accumulate(Character c) {

        if(Character.isWhitespace(c)) {
            return isWhiteSpace? this:new CountParallel(count, true);
        } else {

            return isWhiteSpace? new CountParallel(count+1, false):this;
        }

    }

    public static void runnables() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        };

        Runnable runnable2 = () -> System.out.println("hello stream");
    }

    public static void main(String[] args) {
        runnables();
    }

}
