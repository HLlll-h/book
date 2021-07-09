package com.whh.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车
 */
public class Cart {

//    private Integer totalCount;//总商品数量
//    private BigDecimal totalPrice;//商品总价
//    private List<CartItem> items = new ArrayList();//商品项集合
    /**
     * 商品集合项,Integer是商品id,CartItem是商品项
     */
    private Map<Integer,CartItem> items = new LinkedHashMap<>();

    /**
     *添加商品项
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        //先判断购物车中是否存在此商品,若存在则此商品数量+1,此商品总价更新;
        // 若不存在,则购物车加入此商品
//        for (CartItem item : items) {List集合判断方法
//            if(cartItem.getId() == item.getId()){//存在此商品
//
//            }
//        }
        CartItem item = items.get(cartItem.getId());
        if(item == null){//购物车中不存在此商品
            items.put(cartItem.getId(),cartItem);
        }else{//购物车中存在此商品
            item.setCount(item.getCount() + 1);//修改商品数量
            item.setTotalPrice(item.getTotalPrice().add(cartItem.getPrice()) );
//            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())) );//更新总金额
        }


    }

    /**
     *删除商品项
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车(删除全部)
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改商品数量
     * @param id
     * @param count
     */
    public void updateCount(Integer id,Integer count){
        //先查看购物车中是否有此商品,如果有 修改商品数量,更新总金额
        CartItem cartItem = items.get(id);
        if(cartItem != null){//购物车中有此商品
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())) );//更新总金额
        }
    }





    public Cart() {
    }

    public Cart(Integer totalCount, BigDecimal totalPrice, Map<Integer, CartItem> items) {
//        this.totalCount = totalCount;
//        this.totalPrice = totalPrice;
        this.items = items;
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for(CartItem value : items.values()){
            totalCount += value.getCount();
        }
        return totalCount;
    }

//    public void setTotalCount(Integer totalCount) {
//        this.totalCount = totalCount;
//    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for(CartItem value : items.values()){
            totalPrice = totalPrice.add(value.getTotalPrice());
        }

        return totalPrice;
    }

//    public void setTotalPrice(BigDecimal totalPrice) {
//        this.totalPrice = totalPrice;
//    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
