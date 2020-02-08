package top.clifton.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import top.clifton.community.exception.QuestionNotFoundException;
import top.clifton.community.pojo.Question;
import top.clifton.community.service.QuestionService;

/**
 * @author Clifton
 * @create 2020/2/7 - 11:06
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String toQuestion(@PathVariable("id")int id,
                             Model model){

        Question question = questionService.findByIdWithUser(id);
        if (question == null){
            throw new QuestionNotFoundException("问题已经被删除了！");
        }
        model.addAttribute("question", question);
        questionService.incViewCount(id);
        return "question";
    }

}
