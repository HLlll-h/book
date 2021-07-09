package com.whh.service;

import com.whh.bean.User;

public interface UserService {
    /**
     * 注册用户
     * @param suer
     */
    void registerUser(User suer) throws Exception;

    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user) throws Exception;

    /**
     * 检查用户名是否可用
     * @return 返回true表示用户名已存在，返回false表示用户名可用
     */
    boolean existUsername(String username);

}
