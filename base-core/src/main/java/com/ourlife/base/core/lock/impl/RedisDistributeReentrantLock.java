package com.ourlife.base.core.lock.impl;

import com.ourlife.base.core.lock.DistributeLock;
import org.springframework.stereotype.Service;

/**
 * redis实现分布式可重入锁
 *
 * @author zhangchao
 * @createdOn 2020/5/12
 */
@Service("redisDistributeReentrantLock")
public class RedisDistributeReentrantLock implements DistributeLock {

    @Override
    public boolean tryGetDistributeLock(String lockKey) {
        return false;
    }

    @Override
    public boolean releaseDistributedLock(String lockKey) {
        return false;
    }
}
