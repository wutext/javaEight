package com.learn.chapter.nine.moreExtend;

public interface Rotatable {

    void setRotatableAngle(int angleInDegress);
    int getRotatbleAngle();
    default void rotateBy(int angleInDegress) {
        setRotatableAngle((getRotatbleAngle()+angleInDegress) % 360);
    }
}
