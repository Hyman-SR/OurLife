package com.ourlife.base.api;

import com.ourlife.base.core.lock.DistributeLock;
import com.ourlife.base.core.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class BaseApiApplicationTests {

    @Autowired
    private RedisService redisService;

    @Resource(name = "redisDistributeLock")
    private DistributeLock distributeLock;

    @Test
    public void test1() {
//        redisService.set("stringKey1", "stringValue1");
//        redisService.set("stringKey2", "stringValue2", 100);
        String value2 = (String) redisService.get("stringKey1");
        System.out.println("set 完成" + value2);
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
