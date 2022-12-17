package com.mhawi.slider.utils;

import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DateUtil.java, Used as a utility to handle Date calculations
 **/

@Named("dateUtil")
public class DateUtil {


    private static final List<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat>() {

        private static final long serialVersionUID = 1L;

        {
            add(new SimpleDateFormat("M/dd/yyyy"));
            add(new SimpleDateFormat("dd.M.yyyy"));
            add(new SimpleDateFormat("dd.MMM.yyyy"));
            add(new SimpleDateFormat("dd-MMM-yyyy"));
            add(new SimpleDateFormat("dd-mm-yyyy"));
            add(new SimpleDateFormat("yyyy-mm-dd"));
            add(new SimpleDateFormat("yyyy-MMM-dd"));
            add(new SimpleDateFormat("dd-MM-yy"));
            add(new SimpleDateFormat("dd-MM-yyyy"));
            add(new SimpleDateFormat("MM-dd-yyyy"));
            add(new SimpleDateFormat("yyyy-MM-dd"));
        }
    };

    public static boolean isEqual(LocalDateTime date, LocalDateTime compareDate) {
        return date.equals(compareDate);
    }

    /**
     * Compares if the date specified is equal to compareDate
     *
     * @param date
     * @param compareDate
     * @return
     */
    public static boolean isEqualTrimTime(LocalDateTime date, LocalDateTime compareDate) {
        if (date == null && compareDate == null)
            throw new RuntimeException("Wrong input!!! Both Dates are null. Cannot compare null values");
        if (date == null && compareDate != null) {
            return false;
        }
        if (compareDate == null && date != null) {
            return false;
        }
        LocalDate tempDate = date.toLocalDate();
        LocalDate tempCompareDate = compareDate.toLocalDate();
        return tempDate.isEqual(tempCompareDate);
    }

    /**
     * Compares if the date specified is after or equal to compareDate
     *
     * @param date
     * @param compareDate
     * @return
     */
    public static boolean isAfterOrEqual(LocalDateTime date, LocalDateTime compareDate) {
        return (date.isEqual(compareDate) || date.isAfter(compareDate));
    }

    public static boolean isAfterOrEqual(LocalDate date, LocalDate compareDate) {
        return (date.isEqual(compareDate) || date.isAfter(compareDate));
    }

    /**
     * Compares if the date (from type LocalDateTime) specified is after or equal to compareDate (from type LocalDateTime)
     *
     * @param date
     * @param compareDate
     * @return
     */
    public static boolean isAfter(LocalDateTime date, LocalDateTime compareDate) {
        return (date.isAfter(compareDate));
    }

    /**
     * Compares if the date specified is after or equal to compareDate
     *
     * @param date
     * @param compareDate
     * @return
     */
    public static boolean isAfterWithTrimTime(LocalDateTime date, LocalDateTime compareDate) {

        LocalDate localDate = date.toLocalDate();
        LocalDate localCompareDate = compareDate.toLocalDate();

        return localDate.isAfter(localCompareDate);
    }

    /**
     * Compares if the date specified is before or equal to compareDate
     *
     * @param date
     * @param compareDate
     * @return
     */
    public static boolean isAfterOrEqualWithTrimTime(LocalDateTime date, LocalDateTime compareDate) {
        LocalDate localDate = date.toLocalDate();
        LocalDate localCompareDate = compareDate.toLocalDate();

        return localDate.isAfter(localCompareDate) || localDate.isEqual(localCompareDate);
    }

    /**
     * Compares if the date specified is before or equal to compareDate
     *
     * @param date
     * @param compareDate
     * @return
     */
    public static boolean isBefore(LocalDateTime date, LocalDateTime compareDate) {
        return (date.isBefore(compareDate));
    }

    /**
     * Compares if the date specified is before or equal to compareDate
     *
     * @param date
     * @param compareDate
     * @return
     */
    public static boolean isBeforeWithTrimTime(LocalDateTime date, LocalDateTime compareDate) {
        LocalDate loaclDate = date.toLocalDate();
        LocalDate localCompareDate = compareDate.toLocalDate();
        return loaclDate.isBefore(localCompareDate);
    }

    /**
     * Compares if the date specified is before or equal to current date
     *
     * @param date
     * @return
     */
    public static boolean isBeforeOrEqualCurrentDate(LocalDateTime date) {
        LocalDate localDate = date.toLocalDate();
        LocalDate localCompareDate = LocalDateTime.now().toLocalDate();

        return localDate.isBefore(localCompareDate) || localDate.isEqual(localCompareDate);
    }

    public static boolean isBeforeCurrentDate(LocalDateTime date) {
        LocalDate localDate = date.toLocalDate();
        LocalDate localCompareDate = LocalDateTime.now().toLocalDate();

        return localDate.isBefore(localCompareDate);
    }

