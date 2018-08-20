package com.learn.model;

public class Menu {

    private Integer id;
    private String name;
    private double price;
    private double hotNum;
    private String type;

    public Menu() {
    }

    public Menu(Integer id, String name, double price, double hotnum) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.hotNum = hotnum;
    }

    public Menu(Integer id, String name, double price, double hotnum, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.hotNum = hotnum;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getHotNum() {
        return hotNum;
    }

    public void setHotNum(double hotNum) {
        this.hotNum = hotNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public enum CaloricLevel{
        DIET, NORMAL, FAT;
    }

    public CaloricLevel getCaloricLevel() {
        double diet = 10.0;
        double fet = 20.0;
        double normal = 30.0;
        if(this.getHotNum() <= diet)
            return CaloricLevel.DIET;
        else if(this.getHotNum() <= normal)
            return CaloricLevel.DIET;
        else return CaloricLevel.FAT;
    }
}
