/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.p03.common.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author timofeevan
 */
public class FormatUtils {

    private static final int NUM_LEADING_ZERO = 6;

    public static String formatLeadingZero(int num, int digits) {
        char[] zeros = new char[digits];
        Arrays.fill(zeros, '0');
        DecimalFormat df = new DecimalFormat(String.valueOf(zeros));

        return df.format(num);
    }
    
    public static String formatLeadingZero(String num, int digits) {
        char[] zeros = new char[digits];
        Arrays.fill(zeros, '0');
        DecimalFormat df = new DecimalFormat(String.valueOf(zeros));

        return df.format(Integer.valueOf(num));
    }

    public static String getNextRegNumber(Date date, Integer incrementalNumber) {
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        int year = c.get(Calendar.YEAR);
        return formatLeadingZero(incrementalNumber, NUM_LEADING_ZERO);
    }

    public static String formatAsDDMMYYY(Date date) {
        String str = new SimpleDateFormat("dd.MM.yyyy").format(date);
        return str;
    }
    
    public static String formatAsDDMMYYYHHmm(Date date) {
        String str = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(date);
        return str;
    }
    
    public static String formatAsHHmm (Integer hh,Integer mm){
        return formatLeadingZero(hh, 2) + ":" + formatLeadingZero(mm, 2);
    }
    
    public static String formatAsHH00 (Integer hh){
        return formatAsHHmm(hh, 0);
    }
    
    public static String formatAsHHmm (String hh, String mm){
        return formatLeadingZero(hh, 2) + ":" + formatLeadingZero(mm, 2);
    }
    
    public static String formatAsHH00 (String hh){
        return formatAsHHmm(hh, "0");
    }
}
