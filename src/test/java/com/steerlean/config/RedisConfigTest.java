package com.steerlean.config;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

public class RedisConfigTest {

    @Test
    public void testRedisTemplate() {
        RedisConfig spyConfig = Mockito.spy(RedisConfig.class);
        JedisConnectionFactory mockJedisFactory = Mockito.mock(JedisConnectionFactory.class);
        Mockito.when(spyConfig.jedisConnectionFactory()).thenReturn(mockJedisFactory);

        RedisTemplate<String, Object> redisTemplate = spyConfig.redisTemplate();

        Assert.assertEquals(mockJedisFactory, redisTemplate.getConnectionFactory());
    }

    @Test
    public void testRedisContainer() {
        RedisConfig spyConfig = Mockito.spy(RedisConfig.class);
        JedisConnectionFactory mockJedisFactory = Mockito.mock(JedisConnectionFactory.class);
        Mockito.when(spyConfig.jedisConnectionFactory()).thenReturn(mockJedisFactory);

        RedisMessageListenerContainer redisContainer = spyConfig.redisContainer();

        Assert.assertEquals(mockJedisFactory, redisContainer.getConnectionFactory());
    }

    @Test
    public void testTopic() {
        RedisConfig config = new RedisConfig();
        Assert.assertEquals("pubsub:queue", config.topic().getTopic());
    }

}