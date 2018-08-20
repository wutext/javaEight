package com.learn.chapter.eight.strange;

public class IsNumeric implements ValidationStrategy {
    @Override
    public boolean execute(String s) {
        return s.matches("^[1-9]\\d*$");
    }
}
