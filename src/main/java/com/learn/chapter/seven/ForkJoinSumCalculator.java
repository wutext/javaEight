package com.learn.chapter.seven;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long threshold = 1000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers,0,numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end-start;
        if(length<threshold) {
            return computeSequentially();
        }
        ForkJoinSumCalculator leftTask   = new ForkJoinSumCalculator(numbers, start, start+length/2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start+length/2, end);
        long rightResult = rightTask.compute();
        long leftResult = leftTask.join();
        return leftResult + rightResult;
    }

    private Long computeSequentially() {
        long sum = 0;
        for(int i=start;i<end;i++) {
            sum+=numbers[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int n=4000;
        Long s =  forkJoinSum(n);
        System.out.println(s);
    }

    private static long forkJoinSum(int n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> forkJoinTask = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(forkJoinTask);
    }
}
