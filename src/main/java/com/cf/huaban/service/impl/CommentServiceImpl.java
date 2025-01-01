package com.cf.huaban.service.impl;

import com.cf.huaban.entity.Comment;
import com.cf.huaban.entity.User;
import com.cf.huaban.mapper.CommentMapper;
import com.cf.huaban.service.CommentService;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    // 不加事务，查询操作不需要事务控制
    public List<User> getAllComment() {
        return commentMapper.getAllComment();
    }

    // 查询操作不需要事务控制
    public List<Comment> selectCommentByWorkId(int workId) {
        return commentMapper.selectCommentByWorkId(workId);
    }

    // 方法有数据库操作，使用事务管理
    @Transactional(rollbackFor = Exception.class)
    public void insertComment(int userid, int worksid, String comment, String date) {
        try {
            logger.info("Inserting comment for user {} on work {}: {}", userid, worksid, comment);
            commentMapper.insertComment(userid, worksid, comment, date);
            commentMapper.updateCommentCount(userid);
            logger.info("Comment inserted successfully for user {} on work {}", userid, worksid);
        } catch (Exception e) {
            logger.error("Error inserting comment for user {} on work {}", userid, worksid, e);
            throw new RuntimeException("Error inserting comment", e);
        }
    }

    @Transactional
    public void updateCommentCount(int userid) {
        commentMapper.updateCommentCount(userid);
    }

    @Transactional
    public void updateScore(int worksid, String score, int userid) {
        commentMapper.updateScore(worksid, score, userid);
    }

    // 获取所有评论详情
    public List<Map<String, Object>> getAllCommentsWithDetails() {
        return commentMapper.getAllCommentsWithDetails();
    }

    // 更新评论状态
    public int updateCommentStatus(int commentId, int status) {
        return commentMapper.updateCommentStatus(commentId, status);
    }

    // 删除评论
    public int deleteComment(int commentId) {
        return commentMapper.deleteComment(commentId);
    }
}
