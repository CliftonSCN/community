package top.clifton.community.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import top.clifton.community.enums.CodeEnum;
import top.clifton.community.dto.JsonDto;
import top.clifton.community.enums.NotificationTypeEnum;
import top.clifton.community.pojo.Comment;
import top.clifton.community.pojo.Notification;
import top.clifton.community.pojo.User;
import top.clifton.community.service.CommentService;
import top.clifton.community.websocket.QuestionWebSocket;

import java.io.IOException;
import java.util.List;

/**
 * @author Clifton
 * @create 2020/2/8 - 19:51
 */
@Controller
public class CommentController
{

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @PostMapping("/comment")
    public JsonDto comment(@RequestBody Comment comment, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0L);
        comment.setCommentCount(0);
        comment.setCommentator(user.getAccountId());
        int row = commentService.addComment(comment);

        QuestionWebSocket.systemSendMessageToUser(String.valueOf(user.getAccountId()), "收到"+user.getName()+"的消息了："+comment.getContent());

        if (row != 1) {
            return new JsonDto(CodeEnum.SERVER_ERROR);
        }

        return new JsonDto(CodeEnum.SUCCESS);
    }

    @ResponseBody
    @GetMapping("/comment/{cid}")
    public JsonDto comment(@PathVariable(name = "cid") int cid) {
        JsonDto<List> jsonDto = new JsonDto<>();

        List<Comment> subComments = commentService.findSubComments(cid);
        jsonDto.setResponse(CodeEnum.SUCCESS);
        jsonDto.setData(subComments);
        return jsonDto;
    }

}
