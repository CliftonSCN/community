package top.clifton.community.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;

import top.clifton.community.pojo.Notification;
import top.clifton.community.pojo.Question;
import top.clifton.community.pojo.User;
import top.clifton.community.service.NotificationService;
import top.clifton.community.service.QuestionService;
import top.clifton.community.service.UserService;

/**
 * @author Clifton
 * @create 2020/2/6 - 12:01
 */
@Controller
@RequestMapping("/profile")
public class ProfileController
{

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{action}")
    public String toProfile(@PathVariable(name = "action") String action, Model model,
            @RequestParam(value = "pn", defaultValue = "1") int pn, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // 查询所有数据，封装分页信息
        PageMethod.startPage(pn, 3);
        if ("questions".equals(action)) {
            List<Question> questionList = questionService.findListWithUserByAccountId(user.getAccountId());
            PageInfo<Question> pageInfo = new PageInfo<>(questionList, 3);
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            model.addAttribute("pageInfo", pageInfo);
        }
        else if ("replies".equals(action)) {
            List<Notification> notifications = notificationService.getCurrentUserNotifications(user.getAccountId());
            PageInfo<Notification> pageInfo = new PageInfo<>(notifications, 3);
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "所有回复");
            model.addAttribute("pageInfo", pageInfo);
        }

        return "profile";
    }

}
