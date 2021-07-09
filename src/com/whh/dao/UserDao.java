package com.whh.dao;

import com.whh.bean.User;

public interface UserDao {



    /**
     * 根据用户名查询用户信息
     * @return
     */
    User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     * @return
     */
    User queryUserByUsernameAndPassword(String username,String password) throws Exception;

    /**
     * 保存用户信息(增加一行)
     * @param user
     * @return
     */
    int saveUser(User user) throws Exception;

}
