package com.learn.chapter.six;

import com.learn.model.Menu;

import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 第六章: 1: 用流收集数据
 *       2: 分组
 */
public class StreamCollection {

    /**
     * 比较出最大值：先定义一个比较器，再用Collector.maxBy调用
     * @param menus
     */
    public static void streams(List<Menu> menus) {
        Comparator<Menu> hotNumComparator = Comparator.comparing(Menu::getHotNum);
        Optional<Menu> m = menus.stream().collect(Collectors.maxBy(hotNumComparator));
        System.out.println(m.get().getName());
    }

    /**
     * Collectors.summingInt() (summingLong(),summingDouble())等方法统计总数,
     * @param menus
     */
    public static void sumHotNum(List<Menu> menus) {
        double hotSum = menus.stream()
            .collect(Collectors.summingDouble(Menu::getHotNum));
        System.out.println(hotSum);
    }

    /**
     * 求平均值、最大值、最小值、总数、个数的方法(一次性求出)
     * @param menus
     */
    public static void summaryStatic(List<Menu> menus) {
        DoubleSummaryStatistics iss = menus.stream().collect(Collectors.summarizingDouble(Menu::getHotNum));
        System.out.println(iss.getAverage());
        System.out.println(iss);
    }

    /**
     * 字符串连接
     * @param menus
     */
    public static void reduceMethod(List<Menu> menus) {
       String str = menus.stream()
        .map(Menu::getName)
        .collect(Collectors.joining(","));
        System.out.println("拼接的字符串："+str);
    }

    /**
     * 分组
     * @param
     */
    public static void groupByType(List<Menu> menus) {


        Map<Menu.CaloricLevel, List<Menu>> dishs = menus.stream().collect(
            Collectors.groupingBy(Menu::getCaloricLevel));
        Long s = dishs.get("diet").stream().count();
        System.out.println(s);
    }

    /**
     * 多级分组
     * @param
     */
    public static void moreGroupBy(List<Menu> menus) {

        double diet = 10.0;
        double fet = 20.0;
        double normal = 30.0;
        Map<String, Map<Menu.CaloricLevel, List<Menu>>>map = menus.stream()
            .collect(Collectors.groupingBy(Menu::getType,
                        Collectors.groupingBy(m -> {
                            if(m.getHotNum() <= diet)
                                return Menu.CaloricLevel.DIET;
                            else if(m.getHotNum() <= normal)
                                return Menu.CaloricLevel.DIET;
                            else return Menu.CaloricLevel.FAT;
                    })
                )
            );
    }

    /**
     * 转换分组返回值
     * @param menus
     */
    public static void turnGroup(List<Menu> menus) {
        Map<String, Object> map = menus.stream()
            .collect(
                Collectors.groupingBy(Menu::getType,
                    Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingDouble(Menu::getHotNum)),
                            Optional::get)));
    }

    public static void main(String[] args) {
        //streams(getMenus());
        sumHotNum(getMenus());
        //summaryStatic(getMenus());
        //reduceMethod(getMenus());
        //groupByType(getMenus());

    }

    public static List<Menu> getMenus() {
        return Arrays.asList(
            new Menu(1, "黄花鱼",20.5, 100.89),
            new Menu(1, "红牛烧茄子",28.0, 150.89),
            new Menu(1, "皮蛋豆腐",10, 20.89),
            new Menu(1, "水仙花",30.5, 150.99),
            new Menu(1, "酥肉",35.5, 130.89),
            new Menu(1, "小葱拌豆腐",12.5, 110.89)
        );
    }
}
