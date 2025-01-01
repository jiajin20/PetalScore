package com.cf.huaban.service.impl;

import com.cf.huaban.entity.User;
import com.cf.huaban.mapper.UserMapper;
import com.cf.huaban.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String account, String password) {
    User user = userMapper.login(account,password);
    return user;
    }

    @Override
    public User register(String account, String password, String email) {
      User user = new User();
      user.setAccount(account);
      user.setPassword(password);
      user.setEmail(email);
        user.setName("新用户");
        user.setAge(30);
        user.setRoot("1");
        user.setState("1");
        user.setAvatar("default-avatar.jpg");
        user.setSexy("未知");
        user.setCommentall("0");
        userMapper.register(user);
        return user;
    }
    @Override
    public User selectUserById(int userId)  {
        return userMapper.selectUserById(userId);
    }

    @Override
    public int userimg(String url, int id) throws SQLException {
        return userMapper.updateImgUrl(id, url);
    }
    @Override
    public int updateUserProfile(int userId, String name, int age, String sexy, String password) throws SQLException {
        return userMapper.updateUserProfile(userId, name, age, sexy,password);
    }
    @Override
    public int updateUserProfilenoPassword(int userId, String name, int age, String sexy) throws SQLException {
        return userMapper.updateUserProfilenoPassword(userId, name, age, sexy);
    }
    @Override
    public Map<String, Object> getUserList(int page, int size, String account, String name, String root, String state) throws SQLException {
        // 计算偏移量
        int offset = (page - 1) * size;

        // 获取用户列表
        List<User> users = userMapper.getUserList(offset, size, account, name, root, state);

        // 获取总数
        int total = userMapper.getUserCount(account, name, root, state);

        Map<String, Object> result = new HashMap<>();
        result.put("list", users);
        result.put("total", total);

        return result;
    }

    @Override
    public int deleteUser(int userId) throws SQLException {
        return userMapper.deleteUser(userId);
    }

    @Override
    public int updateUserStatus(int userId, String state) throws SQLException {
        return userMapper.updateUserStatus(userId, state);
    }

    @Override
    public Map<String, Object> getUserStatistics() throws SQLException {
        Map<String, Object> statistics = new HashMap<>();

        // 获取总用户数
        int totalUsers = userMapper.getUserCount(null, null, null, null);
        statistics.put("totalUsers", totalUsers);

        // 获取管理员数量
        int adminCount = userMapper.getUserCount(null, null, "0", null);
        statistics.put("adminCount", adminCount);

        // 获取普通用户数量
        int normalUserCount = userMapper.getUserCount(null, null, "1", null);
        statistics.put("normalUserCount", normalUserCount);

        // 获取活跃用户数（状态为1的用户）
        int activeUserCount = userMapper.getUserCount(null, null, null, "1");
        statistics.put("activeUserCount", activeUserCount);

        return statistics;
    }

    @Override
    public int resetPassword(int userId, String newPassword) throws SQLException {
        return userMapper.resetPassword(userId, newPassword);
    }

    @Override
    public int updateUserProfile1(int userId, String name, int age, String sexy, String password,
                                 String email, String phone, String root, String state) throws SQLException {
        return userMapper.updateUserProfile1(userId, name, age, sexy, password, email, phone, root, state);
    }

    @Override
    public int updateUserProfilenoPassword1(int userId, String name, int age, String sexy,
                                           String email, String phone, String root, String state) throws SQLException {
        return userMapper.updateUserProfilenoPassword1(userId, name, age, sexy, email, phone, root, state);
    }
}
