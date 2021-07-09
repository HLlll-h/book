package com.whh.web;

import com.whh.bean.User;
import com.whh.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    UserServiceImpl usi = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User(null, username, password, null);
        try {
            if(usi.login(user) == null){ //登录失败
            //把错误信息和回显的表单项信息，保存到Request域中
                req.setAttribute("msg","用户名或密码错误,请重新输入");
                req.setAttribute("username",username);

                System.out.println("账号密码错误,登录失败!");
                req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            }else{
                System.out.println("登录成功");
                req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
