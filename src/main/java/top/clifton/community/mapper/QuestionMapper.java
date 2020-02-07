package top.clifton.community.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import top.clifton.community.pojo.Question;

import java.util.List;

/**
 * @author Clifton
 * @create 2020/2/3 - 18:58
 */
@Component
@Mapper
public interface QuestionMapper {

    @Insert("insert into question(TITLE, GMT_CREATE, GMT_MODIFIED, CREATOR, COMMENT_COUNT, VIEW_COUNT, LIKE_COUNT, TAG, DESCRIPTION) "
            + "values (#{title,jdbcType=VARCHAR}, #{gmtCreate,jdbcType=BIGINT}, \n"
            + "      #{gmtModified,jdbcType=BIGINT}, #{creator,jdbcType=BIGINT}, #{commentCount,jdbcType=INTEGER}, \n"
            + "      #{viewCount,jdbcType=INTEGER}, #{likeCount,jdbcType=INTEGER}, #{tag,jdbcType=VARCHAR}, \n"
            + "      #{description,jdbcType=CLOB})")
    void insertQuestion(Question question);


    @Select("select * from question")
    @Results({
            @Result(property = "user", column = "CREATOR", one = @One(select = "top.clifton.community.mapper.UserMapper.findByAccountId"))
    })
    List<Question> findListWithUser();

    @Select("select * from question where creator = #{accountId}")
    @Results({
            @Result(property = "user", column = "CREATOR", one = @One(select = "top.clifton.community.mapper.UserMapper.findByAccountId"))
    })
    List<Question> findListWithUserByAccountId(@Param("accountId") String accountId);

    @Select("select * from question where id = #{id}")
    @Results({
            @Result(property = "user", column = "CREATOR", one = @One(select = "top.clifton.community.mapper.UserMapper.findByAccountId"))
    })
    Question findByIdWithUser(@Param("id")int id);

    @Update("update question set title = #{title}, description = #{description}, tag = #{tag}, gmt_modified = #{gmtModified} "
            + "where id = ${id}")
    void updateQuestion(Question question);
}
