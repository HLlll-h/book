package com.whh.dao;

import com.whh.bean.Book;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BookDao {

    /**
     * 添加图书
     * @param book
     * @return
     */
    int addBook(Book book);

    /**
     * 根据id删除图书
     * @param id
     * @return
     */
    int deleteBookId(Integer id);

    /**
     * 根据id修改图书
     * @param book
     * @return
     */
    int updateBook(Book book);

    /**
     * 根据id查询图书
     * @param id
     * @return
     */
    Book queryBookById(Integer id);

    /**
     * 查询全部图书
     * @return
     */
    List<Book> queryBooks();

    /**
     * 查询总的图书数
     * @return
     * @throws Exception
     */
    Integer queryForPageTotalCount();

    /**
     *求当前页数据
     * @param begin
     * @param pageSize
     * @return
     */
    List<Book> queryForPageItems(int begin,int pageSize);

    /**
     * 查询价格区间的总记录数
     * @param minPrice
     * @param maxPrice
     * @return
     */
    Integer queryForPageTotalCountByPrice(int minPrice,int maxPrice);


    /**
     * 根据价格区间查询图书
     * @param minPrice
     * @param maxPrice
     * @return
     * @throws Exception
     */
    List<Book> queryForPageItemsByPrice(int begin,int pageSize,int minPrice, int maxPrice);


}


