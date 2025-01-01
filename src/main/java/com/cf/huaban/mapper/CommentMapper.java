package com.cf.huaban.mapper;

import com.cf.huaban.entity.Comment;
import com.cf.huaban.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface CommentMapper {

    // 获取所有用户评论
    @Select("SELECT account, name, id, avatar, commentall FROM user")
    List<User> getAllComment();

    // 查询作品评论
    @Select("SELECT c.id, c.comment, c.date, c.worksid, c.userid, u.avatar, u.name " +
            "FROM comment c " +
            "JOIN user u ON c.userid = u.id " +
            "WHERE c.worksid = #{workId}")
    List<Comment> selectCommentByWorkId(@Param("workId") int workId);

    // 插入评论
    @Insert("INSERT INTO comment (id, userid, worksid, comment, date) " +
            "VALUES (null, #{userid}, #{worksid}, #{comment}, #{date})")
    void insertComment(@Param("userid") int userid, @Param("worksid") int worksid,
                       @Param("comment") String comment, @Param("date") String date);

    // 更新评论总数
    @Update("UPDATE user SET commentall = commentall + 1 WHERE id = #{userid}")
    void updateCommentCount(@Param("userid") int userid);

    // 更新评分
    @Update("UPDATE score SET score = #{score}, userid = #{userid} WHERE id = #{worksid}")
    void updateScore(@Param("worksid") int worksid, @Param("score") String score,
                     @Param("userid") int userid);


    // 获取所有评论详情
    @Select("SELECT c.id, c.comment, c.date, c.status, " +
            "w.title AS workTitle, " +
            "u.name AS userName " +
            "FROM comment c " +
            "LEFT JOIN works w ON c.worksid = w.id " +
            "LEFT JOIN user u ON c.userid = u.id " +
            "ORDER BY c.date DESC")
    List<Map<String, Object>> getAllCommentsWithDetails();

    // 更新评论状态
    @Update("UPDATE comment SET status = #{status} WHERE id = #{commentId}")
    int updateCommentStatus(int commentId, int status);

    // 删除评论
    @Delete("DELETE FROM comment WHERE id = #{commentId}")
    int deleteComment(int commentId);
}
