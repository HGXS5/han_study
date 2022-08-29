package cn.han.date;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * @Author han_s
 * @Date 2022/4/19 10:36
 * @ProName maven_test
 */
public class DateDemo {
    public static void main(String[] args) {
        test2();
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
