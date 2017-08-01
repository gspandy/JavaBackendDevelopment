package com.lavor.springmvc;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * 利用@RestController注解实现restful的webservice，传递的是json数据
 * 它比JsonAndAjaxController和JsonAndAjaxController更加方便，简洁，
 * 可以很容易的实现Restful风格的api，更加容易在前后台之间传递json数据
 * Created by lei.zeng on 2017/7/18.
 */
@RestController
public class RestfulController {
    /**
     * @GetMapping注解相当于@RequestMapping与method = RequestMethod.GET的组合
     * @return
     */
    @GetMapping("/restfulWebservice")
    public User greeting() {
        User user=new User();
        user.setId(1190);
        user.setName("greeting");
        return user;
    }
}
