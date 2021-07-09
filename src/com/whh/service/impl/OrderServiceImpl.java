package com.whh.service.impl;

import com.whh.bean.*;
import com.whh.dao.impl.OrderDaoImpl;
import com.whh.dao.impl.OrderItemDaoImpl;
import com.whh.service.OrderService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class OrderServiceImpl implements OrderService {

    OrderDaoImpl odi = new OrderDaoImpl();
    OrderItemDaoImpl oid = new OrderItemDaoImpl();
    BookServiceImpl bsi = new BookServiceImpl();


    @Override
    public String createOrder(Cart cart, Integer userId) {

        String orderId = System.currentTimeMillis() + "" + userId;//订单号,唯一性 用时间戳
        // 创建一个订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        // 保存订单
        odi.saveOrder(order);
        // 遍历购物车中的每一个商品项转换成为订单项保存到数据库中
        for(Map.Entry<Integer,CartItem> entry : cart.getItems().entrySet()){
            //获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
            //转换为每一个订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //保存订单到数据库
            oid.saveOrderItem(orderItem);

            //更新购物车中的库存和销量
            Book book = bsi.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bsi.updateBook(book);
        }

        cart.clear();//购物车商品结算完清空购物车


        return orderId;
    }


    @Override
    public List<Order> showMyOrders(Integer userId) {
        List<Order> orders = odi.queryOrderByUserId(userId);
        return orders;
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        List<OrderItem> orderItems = oid.queryOrderItemsByOrderId(orderId);
        return orderItems;
    }

    @Override
    public List<Order> showAllOrders() {
        List<Order> orders = odi.queryOrders();
        return orders;
    }

    @Override
    public void sendOrder(String orderId) {
        odi.changeOrderStatus(orderId,1);
    }

    @Override
    public void receiverOrder(String orderId) {
        odi.changeOrderStatus(orderId,2);
    }


}
