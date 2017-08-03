package com.lavor.springboot.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lei.zeng on 2017/8/3.
 */
@RestController
public class SpringRest {
    @GetMapping("/spring/user")
    public Map<String,Object> getUser(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",1);
        map.put("name", "lavor");
        return map;
    }
}
