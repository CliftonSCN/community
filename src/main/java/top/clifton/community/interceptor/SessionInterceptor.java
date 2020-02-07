package top.clifton.community.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import top.clifton.community.pojo.User;
import top.clifton.community.service.UserService;

/**
 * @author Clifton
 * @create 2020/2/6 - 20:18
 */
@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        User user = (User) session.getAttribute("user");
        if ( user == null && cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    user = userService.findByToken(cookie.getValue());
                    if (user != null) {
                        // Tomcat中Session的默认失效时间为20分钟,spring boot中默认30分钟未活跃
                        session.setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if (request.getServletPath().equals("/")){
            return true;
        }
        if (user == null){
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
