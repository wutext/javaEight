package com.learn.chapter.thirteen;

public class DiGui {

    public static int factorialIterative(int n) {

        int r=1;
        for(int i=1;i<=n;i++) {
            r*=i;
        }
        return r;
    }

    //原始递归
    public static long factorialRecursive(long n) { //f(n) = n * f(n-1);
        return n==1? 1 : n * factorialRecursive(n-1);
    }

    //改进递归
    public static long factorialRecursiveSecond(long n) {
        return factorialHelper(1, n);
    }

    private static long factorialHelper(long i, long n) {

        return n==1 ? i :  factorialHelper(i*n, n-1);
    }

    public static void intSum() {

    }

    public static void main(String[] args) {
        int first = factorialIterative(3);
        System.out.println("第一个结果： "+first);
        long s = factorialRecursive(3);
        System.out.println("第二个结果："+s);

        long s3 = factorialRecursiveSecond(3);
        System.out.println(s3);
    }
}
