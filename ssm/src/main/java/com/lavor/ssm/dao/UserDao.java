package com.lavor.ssm.dao;

import com.lavor.ssm.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据库操作的dao
 * @Repository注解dao层的bean
 * Created by lei.zeng on 2017/7/18.
 */
@Repository("userDao")
public interface UserDao {
    List<User> findAllUser();
}
