package com.lavor.mybatis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shitian on 2017-07-15.
 */
public interface  UserDao {
    //查询一条结果
    User selectUserById(Integer id);
    //查询多条结果保存到List中，注意对应mapper中的resultType是User而不是List<User>或者List
    List<User> selectAllUser();

    //一对一查询
    UserInfoAndName selectUserInfoAndNameById(Integer id);
    //一对多查询
    OrderListAndName selectOrderListAndNameById(Integer id);

    //延迟加载查询
    List<OrderListAndId> findUser();
    List<Order> findOrderLazy(Integer id);

    //动态语句查询
    User selectUserByName(User user);

    void insertUser(User user);
}
