package com.ourlife.base.core.lock.impl;

import com.ourlife.base.core.lock.DistributeLock;
import org.springframework.stereotype.Service;

/**
 * zookeeper实现分布式可重入锁
 *
 * @author zhangchao
 * @createdOn 2020/5/12
 */
@Service("zookeeperDistributeReentrantLock")
public class ZookeeperDistributeReentrantLock implements DistributeLock {
    @Override
    public boolean tryGetDistributeLock(String lockKey) {
        return false;
    }

    @Override
    public boolean releaseDistributedLock(String lockKey) {
        return false;
    }
}
