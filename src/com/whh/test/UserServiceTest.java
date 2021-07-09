package com.whh.test;

import com.whh.bean.User;
import com.whh.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserServiceImpl userService = new UserServiceImpl();

    @Test
    public void registerUser() throws Exception {
        User ooo = new User(null, "ooo", "456", "jkal@qq.com");
        userService.registerUser(ooo);
    }

    @Test
    public void login() throws Exception {
        User user = new User(null, "ooo", "456", "null");
        User login = userService.login(user);
        System.out.println(login);
    }

    @Test
    public void existUsername() throws Exception {
        if(userService.existUsername("ooo")){
            System.out.println("用户名已存在!");
        }else {
            System.out.println("用户名可用!");
        }
    }
}