package top.clifton.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import top.clifton.community.mapper.QuestionMapper;
import top.clifton.community.pojo.Question;
import top.clifton.community.pojo.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Clifton
 * @create 2020/2/3 - 15:45
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String toPublish(){
        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String toEdit(@PathVariable(name = "id") int id,
                         Model model){
        Question question = questionMapper.findByIdWithUser(id);
        model.addAttribute("question",question);

        return "publish";
    }

    @PostMapping("/publish")
    public String publishQuestion(Question question,
                                  HttpServletRequest request,
                                  Model model){
        StringBuilder error = new StringBuilder();
        if(StringUtils.isEmpty(question.getTitle())){
            error.append("标题不能为空");
            model.addAttribute("error", error.toString());
        }else if(StringUtils.isEmpty(question.getDescription())){
            error.append("问题描述不能少于10个字");
        }else if(StringUtils.isEmpty(question.getTag())){
            error.append("请选择一个标签");
        }
        if (!StringUtils.isEmpty(error.toString())){
            model.addAttribute("error", error.toString());
            model.addAttribute("question",question);
            return "publish";
        }

        if (question.getId() != null){
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.updateQuestion(question);
        }else {
            User user = (User) request.getSession().getAttribute("user");
            question.setCreator(Long.valueOf(user.getAccountId()));
            question.setCommentCount(0);
            question.setLikeCount(0);
            question.setViewCount(0);
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insertQuestion(question);
        }
        return "redirect:/";
    }

}