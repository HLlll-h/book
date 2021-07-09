package com.whh.web;

import com.whh.bean.User;
import com.whh.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    UserServiceImpl usi = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        //2.检查验证码是否正确
        if("abcde".equalsIgnoreCase(code)){
            //3.检查用户名正否正确
            try {
                if(usi.existUsername(username) == true){//用户名不可用
                    System.out.println("用户名" + username + "已存在");

                    //把回显信息保存到request域中
                    req.setAttribute("msg","用户名已存在");
                    req.setAttribute("username",username);
                    req.setAttribute("email",email);

                    //请求重定向,返回注册页面
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
                }else{
                    //用户名可用
                    usi.registerUser(new User(null,username,password,email));
                    //跳转到注册成功页面
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }else{ //验证码输入错误
            //把回显信息保存到request域中
            req.setAttribute("msg","验证码输入错误,请重新输入");
            req.setAttribute("username",username);
            req.setAttribute("email",email);


            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            System.out.println(code + "验证码输入错误");
        }
//        System.out.println(username);
//        System.out.println(password);
//        System.out.println(email);
//        System.out.println(code);
    }
}
