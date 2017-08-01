package com.lavor.mybatis;

/**
 * mybatis自带二级缓存，以mapper的namespace划分,多个SqlSession共享
 * 二级缓存需要查询结果映射的pojo对象实现java.io.Serializable接口实现序列化和反序列化操作，
 * 注意如果存在父类、成员pojo都需要实现序列化接口
 * Created by lei.zeng on 2017/7/17.
 */
public interface CacheUserDao {
    //普通查询
    User selectUserById(Integer id);
    //启用mybatis二级缓存的查询
    User selectUserByIdCache(Integer id);
    //刷新mybatis二级缓存的
    void updateUserByIdFlush(Integer id);
    //不刷新mybatis二级缓存
    void updateUserById(Integer id);
}
