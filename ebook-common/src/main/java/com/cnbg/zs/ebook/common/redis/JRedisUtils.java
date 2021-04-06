package com.cnbg.zs.ebook.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
* @author Open-Free-Team Faye.Wang
* @version 1.0
* @date 2020/9/4 21:53
* @Description redis 工具类
*/
public class JRedisUtils {
    private static Logger logger = LoggerFactory.getLogger(JRedisUtils.class);
    /**
    * redis address, like "{ip}"、"{ip}:{port}"、"{redis/rediss}://xxl-sso:{password}@{ip}:{port:6379}/{db}"；Multiple "," separated
    */
    private static String address;

    public static void init(String address) {
        JRedisUtils.address = address;
        getInstance();
    }

    private static ShardedJedisPool shardedJedisPool;
    private static ReentrantLock INSTANCE_INIT_LOCL = new ReentrantLock(false);

    private static ShardedJedis getInstance() {
        if (shardedJedisPool == null) {
            try {
                if (INSTANCE_INIT_LOCL.tryLock(2, TimeUnit.SECONDS)) {
                    if (shardedJedisPool == null) {
                        JedisPoolConfig config = new JedisPoolConfig();
                        config.setMaxTotal(200);
                        config.setMaxIdle(50);
                        config.setMinIdle(8);
                        config.setMaxWaitMillis(10000);         // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
                        config.setTestOnBorrow(true);           // 在获取连接的时候检查有效性, 默认false
                        config.setTestOnReturn(false);          // 调用returnObject方法时，是否进行有效检查
                        config.setTestWhileIdle(true);          // Idle时进行连接扫描
                        config.setTimeBetweenEvictionRunsMillis(30000);     // 表示idle object evitor两次扫描之间要sleep的毫秒数
                        config.setNumTestsPerEvictionRun(10);               // 表示idle object evitor每次扫描的最多的对象数
                        config.setMinEvictableIdleTimeMillis(60000);        // 表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
                        List<JedisShardInfo> jedisShardInfos = new LinkedList<JedisShardInfo>();

                        List<String> addressList = Arrays.asList(address.split(","));

                        addressList.forEach(item -> {
                            JedisShardInfo jedisShardInfo = new JedisShardInfo(String.valueOf(item));
                            jedisShardInfos.add(jedisShardInfo);
                        });
                        shardedJedisPool = new ShardedJedisPool(config, jedisShardInfos);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error("lock excption-------");
            } finally {
                INSTANCE_INIT_LOCL.unlock();
            }
    }
    if (shardedJedisPool == null) {
        throw new NullPointerException("shardedJedisPool init fail ");
    }
        return shardedJedisPool.getResource();
    }
    public static void close() throws IOException {
        if(shardedJedisPool != null) {
        shardedJedisPool.close();
        }
    }

    /**
    * set value to redis
    * @param key
    * @param value
    * @param seconds
    */
    public static void setKeyValue(String key, String value, int seconds){
        ShardedJedis shardedJedis = getInstance();
        try {
            shardedJedis.setex(key, seconds, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (shardedJedis != null) {
            shardedJedis.close();
            }
        }
    }


    public static void setKeyValue(String key, String value){
        ShardedJedis shardedJedis = getInstance();
        try {
            shardedJedis.set(key,value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (shardedJedis != null) {
            shardedJedis.close();
            }
        }
    }



    /**
    * get value for redis
    * @param key
    * @return
    */
    public static String  getKeyValue(String key){
        String result = null;
        ShardedJedis shardedJedis = getInstance();
        try {
            result = shardedJedis.get(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
        return result;
    }
    public static Long del(String key) {
        Long result = null;
        ShardedJedis client = getInstance();
        try {
            result = client.del(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (client != null) {
            client.close();
            }
        }
        return result;
    }
}
