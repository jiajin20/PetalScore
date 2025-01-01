package com.cf.huaban.service;

import com.cf.huaban.dto.WSDto;
import com.cf.huaban.entity.Category;
import com.cf.huaban.entity.Score;
import com.cf.huaban.entity.Works;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface WorksService {

    // 查询所有作品
    List<Works> Findallworks();

    // 查询作品总数
    int CountFindallworks();

    // 查询所有分类
    List<Category> Findallcategory();

    // 根据分类查询作品
    List<Works> Findallworksbycategory(int category);

    // 获取达人榜作品ID
    List<Works> Findtopworksid();

    // 根据作品ID查询作品
    List<Works> Findtopworks(String worksid);

    // 查询所有作品的评分
    List<Score> findWorksScoreid();

    // 根据作品ID查询作品
    List<Works> Findworksbyid(int id);

    // 查询所有作品的评分
    List<Score> Findscoreall();

    // 查询作品的关注人数
    long Findattentionall(int worksid);

    // 查询用户是否关注了某作品
    long Findattentionuser(int worksid, int userid);

    // 关注/取消关注作品
    int MOattention(int worksid, int userid, Date date);

    // 增加浏览量
    int addBrowse(int id);

    // 获取作品平均评分
    long findScoreById(int worksid);

    // 获取用户关注的作品
    List<Works> findAttentionWorks(int userid);

    // 获取所有作品详情
    List<Works> findTopWorksDetails();


    Map<String, Object> getWorksList(int page, int size, String title, String producer, Integer category) throws SQLException;
    boolean deleteWork(Integer id) throws SQLException;
    boolean updateWork(Works work) throws SQLException;
    boolean createWork(WSDto work,String score) throws SQLException;

    // 分类管理
    boolean addCategory(Category category) throws SQLException;
    boolean updateCategory(Category category) throws SQLException;
    boolean deleteCategory(Integer id) throws SQLException;

    // 获取分类及其作品数量
    List<Map<String, Object>> getCategoriesWithCount() throws SQLException;

}
