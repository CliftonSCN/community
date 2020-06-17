package top.clifton.community.controller;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
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
        int questionId = commentService.addComment(comment);
        comment.setUser(user);
        String message = JSON.toJSONString(comment);

//        QuestionWebSocket.systemSendMessageToUser(String.valueOf(user.getAccountId()), message);
        //当评论后，需向所有打开连接并且 questionId一样 的人发消息
        QuestionWebSocket.sendInfo(String.valueOf(questionId), message);

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
