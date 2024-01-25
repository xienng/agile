package org.agileframework.cache.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;

/**
 * [简要描述类用途]
 * <p>
 * [详细描述类用途、功能等，可选]
 * <p>
 * [额外的细节描述，比如类的组成、原理等，可选]
 *
 * @author xienng
 * @version 1.0
 * @date 2024年01月25日 14:47
 */
@Slf4j
public class StringRedisTemplate extends RedisTemplate<String, String> implements Serializable {

    public StringRedisTemplate() {
        setKeySerializer(RedisSerializer.string());
        setValueSerializer(RedisSerializer.string());
        setHashKeySerializer(RedisSerializer.string());
        setHashValueSerializer(RedisSerializer.string());
        //指定默认序列化器
        setDefaultSerializer(RedisSerializer.string());
    }

    /**
     * Constructs a new <code>StringRedisTemplate</code> instance ready to be used.
     *
     * @param connectionFactory connection factory for creating new connections
     */
    public StringRedisTemplate(RedisConnectionFactory connectionFactory) {
        this();
        setConnectionFactory(connectionFactory);
        afterPropertiesSet();
    }

    @Override
    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }

    /**
     * Set the {@code value} and expiration {@code timeout} for {@code key}.
     *
     * @param key   key must not be {@literal null}.
     * @param value value must not be {@literal null}.
     * @param time  the key expiration timeout, TimeUnit is Day
     */
    public void expireDay(String key, String value, Integer time) {
        opsForValue().set(key, value, time, TimeUnit.DAYS);
    }

    /**
     * Set the {@code value} and expiration {@code timeout} for {@code key}.
     *
     * @param key        key must not be {@literal null}.
     * @param value      value must not be {@literal null}.
     * @param timeSecond the key expiration timeout, TimeUnit is Seconds
     */
    public void expireSecond(String key, String value, int timeSecond) {
        opsForValue().set(key, value, timeSecond, TimeUnit.SECONDS);
    }

    /**
     * Set time to live for given {@code key}.
     *
     * @param key     must not be {@literal null}.
     * @param seconds the key expiration timeout, TimeUnit is Seconds
     */
    public void expire(String key, int seconds) {
        expire(key, seconds, TimeU.SECONDS);
    }

    /**
     * Set {@code value} for {@code key}.
     *
     * @param key   must not be {@literal null}.
     * @param value must not be {@literal null}.
     * @see <a href="https://redis.io/commands/set">Redis Documentation: SET</a>
     */
    public void set(String key, String value) {
        try {
            opsForValue().set(key, value);
        } catch (Throwable t) {
            log.error(String.format("get redis error, key : %s, value :%s", key, value), t);
        }
    }

    /**
     * Get the value of {@code key}.
     *
     * @param key key must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     * @see <a href="https://redis.io/commands/get">Redis Documentation: GET</a>
     */
    public Object get(String key) {
        try {
            return opsForValue().get(key);
        } catch (Throwable t) {
            log.error(String.format("get redis error, key : %s", key), t);
            return null;
        }
    }

    /**
     * Get the value of {@code key}. value is string
     *
     * @param key key must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     * @see <a href="https://redis.io/commands/get">Redis Documentation: GET</a>
     */
    public String getString(String key) {
        Object obj = null;
        try {
            obj = opsForValue().get(key);
        } catch (Exception e) {
            log.error(String.format("get redis error, string key : %s", key), e);
        }
        return obj != null ? (String) obj : null;
    }

    /**
     * Increment an integer value stored as string value under {@code key} by one.
     *
     * @param key must not be {@literal null}.
     * @return
     * @see <a href="https://redis.io/commands/incr">Redis Documentation: INCR</a>
     */
    public long incr(String key) {
        return opsForValue().increment(key, 1);
    }

    /**
     * Insert all values at the head of the list stored at key.
     *
     * @param key    must not be null.
     * @param values must not be empty nor contain null values.
     * @return
     */
    public Long lPush(String key, String... values) {
        return opsForList().leftPushAll(key, values);
    }

    /***
     * Get elements between {@code begin} and {@code end} from list at {@code key}.
     *
     * @param key
     *            must not be {@literal null}.
     * @param start
     * @param end
     * @return {@literal null} when used in pipeline / transaction.
     * @see <a href="https://redis.io/commands/lrange">Redis Documentation: LRANGE</a>
     */
    public List<String> lRange(String key, long start, long end) {
        return opsForList().range(key, start, end);
    }

    /**
     * Removes the first {@code count} occurrences of {@code value} from the list stored at {@code key}.
     *
     * @param key   must not be {@literal null}.
     * @param count
     * @param value
     * @return {@literal null} when used in pipeline / transaction.
     * @see <a href="https://redis.io/commands/lrem">Redis Documentation: LREM</a>
     */
    public Long lRemove(String key, long count, String value) {
        return opsForList().remove(key, count, value);
    }

    /**
     * Set the {@code value} of a hash {@code hashKey}.
     *
     * @param key     must not be {@literal null}.
     * @param hashKey must not be {@literal null}.
     * @param value
     */
    public void hSet(String key, String hashKey, String value) {
        opsForHash().put(key, hashKey, value);
    }

    /**
     * Get value for given {@code hashKey} from hash at {@code key}.
     *
     * @param key     must not be {@literal null}.
     * @param hashKey must not be {@literal null}.
     * @return {@literal null} when key or hashKey does not exist or used in pipeline / transaction.
     */
    public String hGet(String key, String hashKey) {
        return (String) opsForHash().get(key, hashKey);
    }

    /**
     * Get entire hash stored at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    public Map<Object, Object> hEntries(String key) {
        return opsForHash().entries(key);
    }

    /**
     * Delete given hash {@code hashKeys}.
     *
     * @param key      must not be {@literal null}.
     * @param hashKeys must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    public void hDel(String key, Object... hashKeys) {
        opsForHash().delete(key, hashKeys);
    }

    /**
     * Set multiple hash fields to multiple values using data provided in {@code m}.
     *
     * @param key    must not be {@literal null}.
     * @param allMap must not be {@literal null}.
     */
    public <HK, HV> void hPutAll(String key, Map<? extends HK, ? extends HV> allMap) {
        opsForHash().putAll(key, allMap);
    }

    /**
     * Add {@code value} to a sorted set at {@code key}, or update its {@code score} if it already exists, and extend
     * the time of the key
     *
     * @param key      must not be {@literal null}.
     * @param value    the value.
     * @param score    score the score.
     * @param limit    The maximum size of zset, if this number is exceeded, the last record will be deleted
     * @param timeout  the key expiration timeout
     * @param timeUnit timeout unit
     */
    public void zAddAndExpire(String key, String value, double score, Long limit, long timeout, TimeUnit timeUnit) {
        Long size = opsForZSet().size(key);
        if (Objects.nonNull(size) && size >= limit) {
            // 删除最后一条数据
            opsForZSet().removeRange(key, 0, 0);
        }
        opsForZSet().add(key, value, score);
        expire(key, timeout, timeUnit);
    }

    /**
     * Add {@code value} to a sorted set at {@code key}, or update its {@code score} if it already exists.
     *
     * @param key   must not be {@literal null}.
     * @param value the value.
     * @param score score the score.
     * @param limit The maximum size of zset, if this number is exceeded, the last record will be deleted
     */
    public Boolean zAdd(String key, String value, double score, Long limit) {
        Long size = opsForZSet().size(key);
        if (Objects.nonNull(size) && size >= limit) {
            // 删除最后一条数据
            opsForZSet().removeRange(key, 0, 0);
        }
        return opsForZSet().add(key, value, score);
    }

    /**
     * Get all elements from sorted set ordered from high to low.
     *
     * @param key
     * @return
     */
    public Set<String> zGet(String key) {
        return opsForZSet().reverseRange(key, 0, -1);
    }

    /**
     * zSet移除成员
     *
     * @param key     redis key值
     * @param members 成员集合
     */
    public void zRem(String key, List<String> members) {
        opsForZSet().remove(key, members.toArray());
    }

    /**
     * zSet 通过排序分数区间获得成员值list
     *
     * @param key        redis key值
     * @param min        最小分数
     * @param max        最大分时
     * @param withScores 是否返回成员的分数
     * @return Map<String, Double> 解构是 member->score
     */
    public Map<String, Double> zRangeByScore(String key, long min, long max, boolean withScores) {
        Map<String, Double> result = new HashMap<>();
        if (withScores) {
            Set<ZSetOperations.TypedTuple<String>> setResult = opsForZSet().rangeByScoreWithScores(key, min, max);
            if (CollectionUtils.isEmpty(setResult)) {
                return result;
            }
            setResult.forEach(stringTypedTuple -> result.put(stringTypedTuple.getValue(), stringTypedTuple.getScore()));
            return result;
        }
        Set<String> setResult = opsForZSet().rangeByScore(key, min, max);
        if (CollectionUtils.isEmpty(setResult)) {
            return result;
        }
        setResult.forEach(member -> result.put(member, 0.0));
        return result;
    }


    /**
     * Set {@code key} to hold the string {@code value} if {@code key} is absent.
     *
     * @param key   must not be {@literal null}.
     * @param value must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     * @see <a href="https://redis.io/commands/setnx">Redis Documentation: SETNX</a>
     */
    public Boolean setIfAbsent(final String key, final String value) {
        Boolean obj = false;
        try {
            // setNx 如果不存在，则 SET
            obj = opsForValue().setIfAbsent(key, value);
        } catch (Exception e) {
            obj = false;
        }
        return obj;
    }

    /**
     * Publishes the given message to the given channel.
     *
     * @param channel the channel to publish to, must not be {@literal null}.
     * @param message message to publish
     * @return the number of clients that received the message
     * @see <a href="https://redis.io/commands/publish">Redis Documentation: PUBLISH</a>
     */
    public void pub(String channel, String message) {
        convertAndSend(channel, message);
    }

}
