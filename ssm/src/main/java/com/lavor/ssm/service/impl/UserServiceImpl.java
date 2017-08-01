package com.lavor.ssm.service.impl;

import com.lavor.ssm.dao.UserDao;
import com.lavor.ssm.entity.User;
import com.lavor.ssm.service.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 业务逻辑操作的service层
 * @Service注解service层的bean
 * @Transactional注解在类上，表示为类中所有public方法添加上事务
 * 方法上的@Transactional注解会覆盖类上的@Transactional注解
 * Created by lei.zeng on 2017/7/18.
 */
@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService{
    @Resource
    private UserDao userDao;
    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }
}
