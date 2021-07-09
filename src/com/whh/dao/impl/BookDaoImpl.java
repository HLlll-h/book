package com.whh.dao.impl;

import com.whh.bean.Book;
import com.whh.dao.BaseDao;
import com.whh.dao.BookDao;
import com.whh.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {

    QueryRunner runner = new QueryRunner();


    @Override
    public int addBook(Book book) {
//        Connection conn = JDBCUtils.getConnection();
//        String sql = "insert into t_book(name,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
//        int i = runner.update(conn, sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImg_path());
//        JDBCUtils.closeSource(conn);
//        return i;//返回影响的行数
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "insert into t_book(name,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        int i = 0;
        try {
            i = runner.update(conn, sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImg_path());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        JDBCUtils.closeSource(conn);
        return i;//返回影响的行数
    }


    @Override
    public int deleteBookId(Integer id) {
//        Connection conn = JDBCUtils.getConnection();
//        String sql = "delete from t_book where id = ?";
//        runner.update(conn,sql,id);
//        JDBCUtils.closeSource(conn);
//        return 0;
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "delete from t_book where id = ?";
        try {
            runner.update(conn,sql,id);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        JDBCUtils.closeSource(conn);
        return 0;
    }

    @Override
    public int updateBook(Book book) {
//        Connection conn = JDBCUtils.getConnection();
//        String sql = "update t_book set name = ?,author = ?,price = ?,sales = ?,stock = ?,img_path = ? where id = ?";
//        int i = runner.update(conn, sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImg_path(), book.getId());
//        JDBCUtils.closeSource(conn);
//        return i;//返回被修改的行数
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "update t_book set name = ?,author = ?,price = ?,sales = ?,stock = ?,img_path = ? where id = ?";
        int i = 0;
        try {
            i = runner.update(conn, sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImg_path(), book.getId());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        JDBCUtils.closeSource(conn);
        return i;//返回被修改的行数
    }

    @Override
    public Book queryBookById(Integer id) {
//        Connection conn = JDBCUtils.getConnection();
//        String sql = "select * from t_book where id = ?";
//        BeanHandler<Book> handler = new BeanHandler<>(Book.class);
//        Book book = runner.query(conn, sql, handler, id);
//        JDBCUtils.closeSource(conn);
//        return book;
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "select * from t_book where id = ?";
        BeanHandler<Book> handler = new BeanHandler<>(Book.class);
        Book book = null;
        try {
            book = runner.query(conn, sql, handler, id);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        JDBCUtils.closeSource(conn);
        return book;
    }

    @Override
    public List<Book> queryBooks() {
//        Connection conn = JDBCUtils.getConnection();
//        String sql = "select id,name,author,price,sales,stock,img_path from t_book";
//        BeanListHandler<Book> handler = new BeanListHandler<>(Book.class);
//        List<Book> books = runner.query(conn, sql, handler);
//        JDBCUtils.closeSource(conn);
//        return books;
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "select id,name,author,price,sales,stock,img_path from t_book";
        BeanListHandler<Book> handler = new BeanListHandler<>(Book.class);
        List<Book> books = null;
        try {
            books = runner.query(conn, sql, handler);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        JDBCUtils.closeSource(conn);
        return books;
    }

    @Override
    public Integer queryForPageTotalCount() {
//        Connection conn = JDBCUtils.getConnection();
//        String sql = "select count(*) from t_book";
//        ScalarHandler handler = new ScalarHandler();
//        Number count = (Number) runner.query(conn, sql, handler);
//        int i = count.intValue();
//        JDBCUtils.closeSource(conn);
//        return i;
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "select count(*) from t_book";
        ScalarHandler handler = new ScalarHandler();
        Number count = null;
        try {
            count = (Number) runner.query(conn, sql, handler);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        int i = count.intValue();
        JDBCUtils.closeSource(conn);
        return i;
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
//        Connection conn = JDBCUtils.getConnection();
//        String sql = "select id,name,author,price,sales,stock,img_path from t_book limit ?,?";
//        BeanListHandler<Book> handler = new BeanListHandler<>(Book.class);
//        List<Book> books = runner.query(conn, sql, handler, begin, pageSize);
//        JDBCUtils.closeSource(conn);
//        return books;
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "select id,name,author,price,sales,stock,img_path from t_book limit ?,?";
        BeanListHandler<Book> handler = new BeanListHandler<>(Book.class);
        List<Book> books = null;
        try {
            books = runner.query(conn, sql, handler, begin, pageSize);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        JDBCUtils.closeSource(conn);
        return books;
    }


    public Integer queryForPageTotalCountByPrice(int minPrice,int maxPrice) {
//        Connection conn = JDBCUtils.getConnection();
//        String sql = "select count(*) from t_book where price >= ? and price <= ?";
//        ScalarHandler handler = new ScalarHandler();
//        Number countByPrice = (Number)runner.query(conn, sql, handler, minPrice, maxPrice);
//        int i = countByPrice.intValue();
//        JDBCUtils.closeSource(conn);
//        return i;
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "select count(*) from t_book where price >= ? and price <= ?";
        ScalarHandler handler = new ScalarHandler();
        Number countByPrice = null;
        try {
            countByPrice = (Number)runner.query(conn, sql, handler, minPrice, maxPrice);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        int i = countByPrice.intValue();
        JDBCUtils.closeSource(conn);
        return i;
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin,int pageSize,int minPrice, int maxPrice) {
//        Connection conn = JDBCUtils.getConnection();
//        String sql = "select id,name,author,price,sales,stock,img_path from t_book where price >= ? and price <= ? limit ?,?";
//        BeanListHandler<Book> handler = new BeanListHandler<Book>(Book.class);
//        List<Book> books = runner.query(conn, sql, handler, minPrice, maxPrice,(pageNo-1)*pageSize,pageSize);
//        JDBCUtils.closeSource(conn);
//        return books;
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "select id,name,author,price,sales,stock,img_path from t_book " +
                "where price >= ? and price <= ? order by price asc limit ?,?";

        BeanListHandler<Book> handler = new BeanListHandler<Book>(Book.class);
        List<Book> books = null;
        try {
            books = runner.query(conn, sql, handler, minPrice, maxPrice,begin,pageSize);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        JDBCUtils.closeSource(conn);
        return books;
    }





}