    /**
     * @param check
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isBetween(LocalDateTime check, LocalDateTime startTime, LocalDateTime endTime) {
        return ((check.equals(startTime) || check.isAfter(startTime)) &&
                (check.equals(endTime) || check.isBefore(endTime)));
    }

    public static boolean isBetween(LocalDate check, LocalDate startTime, LocalDate endTime) {
        return ((check.equals(startTime) || check.isAfter(startTime)) &&
                (check.equals(endTime) || check.isBefore(endTime)));
    }

    public static boolean isBetween(LocalTime check, LocalTime startTime, LocalTime endTime) {
        return ((check.equals(startTime) || check.isAfter(startTime)) &&
                (check.equals(endTime) || check.isBefore(endTime)));
    }

    public static LocalDateTime addDays(LocalDateTime localDateTime, int days) {
        localDateTime = localDateTime.plusDays(days);

        return localDateTime;
    }

    /**
     * @param date take <b>LocalDate</b> as an argument
     * @param days
     * @return LocalDate
     */
    public static LocalDate addDaysLocalDate(LocalDate date, int days) {
        date.plusDays(days);
        return date;
    }

    /**
     * get Age in years by birth date
     *
     * @param birthDate
     * @return age in years
     */
    public static long getAge(LocalDateTime birthDate) {

        int age = Period.between(birthDate.toLocalDate(), LocalDate.now()).getYears();
        return age;
    }

    /**
     * get Age in years from giving year
     *
     * @param year
     * @return age in years
     */
    public static long getAge(String year) {

        LocalDate start = LocalDate.of(Integer.parseInt(year), 1, 1);

        LocalDate end = LocalDate.now();
        long years = ChronoUnit.YEARS.between(start, end);
        return years;
    }


    public static boolean isIntersected(LocalDateTime startDate1, LocalDateTime endDate1, LocalDateTime startDate2,
                                        LocalDateTime endDate2) {
        return (startDate1.isBefore(endDate2) || startDate1.equals(endDate2))
                && (startDate2.isBefore(endDate1) || startDate2.equals(endDate2));
    }

    /**
     * If the given date is valid against dateFormat (separator does not matter as long as it is defined in list_options 96), Date object is returned. Otherwise null is returned
     *
     * @param date
     * @param dateFormat
     * @return Date Object, or null
     */

    /**
     * convert LocalDateTime to java.util.Date
     *
     * @param localDateTime
     * @return
     */
    public static Date getDateFromLocalDateTime(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return date;

    }

    /**
     * @param localDateTime
     * @return
     */
    public static String getDateTimeStringFromLocalDateTime(LocalDateTime localDateTime, String format) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(format);
        return dateFormat.format(localDateTime);
    }

    /**
     * convert java.util.Date to LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime getLocalDateTimeFromDate(Date date) {
        Instant current = date.toInstant();
        LocalDateTime ldt = LocalDateTime.ofInstant(current, ZoneId.systemDefault());
        return ldt;
    }

    /**
     * check if two periods overlap or not
     *
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     * @return
     */

    public static boolean isOverlaps(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) {

        return (DateUtil.isBeforeOrEqual(start1, end2) && DateUtil.isBeforeOrEqual(start2, end1));

    }

    /**
     * Compares if the date specified is before or equal to compareDate
     *
     * @param date
     * @param compareDate
     * @return
     */
    public static boolean isBeforeOrEqual(LocalDateTime date, LocalDateTime compareDate) {
        return (date.isEqual(compareDate) || date.isBefore(compareDate));
    }

    public static boolean isBeforeOrEqual(LocalDate date, LocalDate compareDate) {
        return (date.isEqual(compareDate) || date.isBefore(compareDate));
    }

    /**
     * Note that this method only works for time when end time is guaranteed to be larger than start time
     * It allows equal. Ex: (9-10) and (10-12) returns false
     */
    public static boolean isOverlaps(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
        return start1.isBefore(end2) && start2.isBefore(end1);

    }

    public static boolean isBeforeOrEqual(LocalTime date, LocalTime compareDate) {
        return (date.equals(compareDate) || date.isBefore(compareDate));
    }

    public static boolean isOverlapsWithTimeTrim(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) {

        return (DateUtil.isBeforeOrEqualWithTrimTime(start1, end2) && DateUtil.isBeforeOrEqualWithTrimTime(start2, end1));

    }

    /**
     * Compares if the date specified is before or equal to compareDate
     *
     * @param date
     * @param compareDate
     * @return
     */
    public static boolean isBeforeOrEqualWithTrimTime(LocalDateTime date, LocalDateTime compareDate) {
        LocalDate localDate = date.toLocalDate();
        LocalDate localCompareDate = compareDate.toLocalDate();

        return localDate.isBefore(localCompareDate) || localDate.isEqual(localCompareDate);
    }

    public static boolean isAfterOrEqual(LocalTime date, LocalTime compareDate) {
        return (date.isAfter(compareDate) || date.equals(compareDate));
    }


    /**
     * @param pattern
     * @param startDate
     * @return
     */
    public static String getLocalDateTimeAsString(String pattern, LocalDateTime startDate) {
        return DateTimeFormatter.ofPattern(pattern).format(startDate);
    }


}
