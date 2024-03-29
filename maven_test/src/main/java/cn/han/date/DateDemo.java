package cn.han.date;

import org.springframework.beans.factory.annotation.Value;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Author han_s
 * @Date 2022/4/19 10:36
 * @ProName maven_test
 */
public class DateDemo {
    @Value("${redenvelope.start-time}")
    String startTime;
    @Value("${redenvelope.end-time}")
    String endTime;
    @Value("${redenvelope.every-day-start-time}")
    String startDayTime;
    @Value("${redenvelope.every-day-end-time}")
    String endDayTime;
    @Value("${redenvelope.status}")
    String status;

    public static void main(String[] args) {
        /*LocalDateTime转换城date*/
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime now = LocalDateTime.now();
        System.out.println("最初time:" + now);
        ZonedDateTime zonedDateTime = now.atZone(zoneId);
        Instant instant = zonedDateTime.toInstant();
        Date from = Date.from(instant);
        System.out.println(from);

        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());
    }

    public  void test7() {
        Integer actStatus = Integer.valueOf(status);
        /*获取当前时间*/
        String timestamp = DateUtils.timestamp();
        /*拼接：当天开始时间、当天结束时间*/
        String[] times = timestamp.split(" ");
        String timeformart = times[0];
        String startDT = timeformart + " " + startDayTime;
        String endDT = timeformart + " " + endDayTime;
        /*得到long值毫秒时间*/
        Long nowLongTime = DateUtils.toMillisecond(timestamp);
        Long startDayLongTime = DateUtils.toMillisecond(startDT);
        Long endDayLongTime = DateUtils.toMillisecond(endDT);
        Long startLongTime = DateUtils.toMillisecond(startTime);
        Long endLongTime = DateUtils.toMillisecond(endTime);

        if (nowLongTime >= startLongTime && nowLongTime <= endLongTime) {
            if (nowLongTime >= startDayLongTime && nowLongTime <= endDayLongTime) {
                actStatus = 1;
            }
        }
    }

    public static void test6() {
        /*date转换成LocalDateTime*/
        Date time = new Date();
        System.out.println("原始的time:" + time);
        Instant instant = time.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime dateTime = instant.atZone(zoneId).toLocalDateTime();
        System.out.println(dateTime);
    }

    public static void test5() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(now));
        LocalDate date = LocalDate.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(pattern.format(date));
    }

    public static void test4() {
        /*间隔日期*/
        List<String> dates = new ArrayList<>();

        String startTime = "2019-12-30";
        String endTime = "2020-01-03";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startDate = LocalDate.parse(startTime);
        LocalDate endDate = LocalDate.parse(endTime);

        Period next = Period.between(startDate, endDate);
        int days = next.getDays();
        dates.add(startTime);
        while (days > 0) {
            startDate = startDate.minusDays(-1);
            String nextDate = formatter.format(startDate);
            dates.add(nextDate);
            days--;
        }

        System.out.println(dates.toString());
    }

    public static void test3() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startTime = formatter.format(date);
        System.out.println(startTime);
    }

    public void test1() {
        //        Long nowData = (new Date()).getTime();
//        Long startTime = nowData-(1209600000+1000 * 60 * 60 * 24 * 7);
////        Long startTime = nowData-(1209600000);
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String searchStartTime = sdf.format(new Date(startTime));
//        System.out.println(searchStartTime);

        LocalDateTime localDateTime = LocalDateTime.parse("2022-06-13T17:55");

        System.out.println(localDateTime);
    }

    public static void test2() {
        Instant now = Instant.now();
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter basicIsoDate = DateTimeFormatter.BASIC_ISO_DATE;
        String format = basicIsoDate.format(dateTime);
        Locale locale = basicIsoDate.getLocale();

        System.out.println(format);
        System.out.println(now.getEpochSecond());
        System.out.println(dateTime);
    }
}
