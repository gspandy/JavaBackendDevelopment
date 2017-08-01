package com.lavor.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

/**
 * @Transactional为事务的注解，既可以添加在类上，也可以添加在方法上
 * 方法上的@Transactional注解会覆盖类上@Transactional注解的相同属性
 * Created by lei.zeng on 2017/7/5.
 */
@Transactional
@Component("userDaoAnnotation")
public class UserDaoAnnotation implements UserDao {
    @Autowired
    private DataSource dataSource;
    /**
     *  Spring声明式事务默认情况下都是在抛出unchecked exception
     *  并且该异常是RuntimeException或它的子类后才会触发事务的回滚
     */
    @Override
    public void insert(){
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("insert into user values (3,'zhangfei')");
        //两次相同的操作，将违反主键约束，事务操作时最终结果是数据没有插进去
        //会触发一个运行时异常，满足事务回滚的条件，insert方法会自动抛出该异常
        template.update("insert into user values (3,'zhangfei')");
    }
}
