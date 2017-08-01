package com.lavor.mybatis;

import java.util.List;

/**
 * Created by lei.zeng on 2017/7/17.
 */
public class OrderListAndId {
    private Integer id;
    private List<Order> orderList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
