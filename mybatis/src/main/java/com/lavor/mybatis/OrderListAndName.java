package com.lavor.mybatis;

import java.util.List;

/**
 * Created by lei.zeng on 2017/7/17.
 */
public class OrderListAndName {
    private String name;
    private List<Order> orderList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
