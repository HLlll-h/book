package com.whh.test;

import com.whh.bean.Book;
import com.whh.dao.impl.BookDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    BookDaoImpl dao = new BookDaoImpl();

    @Test
    public void addBook() {
        Book book = new Book(null,"测试书名","哈午",new BigDecimal(80),55,80,null);
        System.out.println(dao.addBook(book));
    }

    @Test
    public void deleteBookId() {
        dao.deleteBookId(22);
    }

    @Test
    public void updateBook() {
        Book book = new Book(22,"测试书名2","哈0午",new BigDecimal(800),505,800,null);
        System.out.println(dao.updateBook(book));
    }

    @Test
    public void queryBookById() {

        System.out.println(dao.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        List<Book> books = dao.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCount(){
        System.out.println(dao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        List<Book> books = dao.queryForPageItems(4, 4);
        for(Book book : books){
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(dao.queryForPageTotalCountByPrice(80,90));
    }

    @Test
    public void queryForPageItemsByPrice() {
        List<Book> books = dao.queryForPageItemsByPrice(2, 4, 50, 90);
        for(Book book : books){
            System.out.println(book);
        }
    }

}