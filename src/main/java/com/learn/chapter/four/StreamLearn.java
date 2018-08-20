package com.learn.chapter.four;

import com.learn.model.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamLearn {


    /**
     * 此方法测试java中stream(流)的功能
     * @param apples
     * @return
     */
    public static List<String> streamOut(List<Apple> apples) {

        double standard = 40.0;
        return  apples.stream()
            .filter(a -> a.getWeight()>standard) //判断苹果价格大于standard的苹果
            .sorted(Comparator.comparing(Apple::getPrice)) //根据价格进行排序
            .map(Apple::getName)  //只返回苹果的名称
            .collect(Collectors.toList());  //装到list中
    }

    public static void main(String[] args) {

        Apple a1 = new Apple("红富士", "red", 20.9, "5.5", "云南");
        Apple a2 = new Apple("青苹果", "green", 30.9, "7.5", "香港");
        Apple a3 = new Apple("黄牛苹果", "yellow", 50.9, "9.5", "东北");
        Apple a4 = new Apple("美国苹果", "red", 40.9, "15.5", "美国");

        List<Apple> apples = Arrays.asList(a1,a2,a3,a4);
        List<String> strs = streamOut(apples);
        long n = strs.stream().count();
        System.out.println(n);
        //流只能消费一次：下例打印了两次stream中的值会报错
        Stream<String> s = strs.stream();
        s.forEach(System.out::println);
        s.forEach(System.out::println);

    }
}
