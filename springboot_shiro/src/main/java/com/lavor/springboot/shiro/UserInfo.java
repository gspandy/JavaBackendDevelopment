package com.lavor.springboot.shiro;

import java.util.List;

/**
 * Created by lei.zeng on 2017/8/3.
 */
public class UserInfo {
    private String username;
    private String password;
    private List<UserRole> roleList;// 一个用户具有多个角色
    public UserInfo() {

    }
    public UserInfo(String username, String password, List<UserRole> roleList) {
        this.username = username;
        this.password = password;
        this.roleList = roleList;
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

    public List<UserRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<UserRole> roleList) {
        this.roleList = roleList;
    }
}
