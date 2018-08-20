package com.learn.chapter.five;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 第五章 : 使用流
 */
public class UseStream {

    public static List<String> streamSkip(List<String> list) {

        return list.stream()
            .skip(3)
            .collect(Collectors.toList());

    }

    public static List<Integer> streamMap(List<String> list) {
        return list.stream()
            .map(String::length)
            .skip(3)
            .collect(Collectors.toList());
    }

    public static List<String> flatMap(String[] str) {

        Stream<String> words = Arrays.stream(str);
        return words.map(w -> w.split(""))
            .flatMap(Arrays::stream)
            .distinct()
            .collect(Collectors.toList());

    }

    public static List<int[]> flatMapTwo(List<Integer> list1, List<Integer> list2) {

         return list1.stream()
                .flatMap(i -> list2.stream()
                    .map(a -> new int[]{i,a}))
                .collect(Collectors.toList());
    }

    public static boolean anyMatch(List<String> str) {

        return str.stream().anyMatch(a -> a.equals("dsfds"));
    }

    public static Optional<String> findAny (List<String> str) {
        return str.stream()
            .filter(a -> a.length()>10)
            .findAny();
    }

    public static int streamResuce(List<String> list) {

        return list.stream()
            .map(a -> 1)
            .reduce(0, (a,b) -> a+b);
    }

    public static Optional<Integer> streamResuceForMax(List<String> list) {

        return list.stream()
            .map(a -> a.length())
            .reduce(Integer::max);
    }

        public static void main(String[] args) {

        List<String> list = Arrays.asList("aaaa", "dsfd", "12673", "cccccc8jmmmm", "ddddd","eeeee");
        String[] str = {"hello", "world"};
//        UseStream.streamSkip(list).stream().forEach(System.out::println);
//        UseStream.streamMap(list).stream().forEach(System.out::println);
//        UseStream.flatMap(str).stream().forEach(System.out::print);
        List<Integer> list1 = Arrays.asList(1,5,8);
        List<Integer> list2 = Arrays.asList(3,9);
        flatMapTwo(list1,list2).stream()
            .forEach(f -> {
                System.out.print(f[0]);
                System.out.println(f[1]);
            });
        System.out.println(anyMatch(list));
        Optional<String> result = findAny(list);
        System.out.println(result);
        int sum = streamResuce(list);
        System.out.println(sum);
        Optional<Integer> max = streamResuceForMax(list);
        System.out.println(max);

    }


}
