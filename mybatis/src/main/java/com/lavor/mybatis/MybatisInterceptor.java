package com.lavor.mybatis;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 实现一个Mybatis拦截器
 * Mybatis拦截器只能拦截四类对象，分别为：Executor、ParameterHandler、StatementHandler、ResultSetHandler
 * 而SQL数据库的操作都是从Executor开始的，我们这里拦截sql的一些查询更新语句，来判断它们的耗时
 * @Intercepts可以拦截多个@Signature，一个@Signature代表着一个方法的签名
 * Created by shitian on 2017-07-19.
 */
@Intercepts(value = {
        @Signature (type=Executor.class,
                method="update",
                args={MappedStatement.class,Object.class}),
        @Signature(type=Executor.class,
                method="query",
                args={MappedStatement.class,Object.class,RowBounds.class,ResultHandler.class,
                        CacheKey.class,BoundSql.class}),
        @Signature(type=Executor.class,
                method="query",
                args={MappedStatement.class,Object.class,RowBounds.class,ResultHandler.class})
})
public class MybatisInterceptor implements Interceptor{
    private static Logger logger = LoggerFactory.getLogger(MybatisInterceptor.class);

    /**
     * 定义具体的拦截操作
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        Object result = null;
        if (target instanceof Executor) {
            long start = System.currentTimeMillis();
            Method method = invocation.getMethod();
            /**执行被拦截的方法*/
            try {
                result = invocation.proceed();
            }catch (Exception e){

            }
            long end = System.currentTimeMillis();
            logger.info(method.getName()+"方法此次查询/更新耗时：" + (end - start) + "ms");
        }
        return result;
    }

    /**
     * Plugin.wrap生成拦截代理对象
     */
    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
