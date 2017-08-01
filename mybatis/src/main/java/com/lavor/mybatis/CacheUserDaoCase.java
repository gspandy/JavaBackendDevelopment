package com.lavor.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

/**对应CacheUserDao的操作范例
 * Created by lei.zeng on 2017/7/17.
 */
public class CacheUserDaoCase {
    //获取slf4j的Logger，使用slf4j的Logger而不使用log4j的Logger，
    // 这样可以随时更换jar包，使用其他的日志系统
    //注意：slf4j不是日志系统，而是日志系统的门面（一种封装）
    private static Logger logger= LoggerFactory.getLogger(CacheUserDaoCase.class);
    public static void main(String[] args) throws IOException {

        //读取mybatis的xml配置文件
        Reader reader= Resources.getResourceAsReader("mybatis_config.xml");
        //创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
        //创建SqlSession对象，操作数据库的
        SqlSession session=sessionFactory.openSession();
        SqlSession session2=sessionFactory.openSession();

        //输出两个SqlSession可以发现它们是不同的
        System.out.println(session.toString()+session2.toString());
        //获取可以操作数据库的Dao
        CacheUserDao cacheUserDao=session.getMapper(CacheUserDao.class);
        CacheUserDao cacheUserDao2=session2.getMapper(CacheUserDao.class);

        logger.debug("连着查询两次相同的sql(namespace,id,sql都相同)：来检测mybatis的一级缓存");
        //连续进行两次一样的查询操作，日志只输出了一次数据库查询操作
        //这是因为mybatis的一级数据缓存自动开启了，缓存以SqlSession为单位进行划分的
        cacheUserDao.selectUserById(1);
        cacheUserDao.selectUserById(1);
        logger.debug("进行一次更新操作，然后执行与上相同的sql查询：来检测更新后mybatis的一级缓存被清空了");
        //在进行一次同样的查询操作，日志又输出了一次数据库查询操作
        //这是因为进行增删改操作后，SqlSession中的缓存会被清空
        cacheUserDao.updateUserById(1);
        cacheUserDao.selectUserById(1);


        logger.debug("连着查询两次相同的sql，但是在不同的sqlSession中执行：来检测mybatis的二级缓存是多个sqlSession共享的");
        //连续进行两次一样的查询操作，日志只输出了一次数据库查询操作
        //这是因为该查询开启了mybatis的二级数据缓存，该缓存以mapper的namespace为单位进行划分的
        cacheUserDao.selectUserByIdCache(2);
//        session.close();
        //通过openSession()创建的SqlSession需要进行commit操作，只有这样一级缓存才会被写入到二级缓存中
        //通过openSession(true)创建的SqlSession需要进行close操作，只有这样一级缓存才会被写入到二级缓存中
        session.commit();
        cacheUserDao2.selectUserByIdCache(2);

        logger.debug("验证数据先从一级缓存取，还是先从二级缓存取：结果表明先从一级缓存开始");
        cacheUserDao2.updateUserByIdFlush(1);//清空二级缓存
        session2.commit();
        User user=cacheUserDao2.selectUserByIdCache(2);//进行查询，得到一级缓存数据
        cacheUserDao.updateUserById(2);//修改数据
        User user1=cacheUserDao.selectUserByIdCache(2);//另一个SqlSession查询得到更新后的数据
        session.commit();//将新查询到的数据保存到二级缓存
//        session2.commit();//后提交的操作，会覆盖之前二级缓存中的数据
        User user2=cacheUserDao2.selectUserByIdCache(2);//先从一级缓存中取数据，一级缓存中没有再从二级缓存中取

        //关闭SqlSession，一个SqlSession就会占用一个数据库连接，一定要在适当的时候关闭
        session.close();
        session2.close();

    }
}
