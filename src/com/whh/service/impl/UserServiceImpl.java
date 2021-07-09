package com.whh.service.impl;

import com.whh.bean.User;
import com.whh.dao.impl.UserDaoImpl;
import com.whh.service.UserService;


public class UserServiceImpl implements UserService {

    UserDaoImpl dao = new UserDaoImpl();

    @Override
    public void registerUser(User user) throws Exception {
        dao.saveUser(user);
    }

    @Override
    public User login(User user) throws Exception {
        return dao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        if (dao.queryUserByUsername(username) == null){
            //为null说明没查到该用户，可以注册
            return false;
        }else {
            return true;
        }


    }
}
