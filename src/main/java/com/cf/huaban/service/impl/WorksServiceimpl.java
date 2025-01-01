package com.cf.huaban.service.impl;

import com.cf.huaban.dto.WSDto;
import com.cf.huaban.entity.Category;
import com.cf.huaban.entity.Score;
import com.cf.huaban.entity.Works;
import com.cf.huaban.mapper.WorksMapper;
import com.cf.huaban.service.WorksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Service("worksService")
@Transactional
public class WorksServiceimpl implements WorksService {

    private static final Logger log = LoggerFactory.getLogger(WorksServiceimpl.class);
    private final WorksMapper worksMapper;

    @Autowired
    public WorksServiceimpl(WorksMapper worksMapper) {
        this.worksMapper = worksMapper;
    }

    @Override
    public List<Works> Findallworks() {
        return worksMapper.Findallworks();
    }

    @Override
    public int CountFindallworks() {
        return worksMapper.CountFindallworks();
    }

    @Override
    public List<Category> Findallcategory() {
        return worksMapper.Findallcategory();
    }

    @Override
    public List<Works> Findallworksbycategory(int category) {
        return worksMapper.Findallworksbycategory(category);
    }

    @Override
    public List<Works> Findtopworksid() {
        return worksMapper.Findtopworksid();
    }

    @Override
    public List<Works> Findtopworks(String worksid) {
        return worksMapper.Findtopworks(worksid);
    }

    @Override
    public List<Score> findWorksScoreid() {
        return worksMapper.findWorksScoreid();
    }

    @Override
    public List<Works> Findworksbyid(int id) {
        return worksMapper.Findworksbyid(id);
    }

    @Override
    public List<Score> Findscoreall() {
        return worksMapper.Findscoreall();
    }

    @Override
    public long Findattentionall(int worksid) {
        return worksMapper.Findattentionall(worksid);
    }

    @Override
    public long Findattentionuser(int worksid, int userid) {
        return worksMapper.Findattentionuser(worksid, userid);
    }

    @Override
    public int MOattention(int worksid, int userid, Date date) {
        // 先查询是否存在关注记录
        long count = worksMapper.Findattentionuser(worksid, userid);
        if (count > 0) {
            // 如果已经关注，执行取消关注
            return worksMapper.removeAttention(worksid, userid);
        } else {
            // 如果没有关注，执行添加关注
            return worksMapper.addAttention(worksid, userid, date);
        }
    }

    @Override
    public int addBrowse(int id) {
        return worksMapper.addBrowse(id);
    }

    @Override
    public long findScoreById(int worksid) {
        return worksMapper.findScoreById(worksid);
    }

    @Override
    public List<Works> findAttentionWorks(int userid) {
        return worksMapper.findAttentionWorks(userid);
    }

    @Override
    public List<Works> findTopWorksDetails() {
        List<Works> topWorksIds = worksMapper.Findtopworksid();
        List<Works> topWorksDetails = new ArrayList<>();

        for (Works works : topWorksIds) {
            String worksid = String.valueOf(works.getWorksid());
            List<Works> worksDetails = worksMapper.Findtopworks(worksid);
            topWorksDetails.addAll(worksDetails);
        }
        return topWorksDetails;
    }


    @Override
    public Map<String, Object> getWorksList(int page, int size, String title, String producer, Integer category) throws SQLException {
        Map<String, Object> result = new HashMap<>();

        // 计算偏移量
        int offset = (page - 1) * size;

        // 获取分页数据
        List<Map<String, Object>> works = worksMapper.getWorksList(title, producer, category, offset, size);

        // 获取总数
        int total = worksMapper.getWorksCount(title, producer, category);

        result.put("list", works);
        result.put("total", total);

        return result;
    }

    @Override
    public boolean deleteWork(Integer id) throws SQLException {
        return worksMapper.deleteWork(id) > 0;
    }

    @Override
    public boolean updateWork(Works work) throws SQLException {
        return worksMapper.updateWork(
                work.getId(),
                work.getTitle(),
                work.getIntroduction(),
                work.getProducer(),
                work.getCategoryid(),
                new Date()  // 更新时间
        ) > 0;
    }

    @Override
    public boolean createWork(WSDto work,String score) throws SQLException {
        // 插入works数据，并获取生成的id
         worksMapper.createWork(work);
         worksMapper.updateWorksId(work.getId());
         worksMapper.createScore(work.getId(), score);
        return true ;
    }

    @Override
    public boolean addCategory(Category category) throws SQLException {
        return worksMapper.addCategory(category) > 0;
    }

    @Override
    public boolean updateCategory(Category category) throws SQLException {
        return worksMapper.updateCategory(category) > 0;
    }

    @Override
    public boolean deleteCategory(Integer id) throws SQLException {
        return worksMapper.deleteCategory(id) > 0;
    }

    @Override
    public List<Map<String, Object>> getCategoriesWithCount() throws SQLException {
        return worksMapper.getCategoriesWithCount();
    }


}
