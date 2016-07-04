/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtung.dao;

import com.vtung.bean.AccountBean;
import com.vtung.bean.CTPhatSinhBean;
import com.vtung.bean.CTPhatSinhList;
import com.vtung.bean.PhatSinhBean;
import static com.vtung.dao.AccountDAO.SQL_LOGIN;
import com.vtung.utils.Constrains;
import com.vtung.utils.MyJDBC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.sax.SAXResult;
import org.xml.sax.ContentHandler;

/**
 *
 * @author admin
 */
public class NhapxuatDAO implements InterfaceObjectDAO {

    public static final String SQL_GET_NHAPXUATS = "SELECT * FROM view_DSNhapxuat";
    public static final String SQL_GET_NHAPXUAT = "{CALL sp_GetNhapxuat(?,?,?,?,?,?,?,?,?)}";
    public static final String SQL_SAVE_NHAPXUAT = "{CALL sp_SaveNhapxuat(?,?,?,?,?,?,?,?)}";

    public static final String SQL_DELETE_NHAPXUAT = "{CALL sp_XoaPhatsinh(?)}";
    public static final String SQL_SEARCH_NHAPXUAT = "{CALL sp_TimKiemNhapxuat(?)}";

    public static final String SQL_SAVE_PHATSINH = "{? = CALL sp_SavePhatSinhXML(?,?,?,?,?,?,?,?)}";
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
     * @param phieu
     * @return
     * @throws SQLException
     */
    @Override
    public List get(Object phieu) throws SQLException {
        try {
            PhatSinhBean bean = (PhatSinhBean) phieu;
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_GET_NHAPXUAT);
            cs.setString(1, bean.getMaphieu().trim());
            cs.registerOutParameter(2, Types.DATE);
            cs.registerOutParameter(3, Types.NCHAR);
            cs.registerOutParameter(4, Types.NVARCHAR);
            cs.registerOutParameter(5, Types.FLOAT);
            cs.registerOutParameter(6, Types.INTEGER);
            cs.registerOutParameter(7, Types.NVARCHAR);

            cs.registerOutParameter(8, Types.NVARCHAR);
            cs.registerOutParameter(9, Types.NVARCHAR);

            rs = cs.executeQuery();
            //CHI TIET PHAT SINH
            String mavt, tenvt;
            int soluong;
            float dongia;
            CTPhatSinhBean ctps;
            List<CTPhatSinhBean> listCTPS = new ArrayList<>();
            while (rs.next()) {
                //Retrieve by column name
                mavt = rs.getString("MAVT");//getInt("MAVN");
                tenvt = rs.getString("TENVT");
                soluong = rs.getInt("SOLUONG");
                dongia = rs.getFloat("DONGIA");

                ctps = new CTPhatSinhBean(mavt, tenvt, soluong, dongia);
                listCTPS.add(ctps);
            }

            //Get Thông tin phiếu
            Date ngay = cs.getDate(2);
            String loai = cs.getString(3);
            String khachhang = cs.getString(4);
            Float thanhtien = cs.getFloat(5);

            int manv = cs.getInt(6);
            String tennv = cs.getString(7);
            String makho = cs.getString(8);
            String tenkho = cs.getString(9);

            bean.setNgay(ngay);
            bean.setHotenKH(khachhang);
            bean.setLoai(loai);
            bean.setMakho(makho);
            bean.setTenkho(tenkho);
            bean.setManv(manv);
            bean.setTennv(tennv);
            bean.setThanhtien(thanhtien);

            return listCTPS;
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
            cs = conn.prepareCall(SQL_GET_NHAPXUATS);

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
    public int save(PhatSinhBean bean, List<CTPhatSinhBean> ctPhatsinhList) throws SQLException {
        try {
            
            
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_SAVE_PHATSINH);

            //Init LIST
            CTPhatSinhList list = new CTPhatSinhList();
            list.setCtPhatSinhList(ctPhatsinhList);

            //Convert to XML
            JAXBContext jax = JAXBContext.newInstance(CTPhatSinhList.class);
            Marshaller marshaller = jax.createMarshaller();
            SQLXML ctpsXml = conn.createSQLXML();
            SAXResult saxResult = ctpsXml.setResult(SAXResult.class);
            ContentHandler contentHandler = saxResult.getHandler();
            marshaller.marshal(list, contentHandler);
            
           
            //Set Data for Statement.
            cs.registerOutParameter(1, Types.NVARCHAR);
            cs.setString(2, bean.getMaphieu());
            cs.setString(3, bean.getLoai());
            cs.setString(4, bean.getHotenKH());
            cs.setDate(5, bean.getNgay());
            cs.setFloat(6, bean.getThanhtien());
            cs.setInt(7, bean.getManv());
            cs.setString(8, bean.getMakho());
            cs.setSQLXML(9, ctpsXml);

            cs.execute();
            return Integer.parseInt(cs.getString(1).trim());
        } catch (SQLException e) {
            throw e;
        } catch (JAXBException ex) {
            Logger.getLogger(NhapxuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dispose();
        }
        return Constrains.CODE_SQL_FAIL;
    }

    @Override
    public int delete(Object maphieu) throws SQLException {
        conn = MyJDBC.instance.getConnection();

        cs = conn.prepareCall(SQL_DELETE_NHAPXUAT);
        cs.setString(1, (String) maphieu);
        try {
            cs.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            dispose();
        }
        return Constrains.CODE_SQL_FAIL;
    }

    @Override
    public Vector search(String searchText) throws SQLException {
        try {
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_SEARCH_NHAPXUAT);
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

    @Override
    public int save(Object bean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
