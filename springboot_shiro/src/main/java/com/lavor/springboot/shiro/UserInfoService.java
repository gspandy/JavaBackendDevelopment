package com.lavor.springboot.shiro;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lei.zeng on 2017/8/3.
 */
@Service
public class UserInfoService {
    public UserInfo findByUsername(String username){
        //这里为了简化没有去数据库取数据
        UserRole userRole=new UserRole();
        UserPermission userPermission=new UserPermission();
        userPermission.setPermission("permission");
        List<UserPermission> userPermissionList=new ArrayList<>();
        userPermissionList.add(userPermission);
        userRole.setPermissions(userPermissionList);
        List<UserRole> userRoleList=new ArrayList<>();
        return new UserInfo("root","123456",userRoleList);
    }
}
