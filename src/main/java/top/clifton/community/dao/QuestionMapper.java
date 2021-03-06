package top.clifton.community.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.clifton.community.pojo.Question;
import top.clifton.community.pojo.QuestionExample;

public interface QuestionMapper {
    long countByExample(QuestionExample example);

    int deleteByExample(QuestionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    List<Question> selectByExampleWithBLOBs(QuestionExample example);

    List<Question> selectByExample(QuestionExample example);

    Question selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Question record, @Param("example") QuestionExample example);

    int updateByExampleWithBLOBs(@Param("record") Question record, @Param("example") QuestionExample example);

    int updateByExample(@Param("record") Question record, @Param("example") QuestionExample example);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKeyWithBLOBs(Question record);

    int updateByPrimaryKey(Question record);

    List<Question> selectAll(String search);

    List<Question> findListWithUserByAccountId(@Param("accountId") Integer accountId);

    Question selectByPrimaryKeyWithUser(int id);

    void incViewCount(int id);

    void incCommentCount(Integer id);

    List<Question> selectRelated(@Param("id")int id, @Param("tag")String tag);

    void updateCountBatch(ArrayList<Question> list);
}