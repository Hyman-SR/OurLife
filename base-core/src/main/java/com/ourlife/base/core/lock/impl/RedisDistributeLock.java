package com.ourlife.base.core.lock.impl;

import com.ourlife.base.core.lock.DistributeLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁相关操作(严格业务中不建议使用)
 * 该实现方式还是存在部分问题：
 * 1.业务阻塞，导致锁过期自动清除，其他线程会在不合理的情况下获取到锁
 * 2.不可重入
 *
 * @author zhangchao
 * @createdOn 2020/5/11
 */
@Slf4j
@Service("redisDistributeLock")
public class RedisDistributeLock implements DistributeLock {

    private static final Long SUCCESS = 1L;

    /**
     * 锁过期时间应尽可能长，保证业务可以在有效期内执行完成，避免锁失效导致其他线程在不合理的情况下执行(该情况无法避免)
     */
    private static final long INTERNAL_LOCK_LEASE_TIME = 30000;
    /**
     * 重试时间不能设置太久，否则会导致lettuce拒绝处理任务请求
     * 不能设置太短，并发条件下会导致成功获取锁的概率降低
     * 测试重试次数超过800，会大概率失败
     */
    private static final long TIME_OUT = 800;

    private static final RedisScript<Long> RELEASE_LOCK_SCRIPT  = new DefaultRedisScript<>("if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end", Long.class);

    private static final ThreadLocal currentThreadLocal = new ThreadLocal();

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean tryGetDistributeLock(String lockKey) {
        //给当前线程设置一个唯一标识，防止其他线程误删
        String requestId = UUID.randomUUID().toString();

        Long start = System.currentTimeMillis();
        for (; ; ) {
            Boolean result;
            try {
                result = redisTemplate.opsForValue().setIfAbsent(lockKey, requestId, INTERNAL_LOCK_LEASE_TIME, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                return false;
            }
            if (result) {
                //如果获取锁成功，则将requestId设置到threadLocal中
                currentThreadLocal.set(requestId);
                log.debug("thread id:[{}] get lock:[{}] success", requestId, lockKey);
                return Boolean.TRUE;
            }
            //否则循环等待，在timeout时间内仍未获取锁，则获取失败
            long l = System.currentTimeMillis() - start;
            if (l >= TIME_OUT) {
                log.debug("thread id:[{}] get lock:[{}] failed", requestId, lockKey);
                return false;
            }
            try {
                //休眠100ms后再重试
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean releaseDistributedLock(String lockKey) {
        String requestId = (String) currentThreadLocal.get();
        Object execute = redisTemplate.execute(RELEASE_LOCK_SCRIPT, Collections.singletonList(lockKey), requestId);
        if (SUCCESS.equals(execute)) {
            log.debug("thread id:[{}] release lock:[{}] success", requestId, lockKey);
            return Boolean.TRUE;
        }
        log.debug("thread id:[{}] release lock:[{}] failed", requestId, lockKey);
        return Boolean.FALSE;
    }
}
