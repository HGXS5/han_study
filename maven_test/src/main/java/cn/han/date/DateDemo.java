package cn.han.date;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author han_s
 * @Date 2022/4/19 10:36
 * @ProName maven_test
 */
public class DateDemo {
    public static void main(String[] args) {
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
}
