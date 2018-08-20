package com.learn.chapter.eight.observer;

public class NyTimes implements Observer{
    @Override
    public void notify(String tweet) {
        if(tweet !=null && tweet.contains("money")) {
            System.out.println("breaking in Ny:" +tweet);
        }
    }
}
