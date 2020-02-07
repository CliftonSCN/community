package top.clifton.community.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;

import top.clifton.community.pojo.Question;
import top.clifton.community.service.QuestionService;
import top.clifton.community.service.UserService;

/**
 * @author Clifton
 * @create 2020/1/30 - 14:46
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String toIndex(@RequestParam(value = "pn", defaultValue = "1")int pn,
                          HttpServletRequest request,
                          Model model){

        //查询所有数据，封装分页信息
        PageMethod.startPage(pn, 3);
        List<Question> questionList = questionService.findListWithUser();
        PageInfo<Question> pageInfo = new PageInfo<>(questionList, 3);

        model.addAttribute("pageInfo", pageInfo);

        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())){
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        return "redirect:/";
    }

}
