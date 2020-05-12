package com.ourlife.base.core.lock;

/**
 * 分布式锁相关操作
 *
 * @author zhangchao
 * @createdOn 2020/5/11
 */
public interface DistributeLock {

    /**
     * 获取分布式锁
     *
     * @param lockKey 锁
     * @return
     */
    boolean tryGetDistributeLock(String lockKey);

    /**
     * 释放分布式锁
     *
     * @param lockKey
     * @return
     */
    boolean releaseDistributedLock(String lockKey);
}
