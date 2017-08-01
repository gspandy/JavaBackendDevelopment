package com.lavor.spring.ioc;

/**
 * Created by shitian on 2017-06-26.
 */

import org.springframework.stereotype.Component;

/**
 * @Component注解会扫描类，将其装配成bean，调用无参的构造方法
 * bean的id默认与类同名，但是首字母小写
 */
@Component
public class UserScan{
    private String name;

    public UserScan() {
        name="lavor";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}