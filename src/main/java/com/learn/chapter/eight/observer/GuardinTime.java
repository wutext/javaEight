package com.learn.chapter.eight.observer;

public class GuardinTime implements Observer{
    @Override
    public void notify(String tweet) {
        if(tweet!=null && tweet.contains("queen")) {
            System.out.println("Yet another news in nework:" +tweet);
        }
    }
}
