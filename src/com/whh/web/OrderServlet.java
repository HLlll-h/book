package com.whh.web;

import com.whh.bean.Cart;
import com.whh.bean.Order;
import com.whh.bean.OrderItem;
import com.whh.bean.User;
import com.whh.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {

    OrderServiceImpl osi = new OrderServiceImpl();


    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取登录的User对象
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {//若用户未登录,则需返回登录
            req.getRequestDispatcher("pages/user/login.jsp").forward(req, resp);
            return;
        }
        //获取UserId
        Integer userId = loginUser.getId();
        //生成订单
        String orderId = osi.createOrder(cart, userId);

//        req.setAttribute("orderId", orderId);
        req.getSession().setAttribute("orderId", orderId);
        //请求转发方式不支持request域
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
        //使用请求重定向解决表单重复提交
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }


    /**
     * 查看我的订单
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showMyOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取UserId
        User loginUser = (User) req.getSession().getAttribute("user");
        Integer userId = loginUser.getId();
        //根据userid查询该用户的订单信息
        List<Order> orders = osi.showMyOrders(userId);
        //将orders保存到request域中
        req.setAttribute("orders", orders);
        //请求转发到我的订单页面
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }

    /**
     * 查看订单详情(根据订单id)
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取orderId
        String orderId = req.getParameter("orderId");
        //查询该订单的订单详情
        List<OrderItem> orderItems = osi.showOrderDetail(orderId);
        //将查询到的订单详情放到request域中
        req.setAttribute("orderItems", orderItems);
        //请求转发
        req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req, resp);
    }

    /**
     * 查看所有订单(后台管理)
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showAllOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查看所有订单
        List<Order> orders = osi.showAllOrders();
        //将orders保存到request域中
        req.setAttribute("orders", orders);
        //请求转发到订单页面
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

    /**
     * 管理员发货,status从0改为1
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取orderId
        String orderId = req.getParameter("orderId");
        //修改数据库中该订单的状态为1
        osi.sendOrder(orderId);
        //重定向到所有订单页面
        resp.sendRedirect(req.getContextPath() + "/orderServlet?action=showAllOrder");
//        System.out.println(req.getContextPath());
    }

    /**
     * 用户签收订单,status从1改为2
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void receiverOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取orderId
        String orderId = req.getParameter("orderId");
        //修改数据库中该订单的状态为2
        osi.receiverOrder(orderId);
        //重定向到我的订单页面
        resp.sendRedirect(req.getContextPath() + "/orderServlet?action=showMyOrder");
    }
}
