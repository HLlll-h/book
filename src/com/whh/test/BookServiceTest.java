package com.whh.test;

import com.whh.bean.Book;
import com.whh.service.BookService;
import com.whh.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {
    BookServiceImpl bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        Book book = new Book(null,"service书测试","嘻哈",new BigDecimal(62),65,6,null);
        bookService.addBook(book);
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(24);
    }

    @Test
    public void updateBook() {
        Book book = new Book(1,"java从入门到放弃","国哥",new BigDecimal(80.00),9999,10,null);
        bookService.updateBook(book);
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(25));
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();
        for(Book book : books){
            System.out.println(book);
        }
    }

    @Test
    public void page(){
        System.out.println(bookService.page(2, 4));
    }

    @Test
    public void pageByPrice() {
        System.out.println(bookService.pageByPrice(1, 4, 0, 90));
    }
}