package com.whh.test;

import com.whh.bean.Cart;
import com.whh.bean.CartItem;
import com.whh.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    OrderServiceImpl osi = new OrderServiceImpl();

    @Test
    public void createOrder() {

        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"时间简史",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"时间不简史",1,new BigDecimal(1000),new BigDecimal(1000)));

        System.out.println(osi.createOrder(cart, 1));
    }
}