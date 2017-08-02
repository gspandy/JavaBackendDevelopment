package com.lavor.springboot.sql.mybatis;

import org.springframework.stereotype.Repository;

/**
 * Created by lei.zeng on 2017/8/2.
 * 除了使用xml的Mapper的namespace对应起来的方法创建类Mapper外
 * 还可以直接使用@Mapper注解创建类Mapper
 */
public interface UserDaoMapper {
    User selectUserById(Integer id);
}
