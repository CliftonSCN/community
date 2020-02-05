package top.clifton.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import top.clifton.community.mapper.UserMapper;
import top.clifton.community.pojo.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Clifton
 * @create 2020/1/30 - 14:46
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String toIndex(HttpServletRequest request){
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
        return "index";
    }

}
