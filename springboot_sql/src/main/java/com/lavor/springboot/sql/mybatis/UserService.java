package com.lavor.springboot.sql.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lei.zeng on 2017/8/2.
 */
@Service("mybatisUserService")
public class UserService {
    @Autowired
    private UserDaoMapper userDaoMapper;
    public User selectUserById(Integer id) {
        return userDaoMapper.selectUserById(id);
    }
}
