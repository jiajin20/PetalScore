package com.cf.huaban.service;

import com.cf.huaban.entity.Comment;
import com.cf.huaban.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CommentService {
    List<User> getAllComment();
    // 根据作品id查找评论
    List<Comment> selectCommentByWorkId(int workId) throws SQLException;
    //添加评论
    void insertComment(int userid,int worksid, String content,String date) throws SQLException;
    //添加评论数
    void updateCommentCount(int userid) throws SQLException;
    //添加分数
    void updateScore(int worksid, String score,int userid) throws SQLException;


    // 获取所有评论详细信息
    List<Map<String, Object>> getAllCommentsWithDetails() throws SQLException;

    // 更新评论状态
    int updateCommentStatus(int commentId, int status) throws SQLException;

    // 删除评论
    int deleteComment(int commentId) throws SQLException;
}
