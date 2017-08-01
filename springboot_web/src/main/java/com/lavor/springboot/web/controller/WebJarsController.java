package com.lavor.springboot.web.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import org.webjars.WebJarAssetLocator;

/**
 *  WebJars的控制器，让我们可以在前台使用相同的url访问后台更新版本后的WebJars资源
 * Created by shitian on 2017-07-23.
 */
@Controller
public class WebJarsController {
    private final WebJarAssetLocator assetLocator = new WebJarAssetLocator();
    @ResponseBody
    @RequestMapping("/webjarslocator/{webjar}/**")
    public ResponseEntity<Object> locateWebjarAsset(@PathVariable String webjar, HttpServletRequest request) {
        try {
            //前缀必须与请求中的前缀相同
            String mvcPrefix = "/webjarslocator/" + webjar + "/";
            String mvcPath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
            String fullPath = assetLocator.getFullPath(webjar, mvcPath.substring(mvcPrefix.length()));
            return new ResponseEntity<>(new ClassPathResource(fullPath), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
