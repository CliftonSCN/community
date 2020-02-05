package top.clifton.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import top.clifton.community.pojo.Question;

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

}
