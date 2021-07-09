package com.whh.dao;
//只做了生成订单的功能

import com.whh.bean.Order;
import com.whh.bean.User;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {

    /**
     * 保存订单
     * @param order
     * @return
     */
    int saveOrder(Order order);

    /**
     * 查看我的订单,根据userId查询
     * @param userId
     * @return
     */
    List<Order> queryOrderByUserId(Integer userId);

    /**
     * 查询所有订单
     * @return
     */
    List<Order> queryOrders();

    /**
     * 修改订单状态
     * 管理员把订单状态从0改1,用户从1改2
     * @param orderId
     * @param status
     */
    void changeOrderStatus(String orderId,Integer status) ;
}
