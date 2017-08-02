package com.lavor.springboot.cache;


import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

/**
 * SpringBoot默认是使用ConcurrentMapCacheManager管理缓存的
 * 我们可以使用其他的缓存管理器来管理缓存，一旦引入其他缓存的依赖，默认的缓存管理就失效了
 * 导入ehcache的依赖，然后创建ehcache.xml，SpringBoot就会自动配置EhCacheCacheManager来管理缓存
 * 导入ehcache的依赖，然后在application.properties中配置redis信息就会使用RedisCacheManager来管理缓存
 *
 * 在Spring Boot中通过@EnableCaching注解自动化配置合适的缓存管理器（CacheManager），
 * Spring Boot根据下面的顺序去侦测缓存提供者（前提是没有用@Bean显示注解得到CacheManager）：
 Generic
 JCache (JSR-107)
 EhCache 2.x
 Hazelcast
 Infinispan
 Redis
 Guava
 Simple
 * Created by lei.zeng on 2017/8/2.
 */
@Configuration
@EnableCaching
@Service
public class MyCache {
    /**
     * 配置CacheManager的Bean来管理缓存，这里配置的是GuavaCacheManager
     * 这样配置的缓存的级别最高，会优先使用该缓存
     * @return
     */
    @Bean
    public CacheManager cacheManager() {
        GuavaCacheManager cacheManager = new GuavaCacheManager();
        cacheManager.setCacheBuilder(
                CacheBuilder.newBuilder().
                        expireAfterWrite(10, TimeUnit.SECONDS).
                        maximumSize(1000));
        return cacheManager;
    }

    /**
     *  @Cacheable 在方法执行前 Spring 先查看缓存中是否有数据，
     *  如果有数据，则直接返回缓存数据；若没有数据，调用方法并将方法返回值放进缓存。
     *  value表示缓存的名字，使用EhCache做缓存时，ehcache.xml中必须有名叫timeCache的缓存
     *  实际应用时，@Cacheable中最好加上key（存入缓存中键值对的键），或者添加自动生成key的方法
     * @return
     */
    @Cacheable(value = "timeCache")
    public long select(){
        System.out.println("查询操作");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long time=new Timestamp(System.currentTimeMillis()).getTime();
//        System.out.println(time);
         return time;
    }

    /**
     * @CachePut 与 @Cacheable 类似，
     * 但是它无论什么情况，都会将方法的返回值放到缓存中, 主要用于数据新增和修改方法。
     * @return
     */
    @CachePut(value = "timeCache")
    public long update() {
        System.out.println("更新操作");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long time=new Timestamp(System.currentTimeMillis()).getTime();
//        System.out.println(time);
        return time;
    }

    /**
     * @CacheEvict 将一条或多条数据从缓存中删除, 主要用于删除方法，用来从缓存中移除相应数据。
     */
    @CacheEvict(value = "timeCache")
    public void delete() {
        System.out.println("删除操作");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
