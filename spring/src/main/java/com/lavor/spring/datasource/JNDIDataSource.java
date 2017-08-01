package com.lavor.spring.datasource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jndi.JndiTemplate;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Spring配置数据源的方式：JNDI数据源，支持连接池
 * 如果应用配置在高性能的应用服务器（如WebLogic或Websphere,tomcat等）上，
 * 我们可能更希望使用应用服务器本身提供的数据源。应用服务器的数据源使用JNDI开放给调用者使用。
 找到Tomcat安装目录下`conf`文件夹里的`context.xml`文件,然后打开`context.xml`，
 在标签`<context></<context>`之间加入如下内容：
 <Resource
 name="jdbc/test"
 auth="Container"
 type="javax.sql.DataSource"
 driverClassName="com.mysql.jdbc.Driver"
 url="jdbc:mysql://localhost:3306/test"
 username="root"
 password="123456"
 maxActive="50"
 maxIdle="30"
 maxWait="10000">
 </Resource>
 * Created by lei.zeng on 2017/7/4.
 */
public class JNDIDataSource {
    /**
     * 获取JNDI数据源的三种方法
     */
    public static DataSource getDatasource(int flag) throws NamingException {
        if (flag==1){
            //第一种：利用Spring的xml配置文件来获取
            //根据classpath下面的spring的xml配置文件来获取应用程序上下文
            ApplicationContext context = new ClassPathXmlApplicationContext("spring/datasource/jndi-datasource.xml");
            //根据应用程序上下文，来获取数据源的bean
            DataSource dataSource= (DataSource) context.getBean("dataSource");
            return dataSource;
        }else if (flag==2){
            //第二种：利用InitialContext获取数据源
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/test");
            return dataSource;
        }else if (flag==3){
            //第三种：利用JndiTemplate获取数据源
            JndiTemplate jndiTemplate = new JndiTemplate();
            DataSource dataSource =(DataSource) jndiTemplate.lookup("java:comp/env/jdbc/test");
            return dataSource;
        }else {
            return null;
        }
    }

    /**
     * 因为获取的是JNDI数据源依赖于容器（比如Tomcat），所以只能在Web环境中使用
     * 这里的方法只是告诉我们怎么使用它，但不能真正运行
     */
    public static void main(String[] args) throws SQLException, NamingException {
        DataSource dataSource=getDatasource(1);
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
    }
}
