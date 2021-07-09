package com.whh.service;

import com.whh.bean.Book;
import com.whh.bean.Page;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BookService {
    
    void addBook(Book book) throws Exception;

    void deleteBookById(Integer id);

    void updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();

    Page<Book> page(int pageNo,int pageSize);

    Page<Book> pageByPrice(int pageNo,int pageSize,int minPrice,int maxPrice) throws Exception;
}
