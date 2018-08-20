package com.learn.chapter.eight;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Peek {

    public static void peek () {
        List<Integer> list = Arrays.asList(3,4,5,6,8);
        list.stream()
            .peek(x -> System.out.println("first: "+x))
            .map(x -> x+16)
            .peek(x -> System.out.println("nap after: "+x))
            .filter(x -> x%2==0)
            .peek(x -> System.out.println("filter after: "+x))
            .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        peek();
    }
}
