package com.whhp.oa.pojo;

import java.util.List;

public class User {
    private Long uid;

    private String username;

    private String password;

    private String email;

    private String sex;

    private Integer departmentId;

    private Department department;

    private List<Post> posts;

    public User() {
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", departmentId=" + departmentId +
                ", department=" + department +
                ", posts=" + posts +
                '}';
    }

    public User(Long uid, String username, String password, String email, String sex, Integer departmentId) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.sex = sex;
        this.departmentId = departmentId;
    }

    public User(String username, String password, String email, String sex, Integer departmentId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.sex = sex;
        this.departmentId = departmentId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}