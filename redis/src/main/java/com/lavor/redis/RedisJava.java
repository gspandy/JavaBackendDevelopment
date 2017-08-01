package com.lavor.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Transaction;

import java.util.HashMap;
import java.util.Map;

/**
 * 采用Jedis框架来操作redis
 * string,list,hash,set,sorted set,HyperLogLog六种数据结构的操作
 * pub/sub（发布/订阅），事务，lua脚本，连接，服务器这5种常见操作
 * 还有关于key，geo,cluster三种操作没有使用
 * 其实key的部分操作已经穿插在11种操作中了，geo是地理信息操作，cluster是集群操作
 * Created by lei.zeng on 2017/7/21.
 */
public class RedisJava {
    public static void main(String[] args){
        Jedis jedis = new Jedis("localhost",6379);
        RedisJava redisJava=new RedisJava();
        redisJava.operateHyperLogLog(jedis);
    }

    /**
     * 利用Jedis操作redis中string相关的键值对
     */
    public void operateString(Jedis jedis){
        //向redis中添加键值对，值为string类型，如果已存在对应键的值，那么就是修改键对应的值
        jedis.set("name","lavor");
        //设置name对应的键值对多久后过期
        jedis.expire("name",1000);
        //设置name对应的键值对到指定时间过期
        jedis.expireAt("name",122000000);
        //获取redis中的对应键的字符串
        jedis.get("name");
        //向redis中对应键的值中追加字符串
        jedis.append("name","追加内容");
        //删除redis中的键值对
        jedis.del("name");
    }
    /**
     * 利用Jedis操作redis中list相关的键值对
     */
    public void operateList(Jedis jedis){
        //向redis中添加键值对，值为list类型，lpush是从头开始加数据，rpush是从尾开始加数据
        jedis.lpush("name","lavor");
        jedis.lpush("name","jack");
        jedis.lpush("name","john");
        //还可以简写为：jedis.lpush("name","lavor","jack","john");

        //从list头部删除一个数据，同时返回被删除的数据，rpop是从list尾部删除一个数据
        jedis.lpop("name");

        // 取出redis中的list数据
        // 第一个是key，第二个是起始位置，第三个是结束位置，-1表示取得所有
        jedis.lrange("name",0,-1);
        //删除redis中的键值对
        jedis.del("name");
    }

    /**
     * 利用Jedis操作redis中hash相关的键值对
     */
    public void operateHash(Jedis jedis){
        Map<String,String> map=new HashMap<String, String>();
        map.put("hash1","hash1Value");
        map.put("hash2","hash2Value");
        //向redis中添加键值对，值为hash类型
        jedis.hmset("hashName",map);
        //获取对应键的hash中指定域中的值
        jedis.hget("hashName","hash1");
        //获取对应键的hash中所有域
        jedis.hgetAll("hashName");
        //删除redis中的键值对
        jedis.del("hashName");
    }

    /**
     * 利用Jedis操作redis中set(string的无序排列)相关的键值对
     */
    public void operateSet(Jedis jedis){
        //向redis中添加键值对，值为set类型
        jedis.sadd("name","lavor","jack");
        //获取对应键的set
        jedis.smembers("name");
        //从set中随机删除一个值
        jedis.spop("name");
        //删除redis中的键值对
        jedis.del("name");
    }
    /**
     * 利用Jedis操作redis中sorted set(string的有序排列)相关的键值对
     */
    public void operateSortedSet(Jedis jedis){
        //向redis中添加键值对，值为sorted set类型，就是靠第二个参数维持sorted set的有序性
        jedis.zadd("name",1,"lavor");
        jedis.zadd("name",2,"jack");
        //获取sorted set中起始位置到结束位置的数据，结束位置为-1表示取出所有数据
        jedis.zrange("name",0,-1);
        //向sorted set插入一个数据
        jedis.zincrby("name",1.5,"jobs");
        //删除redis中的键值对
        jedis.del("name");
    }

