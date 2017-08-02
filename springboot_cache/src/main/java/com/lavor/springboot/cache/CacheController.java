package com.lavor.springboot.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lei.zeng on 2017/8/2.
 */
@Controller
public class CacheController {
    @Autowired
    private MyCache myCache;
    @Autowired
    CacheManager cacheManager;
    @RequestMapping("/cache")
    public String springCache(){
        System.out.println(cacheManager.toString());
        long time=0;
        time= myCache.select();
        System.out.println(time);
        myCache.update();
        time=time= myCache.select();
        System.out.println(time);
        myCache.delete();
        time=time= myCache.select();
        System.out.println(time);
        return "/index.html";
    }
}
