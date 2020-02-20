package top.clifton.community.service;

import top.clifton.community.pojo.Comment;

import java.util.List;

/**
 * @author Clifton
 * @create 2020/2/8 - 19:54
 */
public interface CommentService {
    int addComment(Comment comment);

    List<Comment> findLevelFirstCommentList(Integer id);

    List<Comment> findSubComments(int id);

    Comment findById(int questionId);
}
