package com.learn.chapter.eleven.ajaxApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

/**
 * 此方法测试CompletableTFuture异步的效率：
 *      测试一: 测试CompletableTFuture的使用方式； 目的--在使用CompletableFuture来调用计算方法，同事可以去做其他事情
 *                      1) getPriceOriginal() 方法为原始方法,直接执行代码；
 *                      2) getPriceAsncy() 使用completableFuture方式来执行代码，并手动捕捉异常；
 *                      3) getAutoPriceAsync() 自动捕捉异常；
 *      测试二: 共四个方法：
 *                  1) getMarketParallelStream()方法 使用stream测试获数据的执行时间；
 *                  2) getMarket()方法使用CompletableFuture()方式异步的执行时间；
 *                  3) getMarketLast() 和2)相同，只是在此基础上使用join()方法等待异步执行完后填充数据在返回；
 *                  4) getMarketLastExecutor() 在3)基础上修改线程的大小执行代码，效率最高；
 */
public class Shop {

    private static Logger logger = LoggerFactory.getLogger(Shop.class);


    /**测试一,二:
     * 获取价格： 原始版本
     */
    public static double getPriceOriginal(String name) {
        return calculatePrice(name).getAsDouble();
    }
    /**
     *测试一:
     * 异步调用版本一:手动捕捉异常
     * @param name
     * @return
     */
    public Future<OptionalDouble> getPriceAsncy(String name) {
        CompletableFuture<OptionalDouble> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            try {
                OptionalDouble price = calculatePrice(name);
                completableFuture.complete(price);
            }catch(Exception e) {
                completableFuture.completeExceptionally(e);
            }

        }).start();
        return completableFuture;
    }

    /**测试一:
     * 异步调用版本2: 使用工厂方法supplyAsync创建CompletableFuture
     * @param name
     * @return
     */
    public static Future<OptionalDouble> getAutoPriceAsync(String name) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(name));
    }

    public static OptionalDouble calculatePrice(String name) {
        delay();
        return getProducts().stream()
            .filter(p -> p.getName().equals(name))
            .mapToDouble(Product::getPrice)
            .min();
    }






    /**
     * 测试二:
     * 此方式通过修改线程数来使效率达到最高优化
     * 线程池大 小与处理器的利用率之比可以使用下面的公式进行估算：Nthreads = NCPU * UCPU * (1 + W/C)
     *      其中：❑NCPU是处理器的核的数目，可以通过Runtime.getRuntime().availableProce- ssors()得到
     *           ❑UCPU是期望的CPU利用率（该值应该介于0和1之间）
     *           ❑W/C是等待时间与计算时间的比率
     *
     * @param products
     * @return
     */
    public static List<String> getMarketLastExecutor(List<Product> products) {
        final Executor executor = Executors.newFixedThreadPool(Math.min(products.size(), 100), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        });

        List<CompletableFuture<String>> list =  products.stream()
            .map(product -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f",product.getName(), getPriceOriginal(product.getName())), executor))
            .collect(Collectors.toList());
        return list.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
    }

    /**
     * 测试二:
     * 添加join方法，将所有异步跑完后填充string数据再返回数据
     * @param products
     * @return
     */
    public static List<String> getMarketLast(List<Product> products) {
        List<CompletableFuture<String>> list =  products.stream()
            .map(product -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f",product.getName(), getPriceOriginal(product.getName()))))
            .collect(Collectors.toList());
        return list.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
    }

    /**
     * 测试二:
     * 使用异步方式执行
     * @param products
     * @return
     */
    public static List<CompletableFuture<String>> getMarket(List<Product> products) {
        return products.stream()
            .map(product -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f",product.getName(), getPriceOriginal(product.getName()))))
            .collect(Collectors.toList());
    }

    /**测试二:
     * 使用parallelStream方法执行
     */
    public static List<String> getMarketParallelStream(List<Product> products) {

        return products.parallelStream()
            .map(product -> String.format("%s price is %.2f",product.getName(), getPriceOriginal(product.getName())))
            .collect(Collectors.toList());
    }

    public static void delay() {
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public static List<Product> getProducts() {
        return Arrays.asList(new Product("北京菜市场",10.0,"西瓜"),
            new Product("天津菜市场",12.0,"西瓜"),
            new Product("青岛菜市场",17.0,"西瓜"),
            new Product("青岛菜市场",17.0,"orange"));
    }

    //此方法可以在main函数中直接运行来测试使用异步发送请求的性能
    public static double getPriceResult(String name) {
        long start = System.nanoTime();
        Future<OptionalDouble> price = getAutoPriceAsync(name);
        long between = (System.nanoTime()-start) / 100000;
        System.out.println("Invocation returned after "+between);
        dosomethingElse();

        try {
            OptionalDouble p  = price.get();
            System.out.println("价格："+p);
            return p.getAsDouble();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } /*catch (TimeoutException e) {
            logger.info("请求超时");
            e.printStackTrace();
        }*/
        return 0.0;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //***测试目的: 检测使用future的效率 **********************
        /*getPriceResult("北京菜市场");*/

        //***测试目的 : 比较stream、parallelStream、CompletableFuture发起异步请求, 和修改线程数方式 各自 的效率  ******
        long start = System.nanoTime();
        System.out.println("d");
        //List<String> s1 = getMarketParallelStream(getProducts()); //方法1
        //List<CompletableFuture<String>> s = getMarket(getProducts()); //方法2
        //List<String> list = getMarketLast(getProducts()); //方法3
        List<String> list = getMarketLastExecutor(getProducts()); //方法4
        System.out.println(list); //方法4 打印结果
        //System.out.println(list); //方法3 打印结果
        long end  = (System.nanoTime()-start) / 1000000;
        //System.out.println(s.get(0).get()); //方法2 打印结果
        //System.out.println(s1.get(0)); //方法1 打印结果
        System.out.println("用时："+end);
        /** 测试报告： (毫秒) : stream : 8000以上, parallelStream : 2000以上左右, CompletableFuture : 200, 利用exector效率更高
         * 注: 方法2 在执行过程中s中String的值在调用getMarket()方法执行完以后不一定立马会有值，需要再执行完异步方法后才会有值；
         *    方法3 是在方法2的基础上进行改进 : 等待所有异步完成后对string的值进行填充完再返回结果
         */

        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    private static void dosomethingElse() {
        System.out.println("do other thing");
    }
}
