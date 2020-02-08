package top.clifton.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.clifton.community.dao.QuestionMapper;
import top.clifton.community.pojo.Question;
import top.clifton.community.service.QuestionService;

import java.util.List;

/**
 * @author Clifton
 * @create 2020/2/7 - 17:42
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<Question> findListWithUser() {
        return questionMapper.selectAll();
    }

    @Override
    public List<Question> findListWithUserByAccountId(Integer accountId) {
        return questionMapper.findListWithUserByAccountId(accountId);
    }

    @Override
    public Question findByIdWithUser(int id) {
        return questionMapper.selectByPrimaryKeyWithUser(id);
    }

    @Override
    public void updateQuestion(Question question) {
        questionMapper.updateByPrimaryKeySelective(question);
    }

    @Override
    public void insertQuestion(Question question) {
        questionMapper.insert(question);
    }

    @Override
    public void incViewCount(int id) {
        questionMapper.incViewCount(id);
    }
}
