package com.lavor.springmvc;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * 文件的上传与下载
 * SpringMVC上传文件需要额外添加commons-fileupload.jar和commons-io.jar。
 * Created by shitian on 2017/7/5.
 */
@Controller
public class FileProcessing {
    /**
     * 单个文件上传，需要在Spring的xml配置文件中配置CommonsMultipartResolver的Bean
     *
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("uploadFile")
    public String uploadFile(@RequestParam("file") CommonsMultipartFile file) throws IOException {
        File newFile = new File("D:/" + file.getOriginalFilename());
        //通过CommonsMultipartFile的方法直接写文件
        file.transferTo(newFile);
        return "index.jsp";
    }

    /**
     * 多个文件的上传，也需要在Spring的xml配置文件中配置CommonsMultipartResolver的Bean
     *
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("uploadMutiFiles")
    public String uploadMutiFiles(HttpServletRequest request) throws IOException {
        long startTime = System.currentTimeMillis();
        //将当前上下文初始化给 CommonsMutipartResolver（多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    String path = "D:/" + file.getOriginalFilename();
                    file.transferTo(new File(path));
                }
            }
        }
        return "index.jsp";
    }

    /**
     * 文件下载
     * produces:指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/downloadFile", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> downloadFile() throws IOException {
        //获取classes目录路径，可能需要进行解码操作
        String dir=FileProcessing.class.getClassLoader().getResource("").getPath();
        String filePath=dir+"lavor.png";
        //对文件路径进行解码操作
        filePath=URLDecoder.decode(filePath, "UTF-8");
        //指定文件,必须是绝对路径
        File file = new File(filePath);
        //下载浏览器响应的那个文件名
        String dfileName = "下载的图片.png";
        //如果dfileName带有中文和特殊字符等还需要进行编码
        dfileName= URLEncoder.encode(dfileName,"UTF-8");
        //下面开始设置HttpHeaders,使得浏览器响应下载
        HttpHeaders headers = new HttpHeaders();
        //设置响应方式
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //设置响应文件
        headers.setContentDispositionFormData("attachment", dfileName);
        //把文件以二进制形式写回
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }
}
