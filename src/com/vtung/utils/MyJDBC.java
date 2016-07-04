/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtung.utils;

import com.vtung.bean.AccountBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author admin
 */
public class MyJDBC {

    public static final MyJDBC instance = new MyJDBC();

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String DB_URL = "jdbc:sqlserver://ADMINB735:1433;databaseName="+MyJDBC.DATABASE;

    public static final String DATABASE = "QLVT";
    
    //  Database credentials
    private String USER ="vantung";
    private String PASS ="123";

    
    private MyJDBC() {
        try {
            Class.forName(JDBC_DRIVER);
            
        } catch (ClassNotFoundException e) {
             e.printStackTrace();
        }
    }

    public ResultSet excuteQuery(String sql, String username, String pass) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, username, pass);
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }
    
    public Connection getConnection() throws SQLException 
    {
        return  DriverManager.getConnection(DB_URL , USER, PASS);
    }
    
    public Connection getConnection(String username, String password) throws SQLException 
    {
        Connection connection = DriverManager.getConnection(DB_URL , username, password);
        USER = username;
        PASS = password;
        return connection;
    }
    
    public void dispose()
    {
        USER = null;
        PASS = null;
    }
}
