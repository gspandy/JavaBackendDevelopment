package com.lavor.springboot.sql.jdbc;

/**
 * Created by lei.zeng on 2017/8/2.
 */
public interface UserDao {
    User selectUserById(Integer id);
}
