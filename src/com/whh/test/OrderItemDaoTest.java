package com.whh.test;

import com.whh.bean.OrderItem;
import com.whh.dao.OrderItemDao;
import com.whh.dao.impl.OrderItemDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    OrderItemDaoImpl dao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        OrderItem orderItem = new OrderItem(null, "hah", 10, new BigDecimal(10), new BigDecimal(100), "123456");

        dao.saveOrderItem(orderItem);
    }

    @Test
    public void queryOrderItemsByOrderId() {
        List<OrderItem> orderItems = dao.queryOrderItemsByOrderId("16247186734351");
        System.out.println(orderItems);
    }

}