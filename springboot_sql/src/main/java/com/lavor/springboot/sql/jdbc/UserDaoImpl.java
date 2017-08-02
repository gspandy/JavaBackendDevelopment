package com.lavor.springboot.sql.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by lei.zeng on 2017/8/2.
 */
@Repository("jdbcUserDaoImpl")
public class UserDaoImpl implements UserDao{
    //SpringBoot的JdbcTemplate是自动配置的，你可以直接使用@Autowired来注入到你自己的bean中来使用。
    //并且自动将application.properties中的数据源配置到JdbcTemplate中
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public User selectUserById(Integer id) {
        return jdbcTemplate.queryForObject("select * from user where id=?",new Object[]{id},new BeanPropertyRowMapper<User>(User.class));
    }
}
