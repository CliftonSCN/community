package top.clifton.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.clifton.community.dao.CommentMapper;
import top.clifton.community.pojo.Comment;
import top.clifton.community.service.CommentService;

/**
 * @author Clifton
 * @create 2020/2/8 - 19:54
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void insertComment(Comment comment) {
        commentMapper.insert(comment);
    }
}
