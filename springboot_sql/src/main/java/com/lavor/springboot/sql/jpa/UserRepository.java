package com.lavor.springboot.sql.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by lei.zeng on 2017/8/2.
 * JpaRepository<User, Integer>表示针对User实体（id为Integer）进行数据库操作
 * 除了继承JpaRepository创建JPA操作数据库的Dao
 * 还可以通过@PersistenceContext注解EntityManager来操作数据库Dao
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //注意此时的查询操作，操作的是实体类而不是数据库表，所以这里是User而不是数据库表user
    @Query("from User u where u.id=:id")
    User selectUserById(@Param("id") Integer id);
}
