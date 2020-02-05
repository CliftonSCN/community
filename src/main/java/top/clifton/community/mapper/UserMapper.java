package top.clifton.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import top.clifton.community.pojo.User;

/**
 * @author Clifton
 * @create 2020/2/2 - 17:42
 */
@Component
@Mapper
public interface UserMapper {

    @Insert("insert into user (account_id,name,token,gmt_created,gmt_modified,avatar_url) values (#{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified},#{avatarUrl})")
    void insertUser(User user);

    /**
     * @Param("token")用于直接取值，而不是调用.getXxx()
     * @param token
     * @return
     */
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") Long accountId);
}
