package com.whh.web;

import com.whh.bean.Book;
import com.whh.bean.Page;
import com.whh.service.impl.BookServiceImpl;
import com.whh.utils.WebUtils;
import org.junit.runner.Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class BookServlet extends BaseServlet{

    BookServiceImpl bsi = new BookServiceImpl();

    public void add(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取请求参数
//        String name = req.getParameter("name");
//        String author = req.getParameter("author");
//        String price = req.getParameter("price
//        String stock = req.getParameter(");
////        String sales = req.getParameter("sales");"stock");
        //以后使用beanutils工具类获取请求参数,可直接将参数注入到bean对象中
        Book book = WebUtils.copyParamToBean(req, new Book());
        //2.调用addBook保存图书
        bsi.addBook(book);
        //3.请求转发,跳转列表页面
        //但是用请求转发有一个bug,F5之后会再添加一次  应该使用请求重定向
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);

    }
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取删除对应的id
        String id = req.getParameter("id");
        //2.将String型id转为int型id
        int i = Integer.parseInt(id);
        //3.删除id对应的图书
        bsi.deleteBookById(i);
        //4.重定向图书列表
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));

    }
    public void update(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.以后使用beanutils工具类获取请求参数,可直接将参数注入到bean对象中
        //注意这里获取的请求参数没id,需在jsp页面中声明
        Book book = WebUtils.copyParamToBean(req, new Book());
        //2.修改
        bsi.updateBook(book);
        //3.重定向图书列表(从数据库刷新)
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));

    }

    public void getBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //用于回显
        //1.获取请求图书
        String id = req.getParameter("id");
        //2.将String型id转为int型id
        int i = Integer.parseInt(id);
        //3.查询id对应的图书
        Book book = bsi.queryBookById(i);
        //4.将该图书保存到request域中
        req.setAttribute("book",book);
        //5.请求转发
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    //展示全部数据,无分页 一条到底
    public void list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.通过BookServiceImpl查询全部图书
        List<Book> books = bsi.queryBooks();
        //2.把查询的全部图书保存到request域中
        req.setAttribute("books",books);
        //3.请求转发到/pages/manager/book_manager.jsp页面(跳转到此页面)
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    /**
     * 分页
     * @param req
     * @param resp
     * @throws Exception
     */
    public void page(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取请求参数pageNo(当前页码)和pageSize(当前页的条数)
        //把请求参数转为int型,没请求参数则为默认值1和4
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用bsi.page(pageNo,pageSize)
        Page<Book> page = bsi.page(pageNo, pageSize);
        //3.保存page对象到Request域中
        req.setAttribute("page",page);
        //4.请求转发到/pages/manager/book_manager.jsp页面(跳转到此页面)
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

}
