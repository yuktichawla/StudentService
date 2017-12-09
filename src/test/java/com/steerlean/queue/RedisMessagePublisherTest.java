package com.steerlean.queue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class RedisMessagePublisherTest {

    @Mock
    private RedisTemplate<String, Object> mockRedisTemplate;

    @Mock
    private ChannelTopic mockTopic;

    @InjectMocks
    private RedisMessagePublisher redisMessagePublisher;

    @Test
    public void testPublish() throws Exception {
        Mockito.when(mockTopic.getTopic()).thenReturn("mock mockTopic");
        redisMessagePublisher.publish("some message");
        Mockito.verify(mockRedisTemplate).convertAndSend("mock mockTopic", "some message");
    }

}