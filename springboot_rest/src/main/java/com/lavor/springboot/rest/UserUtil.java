package com.lavor.springboot.rest;

import java.util.ArrayList;
import java.util.List;

/**
 * User工具类
 * Created by lei.zeng on 2017/8/3.
 */
public class UserUtil {
    public static List<User> list=new ArrayList<>();
    static {
        list.add(new User(1,"lavor"));
        list.add(new User(2,"张三"));
        list.add(new User(3,"李四"));
    }
    public static List<User> getAllUser(){
        return list;
    }
    public static User selectUserById(Integer id){
        for (User user:list){
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }
    public static void deleteUserById(Integer id){
        for (User user:list){
            if (user.getId().equals(id)){
                list.remove(user);
                break;
            }
        }
    }
}
