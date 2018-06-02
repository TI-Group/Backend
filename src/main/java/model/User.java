package model;

import common.constant.UserRole;

public class User {
    private int userId;
    private String username;
    private String password;
    private String tel;
    private UserRole role;

    /* ======================================= */

    public User() {
        super();
    }
    public User(int userId, String username, String password, String tel, UserRole role) {
        super();
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.role = role;
    }
    
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }

}