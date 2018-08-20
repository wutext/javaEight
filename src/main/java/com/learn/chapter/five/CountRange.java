package com.learn.chapter.five;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * 数值流的应用
 */
public class CountRange {

    //jdk自带的从指定数到指定数的集合
    public static void rangeOrRangeClosed() {

      IntStream.rangeClosed(1, 100).forEach(s ->
          System.out.println(s));
    }

    //求勾股定理
    public static void pythagoreanTheorem() {
        Stream<int[]> ints = IntStream.rangeClosed(1, 100).boxed()
            .flatMap(a ->
                IntStream.rangeClosed(a, 100)
                    .filter(b -> Math.sqrt(a*a+b*b) % 1==0)
                    .mapToObj(b -> new int[] {a,b, (int) Math.sqrt(a*a+b*b)})
            );
       ints.forEach(i -> System.out.println(i[0]+","+i[1]+","+i[2]));
    }

    //构建流
    public static void createStream() {

        Stream<String> str = Stream.of("java", "hello", "dog5");
        str.map(String::toUpperCase)
            .forEach(System.out::print);

    }

    public static void fileLine() {
        long uniqueWord = 0;
        try(Stream<String> lines = Files.lines(Paths.get("d://ccc.txt"),Charset.defaultCharset())){
            uniqueWord =  lines.flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct()
                .count();
        }catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public static void sortNumber() {
        Stream.iterate(new int[]{0,1}, t -> new int[]{t[1], t[0]+t[1]})
            .limit(20)
            .forEach(a -> System.out.println(a[0]+".."+a[1]));
    }

    public static void flatMap() {

        List<Integer> s  =new ArrayList();
        IntStream.rangeClosed(1, 100).boxed()
            .flatMap(a -> {
                s.add(a);
                System.out.println();
                return null;
            });
        s.forEach(m -> System.out.println(m));
    }

    public static void main(String[] args) {
        //pythagoreanTheorem();
       // flatMap();
        //createStream();
        //fileLine();
        sortNumber();
    }
}
