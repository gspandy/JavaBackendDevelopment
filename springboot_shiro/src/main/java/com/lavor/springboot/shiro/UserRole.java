package com.lavor.springboot.shiro;

import java.util.List;

/**
 * Created by lei.zeng on 2017/8/3.
 */
public class UserRole {
    private Integer id; // 编号
    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String description; // 角色描述,UI界面显示使用
    private List<UserPermission> permissions;
    private List<UserInfo> userInfos;// 一个角色对应多个用户
    public UserRole() {
    }

    public UserRole(Integer id, String role, String description, List<UserPermission> permissions, List<UserInfo> userInfos) {
        this.id = id;
        this.role = role;
        this.description = description;
        this.permissions = permissions;
        this.userInfos = userInfos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }
}
