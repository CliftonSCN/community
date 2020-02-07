package top.clifton.community.service;

import top.clifton.community.pojo.Question;

import java.util.List;

/**
 * @author Clifton
 * @create 2020/2/7 - 17:42
 */
public interface QuestionService {
    List<Question> findListWithUser();

    List<Question> findListWithUserByAccountId(Integer accountId);

    Question findByIdWithUser(int id);

    void updateQuestion(Question question);

    void insertQuestion(Question question);
}
