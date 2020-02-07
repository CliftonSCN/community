package top.clifton.community.service;

import top.clifton.community.pojo.User;

/**
 * @author Clifton
 * @create 2020/2/7 - 17:40
 */
public interface UserService {

    User findByAccountId(Integer id);

    void insertUser(User user);

    User findByToken(String value);
}
