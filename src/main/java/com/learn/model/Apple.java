package com.learn.model;

public class Apple {

    private String name;
    private String color;
    private double weight;
    private String price;
    private String placeProduction;

    public Apple() {
    }

    public Apple(String name, String color, double weight, String price, String placeProduction) {
        this.name = name;
        this.color = color;
        this.weight = weight;
        this.price = price;
        this.placeProduction = placeProduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPlaceProduction() {
        return placeProduction;
    }

    public void setPlaceProduction(String placeProduction) {
        this.placeProduction = placeProduction;
    }
}
