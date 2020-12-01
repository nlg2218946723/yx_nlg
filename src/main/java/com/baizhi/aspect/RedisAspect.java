package com.baizhi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

@Configuration
@Aspect
public class RedisAspect {

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Around("execution(* com.baizhi.service.impl.*.select*(..))")
    public Object addCache(ProceedingJoinPoint proceedingJoinPoint) {

        System.out.println("环绕通知");

//        序列化解决乱码
        StringRedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);

        StringBuilder sb = new StringBuilder();
        //        获取类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();

        //        获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        sb.append(methodName);
        //        获取参数
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            sb.append(arg);
        }
        String key = sb.toString();
//        判断该key是否存在
        HashOperations hashOperations = redisTemplate.opsForHash();
        Boolean aBoolean = hashOperations.hasKey(className, key);


        Object result = null;
        if (aBoolean) {
            //        key存在，从redis中取出缓存
            result = hashOperations.get(className, key);
            System.out.println("进入缓存");
        } else {
            System.out.println("进入数据库");
            //        不存在
            //没有缓存数据，放行方法
            try {
                result = proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            //加入缓存

            hashOperations.put(className, key, result);
        }
        return result;
    }

    //    清除缓存
    @After("@annotation(com.baizhi.annotcation.DelCahe)")
    public void delCache(JoinPoint joinPoint) {
        System.out.println("进入后置通知");
//        获取类的全限定名
        String className = joinPoint.getTarget().getClass().getName();
        redisTemplate.delete(className);
    }
}
