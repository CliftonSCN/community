package top.clifton.community.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.clifton.community.redis.RedisService;

/**
 * @author Clifton
 * @create 2020/3/7 - 20:50
 */
@Component
public class CountSchudele {

    @Autowired
    RedisService redisService;

    @Scheduled(fixedRate = 20000)
    public void persistCount(){
        redisService.persistCount();
    }

}
