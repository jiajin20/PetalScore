package com.cf.huaban.controller;
import com.alibaba.fastjson.JSONObject;
import com.cf.huaban.dto.UserDto;
import com.cf.huaban.entity.User;
import com.cf.huaban.service.UserService;
import com.cf.huaban.util.EmailUtil;
import com.cf.huaban.util.JwtUtil;
import com.cf.huaban.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2024-12-28 14:54:13
 */
@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public R login(@RequestBody Map<String, String> loginData) throws SQLException {
        String account = loginData.get("account");
        String password = loginData.get("password");
        if (account == null || password == null) {
            return R.error("用户名或密码不能为空");
        }
        User user = userService.login(account, password);
        if (user == null) {
            return R.error("用户名或密码错误");
        }
        String token = JwtUtil.createToken(account);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", user);
        jsonObject.put("token", token);
        return R.success("登录成功",jsonObject);
    }
    @PostMapping("/register")
    public R register(@RequestBody Map<String, String> registerData, HttpSession session) {
        String account = registerData.get("account");
        String password = registerData.get("password");
        String email = registerData.get("email");
        String code = registerData.get("vcode");
        String sessionCode = (String) session.getAttribute("emailCode");
        if (sessionCode == null) {
            return R.error("验证码已过期，请重新获取验证码");
        }
        if (!sessionCode.equals(code)) {
            return R.error("验证码错误");
        }
        log.info("account: {}, password: {}, email: {}", account, password, email);
        User user = userService.register(account, password, email);
        if (user == null) {
            return R.error("注册失败");
        } else {
            return R.success("注册成功", user);
        }
    }
    // 生成并发送验证码
    @GetMapping("/getCode")
    public R code(@RequestParam("email") String email, HttpSession session) throws Exception {
        StringBuilder builder = new StringBuilder();
        int num;
        for (int i = 0; i < 6; i++) {
            num = (int)(Math.random() * 10);
            builder.append(String.valueOf(num));
        }
        String snum = builder.toString();
        EmailUtil.sendMail(email, "验证码", "您的验证码是：" + snum);
        session.setAttribute("emailCode", snum);
        session.setMaxInactiveInterval(5 * 60); // 5 分钟
        return R.success("验证码已发送到您的邮箱，请注意查收");
    }

    // 验证验证码的接口
    @PostMapping("/verifyCode")
    public R verifyCode(@RequestParam("email") String email, @RequestParam("vcode") String code, HttpSession session) {
        // 从Session中获取验证码
        String sessionCode = (String) session.getAttribute("emailCode");
        if (sessionCode == null) {
            return R.error("验证码已过期，请重新获取验证码");
        }
        if (!sessionCode.equals(code)) {
            return R.error("验证码错误");
        }
        return R.success("验证码验证成功");
    }
    @GetMapping("/getUserbyid")
    public R selectUserById(@RequestParam("id") int userId) throws SQLException {
        return R.success("查询成功", userService.selectUserById(userId));

    }


    @PostMapping("imgUpDown")
    public R imgUpDown(@RequestParam("file") MultipartFile file, @RequestParam("id") int ID) throws IOException, SQLException {
        String fileName = UUID.randomUUID() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String projectRootPath = System.getProperty("user.dir");
        File uploadDir = new File(projectRootPath+"/static/img");
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        File destFile = new File(uploadDir, fileName);
        file.transferTo(destFile);
        // 拼接图片的 URL
        String imgurl = "static/" + fileName;
        userService.userimg(imgurl, ID);
        return R.success("上传成功", imgurl);
    }
    @PostMapping("updateUserProfile")
    public R updateUserProfile(@RequestBody Map<String, String> updateData) throws SQLException {
        String username = updateData.get("name");
        String password = updateData.get("password");
        int id = Integer.parseInt(updateData.get("id"));
        String sexy = updateData.get("sexy");
        int age = Integer.parseInt(updateData.get("age"));
        if (password!=null){
            userService.updateUserProfile(id,username,age,sexy,password);
        }else {
            userService.updateUserProfilenoPassword(id,username,age,sexy);
        }
        return R.success("修改成功");
    }
    // 获取用户列表我的
    @GetMapping("/list")
    public R getUserList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String account,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String root,
            @RequestParam(required = false) String state
    ) throws SQLException {
        Map<String, Object> result = userService.getUserList(page, size, account, name, root, state);
        return R.success("获取成功", result);
    }

    // 删除用户
    @PostMapping("/delete")
    public R deleteUser(@RequestBody Map<String, Integer> deleteData) throws SQLException {
        int userId = deleteData.get("id");
        int result = userService.deleteUser(userId);
        if (result > 0) {
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }
    @PostMapping("/update")
    public R updateUser(@RequestBody Map<String, Object> updateData) throws SQLException {
        try {
            int userId = (Integer) updateData.get("id");
            String name = (String) updateData.get("name");
            String email = (String) updateData.get("email");
            String sexy = (String) updateData.get("sexy");
            String phone = (String) updateData.get("phone");
            Integer age = (Integer) updateData.get("age");
            String root = String.valueOf(updateData.get("root"));
            String state = String.valueOf(updateData.get("state"));
            String password = (String) updateData.get("password");
            int result;
            if (password != null && !password.isEmpty()) {
                result = userService.updateUserProfile1(userId, name, age, sexy, password, email, phone, root, state);
            } else {
                result = userService.updateUserProfilenoPassword1(userId, name, age, sexy, email, phone, root, state);
            }
            if (result > 0) {
                return R.success("更新成功");
            } else {
                return R.error("更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("更新失败：" + e.getMessage());
        }
    }

    // 获取用户统计信息
    @GetMapping("/statistics")
    public R getUserStatistics() throws SQLException {
        Map<String, Object> statistics = userService.getUserStatistics();
        return R.success("获取成功", statistics);
    }

    // 重置用户密码
    @PostMapping("/resetPassword")
    public R resetPassword(@RequestBody Map<String, Integer> resetData) throws SQLException {
        try {
            int userId = resetData.get("id");
            // 默认重置密码为123456
            String defaultPassword = "123456";
            int result = userService.resetPassword(userId, defaultPassword);
            if (result > 0) {
                return R.success("密码重置成功");
            } else {
                return R.error("密码重置失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("密码重置失败：" + e.getMessage());
        }
    }
}

