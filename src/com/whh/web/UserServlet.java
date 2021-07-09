package com.whh.web;
/*
合并LoginServlet和RegisterServlet
 */
import com.google.gson.Gson;
import com.whh.bean.User;
import com.whh.service.impl.UserServiceImpl;
import com.whh.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {


    UserServiceImpl usi = new UserServiceImpl();

    /**
     * 登录部分
     *
     * @param req
     * @param resp
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User(null, username, password, null);
        try {
            if (usi.login(user) == null) { //登录失败
                //把错误信息和回显的表单项信息，保存到Request域中
                req.setAttribute("msg", "用户名或密码错误,请重新输入");
                req.setAttribute("username", username);

                System.out.println("账号密码错误,登录失败!");
                req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            } else {
                System.out.println("登录成功");
                //保存用户登录之后的信息到session域中
                req.getSession().setAttribute("user", usi.login(user));
                req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 登出部分(注销)
     *
     * @param req
     * @param resp
     */
    protected void loginOut(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        1.销毁Session中用户登录的信息(销毁Session)
        req.getSession().invalidate();
//        2.重定向到首页
        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }


    /**
     * 注册部分
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取谷歌生成的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //1.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //使用BeanUtils工具类
        //把所有的请求参数注入到user对象中
        User user = WebUtils.copyParamToBean(req, new User());

        //2.检查验证码是否正确
        if (token != null && token.equalsIgnoreCase(code)) {
            //3.检查用户名正否正确
            try {
                if (usi.existUsername(username) == true) {//用户名不可用
                    System.out.println("用户名" + username + "已存在");

                    //把回显信息保存到request域中
                    req.setAttribute("msg", "用户名已存在");
                    req.setAttribute("username", username);
                    req.setAttribute("email", email);

                    //请求重定向,返回注册页面
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
                } else {
                    //用户名可用
                    usi.registerUser(user);
                    //跳转到注册成功页面
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else { //验证码输入错误
            //把回显信息保存到request域中
            req.setAttribute("msg", "验证码输入错误,请重新输入");
            req.setAttribute("username", username);
            req.setAttribute("email", email);


            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            System.out.println(code + "验证码输入错误");
        }
    }


    /**
     *使用Ajax验证注册时用户名是否可用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数
        String username = req.getParameter("username");
        //2.调用usi.existUsername(),为false用户名可用
        boolean existsUsername = usi.existUsername(username);
        //3.把返回结果封装成map对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);

    }
}
