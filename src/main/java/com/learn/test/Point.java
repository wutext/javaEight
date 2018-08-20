package com.learn.test;

import java.util.Arrays;
import java.util.List;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point moveRightBy(int x) {
        return new Point(this.x+x,y);
    }

    public static void main(String[] args) {
        List<Point> ps = Arrays.asList(new Point(10,6),null);
        ps.stream().forEach(p -> p.getX());
    }
}
