package com.cf.huaban.controller;

import com.cf.huaban.dto.CommentDto;
import com.cf.huaban.dto.ScoreRDto;
import com.cf.huaban.dto.WorksinfoDto;
import com.cf.huaban.entity.*;
import com.cf.huaban.service.CommentService;
import com.cf.huaban.service.WorksService;
import com.cf.huaban.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/works")
public class WorksController {

    private static final Logger log = LoggerFactory.getLogger(WorksController.class);
    @Autowired
    private WorksService worksService;
    @Autowired
    private CommentService commentService;


    @GetMapping("/top")
    public ResponseEntity<List<Works>> getTopWorks() {
        List<Works> topWorks = worksService.findTopWorksDetails();
        return ResponseEntity.ok(topWorks);
    }
    @GetMapping("/user/commentcount")
    public List<User> getUserCommentCount() {
        // 调用 DAO 查询用户评论总数
        return commentService.getAllComment();
    }
    @GetMapping("/comments")
    public ResponseEntity<?> selectCommentByWorkId(@RequestParam("id") int workId) {
        try {
            // 获取评论列表
            List<Comment> comments = commentService.selectCommentByWorkId(workId);
            return ResponseEntity.ok().body(comments); // 返回评论列表
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database error occurred: " + e.getMessage());
        }
    }
    @PostMapping("/insertComment")
//    public ResponseEntity<String> insertComment(@RequestParam("uid") int userId,
//                                                @RequestParam("worksid") int worksId,
//                                                @RequestParam("comment") String content,
//                                                @RequestParam("date") String date,
//                                                @RequestParam(value = "rating", required = false) String score)
    public ResponseEntity<String> insertComment(@RequestBody CommentDto commentDto) throws SQLException
    {
        int userId = commentDto.getUserid();
        int worksId = commentDto.getWorksid();
        String content = commentDto.getComment();
        String date = commentDto.getDate();
        String score = commentDto.getRating();
        log.info("userid: {}, worksid: {}, comment: {}, date: {}, score: {}", userId, worksId, content, date, score);
        // 参数校验
        if (userId <= 0 || worksId <= 0 || content == null || content.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("参数错误");
        }

        try {
            // 调用评论插入方法
            commentService.insertComment(userId, worksId, content, date);
            commentService.updateScore(worksId, score, userId);
            commentService.updateCommentCount(userId);
            // 更新评论总数
            // 返回成功响应
            return ResponseEntity.status(HttpStatus.OK).body("评论成功");
        } catch (Exception e) {
            // 处理数据库异常
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database error occurred: " + e.getMessage());
        }
    }





