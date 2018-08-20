package com.learn.chapter.eight.observer;

public class LemodeTime implements Observer {
    @Override
    public void notify(String tweet) {
        if(tweet!=null && tweet.contains("wine")) {
            System.out.println("todays cheese news for wine :" +tweet);
        }
    }
}
