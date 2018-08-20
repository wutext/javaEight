package com.learn.chapter.seven;

import org.omg.SendingContext.RunTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 并行流处理数据的速度比较
 */
public class ParallelStream {

    public static Long parallelSum(int n) {

        return Stream.iterate(1L , i -> i+1)
            .limit(n)
            .parallel()
            .reduce(0L, Long::sum);
    }

    public static Long notParallelSum(int n) {

        return Stream.iterate(1L , i -> i+1)
            .limit(n)
            .parallel()
            .reduce(0L, Long::sum);
    }

    public static void parallel() {
        List<Long> list = new ArrayList();
        LongStream.rangeClosed(1, 100).forEach(a -> list.add(a));
        list.stream().parallel().forEach(c -> {
            System.out.println(c);
        });
    }

    public static void rang() {

        /**
         * 1~9
         */
        LongStream.range(1, 10).forEach(System.out::print);
        /**
         * 1~10
         */
        LongStream.rangeClosed(1, 10).forEach(System.out::print);
    }

    public static void main(String[] args) {

        /*Long start1 = System.currentTimeMillis();
        Long sum = parallelSum(1000);
        System.out.println(sum);
        Long end1 = System.currentTimeMillis();

        System.out.println("parallel:"+(end1-start1));

        Long start2 = System.currentTimeMillis();
        Long sum2 = notParallelSum(1500000);
        System.out.println(sum2);
        Long end2 = System.currentTimeMillis();
        System.out.println("notParallel:"+(end2-start2));

        System.out.println(Runtime.getRuntime().availableProcessors());*/
        parallel();
    }
}
