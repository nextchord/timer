package com.example.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");

    public static String getTimeString(Date date) {
        if (null == date) return null;
        return sdfTime.format(date);
    }

    public static String getTimeStringByMills(long mills) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mills);
        return sdfTime.format(calendar.getTime());
    }

    public static long getDay1(long times1, long times) {
        try {
            long l = times - times1;
            return l / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getDay(long times1, long times) {
        try {
            long l = times1 - times;
            return Math.abs(l / (24 * 60 * 60 * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getHour(long times1, long times) {
        try {
            long l = times1 - times;
            long day = l / (24 * 60 * 60 * 1000);
            return Math.abs(l / (60 * 60 * 1000) - day * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getMin(long times1, long times) {
        try {
            long l = times1 - times;
            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            return Math.abs(((l / (60 * 1000)) - day * 24 * 60 - hour * 60));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getSecond(long times1, long times) {
        try {
            long l = times1 - times;
            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            return Math.abs((l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getNextWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);

        if (Calendar.getInstance().getTimeInMillis() > calendar.getTimeInMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 7);
        }
        return calendar.getTimeInMillis();
    }

    public static long getNextMonth(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        Calendar nowCalendar = Calendar.getInstance();
        int nowyear = nowCalendar.get(Calendar.YEAR);
        int nowmonth = nowCalendar.get(Calendar.MONTH);

        calendar.set(Calendar.MONTH, nowmonth);
        if (calendar.getTimeInMillis() < nowCalendar.getTimeInMillis()) {
            nowmonth++;
            nowCalendar.set(Calendar.MONTH, nowmonth);
        }

        int nowday;
        if (day == 31) {
            if (nowmonth == 3 || nowmonth == 5 || nowmonth == 8 || nowmonth == 10) { // 4,6,9,11
                nowday = 30;
            } else if (nowmonth == 1) {
                if (nowyear % 4 == 0) {
                    nowday = 29;
                } else {
                    nowday = 28;
                }
            } else {
                nowday = 31;
            }
        } else if (day == 30 || day == 29) {
            if (nowyear % 4 == 0) {
                nowday = 29;
            } else {
                nowday = 28;
            }
        } else {
            nowday = day;
        }
        nowCalendar.set(Calendar.DAY_OF_MONTH, nowday);
        nowCalendar.set(Calendar.HOUR_OF_DAY, hour);
        nowCalendar.set(Calendar.MINUTE, minute);
        nowCalendar.set(Calendar.SECOND, second);
        return nowCalendar.getTimeInMillis();
    }

    public static long getNextYear(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        Calendar nowCalendar = Calendar.getInstance();
        int nowyear = nowCalendar.get(Calendar.YEAR);
        calendar.set(Calendar.YEAR, nowyear); // 今年的纪念日
        if (calendar.getTimeInMillis() < nowCalendar.getTimeInMillis()) {
            nowyear++;
            nowCalendar.set(Calendar.YEAR, nowyear);
        }
        nowCalendar.set(Calendar.MONTH, month);
        if (month == 1 && day == 29) { // 如果原来设置的是每年的2月29号，则本年要判断闰年还是平年
            if (nowyear % 4 == 0) {
                nowCalendar.set(Calendar.DAY_OF_MONTH, 29);
            } else {
                nowCalendar.set(Calendar.DAY_OF_MONTH, 28);
            }
        } else {
            nowCalendar.set(Calendar.DAY_OF_MONTH, day);
        }
        nowCalendar.set(Calendar.HOUR_OF_DAY, hour);
        nowCalendar.set(Calendar.MINUTE, minute);
        nowCalendar.set(Calendar.SECOND, second);
        return nowCalendar.getTimeInMillis();
    }
}
