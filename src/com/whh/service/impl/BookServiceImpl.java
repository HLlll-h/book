package com.whh.service.impl;

import com.whh.bean.Book;
import com.whh.bean.Page;
import com.whh.dao.impl.BookDaoImpl;
import com.whh.service.BookService;
import com.whh.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {

    BookDaoImpl dao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        dao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        dao.deleteBookId(id);
    }

    @Override
    public void updateBook(Book book) {
        dao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        Book book = dao.queryBookById(id);
        return book;
    }

    @Override
    public List<Book> queryBooks() {
        List<Book> books = dao.queryBooks();
        return books;
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();

        page.setPageNo(pageNo);//设置当前页码
        page.setPageSize(pageSize);//设置当前页码条数
        Integer pageTotalCount = dao.queryForPageTotalCount();//获取总条数
        page.setPageTotalCount(pageTotalCount);//设置总条数
        Integer pageTotal = pageTotalCount / pageSize;//获取总页码
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);//设置总页码

//        page.setPageNo(pageNo);//设置当前页码(和有效边界检查)

        //求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        //求当前页数据
        List<Book> items = dao.queryForPageItems(begin, pageSize);
        page.setItems(items);//设置当前页数据

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int minPrice, int maxPrice) {
        Page<Book> page = new Page<>();

        page.setPageNo(pageNo);//设置当前页码
        page.setPageSize(pageSize);//设置当前页码条数
        Integer queryForPageTotalCountByPrice =
                dao.queryForPageTotalCountByPrice(minPrice, maxPrice);//获取这个价格区间的总条数
        page.setPageTotalCount(queryForPageTotalCountByPrice);//设置这个价格区间的总条数
        Integer pageTotal = queryForPageTotalCountByPrice / pageSize;//获取总页码
        if(queryForPageTotalCountByPrice % pageSize > 0){
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);//设置总页码

//        page.setPageNo(pageNo);//设置当前页码(和有效边界检查)

        //求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        //求当前页数据
        List<Book> items = dao.queryForPageItemsByPrice(begin,pageSize,minPrice,maxPrice);
        page.setItems(items);//设置当前页数据

        return page;
    }


}