    @GetMapping("/count")
    public ResponseEntity<Integer> countFindAllWorks() {
        int count = worksService.CountFindallworks();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> findAllCategories() throws SQLException {
        List<Category> categories = worksService.Findallcategory();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/by-category")
    public ResponseEntity<?> findWorksByCategory(@RequestParam("categoryid") int categoryid) {
        List<Works> works = worksService.Findallworksbycategory(categoryid);
        List<Score> scores = worksService.Findscoreall();
        List<WorksinfoDto> worksinfos = new ArrayList<>();
        for (Works work : works) {
            for (Score score : scores) {
                if (work.getWorksid() == score.getWorksid()) {
                    worksinfos.add(new WorksinfoDto(work, score));
                    break;
                }
            }
        }
        return ResponseEntity.ok(worksinfos);
    }

@GetMapping("/allworks")
    public ResponseEntity<List<WorksinfoDto>> findAllWorks() {
    List<WorksinfoDto> worksinfos;
    List<Works> works = worksService.Findallworks();
    List<Score> scores = worksService.Findscoreall();

    worksinfos = new ArrayList<>();
    for (Works work : works) {
        for (Score score : scores) {
            if (work.getWorksid() == score.getWorksid()) {
                worksinfos.add(new WorksinfoDto(work, score));
                break;
            }

        }
    }
    return ResponseEntity.ok(worksinfos);

}
    @GetMapping("/negative")
    public ResponseEntity<List<WorksinfoDto>> negativeWorks() {
        List<Works> works = worksService.Findallworks();
        List<Score> scores = worksService.Findscoreall();
        List<WorksinfoDto> worksinfos = new ArrayList<>();
        for (Works work : works) {
            for (Score score : scores) {
                if (work.getWorksid() == score.getWorksid() && Double.parseDouble(score.getScore()) < 3) {
                    worksinfos.add(new WorksinfoDto(work, score));
                }
            }
        }
        return ResponseEntity.ok(worksinfos);
    }

    @GetMapping("/particulars")
    public ResponseEntity<?> findWorksById(@RequestParam("id") int id) {
        List<Works> works = worksService.Findworksbyid(id);
        if (works.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No works found for the given ID");
        }
        return ResponseEntity.ok(works);
    }

    @GetMapping("/attention")
    public R findAttentionUser(@RequestParam("worksid") int worksid, @RequestParam("userid") int userid) {
        long attentionCount = worksService.Findattentionuser(worksid, userid);
        if (attentionCount == 0) {
            return R.success("未关注", null);
        }
        return R.success("已关注", "1");
    }

    @GetMapping("/view")
    public ResponseEntity<?> addView(@RequestParam("worksid") int worksid) {
        worksService.addBrowse(worksid);
        return ResponseEntity.ok("Views added successfully");
    }

    @GetMapping("/score")
    public ResponseEntity<?> findScoreById(@RequestParam("worksid") int worksid) {
        long score = worksService.findScoreById(worksid);
        return ResponseEntity.ok(new ScoreRDto(worksid, score));
    }

    @GetMapping("/attention/add")
    public ResponseEntity<?> addAttention(@RequestParam("worksid") int worksid, @RequestParam("userid") int userid, @RequestParam("date") String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateString);
            int result = worksService.MOattention(worksid, userid, date);
            if (result > 0) {
                return ResponseEntity.ok("Attention processed successfully");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process attention");
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input data");
        }
    }
    @GetMapping("/attention/all")
    public long findAllAttention(@RequestParam("worksid") int worksid) throws SQLException {
        return worksService.Findattentionall(worksid);
    }

    @GetMapping("/findattentionworks")
    public ResponseEntity<List<Works>> findAttentionWorks(@RequestParam("userid") int userid) throws SQLException {
        List<Works> likeworks=worksService.findAttentionWorks(userid);
        return ResponseEntity.ok(likeworks);
    }
    @GetMapping("/comments/all")
    public R getAllComments() {
        try {
            List<Map<String, Object>> comments = commentService.getAllCommentsWithDetails();
            return R.success("获取成功", comments);
        } catch (SQLException e) {
            log.error("获取评论列表失败", e);
            return R.error("获取评论列表失败");
        }
    }

    // 更新评论状态
    @PostMapping("/comments/updateStatus")
    public R updateCommentStatus(@RequestBody Map<String, Object> updateData) {
        try {
            int commentId = (Integer) updateData.get("id");
            int status = (Integer) updateData.get("status");
            int result = commentService.updateCommentStatus(commentId, status);
            if (result > 0) {
                return R.success("更新成功");
            } else {
                return R.error("更新失败");
            }
        } catch (SQLException e) {
            log.error("更新评论状态失败", e);
            return R.error("更新评论状态失败");
        }
    }

    // 删除评论
    @PostMapping("/comments/delete")
    public R deleteComment(@RequestBody Map<String, Integer> deleteData) {
        try {
            int commentId = deleteData.get("id");
            int result = commentService.deleteComment(commentId);
            if (result > 0) {
                return R.success("删除成功");
            } else {
                return R.error("删除失败");
            }
        } catch (SQLException e) {
            log.error("删除评论失败", e);
            return R.error("删除评论失败");
        }
    }

    @GetMapping("/categoriesWithCount")
    public ResponseEntity<?> getCategoriesWithCount() {
        try {
            List<Map<String, Object>> categories = worksService.getCategoriesWithCount();
            return ResponseEntity.ok(categories);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("获取分类统计失败: " + e.getMessage());
        }
    }
}

