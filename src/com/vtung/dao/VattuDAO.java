/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtung.dao;

import com.vtung.bean.AccountBean;
import com.vtung.bean.VattuBean;
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
public class VattuDAO implements InterfaceObjectDAO{

    public static final String SQL_GET_VATTUS = "SELECT * FROM view_DSVattu";
    public static final String SQL_GET_VATTU_ID = "{CALL sp_GetVattuById(?)}";
    public static final String SQL_GET_VATTU_NAME = "{CALL sp_GetVattuByName(?)}";
    
    public static final String SQL_SAVE_VATTU = "{CALL sp_SaveVattu(?,?,?,?)}";

    public static final String SQL_DELETE_VATTU = "{? = CALL sp_XoaVattu(?)}";
    public static final String SQL_SEARCH_VATTU = "{CALL sp_TimKiemVattu(?)}";
    Connection conn = null;
    CallableStatement cs = null;
    Statement st = null;
    ResultSet rs = null;

    public List<VattuBean> getVattus() throws SQLException {
        List<VattuBean> list = new ArrayList<VattuBean>();
        try {
            conn = MyJDBC.instance.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(SQL_GET_VATTUS);

            while (rs.next()) {
                //Retrieve by column name
                String mavt = rs.getString(1);//getInt("MAVN");
                String ten = rs.getString(2);
                String dvt = rs.getString(3);

                VattuBean bean = new VattuBean();
                bean.setMavt(mavt);
                bean.setTenvt(ten);
                bean.setDvt(dvt);

                list.add(bean);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            dispose();
        }
        return list;
    }

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
     * @param mavt
     * @return
     * @throws SQLException
     */
    @Override
    public VattuBean get(Object mavt) throws SQLException {
        try {
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_GET_VATTU_ID);
            cs.setString(1,(String) mavt);
            rs = cs.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                String ten = rs.getString(2);
                String dvt = rs.getString(3);

                VattuBean bean = new VattuBean((String)mavt,ten,dvt);
                dispose();
                return bean;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            dispose();
        }
        return null;
    }
    public VattuBean getByTen(String tenvt) throws SQLException {
        try {
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_GET_VATTU_NAME);
            cs.setString(1,tenvt);
            rs = cs.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                String mavt = rs.getString(1);
                String ten = rs.getString(2);
                String dvt = rs.getString(3);

                VattuBean bean = new VattuBean(mavt,ten,dvt);
                dispose();
                
                return bean;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            dispose();
        }
        return null;
    }
    

    /**
     *
     * @return
     * @throws SQLException
     */
    @Override
    public Vector get() throws SQLException {
        try {
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_GET_VATTUS);

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

    /**
     * *
     * Luu nhan vien vao DB. Nếu nhân viên đã tồn tại thì UPDATE trả về 1. Nếu
     * nhân viên chưa tồn tại thì INSERT trả về 0 Câu truy vấn trả về loại
     * action INSERT/UPDATE thông qua biến truyền vào @RETURN
     *
     * @param ob
     * @param bean
     * @return
     * @throws SQLException
     */
    @Override
    public int save(Object ob) throws SQLException {
        try {
            VattuBean bean = (VattuBean) ob;
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_SAVE_VATTU);
            cs.setString(1, bean.getMavt());
            cs.setString(2, bean.getTenvt());
            cs.setString(3, bean.getDvt());
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
    public int delete(Object mavt) throws SQLException{
        conn = MyJDBC.instance.getConnection();

        cs = conn.prepareCall(SQL_DELETE_VATTU);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setString(2,(String) mavt);
        try {
            cs.execute();
            return cs.getInt(1);
        } catch (SQLException e) {
            throw e;
        }finally{
            dispose();
        }
    }

    @Override
    public Vector search(String searchText) throws SQLException{
         try {
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_SEARCH_VATTU);
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
