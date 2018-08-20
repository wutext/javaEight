package com.learn.chapter.eleven.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BasicFuture {

    public static double doFuture() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Double> future = executorService.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                Thread.sleep(5000);
                return doSomeLongComputation();
            }

            private Double doSomeLongComputation() {
                return 10.0;
            }
        });
        dosomeOtherThing();
        try {
            //运行dofuture步骤，同事运行dosomeOtherThing()方法，处理结果时等待doFuture 11秒钟是否运行完成
            Double result = future.get(2, TimeUnit.SECONDS);
            System.out.println(result);
            return result;
        } catch (InterruptedException e) {
            //线程在运行中中断异常
            e.printStackTrace();
        } catch (ExecutionException e) {
            //计算抛出异常
            e.printStackTrace();
        } catch (TimeoutException e) {
            //在Future对象完成之前超过已过期
            e.printStackTrace();
        }
        return 0.0;
    }

    private static void dosomeOtherThing() {

        System.out.println("some other thing");
    }

    public static void main(String[] args) {
        doFuture();
    }
}
