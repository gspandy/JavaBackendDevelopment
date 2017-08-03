package com.lavor.springboot.rest;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * JerseyRest的类必须@Path注解，否则可能访问不到其下的请求
 * Created by lei.zeng on 2017/8/3.
 */
@Component
@Path("/")
public class JerseyRest {
    /**
     * @GET表示请求的方法类型为get
     * @Path("{username}")表示请求路径
     * @Produces(MediaType.APPLICATION_JSON)表示返回类型为json数据
     * @return
     */
    @GET
    @Path("user")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,Object> getUser(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",1);
        map.put("name", "lavor");
        return map;
    }
}
