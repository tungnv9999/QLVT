/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtung.dao;

import com.vtung.bean.AccountBean;
import com.vtung.bean.NhanvienBean;
import static com.vtung.dao.AccountDAO.SQL_LOGIN;
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
public class EmployeeDAO implements InterfaceObjectDAO {

    public static final String SQL_GET_EMPLOYEES = "SELECT * FROM view_DSNhanvien";
    public static final String SQL_GET_EMPLOYEE = "{CALL sp_GetNhanVien(?)}";
    public static final String SQL_SAVE_EMPLOYEE = "{CALL sp_SaveNhanVien(?,?,?,?,?,?,?,?)}";

    public static final String SQL_GET_FULL_EMPLOYEES = "SELECT * FROM view_DSNhanvienDayDu";
    public static final String SQL_DELETE_EMPLOYEE = "{? = CALL sp_XoaNhanVien(?)}";
    public static final String SQL_SEARCH_EMPLOYEE = "{CALL sp_TimKiemNhanVien(?)}";
    public static final String SQL_GET_EMPLOYEE_ID = "SELECT * FROM view_DSMaNhanvien";
    
    Connection conn = null;
    CallableStatement cs = null;
    Statement st = null;
    ResultSet rs = null;

    public List<NhanvienBean> getEmployees() throws SQLException {
        List<NhanvienBean> list = new ArrayList<NhanvienBean>();
        try {
            conn = MyJDBC.instance.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(SQL_GET_EMPLOYEES);

            while (rs.next()) {
                //Retrieve by column name
                int manv = rs.getInt(1);//getInt("MAVN");
                String ho = rs.getString("HO");
                String ten = rs.getString("TEN");

                NhanvienBean bean = new NhanvienBean();
                bean.setManv(manv);
                bean.setHo(ho);
                bean.setTen(ten);

                list.add(bean);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            dispose();
        }
        return list;
    }

    public List getMaNhanvien() throws SQLException
    {
         List<Integer> list = new ArrayList<Integer>();
        try {
            conn = MyJDBC.instance.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(SQL_GET_EMPLOYEE_ID);

            while (rs.next()) {
                
                list.add( rs.getInt(1));
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

    @Override
    public NhanvienBean get(Object manv) throws SQLException {
        try {
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_GET_EMPLOYEE);
            cs.setInt(1, Integer.parseInt((String)manv));
            rs = cs.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                String ghichu = rs.getString("GHICHU");
                String ho = rs.getString("HO");
                String ten = rs.getString("TEN");
                String diachi = rs.getString("DIACHI");
                Date ngaysinh = rs.getDate("NGAYSINH");
                Float luong = rs.getFloat("LUONG");

                NhanvienBean bean = new NhanvienBean((Integer.parseInt((String)manv)), ho, ten, diachi, ngaysinh, luong, ghichu);

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

    @Override
    public Vector get() throws SQLException {
        try {
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_GET_FULL_EMPLOYEES);

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
            NhanvienBean bean = (NhanvienBean) ob;
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_SAVE_EMPLOYEE);
            cs.setInt(1, bean.getManv());
            cs.setString(2, bean.getHo());
            cs.setString(3, bean.getTen());
            cs.setString(4, bean.getDiachi());
            cs.setDate(5, new java.sql.Date(bean.getNgaysinh().getTime()));
            cs.setFloat(6, bean.getLuong());
            cs.setString(7, bean.getGhichu());
            cs.registerOutParameter(8, Types.INTEGER);

            cs.execute();

            int retr = cs.getInt(8);
            return retr;
        } catch (SQLException e) {
            throw e;
        } finally {
            dispose();
        }
    }

    @Override
    public int delete(Object manv) throws SQLException {
        conn = MyJDBC.instance.getConnection();

        cs = conn.prepareCall(SQL_DELETE_EMPLOYEE);
        cs.setInt(2, Integer.parseInt((String)manv));
        cs.registerOutParameter(1, Types.INTEGER);
        cs.execute();
        return cs.getInt(1);
    }

    @Override
    public Vector search(String searchText) throws SQLException {
        try {
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_SEARCH_EMPLOYEE);
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
