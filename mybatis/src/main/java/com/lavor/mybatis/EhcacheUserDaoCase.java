package com.lavor.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * 对应EhcacheUserDao的操作范例
 * Created by lei.zeng on 2017/7/17.
 */
public class EhcacheUserDaoCase {
    public static void main(String[] args) throws IOException {
        //读取mybatis的xml配置文件
        Reader reader= Resources.getResourceAsReader("mybatis_config.xml");
        //创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
        //创建SqlSession对象，操作数据库的
        SqlSession session=sessionFactory.openSession();
        SqlSession session2=sessionFactory.openSession();
        //获取可以操作数据库的Dao
        EhcacheUserDao ehcacheUserDao=session.getMapper(EhcacheUserDao.class);
        EhcacheUserDao ehcacheUserDao2=session2.getMapper(EhcacheUserDao.class);

        ehcacheUserDao.selectUserById(1);
        session.commit();
        ehcacheUserDao2.selectUserById(1);

        session.close();
        session2.close();
    }
}
