package com.lavor.springboot.sql.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lei.zeng on 2017/8/2.
 */
@Service("jdbcUserService")
public class UserService {
    @Autowired
    private UserDao userDao;
    public User selectUserById(Integer id) {
        return userDao.selectUserById(id);
    }
}
