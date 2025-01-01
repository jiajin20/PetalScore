package com.cf.huaban.service;

import com.cf.huaban.entity.User;

import java.sql.SQLException;
import java.util.Map;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2024-12-28 14:54:16
 */
public interface UserService {

    User login(String account,String password);
    User register(String account, String password,String email);
    User selectUserById(int userId) throws SQLException;
    int userimg(String url, int id) throws SQLException;
    int updateUserProfile(int userId, String name, int age, String sexy, String password) throws SQLException;
    int updateUserProfilenoPassword(int userId, String name, int age, String sexy) throws SQLException;


    // 获取用户列表
    Map<String, Object> getUserList(int page, int size, String account, String name, String root, String state) throws SQLException;
    // 删除用户
    int deleteUser(int userId) throws SQLException;
    // 更新用户状态
    int updateUserStatus(int userId, String state) throws SQLException;
    // 获取用户统计信息
    Map<String, Object> getUserStatistics() throws SQLException;
    // 重置用户密码
    int resetPassword(int userId, String newPassword) throws SQLException;


    int updateUserProfile1(int userId, String name, int age, String sexy, String password,
                          String email, String phone, String root, String state) throws SQLException;
    int updateUserProfilenoPassword1(int userId, String name, int age, String sexy,
                                    String email, String phone, String root, String state) throws SQLException;
}
