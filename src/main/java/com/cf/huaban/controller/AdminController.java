/*
 * @Author: qsrgcz 3149576709@qq.com
 * @Date: 2024-12-30 00:08:44
 * @LastEditors: qsrgcz 3149576709@qq.com
 * @LastEditTime: 2024-12-30 14:28:54
 * @FilePath: \121\java\com\cf\huaban\controller\AdminController.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.cf.huaban.controller;

import com.cf.huaban.dto.WSDto;
import com.cf.huaban.entity.Category;
import com.cf.huaban.entity.Works;
import com.cf.huaban.service.WorksService;
import com.cf.huaban.util.R;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private WorksService worksService;

    @GetMapping("/works/list")
    public R getWorksList(@RequestParam(defaultValue = "1") int page,
                          @RequestParam(defaultValue = "10") int size,
                          @RequestParam(required = false) String title,
                          @RequestParam(required = false) String producer,
                          @RequestParam(required = false) Integer category) {
        try {
            Map<String, Object> result = worksService.getWorksList(page, size, title, producer, category);
            return R.success("获取成功", result);
        } catch (SQLException e) {
            return R.error("获取作品列表失败");
        }
    }

    @DeleteMapping("/works/{id}")
    public R deleteWork(@PathVariable Integer id) {
        try {
            boolean success = worksService.deleteWork(id);
            return success ? R.success("删除成功") : R.error("删除失败");
        } catch (SQLException e) {
            return R.error("删除作品失败");
        }
    }

    @GetMapping("/categories")
    public R getCategories() {
        try {
            List<Map<String, Object>> categories = worksService.getCategoriesWithCount();
            return R.success("获取成功", categories);
        } catch (SQLException e) {
            return R.error("获取分类列表失败");
        }
    }

    @PutMapping("/works/{id}")
    public R updateWork(@PathVariable Integer id, @RequestBody Works work) {
        try {
            work.setId(id);
            boolean success = worksService.updateWork(work);
            return success ? R.success("更新成功") : R.error("更新失败");
        } catch (SQLException e) {
            return R.error("更新作品失败");
        }
    }

    @PostMapping("/works")
    public R createWork(@RequestBody WSDto work) {
        try {
            // 参数验证
            if (work.getTitle() == null || work.getTitle().trim().isEmpty()) {
                return R.error("标题不能为空");
            }
            if (work.getCategoryid() == null) {
                return R.error("请选择作品分类");
            }
            if (work.getProducer() == null || work.getProducer().trim().isEmpty()) {
                return R.error("制作者不能为空");
            }

            boolean result = worksService.createWork(work,"0");
            if (result) {
                return R.success("添加成功", work);
            }
            return R.error("添加失败");
        } catch (Exception e) {
            log.error("添加作品失败: ", e);
            return R.error("系统错误，请稍后重试");
        }
    }

    @PostMapping("/categories")
    public R addCategory(@RequestBody Category category) {
        try {
            boolean success = worksService.addCategory(category);
            return success ? R.success("添加分类成功") : R.error("添加分类失败");
        } catch (SQLException e) {
            return R.error("添加分类失败: " + e.getMessage());
        }
    }

    @PutMapping("/categories/{id}")
    public R updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        try {
            category.setId(id);
            boolean success = worksService.updateCategory(category);
            return success ? R.success("更新分类成功") : R.error("更新分类失败");
        } catch (SQLException e) {
            return R.error("更新分类失败: " + e.getMessage());
        }
    }

    @DeleteMapping("/categories/{id}")
    public R deleteCategory(@PathVariable Integer id) {
        try {
            boolean success = worksService.deleteCategory(id);
            return success ? R.success("删除分类成功") : R.error("删除分类失败");
        } catch (SQLException e) {
            return R.error("删除分类失败: " + e.getMessage());
        }
    }

    @GetMapping("/works/categoriesWithCount")
    public R getCategoriesWithCount() {
        try {
            List<Map<String, Object>> categories = worksService.getCategoriesWithCount();
            return R.success("获取成功", categories);
        } catch (SQLException e) {
            return R.error("获取分类统计失败: " + e.getMessage());
        }
    }
}