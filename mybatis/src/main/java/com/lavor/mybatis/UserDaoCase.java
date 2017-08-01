package com.lavor.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**对应UserDao的操作范例
 * Created by lei.zeng on 2017/7/17.
 */
public class UserDaoCase {
    public static void main(String[] args) throws IOException {
        //读取mybatis的xml配置文件
        Reader reader= Resources.getResourceAsReader("mybatis_config.xml");
        //创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
        //创建SqlSession对象，操作数据库的
        SqlSession session=sessionFactory.openSession();
        //获取可以操作数据库的Dao
        UserDao userDao=session.getMapper(UserDao.class);

        //可以用SqlSession或者Dao两种方式操作数据库
        session.selectOne("com.lavor.mybatis.UserDao.selectUserById",1);
        userDao.selectUserById(1);

        //查询返回List数据
        userDao.selectAllUser();
        //一对一查询
        UserInfoAndName userInfoAndName=userDao.selectUserInfoAndNameById(1);
        //一对多查询
        OrderListAndName orderListAndName=userDao.selectOrderListAndNameById(1);

        //延迟加载，先查询user表的id，然后在需要根据id查询order的时候才加载
        List<OrderListAndId> list = userDao.findUser();
        //这里不该一下子把所有数据都查出来，否则就失去了延迟加载的意义，
        for (OrderListAndId orderListAndId:list) {
            System.out.println(orderListAndId.getOrderList().toString());
        }

        User user=new User();
        user.setName("hans");
        //动态语句查询
        userDao.selectUserByName(user);

        //插入一条数据不带id的数据，返回自增的id
        userDao.insertUser(user);
        System.out.println( user.getId());

        //关闭SqlSession，一个SqlSession就会占用一个数据库连接，一定要在适当的时候关闭
        session.close();

    }
}
