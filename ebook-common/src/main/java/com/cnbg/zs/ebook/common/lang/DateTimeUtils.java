package com.cnbg.zs.ebook.common.lang;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/9/4 11:03
* @Description 日期处理类，基于JDK8进行日期处理
*/
public class DateTimeUtils {
    public static final String FORMAT_PATTERN_DATE = "yyyy-MM-dd";
    public static final String FORMAT_PATTERN_DATE_TIME_ZONE_MIN = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_PATTERN_DATE_TIME_ZONE_MIS = "yyyy-MM-dd HH:mm:ss";

    /**
    * 获取当前时间
    * @return
    */
    public static LocalDateTime getLocalDateTime(){
        return LocalDateTime.now();
    }

    /**
    * 獲取LocalDate對象，即當日时间
    * @return
    */
    public static LocalDate getLocalDate(){
        return LocalDate.now();
    }

    /**
    * localDate To Date
    * @param localDate
    * @return
    */
    public static Date getLocalDateToDate(LocalDate localDate){
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date;
    }


    /**
    * 获取系统时间默认格式-YYYY-MM-DD
    * @return
    */
    public static Date getSystemDate(){
        LocalDate localDate = getLocalDate();
        return getLocalDateToDate(localDate);
    }

    /**
    * 字符串转日期
    * @param date 日期
    * @param pattern 格式
    * @return
    */
    public static Date getStringTimeToDate(String date,String pattern){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        Instant instant = getZoneInstant(date, df);
        return Date.from(instant);
    }


