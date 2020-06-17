package top.clifton.community.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSException;
import top.clifton.community.exception.QuestionNotFoundException;
import top.clifton.community.pojo.Question;
import top.clifton.community.service.QuestionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Clifton
 * @create 2020/3/7 - 14:27
 */
@Service
public class RedisService {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    QuestionService questionService;

    public void incCount(String key, String target){
        if (!redisUtil.containKey(key)){
            String[] split = key.split("_");
            Question question = questionService.findById(Integer.parseInt(split[1]));
            if (question != null){
                HashMap<String, String> countMap = new HashMap<>();
                countMap.put("view", String.valueOf(question.getViewCount()));
                countMap.put("like", String.valueOf(question.getLikeCount()));
                countMap.put("comment", String.valueOf(question.getCommentCount()));
                redisUtil.mPut(key, countMap);
                redisUtil.sSet("countList", key);
            }else {
                throw new QuestionNotFoundException("问题没找到");
            }
        }else {
            redisUtil.incCount(key, target, 1);
        }
    }

    public void persistCount() {
        Set<String> countList = redisUtil.sGet("countList");
        ArrayList<Question> list = new ArrayList<>();
        countList.forEach(key -> {
            Map<Object, Object> map = redisUtil.hGet(key);
            Question question = new Question();
            question.setId(Integer.valueOf(key.split("_")[1]));
            map.forEach((k,v)->{
                switch (String.valueOf(k)){
                    case "view":
                        question.setViewCount(Integer.valueOf(String.valueOf(v)));
                        break;
                    case "like":
                        question.setLikeCount(Integer.valueOf(String.valueOf(v)));
                        break;
                    case "comment":
                        question.setCommentCount(Integer.valueOf(String.valueOf(v)));
                        break;
                }
            });
            list.add(question);
        });
        questionService.updateCount(list);
    }
}
