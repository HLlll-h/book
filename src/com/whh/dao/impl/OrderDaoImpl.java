package com.whh.dao.impl;

import com.whh.bean.Order;
import com.whh.bean.User;
import com.whh.dao.BaseDao;
import com.whh.dao.OrderDao;
import com.whh.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    QueryRunner runner = new QueryRunner();

    @Override
    public int saveOrder(Order order) {
//        Connection conn = JDBCUtils.getConnection();
//        String sql = "insert into t_order(order_id,create_time,price,status,user_id) values(?,?,?,?,?)";
//        int i = runner.update(conn, sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
//        JDBCUtils.closeSource(conn);
//        return i;
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "insert into t_order(order_id,create_time,price,status,user_id) values(?,?,?,?,?)";
        int i = 0;
        try {
            i = runner.update(conn, sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        JDBCUtils.closeSource(conn);
        return i;
    }

    @Override
    public List<Order> queryOrderByUserId(Integer userId) {
//        Connection conn = JDBCUtils.getConnection();
//        String sql = "select order_id as orderId,create_time as createTime,price,status,user_id as userId from t_order where user_id = ?";
//        BeanListHandler<Order> handler = new BeanListHandler<>(Order.class);
//        List<Order> orders = runner.query(conn, sql, handler,userId);
//        JDBCUtils.closeSource(conn);
//        return orders;
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "select order_id as orderId,create_time as createTime,price,status,user_id as userId from t_order where user_id = ?";
        BeanListHandler<Order> handler = new BeanListHandler<>(Order.class);
        List<Order> orders = null;
        try {
            orders = runner.query(conn, sql, handler,userId);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        JDBCUtils.closeSource(conn);
        return orders;
    }

    @Override
    public List<Order> queryOrders() {
//        Connection conn = JDBCUtils.getConnection();
//        String sql = "select order_id as orderId,create_time as createTime,price,status,user_id as userId from t_order";
//        BeanListHandler<Order> handler = new BeanListHandler<>(Order.class);
//        List<Order> orders = runner.query(conn, sql, handler);
//        JDBCUtils.closeSource(conn);
//        return orders;
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "select order_id as orderId,create_time as createTime,price,status,user_id as userId from t_order";
        BeanListHandler<Order> handler = new BeanListHandler<>(Order.class);
        List<Order> orders = null;
        try {
            orders = runner.query(conn, sql, handler);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        JDBCUtils.closeSource(conn);
        return orders;
    }

    @Override
    public void changeOrderStatus(String orderId, Integer status) {
//        Connection conn = JDBCUtils.getConnection();
//        String sql = "update t_order set status = ? where order_id = ?";
//        runner.update(conn,sql,status,orderId);
//        JDBCUtils.closeSource(conn);
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "update t_order set status = ? where order_id = ?";
        try {
            runner.update(conn,sql,status,orderId);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        JDBCUtils.closeSource(conn);

    }


}
