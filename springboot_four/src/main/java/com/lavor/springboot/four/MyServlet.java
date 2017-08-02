package com.lavor.springboot.four;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet：Java动态页面的根本
 * 使用@WebServlet注解说明它是一个Servlet，并配置它的urlPatterns
 * 需要被@ServletComponentScan注解扫描到才可以起作用
 * 相当于在web.xml中配置了Servlet
 * Created by lei.zeng on 2017/8/2.
 */
@WebServlet(urlPatterns = "/servlet/*")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet()");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost()");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>我是Servlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>我是Servlet</h2>");
        out.println("</body>");
        out.println("</html>");
    }

}
