package top.clifton.community.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.clifton.community.pojo.Comment;
import top.clifton.community.pojo.User;
import top.clifton.community.service.CommentService;

/**
 * @author Clifton
 * @create 2020/2/8 - 19:51
 */
@RequestMapping("/comment")
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @PostMapping("/")
    public Comment comment(@RequestBody Comment comment,
                           HttpServletRequest request){
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0L);
        comment.setCommentator(((User)request.getSession().getAttribute("user")).getAccountId());
        commentService.insertComment(comment);
        return comment;
    }

}
