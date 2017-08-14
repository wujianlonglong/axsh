package anxian.gateway.admin.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by qinhailong on 16-3-23.
 */
public class DateTimeUtils {


    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return null != dateTime ? formatter.format(dateTime) : "";
    }
}
