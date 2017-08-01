package com.lavor.springmvc;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Converter都可以将一种对象类型转换成另一种对象类型。Converter是通用组件，它可以作用在应用程序的任意层。
 * 实现Converter接口就可以自定义转换器（Converter）了
 * StringToDateConverter是一个将String转换成Date的转换器
 * Created by lei.zeng on 2017/7/6.
 */
public class StringToDateConverter implements Converter<String,Date> {
    private String datePattern;

    public StringToDateConverter(String datePattern) {
        this.datePattern = datePattern;
    }

    public Date convert(String string) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(datePattern);
        try {
            return simpleDateFormat.parse(string);
        } catch (ParseException e) {
            throw new IllegalArgumentException("该字符串不能转换成日期类型");
        }
    }
}
