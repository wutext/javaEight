package com.learn.chapter.eleven.future;

import com.learn.chapter.eleven.ajaxApi.Product;
import com.learn.chapter.eleven.moreAsync.Discount;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Shop {

    static final Executor executor = Executors.newFixedThreadPool(Math.min(Product.getProducts().size(), 100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    public static List<String> findPrices() {
        List<CompletableFuture<String>> strs =  Product.getProducts().stream()
            .map(p -> CompletableFuture.supplyAsync(() -> p.getPriceByName(p.getName()), executor))
            .map(future -> future.thenApply(Quote::parse))
            .map(future -> future.thenCompose(quote ->
                CompletableFuture.supplyAsync(
                    () ->  Discount.applyDiscount(quote),executor)))
            .collect(Collectors.toList());
        return strs.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
    }

    /**
     * 重构方法：模拟不同延迟时间下最先返回的数据
     * @return
     */
    public static Stream<CompletableFuture<String>> findPricesStream() {
        return  Product.getProducts().stream()
                    .map(p -> CompletableFuture.supplyAsync(
                        () -> p.getPriceByName(p.getName()), executor))
                    .map(future -> future.thenApply(Quote::parse))
                    .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(
                            () ->  Discount.applyDiscount(quote),executor)));

    }

    public static void main(String[] args) {

        /*System.out.println(findPrices());*/
        CompletableFuture[] futures  = findPricesStream().map(p -> p.thenAccept(System.out::println))
        .toArray(size -> new CompletableFuture[size]);
        Object s  = CompletableFuture.anyOf(futures).join();
        int num = 0;
        if(num>0) {
            System.out.println(num);
        }

        System.out.println("result: "+s+"。。。。。。");
    }
}
