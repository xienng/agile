package org.agileframework.core.utils;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

/**
 * 时间助手类
 *
 * @author xienng
 * @create 2024年01月14日 13:26
 */
public class TimeUtils {
    private static final Map<Duration, String> TIME_INTERVALS = new HashMap<>();
    /**
     * 只含年月日的时间格式
     */
    public static final String YYYYMM = "yyyyMM";
    public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_TIME_ONLY_FORMAT = "HH:mm:ss";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String BLANK = " ";
    public static final String OPERATOR_T = "T";
    public static final String ZONE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String UTC_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String HOLIDAY_REPEAT_FORMAT = "0000-MM-dd";
    public static final String TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public static final String YYYYMMDD = "yyyyMMdd";

    /**
     * 间隔时间的最小数量，如果超过了这个值则保持更小的时间单位，如果超过了则保持不变。比如说，起止时间大于5个月，则返回月份单位；小于等于5个月，则返回天这个时间单位（月的下一级单位）
     */
    public static final int MIN_TIME_INTERVAL = 5;


    static {
        TIME_INTERVALS.put(Duration.ofSeconds(0), "刚刚");
        TIME_INTERVALS.put(Duration.ofMinutes(1), "%d分钟前");
        TIME_INTERVALS.put(Duration.ofHours(1), "%d小时前");
        TIME_INTERVALS.put(Duration.ofDays(1), "%d天前");
        TIME_INTERVALS.put(Duration.ofDays(7), "%d周前");
        TIME_INTERVALS.put(Duration.ofDays(30), "%d个月前");
        TIME_INTERVALS.put(Duration.ofDays(365), "%d年前");
    }

    /**
     * 比较时间戳，返回对人类友好的字符串
     *
     * @param timestamp 指定时间戳
     * @return
     */
    public static String humanComparison(Long timestamp) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime targetDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
        Duration duration = Duration.between(targetDateTime, currentDateTime);
        for (Map.Entry<Duration, String> entry : TIME_INTERVALS.entrySet()) {
            if (duration.compareTo(entry.getKey()) < 0) {
                long diff = Math.abs(entry.getKey().toMinutes() - duration.toMinutes());
                return String.format(entry.getValue(), diff);
            }
        }
        return "";
    }

    /**
     * 获取当前时间，转换成毫秒数
     *
     * @return
     */
    public static long nowEpochMilli() {
        return Instant.now().toEpochMilli();
    }


    /**
     * 将ISO标准时间戳转换成unixTime时间戳。'2011-12-03T10:15:30Z'
     *
     * @param isoTime
     * @return
     */
    public static Long unixTimeWithISO(String isoTime) {
        Instant instant = Instant.parse(isoTime);
        return instant.toEpochMilli();
    }
}
