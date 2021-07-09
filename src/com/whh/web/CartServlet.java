package com.whh.web;

import com.google.gson.Gson;
import com.whh.bean.Book;
import com.whh.bean.Cart;
import com.whh.bean.CartItem;
import com.whh.service.impl.BookServiceImpl;
import com.whh.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {

    BookServiceImpl bsi = new BookServiceImpl();

    /**
     * 商品加入购物车
     *商品项加入数据库
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取商品id
        String id = req.getParameter("id");
        //调用bsi.queryBookById查询到此图书
        int i = WebUtils.parseInt(id, 1);
        Book book = bsi.queryBookById(i);
        //把图书信息,转化为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用cart.addItem()加入购物车商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);//存到session域中
        }
        cart.addItem(cartItem);
//        System.out.println(cart);

        //把最后一个添加的商品名称加到session域中
        req.getSession().setAttribute("lastName",cartItem.getName());
        //重定向到商品列表页面 如在第二页加入购物车就返回到第二页
        resp.sendRedirect(req.getHeader("Referer"));

    }


    /**
     * 删除购物车中商品项
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取要删除的商品id
        String id = req.getParameter("id");
        int i = WebUtils.parseInt(id, 1);
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.deleteItem(i);
        //重定向
        resp.sendRedirect(req.getHeader("Referer"));
    }


    /**
     * 清空购物车(全部删除)
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clearItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //清空购物车
        cart.clear();
        //重定向
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        int id = WebUtils.parseInt(req.getParameter("id"), 1);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //修改商品数量
        cart.updateCount(id,count);
        //重定向
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     *使用AJax请求替换加入购物车部分addItem()
     * 请求转发(重定向)———>AJax局部更新页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取商品id
        String id = req.getParameter("id");
        //调用bsi.queryBookById查询到此图书
        int i = WebUtils.parseInt(id, 1);
        Book book = bsi.queryBookById(i);
        //把图书信息,转化为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用cart.addItem()加入购物车商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);//存到session域中
        }
        cart.addItem(cartItem);
//        System.out.println(cart);

        //把最后一个添加的商品名称加到session域中
        req.getSession().setAttribute("lastName",cartItem.getName());
        //
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());

        Gson gson = new Gson();
        String resultMapJsonString = gson.toJson(resultMap);
        resp.getWriter().write(resultMapJsonString);
    }



}
