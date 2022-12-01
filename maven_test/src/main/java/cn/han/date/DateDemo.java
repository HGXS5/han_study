package cn.han.date;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Author han_s
 * @Date 2022/4/19 10:36
 * @ProName maven_test
 */
public class DateDemo {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(now));
        LocalDate date = LocalDate.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(pattern.format(date));

    }

    public static void test4(){
        /*间隔日期*/
        List<String> dates = new ArrayList<>();

        String startTime = "2019-12-30";
        String endTime = "2020-01-03";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startDate = LocalDate.parse(startTime);
        LocalDate endDate = LocalDate.parse(endTime);

        Period next = Period.between(startDate,endDate);
        int days = next.getDays();
        dates.add(startTime);
        while (days>0){
            startDate = startDate.minusDays(-1);
            String nextDate = formatter.format(startDate);
            dates.add(nextDate);
            days--;
        }

        System.out.println(dates.toString());
    }
    public static void test3(){
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startTime = formatter.format(date);
        System.out.println(startTime);
    }
    public void test1(){
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
    public static void test2(){
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
