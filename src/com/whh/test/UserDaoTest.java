package com.whh.test;

import com.whh.bean.User;
import com.whh.dao.impl.UserDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDaoImpl dao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() throws Exception {
        User hah = dao.queryUserByUsername("hah");
        if(hah == null){
            System.out.println("该用户名可用!");
        }else {
            System.out.println("该用户名已存在!");
            System.out.println(hah);
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() throws Exception {
        User hah = dao.queryUserByUsernameAndPassword("hah1", "123456");
        //如果没有该用户名和密码，则返回null
        if(hah == null){
            System.out.println("用户名或密码错误，登录失败!");
        }else {
            System.out.println("登录成功!");
        }
    }

    @Test
    public void saveUser() throws Exception {
        User user = new User(null, "kgkk", "123", "jkk@");
        int i = dao.saveUser(user);
        System.out.println(i);
    }
}