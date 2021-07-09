package com.whh.dao.impl;

import com.whh.bean.User;
import com.whh.dao.BaseDao;
import com.whh.dao.UserDao;
import com.whh.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDaoImpl extends BaseDao implements UserDao {

    QueryRunner runner = new QueryRunner();

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     * @throws Exception
     */
    @Override
    public User queryUserByUsername(String username){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "select id,username,password,email from t_user where username = ?";
        BeanHandler<User> handler = new BeanHandler<>(User.class);
        User user = null;
        try {
            user = runner.query(conn, sql, handler, username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtils.closeSource(conn,null,null);

        return user;
    }

    /**
     * 根据用户名和密码查询用户信息
     * @return
     */
    @Override
    public User queryUserByUsernameAndPassword(String username, String password) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "select id,username,password,email from t_user where username = ? and password = ?";
        BeanHandler<User> handler = new BeanHandler<>(User.class);
        User user = runner.query(conn, sql, handler, username, password);
        JDBCUtils.closeSource(conn,null,null);
        return user;
    }

    /**
     * 保存用户信息(增加一行)
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public int saveUser(User user) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        int i = runner.update(conn, sql, user.getUsername(), user.getPassword(), user.getEmail());
        JDBCUtils.closeSource(conn,null,null);
        return i;//返回影响的行数
    }



}