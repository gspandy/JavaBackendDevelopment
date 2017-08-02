package com.lavor.mybatis;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * 使用注解操作数据库的mybatis实例
 * Created by lei.zeng on 2017/7/28.
 */
public class AnnotationUserDaoCase {
    public static void main(String[] args) throws IOException {
        //读取mybatis的xml配置文件
        Reader reader = Resources.getResourceAsReader("mybatis_config.xml");
        //创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建SqlSession对象，操作数据库的
        SqlSession session = sessionFactory.openSession();
        //获取SqlSessionFactory对象的配置信息
        Configuration configuration=sessionFactory.getConfiguration();
        //将AnnotationUserDao.class作为Mapper添加到configuration中
        configuration.addMapper(AnnotationUserDao.class);

        //获取可以操作数据库的Dao
        AnnotationUserDao annotationUserDao = session.getMapper(AnnotationUserDao.class);
        annotationUserDao.selectAllUser();
    }
}
