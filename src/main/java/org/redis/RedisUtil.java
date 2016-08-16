package org.redis;


import redis.clients.jedis.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by break on 2016/8/12.
 */
public class RedisUtil {

    private Jedis jedis;//非切片额客户端连接
    private JedisPool jedisPool;//非切片连接池
    private ShardedJedis shardedJedis;//切片额客户端连接
    private ShardedJedisPool shardedJedisPool;//切片连接池

//    private final String host = "192.168.0.114";
    private final String host = "172.31.102.118";
//    private final int port = 6379;
    private final int port = 6379;


    public RedisUtil(){

        initialPool();
        jedis = jedisPool.getResource();
    }
    /**
     * 初始化非切片池
     */
    private void initialPool(){
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(20);
        config.setMaxTotal(40);
        config.setMinIdle(10);

        jedisPool = new JedisPool(config,host,port);
    }

    public static void main(String[] args) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.jedis.set("hello","e");
        System.out.println(redisUtil.jedis.get("hello"));

        RedisBillLockHandler redisBillLockHandler = new RedisBillLockHandler(redisUtil.jedis);
        while (true) {
            System.out.println(redisBillLockHandler.tryLock(0));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
