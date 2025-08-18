package selfprojects.my_telegram_gpt_bot.Redis;


import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
public class RedisCache {
    StringRedisTemplate redisTemplate;

    public RedisCache(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean hasRedis(String userId){
        return redisTemplate.hasKey("greeted" + userId);
    }

    public void setGreeted(String userId){
        redisTemplate.opsForValue().set("greeted" + userId, "true", 24, TimeUnit.HOURS);
    }


}
