package com.whh.dao.impl;

import com.whh.bean.OrderItem;
import com.whh.dao.BaseDao;
import com.whh.dao.OrderItemDao;
import com.whh.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

    QueryRunner runner = new QueryRunner();


    @Override
    public int saveOrderItem(OrderItem orderItem) {
//        Connection conn = JDBCUtils.getConnection();
//        String sql = "insert into t_order_item(name,count,price,total_price,order_id) values(?,?,?,?,?)";
//        int i = runner.update(conn, sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
//        JDBCUtils.closeSource(conn);
//        return i;
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "insert into t_order_item(name,count,price,total_price,order_id) values(?,?,?,?,?)";
        int i = 0;
        try {
            i = runner.update(conn, sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        JDBCUtils.closeSource(conn);
        return i;
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
//        Connection conn = JDBCUtils.getConnection();
//        String sql = "select id,name,count,price,total_price as totalPrice,order_id as orderId from t_order_item where order_id = ?";
//        BeanListHandler<OrderItem> handler = new BeanListHandler<>(OrderItem.class);
//        List<OrderItem> orderItems = runner.query(conn, sql, handler, orderId);
//        JDBCUtils.closeSource(conn);
//        return orderItems;
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "select id,name,count,price,total_price as totalPrice,order_id as orderId from t_order_item where order_id = ?";
        BeanListHandler<OrderItem> handler = new BeanListHandler<>(OrderItem.class);
        List<OrderItem> orderItems = null;
        try {
            orderItems = runner.query(conn, sql, handler, orderId);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        JDBCUtils.closeSource(conn);
        return orderItems;
    }
}
