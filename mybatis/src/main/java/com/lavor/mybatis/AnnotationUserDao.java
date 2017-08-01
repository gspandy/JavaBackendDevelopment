package com.lavor.mybatis;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by lei.zeng on 2017/7/28.
 */
public interface AnnotationUserDao {
    @Select("select * from user")
    public List<User> selectAllUser();
    @Select("select * from user where id=#{id}")
    @ResultMap("userMapXML")
    public User selectUserById(Integer id);
}
