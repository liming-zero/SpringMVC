package com.bjpowernode.redis.util;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

    private static JedisPool pool;

    //创建JedisPool对象的方法
    public static JedisPool open(String ip, int port, String password){
        if (pool == null){
            //创建JedisPool
            //创建JedisPoolConfig， 给config设置连接池的参数， 使用config对象创建JedisPool
            JedisPoolConfig config = new JedisPoolConfig();

            //设置连接池最大的线程数量，一个线程就是一个jedis
            config.setMaxTotal(20);

            //设置最大空闲数
            config.setMaxIdle(2);

            //设置检查项为true，表示从线程池中获取的对象一定是经过检查可用的
            config.setTestOnBorrow(true);


            /**
             * 创建Pool对象
             *      poolConfig: 配置器JedisPoolConfig
             *      host: redis所在的linux的ip
             *      port: redis的端口
             *      timeout: 连接redis的超时，毫秒值
             *      password: redis的访问密码
             */
            pool = new JedisPool(config,ip,port,6000,password);
        }
        return pool;
    }

    //关闭Pool对象
    public static void close(){
        if (pool != null){
            pool.close();
        }
    }
}
