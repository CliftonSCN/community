package top.clifton.community.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.clifton.community.dao.CommentMapper;
import top.clifton.community.dao.NotificationMapper;
import top.clifton.community.dao.QuestionMapper;
import top.clifton.community.enums.NotificationStatusEnum;
import top.clifton.community.pojo.Comment;
import top.clifton.community.pojo.Notification;
import top.clifton.community.pojo.Question;
import top.clifton.community.pojo.User;
import top.clifton.community.service.CommentService;

/**
 * @author Clifton
 * @create 2020/2/8 - 19:54
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    @Transactional
    public int addComment(Comment comment) {
        int questionId = 0;

        if (comment.getType() == 2){
            commentMapper.incCommentCount(comment.getParentId());
            Comment parentComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            questionId = parentComment.getParentId();
            questionMapper.incCommentCount(parentComment.getParentId());
        }else {
            questionMapper.incCommentCount(comment.getParentId());
            questionId = comment.getParentId();
        }
        commentMapper.insert(comment);
        createNotify(comment);
        return questionId;
    }

    private void createNotify(Comment comment) {
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        //发出通知的人 评论者
        notification.setNotifier(comment.getCommentator());
        User user = null;
        //接收通知的人
        if (comment.getType() == 1){
            //回复问题
            Question question = questionMapper.selectByPrimaryKeyWithUser(comment.getParentId());
            notification.setOuterTitle(question.getTitle());
            user = question.getUser();
        }else {
            //回复的是评论
            Comment comment1 = commentMapper.selectByPrimaryKeyWithUser(comment.getParentId());
            notification.setOuterTitle(comment1.getContent().length()>5?comment1.getContent().substring(0,5)+"...":comment1.getContent());
            user = comment1.getUser();
        }
        notification.setReceiver(user.getAccountId());
        notification.setNotifierName(user.getName());
        //问题或评论的id
        notification.setOuterid(comment.getParentId());
        notification.setType(comment.getType());


        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());

        notificationMapper.insert(notification);
    }

    @Override
    public List<Comment> findLevelFirstCommentList(Integer id) {
        return commentMapper.findComments(id , 1);
    }

    @Override
    public List<Comment> findSubComments(int id) {
        return commentMapper.findComments(id, 2);
    }

    @Override
    public Comment findById(int id) {
        return commentMapper.selectByPrimaryKey(id);
    }
}
