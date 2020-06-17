package top.clifton.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import top.clifton.community.exception.QuestionNotFoundException;
import top.clifton.community.pojo.Comment;
import top.clifton.community.pojo.Question;
import top.clifton.community.redis.RedisConstants;
import top.clifton.community.redis.RedisService;
import top.clifton.community.service.CommentService;
import top.clifton.community.service.QuestionService;

import java.util.List;

/**
 * @author Clifton
 * @create 2020/2/7 - 11:06
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisService redisService;

    @GetMapping("/question/{id}")
    public String toQuestion(@PathVariable("id")int id,
                             Model model){

        Question question = questionService.findByIdWithUser(id);
        if (question == null){
            throw new QuestionNotFoundException("问题已经被删除了！");
        }

        //查出所有的一级评论
        List<Comment> levelFirstCommentList = commentService.findLevelFirstCommentList(question.getId());

        //相关问题
        List<Question> relatedQuestions = questionService.findRelatedQuestion(id, question.getTag());
        model.addAttribute("comments",levelFirstCommentList);
        model.addAttribute("question", question);
        model.addAttribute("relatedQuestions", relatedQuestions);

        //questionService.incViewCount(id);
        //增加阅读数
        redisService.incCount("count_"+id, RedisConstants.VIEW);

        return "question";
    }

}
