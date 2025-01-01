package com.cf.huaban.mapper;

import com.cf.huaban.dto.WSDto;
import com.cf.huaban.entity.Category;
import com.cf.huaban.entity.Score;
import com.cf.huaban.entity.Works;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface WorksMapper {

    // 查询所有作品
    @Select("SELECT * FROM works")
    List<Works> Findallworks();

    // 查询作品总数
    @Select("SELECT COUNT(*) FROM works")
    int CountFindallworks();

    // 查询所有分类
    @Select("SELECT * FROM category")
    List<Category> Findallcategory();

    // 根据分类查询作品
    @Select("SELECT * FROM works WHERE categoryid = #{category}")
    List<Works> Findallworksbycategory(int category);

    // 获取达人榜作品ID
    @Select("SELECT worksid FROM top")
    List<Works> Findtopworksid();

    // 根据作品ID查询作品
    @Select("SELECT * FROM works WHERE worksid = #{worksid}")
    List<Works> Findtopworks(String worksid);

    // 查询所有作品的评分
    @Select("SELECT id, worksid, score FROM score")
    List<Score> findWorksScoreid();

    // 根据作品ID查询作品
    @Select("SELECT * FROM works WHERE worksid = #{id}")
    List<Works> Findworksbyid(int id);

    // 查询所有作品的评分
    @Select("SELECT w.worksid, AVG(s.score) AS score FROM works w JOIN score s ON w.worksid = s.worksid GROUP BY w.worksid")
    List<Score> Findscoreall();

    // 查询作品的关注人数
    @Select("SELECT COUNT(*) FROM attention WHERE worksid = #{worksid}")
    long Findattentionall(int worksid);

    // 查询用户是否关注了某作品
    @Select("SELECT COUNT(*) FROM attention WHERE worksid = #{worksid} AND userid = #{userid}")
    long Findattentionuser(int worksid, int userid);

    // 关注作品
    @Insert("INSERT INTO attention (worksid, userid, date) VALUES (#{worksid}, #{userid}, #{date})")
    int addAttention(int worksid, int userid, Date date);

    // 取消关注作品
    @Delete("DELETE FROM attention WHERE worksid = #{worksid} AND userid = #{userid}")
    int removeAttention(int worksid, int userid);

    // 增加浏览量
    @Update("UPDATE works SET viewcount = viewcount + 1 WHERE worksid = #{id}")
    int addBrowse(int id);

    // 获取作品评分
    @Select("SELECT AVG(s.score) AS score FROM works w JOIN score s ON w.worksid = s.worksid WHERE w.worksid = #{worksid}")
    long findScoreById(int worksid);

    // 获取用户关注的作品
    @Select("SELECT w.* FROM attention a JOIN works w ON a.worksid = w.worksid WHERE a.userid = #{userid}")
    List<Works> findAttentionWorks(int userid);

    // 获取所有作品详情
    List<Works> findTopWorksDetails();



    @Select({"<script>",
            "SELECT w.*, c.categoryname, ",
            "(SELECT CAST(AVG(CAST(s.score AS DECIMAL(10,2))) AS DECIMAL(10,2)) ",
            "FROM score s WHERE s.worksid = w.worksid) as score ",
            "FROM works w ",
            "LEFT JOIN category c ON w.categoryid = c.id ",
            "WHERE 1=1 ",
            "<if test='title != null and title != \"\"'>",
            "AND w.title LIKE CONCAT('%',#{title},'%')",
            "</if>",
            "<if test='producer != null and producer != \"\"'>",
            "AND w.producer LIKE CONCAT('%',#{producer},'%')",
            "</if>",
            "<if test='category != null'>",
            "AND w.categoryid = #{category}",
            "</if>",
            "ORDER BY w.date DESC ",
            "LIMIT #{offset}, #{size}",
            "</script>"})
    List<Map<String, Object>> getWorksList(@Param("title") String title,
                                           @Param("producer") String producer,
                                           @Param("category") Integer category,
                                           @Param("offset") int offset,
                                           @Param("size") int size);

    @Select({"<script>",
            "SELECT COUNT(*) FROM works w",
            "WHERE 1=1",
            "<if test='title != null and title != \"\"'>",
            "AND w.title LIKE CONCAT('%',#{title},'%')",
            "</if>",
            "<if test='producer != null and producer != \"\"'>",
            "AND w.producer LIKE CONCAT('%',#{producer},'%')",
            "</if>",
            "<if test='category != null'>",
            "AND w.categoryid = #{category}",
            "</if>",
            "</script>"})
    int getWorksCount(@Param("title") String title,
                      @Param("producer") String producer,
                      @Param("category") Integer category);

    @Delete("DELETE FROM works WHERE id = #{id}")
    int deleteWork(@Param("id") Integer id);

    @Select("SELECT * FROM category")
    List<Category> getAllCategories();

    @Update("UPDATE works SET title=#{title}, introduction=#{introduction}, " +
            "producer=#{producer}, categoryid=#{categoryid}, " +
            "date=#{date} " +
            "WHERE id=#{id}")
    int updateWork(@Param("id") Integer id,
                   @Param("title") String title,
                   @Param("introduction") String introduction,
                   @Param("producer") String producer,
                   @Param("categoryid") Integer categoryid,
                   @Param("date") Date date);

    // 插入 works 表数据，返回生成的 id
    @Insert("INSERT INTO works (title, introduction, producer, categoryid, date, viewcount) " +
            "VALUES (#{work.title}, #{work.introduction}, #{work.producer}, #{work.categoryid}, #{work.date}, 0)")
    @Options(useGeneratedKeys = true, keyProperty = "work.id")
    int createWork(@Param("work") WSDto work);

    // 插入 score 表数据，使用 work.id 作为 worksid，并插入评分和用户 id
    @Insert("INSERT INTO score (worksid, score) " +
            "VALUES (#{worksid}, #{score})")
    int createScore(@Param("worksid") int worksid, @Param("score") String score);


    @Insert("INSERT INTO category (categoryname) VALUES (#{categoryname})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addCategory(Category category);

    @Update("UPDATE category SET categoryname = #{categoryname} WHERE id = #{id}")
    int updateCategory(Category category);

    @Delete("DELETE FROM category WHERE id = #{id}")
    int deleteCategory(Integer id);

    @Select("SELECT c.*, COUNT(w.id) as workcount " +
            "FROM category c " +
            "LEFT JOIN works w ON c.id = w.categoryid " +
            "GROUP BY c.id, c.categoryname")
    List<Map<String, Object>> getCategoriesWithCount();

    @Update("UPDATE works SET worksid = #{id} WHERE id = #{id}")
    int updateWorksId(@Param("id") Integer id);
}
