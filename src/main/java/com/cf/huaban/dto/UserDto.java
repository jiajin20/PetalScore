package com.cf.huaban.dto;

public class UserDto {
    private Integer id;
    /**
     * 用户账号
     */
    private String account;
    /**
     * 用户名字
     */
    private String name;
    /**
     * 用户年龄
     */
    private Integer age;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户性别
     */
    private String sexy;
    /**
     * 用户手机
     */
    private String phone;
    /**
     * 管理
     */
    private String root;
    /**
     * 状态
     */
    private String state;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户密码
     */
    private String password;

    private String commentall;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexy() {
        return sexy;
    }

    public void setSexy(String sexy) {
        this.sexy = sexy;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCommentall() {
        return commentall;
    }

    public void setCommentall(String commentall) {
        this.commentall = commentall;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", sexy='" + sexy + '\'' +
                ", phone='" + phone + '\'' +
                ", root='" + root + '\'' +
                ", state='" + state + '\'' +
                ", avatar='" + avatar + '\'' +
                ", password='" + password + '\'' +
                ", commentall='" + commentall + '\'' +
                '}';
    }
}
