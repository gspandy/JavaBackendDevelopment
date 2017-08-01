package com.lavor.springmvc;

/**
 * Created by lei.zeng on 2017/7/6.
 */
public class Car {
    private User user;
    private Integer number;
    public Car() {
    }
    public Car(User user, Integer number) {
        this.user = user;
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
