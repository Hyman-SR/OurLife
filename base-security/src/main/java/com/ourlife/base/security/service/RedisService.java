package com.ourlife.base.security.service;

/**
 * redis操作实现类
 *
 * @author zhangchao
 * @createdOn 2020/4/20
 */
public interface RedisService {

    /*----------string类型的操作---------*/


    /**
     * 保存
     *
     * @param key
     * @param value
     * @param time   单位是秒
     */
    void set(String key, Object value, long time);

    /**
     * 保存
     *
     * @param key
     * @param value
     */
    void set(String key, Object value);



    /*----------list类型的操作---------*/

    /*----------set类型的操作---------*/

    /*----------zset类型的操作---------*/

    /*----------hash类型的操作---------*/

}
