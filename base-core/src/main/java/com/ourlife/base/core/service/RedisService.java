package com.ourlife.base.core.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis操作实现类
 *
 * @author zhangchao
 * @createdOn 2020/4/20
 */
public interface RedisService {

    /*----------string类型的操作---------*/

    /**
     * 添加/修改数据 (设置有效期)
     *
     * @param key
     * @param value
     * @param time   单位是秒
     */
    void set(String key, Object value, long time);

    /**
     * 添加/修改数据
     *
     * @param key
     * @param value
     */
    void set(String key, Object value);

    /**
     * 批量添加/修改数据
     * @param map
     */
    void multiSet(Map<String, Object> map);

    /**
     * 获取属性
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 删除属性
     *
     * @param key
     * @return
     */
    Boolean del(String key);

    /**
     * 批量删除属性
     *
     * @param keys
     * @return
     */
    Long del(List<String> keys);

    /**
     * 设置过期时间
     *
     * @param key
     * @param time
     * @return
     */
    Boolean expire(String key, long time);

    /**
     * 获取过期时间
     *
     * @param key
     * @return
     */
    Long getExpire(String key);

    /**
     * 判断是否有该属性
     *
     * @param key
     * @return
     */
    Boolean hasKey(String key);

    /**
     * 按delta递增
     *
     * @param key
     * @param delta
     * @return
     */
    Long incr(String key, long delta);

    /**
     * 按delta递减
     *
     * @param key
     * @param delta
     * @return
     */
    Long decr(String key, long delta);

    /*----------list类型的操作---------*/

    /**
     * 获取list结构中的属性
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<Object> lRange(String key, long start, long end);

    /**
     * 获取list结构的长度
     *
     * @param key
     * @return
     */
    Long lSize(String key);

    /**
     * 根据索引获取list中的属性
     *
     * @param key
     * @param index
     * @return
     */
    Object lIndex(String key, long index);

    /**
     * 从list结构中头部开始添加属性
     *
     * @param key
     * @param value
     * @return
     */
    Long llpush(String key, Object value);

    /**
     * 从list结构中头部开始批量添加属性
     *
     * @param key
     * @param values
     * @return
     */
    Long llpushAll(String key, Object... values);

    /**
     * 从list结构中尾部开始添加属性
     *
     * @param key
     * @param value
     * @return
     */
    Long lrpush(String key, Object value);

    /**
     * 从list结构中尾部开始批量添加属性
     *
     * @param key
     * @param values
     * @return
     */
    Long lrpushAll(String key, Object... values);

    /**
     * 从list移除属性
     *
     * @param key
     * @param count
     * count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT 。
     * count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值
     * count = 0 : 移除表中所有与 VALUE 相等的值。
     * @param value
     * @return
     */
    Long lRemove(String key, long count, Object value);

    /*----------set类型的操作---------*/

    /**
     * 获取set结构
     *
     * @param key
     * @return
     */
    Set<Object> sMembers(String key);

    /**
     * 向set中添加属性
     *
     * @param key
     * @param values
     * @return
     */
    Long sAdd(String key, Object... values);

    /**
     * 判断是否已存在于set中
     *
     * @param key
     * @param value
     * @return
     */
    Boolean sIsMember(String key, Object value);

    /**
     * 获取set结构的长度
     *
     * @param key
     * @return
     */
    Long sSize(String key);

    /**
     *
     * @param key
     * @param values
     * @return
     */
    Long sRemove(String key, Object... values);


    /*----------zset类型的操作---------*/

    /**
     * 往zset中批量添加元素
     *
     * @param key
     * @param value
     * @param score
     * @return
     */
    Boolean zAdd(String key, Object value, double score);



    /*----------hash类型的操作---------*/

    /**
     * 获取hash结构中的属性
     *
     * @param key
     * @param hashKey
     * @return
     */
    Object hGet(String key, String hashKey);

    /**
     * 向hash结构中放入一个属性
     *
     * @param key
     * @param hashKey
     * @param value
     * @return
     */
    void hSet(String key, String hashKey, Object value);

    /**
     * 获取整个hash结构
     *
     * @param key
     * @return
     */
    Map<Object, Object> hGetAll(String key);

    /**
     * 设置整个hash结构
     *
     * @param key
     * @param map
     */
    void hSetAll(String key, Map<String, Object> map);

    /**
     * 删除hash结构中的属性
     *
     * @param key
     * @param hashKey
     */
    void hDel(String key, Object... hashKey);

    /**
     * 判断hash结构中是否存在该属性
     *
     * @param key
     * @param hashKey
     * @return
     */
    Boolean hHasKey(String key, String hashKey);

    /**
     * hash结构中属性递增
     *
     * @param key
     * @param hashKey
     * @param delta
     * @return
     */
    Long hIncr(String key, String hashKey, Long delta);

    /**
     * hash结构中属性递减
     *
     * @param key
     * @param hashKey
     * @param delta
     * @return
     */
    Long hDecr(String key, String hashKey, Long delta);



}
