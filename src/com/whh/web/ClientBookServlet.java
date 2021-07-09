package com.whh.web;

import com.whh.bean.Book;
import com.whh.bean.Page;
import com.whh.service.impl.BookServiceImpl;
import com.whh.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ClientBookServlet extends BaseServlet{

    BookServiceImpl bsi = new BookServiceImpl();

    /**
     * 用户首页
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
        //4.请求转发到/pages/client/index.jsp页面(跳转到此页面)
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    /**
     * 按价格查询图书列表
     * +分页
     * @param req
     * @param resp
     * @throws Exception
     */
    public void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取请求参数pageNo(当前页码)和pageSize(当前页的条数)
        //把请求参数转为int型,没请求参数则为默认值1和4
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), 100000);
        //2.调用bsi.pageByPrice(pageNo,pageSize,minPrice,maxPrice)
        Page<Book> page = bsi.pageByPrice(pageNo, pageSize, min, max);
        //3.保存page对象到Request域中
        req.setAttribute("page",page);
        //4.请求转发到/pages/client/index.jsp页面(跳转到此页面)
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);

    }

}