    public static LocalDateTime getStringToLocalDateTime(String date){
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(FORMAT_PATTERN_DATE_TIME_ZONE_MIS));
    }

    /**
    * 字符串转日期（YYYY-MM-DD）
    * @param date
    * @return
    */
    public static Date getStringToDate(String date){
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(FORMAT_PATTERN_DATE));
        LocalDateTime localDateTime = localDate.atStartOfDay();
        localDateTime.format(DateTimeFormatter.ofPattern(FORMAT_PATTERN_DATE_TIME_ZONE_MIS));
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static Date getLocalDateTimeToDate(LocalDateTime time){
        time.format(DateTimeFormatter.ofPattern(FORMAT_PATTERN_DATE_TIME_ZONE_MIS));
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = time.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
    * 日期转成字符串（YYYY-MM-DD)
    * @param date
    * @return
    */
    public static String getDateToString(Date date){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(FORMAT_PATTERN_DATE);
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate.format(df);
    }

    /**
    * localDateTime 转 字符串
    * @param localDateTime
    * @param dateTimeFotmat
    * @return
    */
    public static String getDateToString(LocalDateTime localDateTime,String dateTimeFotmat){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateTimeFotmat);
        return localDateTime.format(df);
    }

    /**
    * 日期转成LocalDateTime
    * @param date
    * @return
    */
    public static LocalDateTime getDateToLocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    private static Instant getZoneInstant(String date, DateTimeFormatter df) {
        LocalDateTime ldt = LocalDateTime.parse(date,df);
        ZoneId zone = ZoneId.systemDefault();
        return ldt.atZone(zone).toInstant();
    }

    /**
    * 获取当前年
    * @return
    */
    public static Integer getCurrentYeas(){
        LocalDateTime nowTime = LocalDateTime.now();
        return nowTime.getYear();
    }

    /**
    * 获取当前月
    * @return
    */
    public static Integer getCurrentMonth(){
        LocalDateTime nowTime = LocalDateTime.now();
        return nowTime.getMonthValue();
    }

    /**
    * 获取月份格式为双数
    * @return
    */
    public static String getCurrentMonthString(){
        Integer month = getCurrentMonth();
        if(month.toString().length()==1){
            return "0"+month;
        }else{
            return String.valueOf(month);
        }
    }

    /**
    * 获取当月第一天
    * @return
    */
    public static LocalDate getFirstDayOfMonth() {
        LocalDate currentDay = LocalDate.now();
        return currentDay.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
    * 获取当月最一天
    * @return
    */
    public static LocalDate getLastDayOfMonth() {
        LocalDate currentDay = LocalDate.now();
        return currentDay.with(TemporalAdjusters.lastDayOfMonth());
    }
    /**
    * 获取当天开始时间 2019-06-12 00:00:00
    * @return
    * @author
    * @date 2019年6月12日
    */
    public static LocalDateTime getTodayBeginTime(){
        LocalDate currentDay = LocalDate.now();
        return LocalDateTime.of(currentDay, LocalTime.MIN);
    }

    /**
    * 获取当天结束时间 2019-06-12 23:59:59
    * @return
    * @author
    * @date 2019年6月12日
    */
    public static LocalDateTime getTodayEndTime(){
        LocalDate currentDay = LocalDate.now();
        return LocalDateTime.of(currentDay, LocalTime.MAX);
    }

    /**
    * 获取本周开始时间 2019-06-10 00:00:00
    * @return
    * @author
    * @date 2019年6月12日
    */
    public static LocalDateTime getWeekBeginTime(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        int currentOrdinal = currentDateTime.getDayOfWeek().ordinal();
        return currentDateTime.minusDays(currentOrdinal)
        .withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    /**
    * 获取本周开始时间 2019-06-10 00:00:00
    * @return
    * @author
    * @date 2019年6月12日
    */
    public static String getWeekBeginTimeString(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        int currentOrdinal = currentDateTime.getDayOfWeek().ordinal();
        LocalDateTime weekBeginDateTime =  currentDateTime.minusDays(currentOrdinal)
        .withHour(0).withMinute(0).withSecond(0).withNano(0);
        return getDateToString(weekBeginDateTime,FORMAT_PATTERN_DATE_TIME_ZONE_MIS);
    }

    /**
    * 获取本周结束时间 2019-06-16 23:59:59
    * @return
    * @author
    * @date 2019年6月12日
    */
    public static LocalDateTime getWeekEndTime(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        int currentOrdinal = currentDateTime.getDayOfWeek().ordinal();
        return currentDateTime.plusDays(6-currentOrdinal)
        .withHour(23).withMinute(59).withSecond(59).withNano(999999999);
    }

    /**
    * 获取本周结束时间字符串 2019-06-16 23:59:59
    * @return
    * @author
    * @date 2019年6月12日
    */
    public static String getWeekEndTimeString(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        int currentOrdinal = currentDateTime.getDayOfWeek().ordinal();
        LocalDateTime weekEndDateTime = currentDateTime.plusDays(6-currentOrdinal)
        .withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        return getDateToString(weekEndDateTime,FORMAT_PATTERN_DATE_TIME_ZONE_MIS);
    }
    /**
    * 判断字符串日期比较（YYYY-MM-DD)
    * @param source（YYYY-MM-DD)
    * @param target（YYYY-MM-DD)
    * @return
    */
    public static boolean getDateCompare(String source,String target){
        LocalDateTime localDateTime1 = getStringToLocalDateTime(source);
        LocalDateTime localDateTime2 = getStringToLocalDateTime(target);
        if(localDateTime1.isAfter(localDateTime2)){
            return true;
        }else{
            return false;
        }
    }
    /**
    * 判断字符串日期比较（YYYY-MM-DD)
    * @param source（YYYY-MM-DD)
    * @param target（YYYY-MM-DD)
    * @return
    */
    public static boolean getDateCompare(Date source,Date target){
        LocalDateTime localDateTime1 = getDateToLocalDateTime(source);
        LocalDateTime localDateTime2 = getDateToLocalDateTime(target);
        if(localDateTime1.isAfter(localDateTime2)){
            return true;
        }else{
            return false;
        }
    }


    /**
    * 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
    * @param time
    * @param number
    * @param field
    * @return
    */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }
    /**
    * 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
    * @param time
    * @param number
    * @param field
    * @return
    */
    public static LocalDateTime minus(LocalDateTime time, long number, TemporalUnit field){
        return time.minus(number,field);
    }

    /**
    * 获取时间错精确到毫秒
    * @return
    */
    public static long getSystemDateString(){
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }


}
