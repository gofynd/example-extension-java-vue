package com.fynd.example.java.service;

import com.fynd.example.java.properties.RedisProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import redis.clients.jedis.JedisPool;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.net.URISyntaxException;

@ExtendWith(MockitoExtension.class)
class RedisServiceTest {

    @Mock
    private RedisProperties redisProperties;

    @Mock
    private JedisPool jedisPool;

    @InjectMocks
    private RedisService redisService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        redisService.jedisPool = jedisPool;
    }

    @Test
    void testGetJedis() throws URISyntaxException {
        when(redisProperties.getHost()).thenReturn("redis://127.0.0.1:6379");
        when(redisProperties.getMaxTotal()).thenReturn(1100);
        when(redisProperties.getMaxIdle()).thenReturn(16);
        when(redisProperties.getMinIdle()).thenReturn(16);
        when(redisProperties.getIdleTime()).thenReturn(60);
        when(redisProperties.getEviction()).thenReturn(30);
        when(redisProperties.getTests()).thenReturn(3);
        JedisPool jedisPool = redisService.getJedis();
        assertNotNull(jedisPool, "JedisPool should not be null");
        verify(redisProperties, times(1)).getMaxTotal();
        verify(redisProperties, times(1)).getMaxIdle();
        verify(redisProperties, times(1)).getMinIdle();
        verify(redisProperties, times(1)).getIdleTime();
        verify(redisProperties, times(1)).getEviction();
        verify(redisProperties, times(1)).getTests();
    }

    @Test
    void testDestroy() {
        redisService.destroy();
        verify(jedisPool, times(1)).close();
        assertNull(redisService.jedisPool, "JedisPool should be null after destroy method");
    }
}