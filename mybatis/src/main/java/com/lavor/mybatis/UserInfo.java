package com.lavor.mybatis;

/**
 * Created by lei.zeng on 2017/7/17.
 */
public class UserInfo {
    private Integer id;
    private String address;
    private String user_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
