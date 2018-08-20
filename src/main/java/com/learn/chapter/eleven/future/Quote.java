package com.learn.chapter.eleven.future;

import com.learn.chapter.eleven.moreAsync.Discount;

public class Quote {

    private final String shopName;
    private final double price;
    private final Discount.Code code;

    public Quote(String shopName, double price, Discount.Code code) {
        this.shopName = shopName;
        this.price = price;
        this.code = code;
    }

    public static Quote parse(String s) {
        String[] split = s.split(":");
        System.out.println("parse"+split[0]+".."+split[1]);
        return new Quote(split[0], Double.parseDouble(split[1]), Discount.Code.valueOf(split[2].trim()));
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getCode() {
        return code;
    }
}
