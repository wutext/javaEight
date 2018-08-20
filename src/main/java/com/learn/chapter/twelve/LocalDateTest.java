package com.learn.chapter.twelve;

import javafx.scene.input.DataFormat;
import org.apache.tomcat.jni.Local;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.zone.ZoneRules;
import java.util.TimeZone;

public class LocalDateTest {

    private TemportalAdjuster temportalAdjuster;

    /**
     * 日期 ： 获取年月日的方式
     */
    public static void yearMonthDay() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        LocalDate localDate2 = LocalDate.of(2018,8,16);
        System.out.println("日期："+localDate2);
        int year = localDate2.getYear();
        System.out.println("年："+year);
        Month month = localDate2.getMonth();
        System.out.println("月："+month);
        int day = localDate2.getDayOfMonth();
        System.out.println("日："+day);
        DayOfWeek dayOfWeek = localDate2.getDayOfWeek();
        System.out.println("周中天："+dayOfWeek);
        int lengthOfMonth = localDate2.lengthOfMonth();
        System.out.println("年中月："+lengthOfMonth);
    }

    /**
     * 日期 ：获取年月日的方式 ChronoField
     */
    public static void temporalFieeld() {
        LocalDate date = LocalDate.of(2018,8,16);
        int year = date.get(ChronoField.YEAR);
        System.out.println(year);
        int week = date.get(ChronoField.MONTH_OF_YEAR);
        System.out.println(week);
        int dayOfMonth = date.get(ChronoField.DAY_OF_MONTH);
        System.out.println(dayOfMonth);
    }

    /**
     * 小时：获取当前小时数
     */
    public static void localTime() {

        LocalTime localTime = LocalTime.of(16,21,59);
        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        int second = localTime.getSecond();
        System.out.println(hour+" 时 "+minute+" 分 "+second+" 秒");

        LocalTime nowTime = LocalTime.now();
        int now_hour = nowTime.getHour();
        int now_minute = nowTime.getMinute();
        int now_second = nowTime.getSecond();
        System.out.println("当前时间："+now_hour+" 时 "+now_minute+" 分 "+now_second+" 秒");
    }

    public static void timeDateFormate() {

        LocalDate localDate = LocalDate.parse("2018-08-16");
        System.out.println("年："+localDate.getYear());
        LocalTime localTime = LocalTime.parse("16:30:27");
        System.out.println("时间："+localTime);
    }

    public static void localDateTime() {

        LocalDateTime handDateTime = LocalDateTime.of(2018,8,16,16,38,23);
        System.out.println("手动时间："+handDateTime);
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println("日期："+localDateTime.toLocalDate()+"  时间"+localDateTime.toLocalTime());
        System.out.println("当前年月日时分秒："+localDateTime.toString());
        System.out.println("年："+localDateTime.getYear());
        System.out.println("月："+localDateTime.getMonth());
        System.out.println("日："+localDateTime.getDayOfMonth());
        System.out.println("时分秒："+localDateTime.getHour());

        //转换日期样式
        String dataFormat = handDateTime.format(DateTimeFormatter.ofPattern("yyyy MM dd hh:mm:ss"));
        System.out.println(dataFormat);
    }

    public static void duration() {

        Duration duration = Duration.ofMinutes(3);
        System.out.println(duration);

        Duration duration2 = Duration.of(3, ChronoUnit.MINUTES);
        System.out.println(duration2);

        Period period = Period.ofDays(6);
        System.out.println(period);
        Period period2 = Period.ofWeeks(8);
        System.out.println(period2.getMonths());
    }

    //加减日期 像LocalDate、LocalTime、LocalDateTime以及Instant都有很多相同的方法

    /**
     * with(x, y): 可以置换时间
     * plus(), minus() : 加减时间
     */
    public static void modifyDate() {
        LocalDate localDate = LocalDate.of(2018,8,16);
        LocalDate localDate1 = localDate.withYear(2011);
        System.out.println(localDate1);

        LocalDate localDate2 = localDate1.withDayOfMonth(10);
        System.out.println(localDate2);

        LocalDate localDate3 = localDate2.with(ChronoField.DAY_OF_MONTH,5);
        System.out.println(localDate3);

        LocalDate localDate4 = localDate3.with(ChronoField.MONTH_OF_YEAR,2);
        System.out.println(localDate4);

        LocalDate localDate5 = localDate4.minusDays(6);
        System.out.println(localDate5);

        //减月
        LocalDate localDate5_1 = localDate4.minus(6, ChronoUnit.MONTHS);
        System.out.println("减月："+localDate5_1);

        LocalDate localDate6 = localDate5.plusMonths(3);
        System.out.println("加月："+localDate6);

        //plus(*,*)加年、月、日 使用ChronoUtil类
        LocalDate localDate7 = localDate6.plus(2,ChronoUnit.YEARS);
        System.out.println("加年："+localDate7);

        LocalDateTime last = LocalDateTime.now();
        LocalDateTime result = last.with(ChronoField.YEAR,2016).plus(2,ChronoUnit.MONTHS)
            .minus(5,ChronoUnit.DAYS)
            .plus(2, ChronoUnit.HOURS);
        System.out.println("最终时间: "+result);
    }

    public static void temporalAdjuster() {
        LocalDate date = LocalDate.now();
        LocalDate date1 = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(date1);

        LocalDate date2 = date1.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(date2);
    }

    /**
     * 解析日期-时间对象，转换日期格式
     */
    public static void formate() {

        LocalDate date = LocalDate.of(2018, 8, 18);
        String s = date.format(DateTimeFormatter.BASIC_ISO_DATE); //yyyyMMdd
        System.out.println("日期格式转字符串 1 ："+s);

        LocalDate date1 = LocalDate.parse(s, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("字符串转日期格式-1 : "+date1);

        String s1 = date.format(DateTimeFormatter.ISO_DATE); //yyyy-MM-dd
        System.out.println("日期格式转字符串 2 ："+s1);

        LocalDate date2 = LocalDate.parse(s1, DateTimeFormatter.ISO_DATE);
        System.out.println("字符串转日期格式-2 : "+date2);

        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);//yyyy-MM-dd
        System.out.println("日期格式转字符串 3 ："+s2);
    }

    /**
     * 日期模板类型转换
     */
    public static void formate2() {
        //yyyy-MM-dd,  yyyy:MM:dd
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy:MM:dd");
        LocalDate date = LocalDate.of(2012,8,12);

        //日期转格式
        String s = date.format(dateTimeFormatter);
        System.out.println("日期格式转化为 ： "+s);


        LocalDate date1 = LocalDate.parse(s, dateTimeFormatter);
        System.out.println("字符串转为时间格式 : "+date1);
    }

    //获取时区时间
    public static void zoneTime() {

        ZoneId romeZone = ZoneId.of("Europe/Rome");
        //获取地区时区信息
        TimeZone timeZomeZone = TimeZone.getTimeZone("Europe/Rome");
        System.out.println(romeZone);

        //获取当地时区名称
        ZoneId zoneId = TimeZone.getDefault().toZoneId();
        System.out.println(zoneId);

        //获取时间
        LocalDateTime date = LocalDateTime.of(2018, Month.MARCH, 20, 13,46);
        System.out.println("打印时间： "+date);

        //转换为指定时区时间
        ZonedDateTime time = date.atZone(timeZomeZone.toZoneId());
        System.out.println(time);

        //获取当前时间
        Instant instant = Instant.now();
        System.out.println(instant);
        ZonedDateTime zonedDateTime = instant.atZone(romeZone);
        System.out.println("获取指定时区时间—："+zonedDateTime);
    }

    public static void main(String[] args) {
        //yearMonthDay();
        //temporalFieeld();
        //localTime();
        //timeDateFormate();
        //localDateTime();
        //duration();
        //modifyDate();
        //formate();
        //formate2();
        zoneTime();
    }
}
