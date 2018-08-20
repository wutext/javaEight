package com.learn.chapter.eight.observer;

import java.util.ArrayList;
import java.util.List;

public class Feed implements Subject {

    private final List<Observer> observerList = new ArrayList();

    @Override
    public void registerObserver(Observer observer) {
        this.observerList.add(observer);
    }

    @Override
    public void notifyObserver(String tweet) {
        observerList.forEach(t -> t.notify(tweet));
    }

    public static void main(String[] args) {
        String tweet = "money";
        Feed f = new Feed();
        f.registerObserver(new NyTimes());
        f.registerObserver((String s) -> {
            if(tweet!=null && tweet.contains("money")) {
                System.out.println("give me "+tweet);
            }
        });
        f.registerObserver(new LemodeTime());
        f.notifyObserver("i'm wine");
    }
}
