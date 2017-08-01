package com.lavor.ssm.service;

import com.lavor.ssm.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lei.zeng on 2017/7/18.
 */
public interface UserService {
    List<User> findAllUser();
}
