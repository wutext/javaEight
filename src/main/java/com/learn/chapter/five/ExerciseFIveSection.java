package com.learn.chapter.five;

import com.learn.model.Trader;
import com.learn.model.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class ExerciseFIveSection {


    public static List<Transaction> sortByvalue(List<Transaction> list) {

        return list.stream()
            .filter(t -> t.getYear()==2011)
            .sorted(Comparator.comparing(Transaction::getValue))
            .collect(Collectors.toList());
    }

    public static List<String> filterCityByTrader(List<Transaction> transactions) {

        return transactions.stream()
            .map(t -> t.getTrader().getCity())
            .sorted()
            .collect(Collectors.toList());
    }

    public static List<Trader> getTraderName(List<Transaction> transactions) {

        return transactions.stream()
            .map(t -> t.getTrader())
            .filter(t -> t.getCity().equals("Cambridge"))
            .collect(Collectors.toList());
    }

    public static String getNameReduce(List<Transaction> transactions) {

        return transactions.stream()
            .map(t -> t.getTrader().getName())
            .distinct()
            .sorted()
            .reduce("", (a,b) -> a+b);
    }

    public static boolean isHaveMilan(List<Transaction> transactions) {
        return transactions.stream()
            .anyMatch(transaction -> transaction.getTrader().getCity().equals("milan"));
    }

    public static int getValueSum(List<Transaction> transactions) {

         return transactions.stream()
            .filter(transaction -> transaction.getTrader().getCity().equals("milan"))
            .map(transaction -> transaction.getValue())
            .reduce(0, (a,b) -> a+b);

    }

    public static int findValueSum(List<Transaction> transactions) {

        return transactions.stream()
            .mapToInt(Transaction::getValue)
            .sum();
    }

    public static OptionalInt findValueMin(List<Transaction> transactions) {

        return transactions.stream()
            .mapToInt(Transaction::getValue)
            .max();
    }


    public static void optionalStream(List<Transaction> list) {

       OptionalInt max =  list.stream()
            .mapToInt(Transaction::getValue)
            .max();
       int m = max.orElse(5);
        System.out.println("特化后的最大值：" +m);
    }

    public static void main(String[] args) {

        //找出2011年的记录并排序
        sortByvalue(getList()).stream().forEach(s -> {
            System.out.print("name: "+ s.getValue()+" | ");
        });

        //找出Trader中所有的city并不重复
        System.out.println();
        System.out.println("************************************");
        filterCityByTrader(getList()).stream().forEach(System.out::print);

        //找出所有来自剑桥的交易员
        System.out.println();
        System.out.println("************************************");
        getTraderName(getList()).stream().forEach(t -> {
            System.out.print("name: "+ t.getName()+" | ");
        });

        //拼接每个Trader的name
        System.out.println();
        System.out.println("************************************");
        String name = getNameReduce(getList());
        System.out.println("拼接的名字："+ name+" | ");

        System.out.println();
        System.out.println("************************************");
        boolean flag = isHaveMilan(getList());
        System.out.println("是否有在米兰工作的："+flag);

        System.out.println();
        System.out.println("************************************");
        int totalSum = getValueSum(getList());
        System.out.println("生活在米兰的同事的所有交易额："+totalSum);

        //最大的交易额
        int x = findValueSum(getList());
        System.out.println("总交易额："+x);

        OptionalInt minValue = findValueMin(getList());
        System.out.println("最小交易额："+ minValue);

        //特化后的数值
        optionalStream(new ArrayList());
    }

    public static List<Transaction> getList() {

        Trader raoul = new Trader("raoul", "Cambridge");
        Trader mario = new Trader("mario", "milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        return Arrays.asList(
            new Transaction(2011, 300,brian),
            new Transaction(2012, 1000, raoul),
            new Transaction(2011, 400, raoul),
            new Transaction(2012, 710, mario),
            new Transaction(2012, 700,mario),
            new Transaction(2012, 950, alan)
        );
    }
}
