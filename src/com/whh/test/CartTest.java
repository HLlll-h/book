package com.whh.test;

import com.whh.bean.Cart;
import com.whh.bean.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    Cart cart = new Cart();


    @Test
    public void addItem() {
        cart.addItem(new CartItem(1,"时间简史",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"时间简史",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"时间不简史",1,new BigDecimal(1000),new BigDecimal(1000)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
//        cart.addItem(new CartItem(1,"时间简史",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"时间简史",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"时间不简史",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.deleteItem(2);
        System.out.println(cart);
    }

    @Test
    public void clear() {
//        cart.addItem(new CartItem(1,"时间简史",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"时间简史",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"时间不简史",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
//        cart.addItem(new CartItem(1,"时间简史",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"时间简史",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"时间不简史",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.updateCount(1,6);
        System.out.println(cart);
    }
}