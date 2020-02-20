package top.clifton.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.clifton.community.pojo.Comment;
import top.clifton.community.pojo.Notification;
import top.clifton.community.service.CommentService;
import top.clifton.community.service.NotificationService;

/**
 * @author Clifton
 * @create 2020/2/15 - 10:13
 */
@Controller
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @Autowired
    CommentService commentService;

    @GetMapping("/notification/{id}")
    public String notification(@PathVariable("id")int id){
        Notification notification = notificationService.selectById(id);
        int questionId = notification.getOuterid();
        //通知未读
        if (notification.getStatus() == 0){
            notificationService.UpdateStatus(id);
        }
        //通知是回复评论 需要查出问题id
        if (notification.getType() == 2){
            Comment comment = commentService.findById(questionId);
            questionId = comment.getParentId();
        }
        return "redirect:/question/"+questionId;
    }

}
