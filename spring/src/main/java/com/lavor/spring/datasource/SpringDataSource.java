package com.lavor.spring.datasource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Spring配置数据源的方式：Spring自带的数据源，不支持连接池
 * Created by lei.zeng on 2017/7/4.
 */
public class SpringDataSource {
    public static void main(String[] args) throws SQLException {
        //根据classpath下面的spring的xml配置文件来获取应用程序上下文
        ApplicationContext context = new ClassPathXmlApplicationContext("datasource/spring-datasource.xml");
        //根据应用程序上下文，来获取数据源的bean
        DataSource dataSource= (DataSource) context.getBean("dataSource");
        //根据数据源来获取数据库连接
        Connection connection=dataSource.getConnection();
        //数据库查询语句
        String sql="select * from user";
        Statement statement=connection.createStatement();
        //将查询结果保存到resultSet中
        ResultSet resultSet=statement.executeQuery(sql);
        while (resultSet.next()){
            String name=resultSet.getString("name");
            System.out.println(name);
        }
        //关闭数据库连接
        connection.close();
    }
}
