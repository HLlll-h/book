package com.whh.service;

import com.whh.bean.Cart;
import com.whh.bean.Order;
import com.whh.bean.OrderItem;

import java.util.List;

public interface OrderService {

    /**
     * 生成订单(保存订单和保存订单项)
     * @param cart
     * @param userId
     * @return
     */
    String createOrder(Cart cart,Integer userId);

    /**
     * 查看我的订单
     * @param userId
     * @return
     */
    List<Order> showMyOrders(Integer userId);

    /**
     * 查看订单详情
     * @param orderId
     * @return
     */
    List<OrderItem> showOrderDetail(String orderId);

    /**
     * 查看所有订单(管理员)
     * @return
     */
    List<Order> showAllOrders();

    /**
     * 管理员发货
     * @param orderId
     */
    void sendOrder(String orderId);

    /**
     * 用户签收
     * @param orderId
     */
    void receiverOrder(String orderId);
}
