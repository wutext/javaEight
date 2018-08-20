package com.learn.chapter.seven;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.SubstituteLogger;

import java.util.logging.Level;

public class SpliteratorStream {


    private static Logger logger = LoggerFactory.getLogger(SpliteratorStream.class);

    public static int countSum(String str) {

        int count = 0;
        boolean flag = true;
        for(char ch:str.toCharArray()) {
            if(Character.isWhitespace(ch)) {
                flag = true;
            }else {
                if(flag) {
                    count++;
                }
                flag = false;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String str = "A life-changing leukaemia drug will be";
        System.out.println(countSum(str));
    }
}
