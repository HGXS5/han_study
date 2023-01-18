package cn.han.date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @Author han_s
 * @Date 2022/11/2 16:19
 * @ProName wasu-obmp-activity5g
 */
public class DateUtils {

    public static String timestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(now);
    }

    public static Long toMillisecond(String formatTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(formatTime, formatter);
        return LocalDateTime.from(startDateTime).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
