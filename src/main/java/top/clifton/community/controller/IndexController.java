package top.clifton.community.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.clifton.community.mapper.QuestionMapper;
import top.clifton.community.mapper.UserMapper;
import top.clifton.community.pojo.Question;
import top.clifton.community.pojo.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Clifton
 * @create 2020/1/30 - 14:46
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/")
    public String toIndex(@RequestParam(value = "pn", defaultValue = "1")int pn,
                          HttpServletRequest request,
                          Model model){
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        if (session.getAttribute("user") == null && cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    User user = userMapper.findByToken(cookie.getValue());
                    if (user != null) {
                        //Tomcat中Session的默认失效时间为20分钟,spring boot中默认30分钟未活跃
                        session.setAttribute("user", user);
                    }
                }
            }
        }

        //查询所有数据，封装分页信息
        PageMethod.startPage(pn, 3);
        List<Question> questionList = questionMapper.findListWithUser();
        PageInfo<Question> pageInfo = new PageInfo<>(questionList, 3);

        model.addAttribute("pageInfo", pageInfo);

        return "index";
    }

}
