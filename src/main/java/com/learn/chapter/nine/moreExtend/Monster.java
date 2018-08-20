package com.learn.chapter.nine.moreExtend;

public class Monster implements Rotatable, MoveAble, Resizable{

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public void setX(int x) {

    }

    @Override
    public void setY(int y) {

    }

    @Override
    public int getWeight() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void setWeight(int w) {

    }

    @Override
    public void setHeight(int h) {

    }

    @Override
    public void setAbsoluteSize(int w, int h) {

    }

    @Override
    public void setRotatableAngle(int angleInDegress) {
        angleInDegress = 50;
    }

    @Override
    public int getRotatbleAngle() {
        return 200;
    }

    public static void main(String[] args) {
        Monster monster = new Monster();
        monster.rotateBy(180);
        int s = monster.getRotatbleAngle();
        System.out.println(s);
    }
}
