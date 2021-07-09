package com.whh.test;

import com.whh.bean.Order;
import com.whh.dao.OrderDao;
import com.whh.dao.impl.OrderDaoImpl;
import com.whh.utils.JDBCUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoTest {

    OrderDaoImpl dao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        Order order = new Order("123456", new Date(), new BigDecimal(10), 0, 1);
        dao.saveOrder(order);
    }

    @Test
    public void queryOrderByUserId() {
        List<Order> orders = dao.queryOrderByUserId(1);
        for(Order order : orders){
            System.out.println(order);
        }
    }

    @Test
    public void queryOrders() {
        List<Order> orders = dao.queryOrders();
        for(Order order : orders){
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus() {
        dao.changeOrderStatus("16247984630722",1);
    }


    @Test
    public void test() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "select * from t_order where user_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1,3);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            String orderId = rs.getString(1);
            int userId = rs.getInt(5);
            java.sql.Date date = rs.getDate(2);
            BigDecimal price = rs.getBigDecimal(3);
            int status = rs.getInt(4);
            Order order = new Order(orderId,date,price,status,userId);
//            System.out.println(orderId);
//            System.out.println(userId);
//            System.out.println(date);
            System.out.println(order);
        }
    }
}