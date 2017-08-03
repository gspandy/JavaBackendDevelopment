package com.lavor.springboot.shiro;

import java.util.List;

/**
 * Created by lei.zeng on 2017/8/3.
 */
public class UserPermission {
    private Integer id;//主键.
    private String name;//名称.
    private String resourceType;//资源类型
    private String url;//资源路径.
    private String permission; //权限字符串,如：role:create,role:update,role:delete,role:view
    private List<UserRole> roles;

    public UserPermission() {
    }

    public UserPermission(Integer id, String name, String resourceType, String url, String permission, List<UserRole> roles) {
        this.id = id;
        this.name = name;
        this.resourceType = resourceType;
        this.url = url;
        this.permission = permission;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }
}
