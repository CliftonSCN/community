package top.clifton.community.redis;

import java.sql.Time;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.ietf.jgss.Oid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * (view , (qId , count))
 * (like
 * (comment
 * @author Clifton
 * @create 2020/3/7 - 14:28
 */
@Component
public class RedisUtil {

    @Autowired
    StringRedisTemplate redisTemplate;

    public boolean containKey(String key){
        return redisTemplate.hasKey(key);
    }

    public Map<Object, Object> hGet(String key){
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        return opsForHash.entries(key);
    }

    public void incCount(String key, String target, long num){
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        opsForHash.increment(key, target, num);
        redisTemplate.expire(key, 30, TimeUnit.MINUTES);
    }

    public void sSet(String key, String value){
        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        opsForSet.add(key, value);
        redisTemplate.expire(key, 30, TimeUnit.MINUTES);
    }

    public Set<String> sGet(String key){
        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        return opsForSet.members(key);
    }

   /* public void mPut(String key, Map<String, String> map){
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        opsForHash.putAll(key, map);
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        opsForList.
    }*/



}
