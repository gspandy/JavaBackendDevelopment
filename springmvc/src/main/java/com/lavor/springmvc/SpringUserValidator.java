package com.lavor.springmvc;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 在SpringMVC中有两种方式进行输入验证，一是利用Spring自带的验证框架，而是利用JSR 303（及其升级版本）实现。
 * SpringUserValidator是利用Spring自带的验证框架实现的用户输入验证
 * Created by shitian on 2017-07-15.
 */
public class SpringUserValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    public void validate(Object o, Errors errors) {
        User user= (User) o;
        String name=user.getName();
        //检查user的name是否为null或者为空
        if (name==null||name.isEmpty()){
            //在name字段上给出name.empty错误码对应的错误消息，如果没有就使用默认的错误消息
            errors.rejectValue("name","name.empty",null,"defaultMessage");
        }
        //以上的验证代码，可以利用ValidationUtils简化成以下形式
        //ValidationUtils.rejectIfEmpty(errors,"name","name.empty",null,"defaultMessage");
    }
}