/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtung.dao;

import com.vtung.bean.AccountBean;
import com.vtung.bean.KhoBean;
import com.vtung.utils.Constrains;
import com.vtung.utils.MyJDBC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author admin
 */
public class KhoDAO implements InterfaceObjectDAO{

    public static final String SQL_GET_KHOS = "SELECT * FROM view_DSKho";
    public static final String SQL_GET_KHO = "{CALL sp_GetKho(?)}";
    public static final String SQL_SAVE_KHO = "{CALL sp_SaveKho(?,?,?,?)}";

    public static final String SQL_DELETE_KHO = "{? = CALL sp_XoaKho(?)}";
    public static final String SQL_SEARCH_KHO = "{CALL sp_TimKiemKho(?)}";
    Connection conn = null;
    CallableStatement cs = null;
    Statement st = null;
    ResultSet rs = null;

    

    @Override
    public void dispose() throws SQLException {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new SQLException();
            }
        }
        if (cs != null) {
            try {
                cs.close();
            } catch (SQLException e) {
                throw new SQLException();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new SQLException();
            }
        }
    }

    /**
     *
     * @param ma
     * @return
     * @throws SQLException
     */
    @Override
    public KhoBean get(Object ma) throws SQLException {
        try {
            String makho =(String) ma;
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_GET_KHO);
            cs.setString(1,(String) makho);
            rs = cs.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                String ten = rs.getString(2);
                String diachi = rs.getString(3);

                KhoBean bean = new KhoBean(makho,ten,diachi);
                dispose();
                return bean;
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            dispose();
        }
    }

    @Override
    public Vector get() throws SQLException {
        try {
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_GET_KHOS);

            rs = cs.executeQuery();
            Vector data = new Vector();
            ResultSetMetaData metaData = rs.getMetaData();
            int columns = metaData.getColumnCount();
            while (rs.next()) {
                Vector row = new Vector(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));
                }
                data.addElement(row);
            }
            return data;
        } catch (SQLException e) {
            throw e;
        } finally {
            dispose();
        }
    }

    
    @Override
    public int save(Object bean) throws SQLException {
        try {
            KhoBean kho = (KhoBean) bean;
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_SAVE_KHO);
            cs.setString(1, kho.getMakho());
            cs.setString(2, kho.getTen());
            cs.setString(3, kho.getDiachi());
           
            cs.registerOutParameter(4, Types.INTEGER);

            cs.execute();
            
            int retr = cs.getInt(4);
            return retr;
        } catch (SQLException e) {
            throw e;
        } finally {
            dispose();
        }
    }

    @Override
    public int delete(Object ma) throws SQLException{
        String makho = (String) ma;
        conn = MyJDBC.instance.getConnection();

        cs = conn.prepareCall(SQL_DELETE_KHO);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setString(2, makho);
        try {
             cs.execute();
             return cs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            dispose();
        }
        return Constrains.CODE_SQL_FAIL;
    }

    @Override
    public Vector search(String searchText) throws SQLException{
         try {
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_SEARCH_KHO);
            cs.setString(1, searchText);
            
            rs = cs.executeQuery();
            Vector data = new Vector();
            ResultSetMetaData metaData = rs.getMetaData();
            int columns = metaData.getColumnCount();
            while (rs.next()) {
                Vector row = new Vector(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));
                }
                data.addElement(row);
            }
            return data;
        } catch (SQLException e) {
            throw e;
        } finally {
            dispose();
        }
        }

}
