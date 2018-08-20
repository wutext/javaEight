package com.learn.chapter.nine.moreExtend;

public interface Resizable {
    int getWeight();
    int getHeight();
    void setWeight(int w);
    void setHeight(int h);
    void setAbsoluteSize(int w, int h);
    default void setRelativeSize(int wFactor, int hFactor) {
        setAbsoluteSize(getWeight() / wFactor, getHeight() /hFactor);
    }
}
