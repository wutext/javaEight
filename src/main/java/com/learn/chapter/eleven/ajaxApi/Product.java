package com.learn.chapter.eleven.ajaxApi;

import com.learn.chapter.eleven.moreAsync.Discount;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class Product {

    private Integer id;
    private String name;
    private double price;
    private String productName;

    public Product() {
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(Integer id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(String name, double price, String productName) {
        this.name = name;
        this.price = price;
        this.productName = productName;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPriceByName(String name) {
        double price = calculatePrice(name).getAsDouble();
        Discount.Code discount = Discount.Code.valueOf("NONE");
        System.out.println(String.format("%s : %.2f : %s", name, price, discount));
        return String.format("%s : %.2f : %s", name, price, discount);
    }

    public static OptionalDouble calculatePrice(String name) {
        delay();
        return getProducts().stream()
            .filter(p -> p.getName().equals(name))
            .mapToDouble(Product::getPrice)
            .min();
    }

    public static void delay() {
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public static List<Product> getProducts() {
        return Arrays.asList(new Product("北京菜市场",10.0,"西瓜"),
            new Product("天津菜市场",12.0,"西瓜"),
            new Product("青岛菜市场",17.0,"西瓜"),
            new Product("青岛菜市场",17.0,"orange"));
    }
}
