package com.lavor.springboot.others.controller;

import com.lavor.springboot.others.EventEnum;
import com.lavor.springboot.others.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lei.zeng on 2017/8/4.
 */
@RestController
public class OthresControllers {
    @RequestMapping("/others")
    public String others(){
        return "others";
    }
}
