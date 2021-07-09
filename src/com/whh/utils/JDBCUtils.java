package com.whh.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() throws Exception {

        FileInputStream is = new FileInputStream(new File("D:\\idea-workspace\\JavaWeb\\10_book\\src\\druid.properties"));
        //还是全路径好哇，不会有问题  绝对路径

        Properties pros = new Properties();
        pros.load(is);

        DataSource source = DruidDataSourceFactory.createDataSource(pros);
        Connection conn = source.getConnection();

        return conn;

    }

    /**
     * 关闭资源
     */
    public static void closeSource(Connection conn, Statement ps, ResultSet rs) {
        DbUtils.closeQuietly(conn);
        DbUtils.closeQuietly(ps);
        DbUtils.closeQuietly(rs);
    }

    public static void closeSource(Connection conn) {
        DbUtils.closeQuietly(conn);
    }

}
