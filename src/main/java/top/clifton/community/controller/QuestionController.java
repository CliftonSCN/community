package top.clifton.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.clifton.community.mapper.QuestionMapper;
import top.clifton.community.pojo.Question;

/**
 * @author Clifton
 * @create 2020/2/7 - 11:06
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/question/{id}")
    public String toQuestion(@PathVariable("id")int id,
                             Model model){

        Question question = questionMapper.findByIdWithUser(id);
        model.addAttribute("question", question);
        return "question";
    }

}
