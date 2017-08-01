package com.lavor.mybatis;

/**
 * mybatis自带的二级缓存不是很方面管理，一般可以采用ehcache进行二级缓存管理
 * 实现mybatis的Cache接口就可以实现二级缓存了，ehcache中的类实现了该接口
 * 使用ehcache需要配置ehcache.xml文件，并且在mapper中开启缓存，开启方法如下：
 * <cache type="org.mybatis.caches.ehcache.EhcacheCache"/>
 * 其他操作与mybatis自带的二级缓存相同
 * Created by lei.zeng on 2017/7/17.
 */
public interface EhcacheUserDao {
    User selectUserById(Integer id);
}
