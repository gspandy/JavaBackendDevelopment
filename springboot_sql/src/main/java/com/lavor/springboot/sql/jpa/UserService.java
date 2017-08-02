package com.lavor.springboot.sql.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lei.zeng on 2017/8/2.
 */
@Service("jpaUserService")
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User selectUserById(Integer id) {
        return userRepository.selectUserById(id);
    }
}
