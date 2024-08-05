package com.fynd.example.java.service;

import com.fynd.example.java.properties.RedisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PreDestroy;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;


@Component
public class RedisService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    JedisPool jedisPool;

    @Autowired
    RedisProperties redisProperties;

    @Bean(name = "redis-pool")
    public JedisPool getJedis() throws URISyntaxException {
        logger.info("Creating JEDIS pool connection on Host: {} ", redisProperties.getHost());
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(redisProperties.getMaxTotal());
        poolConfig.setMaxIdle(redisProperties.getMaxIdle());
        poolConfig.setMinIdle(redisProperties.getMinIdle());
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setJmxEnabled(false);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(redisProperties.getIdleTime())
                .toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(redisProperties.getEviction())
                .toMillis());
        poolConfig.setNumTestsPerEvictionRun(redisProperties.getTests());
        poolConfig.setBlockWhenExhausted(true);
        jedisPool = new JedisPool(poolConfig, redisProperties.getHost());
        return jedisPool;
    }

    @PreDestroy
    public void destroy() {
        logger.info("Closing the jedis pool connection");
        if (jedisPool != null)
            jedisPool.close();
        jedisPool = null;
    }
}


