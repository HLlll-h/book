package com.whh.dao;

import com.whh.bean.Order;
import com.whh.bean.OrderItem;

import java.util.List;

public interface OrderItemDao {

    /**
     * 保存订单项
     * @param orderItem
     * @return
     */
    int saveOrderItem(OrderItem orderItem);

    /**
     * 根据订单号查询该订单号的商品项
     * @param orderId
     * @return
     */
    List<OrderItem> queryOrderItemsByOrderId(String orderId);
}
