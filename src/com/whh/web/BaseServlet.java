package com.whh.web;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {
//声明为抽象的只是为了不被实例化


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");//防止乱码
        resp.setContentType("text/html;charset=UTF-8");//解决响应乱码
        String action = req.getParameter("action");
//        if ("login".equals(action)) {//处理登录请求
//            login(req,resp);
//        } else if ("register".equals(action)) {//处理注册请求
//            register(req,resp);
//        }
        try {
            //通过反射代替if-else if代码
            //获取action业务鉴别字符串，获取相应的业务 方法反射对象
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //调用目标业务 方法
            method.invoke(this,req,resp);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
