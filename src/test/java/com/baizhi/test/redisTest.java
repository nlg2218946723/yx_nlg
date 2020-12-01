package com.baizhi.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class redisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;//用于字符串友好
    @Autowired
    private RedisTemplate redisTemplate;

    //    @Resource
//    StringRedisTemplate stringRedisTemplate;
//    @Resource
//    RedisTemplate redisTemplate;
    @Test
    public void test() {
        String s = stringRedisTemplate.opsForValue().get("name");
        System.out.println(s);

    }
}
