/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main_ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 *
 * @author HMBAO
 */
public class DateLabelFormatter extends AbstractFormatter {

    private String datePattern = "dd-MM-yyyy";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

    public static String dateToTime(java.util.Date d) {
        return new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(d);
    }

    public static int getDay(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonth(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(Calendar.MONTH);
    }

    public static int getYear(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(Calendar.YEAR);
    }

}
