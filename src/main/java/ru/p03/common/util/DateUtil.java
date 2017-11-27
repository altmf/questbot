/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.p03.common.util;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.util.Calendar.*;

/**
 *
 * @author timofeevan
 */
public class DateUtil {

    public static final String XML_DATE_FORMAT = "dd.MM.yyyy";

    private DateUtil() {
    }

    /**
     * Creates a date.
     *
     * @param day
     * @param month starts with 1
     * @param year
     * @return {@link Calendar}
     */
    public static Date date(Integer day, Integer month, Integer year) {
        return calendar(day, month, year).getTime();
    }

    public static Date date(Integer day, Integer month, Integer year, Integer hour) {
        return calendar(day, month, year, hour).getTime();
    }

    /**
     * Creates a calendar.
     *
     * @param day
     * @param month starts with 1
     * @param year
     * @return {@link Calendar}
     */
    public static Calendar calendar(Integer day, Integer month, Integer year) {
        Calendar date = Calendar.getInstance();
        date.set(DAY_OF_MONTH, day);
        date.set(MONTH, month - 1);
        date.set(YEAR, year);
        return date;
    }

    public static Calendar calendar(Integer day, Integer month, Integer year, Integer hour) {
        Calendar date = Calendar.getInstance();
        date.set(DAY_OF_MONTH, day);
        date.set(MONTH, month - 1);
        date.set(YEAR, year);
        date.set(HOUR, hour);
        date.set(MINUTE, 0);
        date.set(SECOND, 0);
        return date;
    }

    public static Date dateBeg(Date date) {
        DayMonthYear dmy = DateUtil.dmy(date);
        Calendar calendar = Calendar.getInstance();
        calendar.set(DAY_OF_MONTH, dmy.getDay());
        calendar.set(MONTH, dmy.month - 1);
        calendar.set(YEAR, dmy.getYear());
        calendar.set(HOUR, -11);
        calendar.set(MINUTE, 0);
        calendar.set(SECOND, 0);
        return calendar.getTime();
    }

    public static Date dateEnd(Date date) {
        DayMonthYear dmy = DateUtil.dmy(date);
        Calendar calendar = Calendar.getInstance();
        calendar.set(DAY_OF_MONTH, dmy.getDay());
        calendar.set(MONTH, dmy.month - 1);
        calendar.set(YEAR, dmy.getYear());
        calendar.set(HOUR, 11);
        calendar.set(MINUTE, 59);
        calendar.set(SECOND, 59);
        return calendar.getTime();
    }

    /**
     *
     * @param date
     * @return {@link Integer}
     */
    public static Integer getFirstMonthOfQuarter(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return (cal.get(Calendar.MONTH) / 3) * 3 + 1;
    }

    /**
     *
     * @param date
     * @return {@link Integer}
     */
    public static Integer getQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) / 3 + 1;
    }

    public static DayMonthYear dmy(Date date) {
        return new DayMonthYear(date);
    }

    /**
     * Transform string in format 'dd.MM.yyyy' to {@link Date}
     *
     * @param date - String with date
     * @return {@link Date}
     */
    public static Date transformDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(XML_DATE_FORMAT);
        return sdf.parse(date, new ParsePosition(0));
    }
    
    public static Date transformDate(String date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.parse(date, new ParsePosition(0));
    }

    public static boolean isValid(String dateToValidate) {
        return isValid(dateToValidate, XML_DATE_FORMAT);
    }

    public static boolean isValid(String dateString, String dateFormat) {
        if (dateString == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Transform {@link Date} in {@link String} with format 'dd.MM.yyyy'
     *
     * @param date - {@link Date}
     * @return {@link String}
     */
    public static String transformDate(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(date);
    }

    /**
     * Transform {@link Calendar} in {@link String} with format 'dd.MM.yyyy'
     *
     * @param calendar - {@link Calendar}
     * @return {@link String}
     */
    public static String transformCalendar(Calendar calendar) {
        if (calendar == null) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(calendar.getTime());
    }

    public static Date removeTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return date(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
    }

    public static Date incDate(Date dt, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    public static Calendar getCalendarWithCurrentYearEmptyTime(int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), month, date, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static Calendar getCalendarWithEmptyTime(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }
    //
    //
    //

    /**
     * Month starts with 1.
     */
    public static class DayMonthYear {

        Integer day;
        Integer month;
        Integer year;

        public DayMonthYear(Date date) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            this.day = calendar.get(DAY_OF_MONTH);
            this.month = calendar.get(MONTH) + 1;
            this.year = calendar.get(YEAR);
        }

        public Integer getDay() {
            return day;
        }

        public Integer getMonth() {
            return month;
        }

        public Integer getYear() {
            return year;
        }
    }

    public static Calendar getNormalizeCalendar(int day, int month, int year) {
        Calendar date = Calendar.getInstance();
        /**
         * [ptarasevich] DON'T REMOVE that. If day of month more than it can be
         * in month which we try to set than month will increase by one E.g. if
         * now 29.01.2015 and we try to set month FEBRUARY that adduct to roll
         * of month(because february has only 28 days). And the result will be
         * 01.03.2015
         */
        date.set(DAY_OF_MONTH, 1);

        date.set(YEAR, year);

        if (month > date.getActualMaximum(MONTH)) {
            date.set(MONTH, date.getActualMaximum(MONTH));
        } else {
            date.set(MONTH, month - 1);
        }

        if (day > date.getActualMaximum(DAY_OF_MONTH)) {
            date.set(DAY_OF_MONTH, date.getActualMaximum(DAY_OF_MONTH));
        } else {
            date.set(DAY_OF_MONTH, day);
        }

        // TODO [schurukanov]: compare dates using Calendar (PersonValidationImpl)
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        return date;
    }

    public static Date truncateTime(Date date) {
        // TODO kolosnicyn for some unknown reason DB2 driver ignores TemporalType.DATE and fails if Date parameter has
        // non-zero time (tests pass such dates)
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static String toXMLFormat(Date date) throws Exception {
        if (date == null) {
            return null;
        }
        return toXMLGregorianCalendar(date).toXMLFormat();
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) throws Exception {
        if (date == null) {
            return null;
        }
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
    }

    public static XMLGregorianCalendar yearToXMLGregorianCalendar(int year) throws Exception {
        //GregorianCalendar calendar= new GregorianCalendar();
        //calendar.setTime(date);
        XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar();
        calendar.setYear(year);
        return calendar;
    }

    public static Date toDate(XMLGregorianCalendar xmlGregorianCalendar) throws Exception {
        if (xmlGregorianCalendar == null) {
            return null;
        }
        return xmlGregorianCalendar.toGregorianCalendar().getTime();
    }
}
