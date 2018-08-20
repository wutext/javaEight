package com.learn.model;

public class Transaction {

    private int year;
    private int value;
    private Trader trader;

    public Transaction() {
    }

    public Transaction(int year, int value, Trader trader) {
        this.year = year;
        this.value = value;
        this.trader = trader;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }
}
