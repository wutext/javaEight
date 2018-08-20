package com.learn.chapter.eleven.moreAsync;

import com.learn.chapter.eleven.future.Quote;

public class Discount {

    public enum Code {
        NONE(0), SILVEL(5), GOLD(10), PLATINUM(15), DIAMOND(20);
        private final int percentage;
        Code(int percentage) {
            this.percentage = percentage;
        }

        public int getPercentage() {
            return percentage;
        }
    }

    public static String applyDiscount(Quote quote) {

        System.out.println("discount"+quote.getShopName()+".."+quote.getPrice());
        return quote.getShopName()+" price is " + Discount.apply(quote.getPrice(),quote.getCode());
    }

    private static String apply(double price, Code code ) {
        delay();
        return String.valueOf(price * (100-code.percentage) / 100);
    }

    public static void delay() {
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e) {
            throw new RuntimeException();
        }
    }


}
