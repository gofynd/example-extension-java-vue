package com.fynd.example.java.properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

public class RedisPropertiesTest {

    private ApplicationContextRunner contextRunner;

    @BeforeEach
    void setup() {
        contextRunner = new ApplicationContextRunner()
                .withUserConfiguration(TestConfig.class)
                .withPropertyValues(
                        "redis.host=redis://127.0.0.1:6379",
                        "redis.maxTotal=1100",
                        "redis.maxIdle=16",
                        "redis.minIdle=16",
                        "redis.idleTime=60",
                        "redis.eviction=30",
                        "redis.tests=3"
                );
    }

    @Test
    void testRedisPropertiesBinding() {
        contextRunner.run(context -> {
            RedisProperties redisProperties = context.getBean(RedisProperties.class);
            assertThat(redisProperties.getHost()).isEqualTo("redis://127.0.0.1:6379");
            assertThat(redisProperties.getMaxTotal()).isEqualTo(1100);
            assertThat(redisProperties.getMaxIdle()).isEqualTo(16);
            assertThat(redisProperties.getMinIdle()).isEqualTo(16);
            assertThat(redisProperties.getIdleTime()).isEqualTo(60);
            assertThat(redisProperties.getEviction()).isEqualTo(30);
            assertThat(redisProperties.getTests()).isEqualTo(3);
        });
    }

    @Test
    void testDefaultValues() {
        RedisProperties redisProperties = new RedisProperties();
        redisProperties.setHost("redis://127.0.0.1:6379");
        redisProperties.setMaxTotal(1100);
        redisProperties.setMaxIdle(16);
        redisProperties.setMinIdle(16);
        redisProperties.setIdleTime(60);
        redisProperties.setEviction(30);
        redisProperties.setTests(3);

        assertThat(redisProperties.getHost()).isEqualTo("redis://127.0.0.1:6379");
        assertThat(redisProperties.getMaxTotal()).isEqualTo(1100);
        assertThat(redisProperties.getMaxIdle()).isEqualTo(16);
        assertThat(redisProperties.getMinIdle()).isEqualTo(16);
        assertThat(redisProperties.getIdleTime()).isEqualTo(60);
        assertThat(redisProperties.getEviction()).isEqualTo(30);
        assertThat(redisProperties.getTests()).isEqualTo(3);
    }

    @Configuration
    @EnableConfigurationProperties(RedisProperties.class)
    static class TestConfig {
    }
}