package com.lavor.springboot.actuator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shitian on 2017-08-03.
 */
@Controller
public class ActuatorController {
    @RequestMapping("/")
    public String actuator(){
        return "/index.html";
    }
}
