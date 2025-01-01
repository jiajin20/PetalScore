package com.cf.huaban.mapper;

import com.cf.huaban.entity.User;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where account=#{account} and password=#{password}")
    User login(@Param("account") String email,@Param("password") String password);
    @Insert("INSERT INTO user (id,account, password, name,email, age, root, state, avatar,sexy,commentall) " +
            "VALUES (null,#{account}, #{password},#{name},#{email}, #{age}, #{root}, #{state}, #{avatar},#{sexy},#{commentall})")
    void register(User user);
    @Select("select * from user where id=#{userId}")
    User selectUserById(int userId);
    @Update("update user set avatar=#{img} where id=#{id}")
    int updateImgUrl(@Param("id") int id,@Param("img") String img);
    @Update("update user set name=#{name},age=#{age},sexy=#{sexy},password=#{password} where id=#{id}")
    int updateUserProfile(@Param("id") int id,@Param("name") String name,@Param("age") int age,@Param("sexy") String sexy,@Param("password") String password);
    @Update("update user set name=#{name},age=#{age},sexy=#{sexy} where id=#{id}")
    int updateUserProfilenoPassword(@Param("id") int id,@Param("name") String name,@Param("age") int age,@Param("sexy") String sexy);

    @Select({"<script>",
            "SELECT * FROM user",
            "WHERE 1=1",
            "<if test='account != null and account != \"\"'>",
            "AND account LIKE CONCAT('%', #{account}, '%')",
            "</if>",
            "<if test='name != null and name != \"\"'>",
            "AND name LIKE CONCAT('%', #{name}, '%')",
            "</if>",
            "<if test='root != null and root != \"\"'>",
            "AND root = #{root}",
            "</if>",
            "<if test='state != null and state != \"\"'>",
            "AND state = #{state}",
            "</if>",
            "LIMIT #{offset}, #{size}",
            "</script>"})
    List<User> getUserList(@Param("offset") int offset, @Param("size") int size,
                           @Param("account") String account, @Param("name") String name,
                           @Param("root") String root, @Param("state") String state) throws SQLException;

    @Select({"<script>",
            "SELECT COUNT(*) FROM user",
            "WHERE 1=1",
            "<if test='account != null and account != \"\"'>",
            "AND account LIKE CONCAT('%', #{account}, '%')",
            "</if>",
            "<if test='name != null and name != \"\"'>",
            "AND name LIKE CONCAT('%', #{name}, '%')",
            "</if>",
            "<if test='root != null and root != \"\"'>",
            "AND root = #{root}",
            "</if>",
            "<if test='state != null and state != \"\"'>",
            "AND state = #{state}",
            "</if>",
            "</script>"})
    int getUserCount(@Param("account") String account, @Param("name") String name,
                     @Param("root") String root, @Param("state") String state) throws SQLException;

    @Delete("DELETE FROM user WHERE id = #{userId}")
    int deleteUser(@Param("userId") int userId) throws SQLException;

    @Update("UPDATE user SET state = #{state} WHERE id = #{userId}")
    int updateUserStatus(@Param("userId") int userId, @Param("state") String state) throws SQLException;

    @Update("UPDATE user SET password = #{newPassword} WHERE id = #{userId}")
    int resetPassword(@Param("userId") int userId, @Param("newPassword") String newPassword) throws SQLException;


    @Update("UPDATE user SET name = #{name}, age = #{age}, sexy = #{sexy}, password = #{password}, " +
            "email = #{email}, phone = #{phone}, root = #{root} -1 , state = #{state} " +
            "WHERE id = #{userId}")
    int updateUserProfile1(@Param("userId") int userId, @Param("name") String name,
                          @Param("age") int age, @Param("sexy") String sexy,
                          @Param("password") String password, @Param("email") String email,
                          @Param("phone") String phone, @Param("root") String root,
                          @Param("state") String state) throws SQLException;

    @Update("UPDATE user SET name = #{name}, age = #{age}, sexy = #{sexy}, " +
            "email = #{email}, phone = #{phone}, root = #{root} - 1, state = #{state} " +
            "WHERE id = #{userId}")
    int updateUserProfilenoPassword1(@Param("userId") int userId, @Param("name") String name,
                                    @Param("age") int age, @Param("sexy") String sexy,
                                    @Param("email") String email, @Param("phone") String phone,
                                    @Param("root") String root, @Param("state") String state) throws SQLException;

}
