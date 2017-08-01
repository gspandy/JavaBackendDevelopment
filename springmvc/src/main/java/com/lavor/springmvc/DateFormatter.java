package com.lavor.springmvc;

import org.springframework.format.Formatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Formatter（格式化）是将String类型转换成别的类型，专门为Web层设计的。
 * 实现Formatter接口皆可以实现格式化了
 * DateFormatter是将String转换成Date的Formatter
 * Created by shitian on 2017-07-15.
 */
public class DateFormatter implements Formatter<Date> {
    private String datePattern;
    private DateFormat dateFormat;

    public DateFormatter(String datePattern) {
        this.datePattern = datePattern;
        dateFormat=new SimpleDateFormat(datePattern);
    }

    public Date parse(String s, Locale locale) throws ParseException {
        try {
            return dateFormat.parse(s);
        } catch (ParseException e) {
            throw new IllegalArgumentException("该字符串不能转换成日期类型");
        }
    }

    public String print(Date date, Locale locale) {
        return dateFormat.format(date);
    }
}
