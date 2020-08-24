package com.utils;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisUtils {

    private static Jedis jedis;
    private static final String PREFIX = "ll_idea";
    private static final Logger logger = LoggerFactory.getLogger(JedisUtils.class);

    // Redis服务器IP

    private static String ADDR_ARRAY = "127.0.0.1";// FileUtil.getPropertyValue("/properties/redis.properties",
    //private static String ADDR_ARRAY = "127.0.0.1,192.168.241.132";// FileUtil.getPropertyValue("/properties/redis.properties",
    // "server");

    // Redis的端口号
    private static int PORT = 6379;// FileUtil.getPropertyValueInt("/properties/redis.properties",
    // "port");

    // 访问密码
    // private static String AUTH =
    // FileUtil.getPropertyValue("/properties/redis.properties", "auth");

    // 可用连接实例的最大数目，默认值为8；
    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 1000;// FileUtil.getPropertyValueInt("/properties/redis.properties",
    // "max_active");;

    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 8;// FileUtil.getPropertyValueInt("/properties/redis.properties",
    // "max_idle");;

    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = -1;// FileUtil.getPropertyValueInt("/properties/redis.properties",
    // "max_wait");;

    // 超时时间 毫秒
    private static int TIMEOUT = 100000;// FileUtil.getPropertyValueInt("/properties/redis.properties",
    // "timeout");;

    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;// FileUtil.getPropertyValueBoolean("/properties/redis.properties",
    // "test_on_borrow");;

    private static JedisPool jedisPool = null;

    /**
     * 初始化Redis连接池
     */
    private static void initialPool() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR_ARRAY.split(",")[0], PORT, TIMEOUT);
        } catch (Exception e) {
            logger.error("First create JedisPool error : " + e);
            try {
                // 如果第一个IP异常，则访问第二个IP
                JedisPoolConfig config = new JedisPoolConfig();
                config.setMaxTotal(MAX_ACTIVE);
                config.setMaxIdle(MAX_IDLE);
                config.setMaxWaitMillis(MAX_WAIT);
                config.setTestOnBorrow(TEST_ON_BORROW);
                jedisPool = new JedisPool(config, ADDR_ARRAY.split(",")[1], PORT, TIMEOUT);
            } catch (Exception e2) {
                logger.error("Second create JedisPool error : " + e2);
            }
        }
    }

    /**
     * 在多线程环境同步初始化
     */
    private static synchronized void poolInit() {
        if (jedisPool == null) {
            initialPool();
        }
    }

    /**
     * 同步获取Jedis实例
     *
     * @return Jedis
     */
    public synchronized static Jedis getJedis() {
        if (jedisPool == null) {
            poolInit();
        }
        Jedis jedis = null;
        try {
            if (jedisPool != null) {
                jedis = jedisPool.getResource();
            }
        } catch (Exception e) {
            logger.error("Get jedis error : " + e);
        } finally {
            returnResource(jedis);
        }
        return jedis;
    }

    /**
     * 释放jedis资源 jedispool returnresource 废弃 用 colose代码 3.0
     *
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null && jedisPool != null) {
            jedis.close();
        }
    }

    public static Jedis getJedis(String host_ip, int host_port) {
        jedis = new Jedis(host_ip, host_port);
        // jedis.auth("admin.123"); //开启密码验证（配置文件中为 requirepass root）的时候需要执行该方法
        return jedis;
    }

    public static Jedis getDefaultJedis() {
        // return getJedis(HOST_IP, HOST_PORT);//简装版

        return getJedis();
    }

    /**
     * 清空 redis 中的所有数据
     */
    public static String flushRedis() {
        logger.debug("flush redis data");
        return getDefaultJedis().flushDB();
    }

    /**
     * 根据 pattern 获取 redis 中的键
     */
    public static Set<String> getKeysByPattern(String pattern) {
        return getDefaultJedis().keys(pattern);
    }

    /**
     * 获取 redis 中所有的键
     */
    public static Set<String> getAllKeys() {
        return getKeysByPattern("*");
    }

    /**
     * 判断key是否存在redis中
     */
    public static boolean exists(String key) throws Exception {

        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }

        return getDefaultJedis().exists(PREFIX + key);
    }

    /**
     * 从Redis中移除一个key
     */
    public static void del(String key) throws Exception {
        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }
        getDefaultJedis().del(PREFIX + key);
    }

    // ======================String 类型接口======================================

    /**
     * 存储字符串
     */
    public static void setString(String key, String value, int expireTime) throws Exception {
        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }

        String finalKey = PREFIX + key;
        getDefaultJedis().set(finalKey, value);
        if (expireTime > 0) {
            /**
             * 如果设置了 expireTime， 那么这个 finalKey会在expireTime秒后过期,那么该键会被自动删除
             * 这一功能配合出色的性能让Redis可以作为缓存系统来使用，成为了缓存系统Memcached的有力竞争者
             */
            getDefaultJedis().expire(finalKey, expireTime);
        }
    }

    /**
     * 获取字符串
     */
    public static String getString(String key) throws Exception {
        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }
        return getDefaultJedis().get(PREFIX + key);
    }

    public static long setnx(String key, String value) throws Exception {

        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }

        return getDefaultJedis().setnx(PREFIX + key, value);
    }

    public static long expire(String key, int seconds) throws Exception {

        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }

        return getDefaultJedis().expire(PREFIX + key, seconds);
    }

    // ========================List类型接口==========================
    /**
     * 存储 List
     */
    public static void pushList(String key, String value, String flag) throws Exception {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(flag)) {
            logger.error("key or flag is null");
            throw new Exception("key or flag is null");
        }

        /**
         * key代表的是链表的名字 List是一个双端链表，lpush是往链表的头部插入一条数据，rpush是往尾部插入一条数据
         */
        if (flag.equalsIgnoreCase("L")) {
            getDefaultJedis().lpush(PREFIX + key, value);
        } else if (flag.equalsIgnoreCase("R")) {
            getDefaultJedis().rpush(PREFIX + key, value);
        } else {
            logger.error("unknown flag");
            throw new Exception("unknown flag");
        }
    }

    /**
     * 获取 List 中的单个元素
     */
    public static String popList(String key, String flag) throws Exception {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(flag)) {
            logger.error("key or flag is null");
            throw new Exception("key or flag is null");
        }

        if (flag.equalsIgnoreCase("L")) {
            return getDefaultJedis().lpop(PREFIX + key);
        } else if (flag.equalsIgnoreCase("R")) {
            return getDefaultJedis().rpop(PREFIX + key);
        } else {
            logger.error("unknown flag");
            throw new Exception("unknown flag");
        }
    }

    /**
     * 获取 List 中指定区间上的元素
     */
    public static List<String> getAppointedList(String key, long start, long end) throws Exception {
        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }
        return getDefaultJedis().lrange(PREFIX + key, start, end);
    }

    /**
     * 获取 List 上所有的元素
     */
    public static List<String> getList(String key) throws Exception {
        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }
        return getDefaultJedis().lrange(PREFIX + key, 0, -1);
    }

    /**
     * 获取 List 的长度
     */
    public static long getListLength(String key) throws Exception {
        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }
        return getDefaultJedis().llen(PREFIX + key);
    }

    // =====================Set类型接口==================
    /**
     * 存储 Set : 单值存储
     */
    public static void addValueToSet(String key, String value) throws Exception {
        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }
        getDefaultJedis().sadd(PREFIX + key, value);
    }

    /**
     * 存储 Set : 多值存储
     */
    public static void addListToSet(String key, List<String> values) throws Exception {
        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }
        for (String value : values) {
            getDefaultJedis().sadd(PREFIX + key, value);
        }
    }

    /**
     * 删除 Set 中的某个元素
     */
    public static void deleteElementInSet(String key, String value) throws Exception {
        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }
        getDefaultJedis().srem(PREFIX + key, value);
    }

    /**
     * 获取 Set 中所有的成员
     */
    public static Set<String> getSet(String key) throws Exception {
        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }
        return getDefaultJedis().smembers(PREFIX + key);
    }

    /**
     * 判断 value 是否属于 set
     */
    public static boolean isExistInSet(String key, String value) throws Exception {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            logger.error("key or value is null");
            throw new Exception("key or value is null");
        }
        return getDefaultJedis().sismember(PREFIX + key, value);
    }

    /**
     * 获取 Set 中元素个数
     */
    public static long getLengthOfSet(String key) throws Exception {
        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }
        return getDefaultJedis().scard(PREFIX + key);
    }

    /**
     * 取两个 Set 的交集
     */
    public static Set<String> getSetInter(String key1, String key2) throws Exception {
        if (StringUtils.isEmpty(key1) || StringUtils.isEmpty(key2)) {
            logger.error("key1 or key2 is null");
            throw new Exception("key1 or key2 is null");
        }
        return getDefaultJedis().sinter(PREFIX + key1, PREFIX + key2);
    }

    /**
     * 取两个 Set 的并集
     */
    public static Set<String> getSetUnion(String key1, String key2) throws Exception {
        if (StringUtils.isEmpty(key1) || StringUtils.isEmpty(key2)) {
            logger.error("key1 or key2 is null");
            throw new Exception("key1 or key2 is null");
        }
        return getDefaultJedis().sunion(PREFIX + key1, PREFIX + key2);
    }

    /**
     * 取两个 Set 的差集
     */
    public static Set<String> getSetDiff(String key1, String key2) throws Exception {
        if (StringUtils.isEmpty(key1) || StringUtils.isEmpty(key2)) {
            logger.error("key1 or key2 is null");
            throw new Exception("key1 or key2 is null");
        }
        return getDefaultJedis().sdiff(PREFIX + key1, PREFIX + key2);
    }

    // ==================================SortedSet类型接口
    /**
     * 存储有序集合 SortedSet
     */
    public static void setSortedSet(String key, double weight, String value) throws Exception {
        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }
        getDefaultJedis().zadd(PREFIX + key, weight, value);
    }

    /**
     * 获取有序集合指定区间上的元素
     */
    public static Set<String> getAppointedSortedSet(String key, long start, long end) throws Exception {
        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }
        return getDefaultJedis().zrange(PREFIX + key, start, end);
    }

    /**
     * 获取有序集合上的所有元素
     */
    public static Set<String> getSortedSet(String key) throws Exception {
        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }
        return getDefaultJedis().zrange(PREFIX + key, 0, -1);
    }

    /**
     * 获取有序集合上某个权重区间上的元素
     */
    public static long getLengthOfSortedSetByWeight(String key, double min, double max) throws Exception {
        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }
        return getDefaultJedis().zcount(PREFIX + key, min, max);
    }

    /**
     * 删除有序集合上的元素
     */
    public static void deleteElementInSortedSet(String key, String value) throws Exception {
        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }
        getDefaultJedis().zrem(PREFIX + key, value);
    }

    /**
     * 获取有序集合中元素的个数
     */
    public static long getLengthOfSortedSet(String key) throws Exception {
        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }
        return getDefaultJedis().zcard(PREFIX + key);
    }

    /**
     * 查看有序集合中元素的权重
     */
    public static double getWeight(String key, String value) throws Exception {
        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }
        return getDefaultJedis().zscore(PREFIX + key, value);
    }

    // ========================hash 类型接口==============
    /**
     * 存储 HashMap
     */
    public static void setHashMapByFieldAndValue(String key, String field, String value) throws Exception {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(field)) {
            logger.error("key or field is null");
            throw new Exception("key or field is null");
        }
        getDefaultJedis().hset(PREFIX + key, field, value);
    }

    /**
     * 存储 HashMap
     */
    public static void setHashMapByMap(String key, Map<String, String> map) throws Exception {
        if (StringUtils.isEmpty(key) || map == null) {
            logger.error("key or map is null");
            throw new Exception("key or map is null");
        }
        getDefaultJedis().hmset(PREFIX + key, map);
    }

    /**
     * 删除 HashMap 中的键值对
     */
    public static void deleteHashMapValueByField(String key, String field) throws Exception {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(field)) {
            logger.error("key or field is null");
            throw new Exception("key or field is null");
        }
        getDefaultJedis().hdel(PREFIX + key, field);
    }

    /**
     * 获取 HashMap 中键对应的值
     */
    public static String getHashMapValueByField(String key, String field) throws Exception {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(field)) {
            logger.error("key or field is null");
            throw new Exception("key or field is null");
        }
        return getDefaultJedis().hget(PREFIX + key, field);
    }

    /**
     * 获取 HashMap 中所有的 key
     */
    public static Set<String> getHashMapKeys(String key) throws Exception {
        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }
        return getDefaultJedis().hkeys(PREFIX + key);
    }

    /**
     * 获取 HashMap 中所有的值
     */
    public static List<String> getHashMapValues(String key) throws Exception {
        if (StringUtils.isEmpty(key)) {
            logger.error("key is null");
            throw new Exception("key is null");
        }
        return getDefaultJedis().hvals(PREFIX + key);
    }

    /**
     * 判断 HashMap 中是否存在某一个键
     */
    public static boolean isFieldExistsInHashMap(String key, String field) throws Exception {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(field)) {
            logger.error("key or field is null");
            throw new Exception("key or field is null");
        }
        return getDefaultJedis().hexists(PREFIX + key, field);
    }

    public static long lpush(String key, String value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            logger.error("key or field is null");
        }

        return getDefaultJedis().lpush(key, value);
    }

    public static long rpush(String key, String value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            logger.error("key or field is null");
        }

        return getDefaultJedis().rpush(key, value);
    }

    public static String lpop(String key) {
        if (StringUtils.isEmpty(key)) {
            logger.error("key or field is null");
        }

        return getDefaultJedis().lpop(key);
    }

    public static String rpop(String key) {
        if (StringUtils.isEmpty(key)) {
            logger.error("key or field is null");
        }

        return getDefaultJedis().rpop(key);
    }

    static {

        getDefaultJedis().lpush("key1", "123");
        getDefaultJedis().lpush("key1", "456");
        getDefaultJedis().lpush("key1", "789");
        getDefaultJedis().lpush("key1", "012");

    }

    public static void main(String[] args) throws Exception {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //在添加集群节点的时候只需要添加一个，其余同一集群的节点会被自动加入
        jedisClusterNodes.add(new HostAndPort("192.168.241.132", 7000));
        JedisCluster jc = new JedisCluster(jedisClusterNodes);
        jc.set("rediskey", "redisvalue_123");
        String value = jc.get("rediskey");
        System.out.println(value);

    }
}