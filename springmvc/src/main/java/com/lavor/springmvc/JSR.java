package com.lavor.springmvc;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 用来测试JSR系列验证器规范所使用的实体类
 * Created by shitian on 2017-07-15.
 */
public class JSR {
    //硬编码错误信息，等级最高
    @NotEmpty(message = "用户名不能为空")
    private String name;
    //使用默认的消息键（错误代码）
    @NotEmpty
    private String email;
    //自定义错误代码
    @NotEmpty(message = "{name.empty}")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