    /**
     * 利用Jedis操作redis中HyperLogLog相关的键值对
     * HyperLogLog是一种使用随机化的算法，以少量内存提供集合中唯一元素数量的近似值（就是去重后元素个数的近似值）。
     */
    public void operateHyperLogLog(Jedis jedis){
        //向redis中添加键值对，值为HyperLogLog类型，
        jedis.pfadd("name","lavor");
        //查看指定键中值是唯一元素的个数的近似值
        jedis.pfcount("name");
        jedis.pfadd("name2","lavor","jack","jobs");
        //合并两个HyperLogLog，合并成功后可以用两个键中的任意一个键来操作
        jedis.pfmerge("name","name2");
        //删除redis中的键值对
        jedis.del("name");
    }

    /**
     * redis的pub/sub操作
     * @param jedis
     */
    public void operatePubSub(Jedis jedis){
        //JedisPubSub是用来处理redis的pub/sub系统的
        JedisPubSub jedisPubSub=new JedisPubSub() {
        };
        //发布一条消息到指定频道
        jedis.publish("channel","message");
        //获取符合指定模式的频道
        jedis.pubsubChannels("channel");
        //订阅一个或多个符合给定模式的频道
        jedis.psubscribe(jedisPubSub,"channel");
        //订阅一个或多个频道
        jedis.subscribe(jedisPubSub,"channel","channel2");
        //退订一个或多个频道
        jedisPubSub.unsubscribe("channel");
        //根据指定模式退订频道
        jedisPubSub.punsubscribe("patterns");
    }
    /**
     * redis的事务操作
     * @param jedis
     */
    public void operateTransaction(Jedis jedis){
        //启动事务执行命令，获取事务，标志事务块的开始
        Transaction transaction=jedis.multi();
        //丢弃在multi之后发出的所有命令
        transaction.discard();
        transaction.set("transaction","transaction");
        //执行multi后发出的所有命令，也就是提交事务
        transaction.exec();
        //监视给定的键以确定multi/exec块的执行
        transaction.watch("transaction");
        //取消watch命令对所有 key 的监视
        jedis.unwatch();
    }
    /**
     * redis的脚本操作
     * @param jedis
     */
    public void operateScripting(Jedis jedis){
        //在服务器端执行lua脚本
        jedis.eval("script");
        //在服务器端执行lua脚本
        jedis.evalsha("script");
        //检查lua脚本在脚本缓存中是否存在
        jedis.scriptExists("script");
        //杀死当前正在运行的脚本
        jedis.scriptKill();
        //将指定的Lua脚本加载到脚本缓存中
        jedis.scriptLoad("script");
        //在服务器端删除所有lua脚本
        jedis.scriptFlush();

    }
    /**
     * redis的连接
     * @param jedis
     */
    public void operateConnection(Jedis jedis){
        //使用给定的密码验证服务器
        jedis.auth("password");
        //打印指定字符串信息
        jedis.echo("message");
        //检查服务器是否正在运行
        jedis.ping();
        //更改当前连接所使用的数据库
        jedis.select(1);
        //关闭当前连接
        jedis.quit();
    }
    /**
     * redis的服务器
     * @param jedis
     */
    public void operateServer(Jedis jedis){
        //异步重写仅追加的文件
        jedis.bgrewriteaof();
        //将数据异步到存到磁盘
        jedis.bgsave();
        //杀死或断开指定的客户端的连接
        jedis.clientKill("192.168.1.101");
        //获取到服务器的客户端连接列表
        jedis.clientList();
        //获取当前连接的名称
        jedis.clientGetname();
        //设置当前连接的名称
        jedis.clientSetname("client");
        //指定当前服务器的主服务器
        jedis.slaveof("192.168.1.1",6379);
        //返回服务器时间
        jedis.time();
        //关闭服务
        jedis.shutdown();
    }
}
