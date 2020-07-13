package com.ourlife.base.api;

import com.ourlife.base.core.lock.DistributeLock;
import com.ourlife.base.core.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class BaseApiApplicationTests {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RedisService redisService;

    @Autowired
    private RestTemplate restTemplate;

    @Resource(name = "redisDistributeLock")
    private DistributeLock distributeLock;

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext();
    }

    @Test
    public void test1() {

    }

    @Test
    public void test2() {
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String lockKey = "redis_lock_test_";
                    boolean result = distributeLock.tryGetDistributeLock(lockKey);
                    if (result) {
                        System.out.println("thread " + Thread.currentThread().getName() + "获取锁成功");
                    } else {
                        System.err.println("thread " + Thread.currentThread().getName() + "获取锁失败");
                    }
                    boolean b = distributeLock.releaseDistributedLock(lockKey);
                    if (b) {
                        System.out.println("thread " + Thread.currentThread().getName() + "删除锁成功");
                    } else {
                        System.err.println("thread " + Thread.currentThread().getName() + "删除锁失败");
                    }
                }
            }).start();
        }
    }

}
