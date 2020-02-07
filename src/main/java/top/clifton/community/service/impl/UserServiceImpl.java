package top.clifton.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.clifton.community.dao.UserMapper;
import top.clifton.community.pojo.User;
import top.clifton.community.service.UserService;

/**
 * @author Clifton
 * @create 2020/2/7 - 17:41
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByAccountId(Integer id) {
        return userMapper.selectByAccountId(id);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public User findByToken(String token) {
        return userMapper.selectByToken(token);
    }
}
