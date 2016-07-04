/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtung.dao;

import com.vtung.bean.AccountBean;
import com.vtung.exception.DupplicateLoginException;
import com.vtung.exception.DupplicateUserException;
import com.vtung.utils.MyJDBC;
import com.vtung.exception.NonLoginException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author admin
 */
public class AccountDAO {

    public static final String SQL_LOGIN = "{CALL sp_DangNhap(?)}";//"EXEC sp_DangNhap :loginname";
    public static final String SQL_CHANGE_PASSWORD = "{CALL sp_password (NULL,?,?)}";
    public static final String SQL_GET_ROLE = "{CALL sp_GetUserRole (?)}";
    public static final String SQL_GET_LOGIN = "{CALL sp_GetLogin (?)}";
    public static final String SQL_SIGNUP = "{? = CALL sp_DangKi (?,?,?,?) }";
    public static final String SQL_DELETE_ACCOUNT = "{CALL sp_XoaDangKi (?) }";
    
    public static final String SQL_BACKUP_HISTORY = "SELECT * FROM view_BackupHistory";
    public static final String SQL_BACKUP = "{CALL sp_Backup}";
    Connection conn = null;
    CallableStatement cs = null;
    ResultSet rs = null;

    public AccountBean login(String username, String password) throws SQLException {

        try {
            conn = MyJDBC.instance.getConnection(username, password);
            cs = conn.prepareCall(SQL_LOGIN);

            cs.setString(1, username);
            rs = cs.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                String user = rs.getString("USERNAME");
                String hoten = rs.getString("HOTEN");
                String role = rs.getString("ROLENAME");

                AccountBean accountBean = new AccountBean(user, hoten, role);
                accountBean.setLoginname(username);
                dispose();
                return accountBean;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            dispose();
        }
        throw new SQLException();
    }
    
    public AccountBean getLogin(int username) throws SQLException, NonLoginException {

        try {
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_GET_LOGIN);

            cs.setString(1, username+"");
            rs = cs.executeQuery();

            while (rs.next()) {
                String loginname = rs.getString(1); 
                String rolename = rs.getString(2); 
                AccountBean bean = new AccountBean();
                bean.setRole(rolename);
                bean.setLoginname(loginname);
                return bean;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            dispose();
        }
        throw new NonLoginException();
    }
    
    public void signUp(String loginname,String password,int username,String role) throws SQLException, DupplicateLoginException, DupplicateUserException {

        try {
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_SIGNUP);
            cs.registerOutParameter(1, Types.INTEGER);
            
            cs.setString(2, loginname+"");
            cs.setString(3, password+"");
            cs.setString(4, username+"");
            cs.setString(5, role+"");
            cs.execute();
           
            int retr = cs.getInt(1);
            switch (retr)
            {
                case 1:
                    throw new DupplicateLoginException();
                case 2:
                    throw new DupplicateUserException();
                      
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            dispose();
        }
    }
    
    public void deleteAccount(String loginname) throws SQLException {

        try {
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_DELETE_ACCOUNT);
            cs.setString(1, loginname+"");
            cs.execute();
           
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            dispose();
        }
    }

    public void changePassword(String username, String password) throws SQLException {
        try {
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_CHANGE_PASSWORD);
            cs.setString(2, username);
            cs.setString(1, password);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            dispose();
            
            throw e;
        } finally {
            dispose();
        }
    }
    
    

    public List getRole(String username) throws SQLException {

        try {
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_GET_ROLE);
            cs.setString(1, username);
            rs = cs.executeQuery();
            List<String> result = new ArrayList();
            while (rs.next()) {
                String role = rs.getString(1);
                result.add(role);
            }
            dispose();
            return result;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            dispose();
        }
    }

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
    
    
    //BACKUP_RESTORE
    
    public Vector getBackupHistory() throws SQLException {
        try {
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_BACKUP_HISTORY);

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
    
    public void backup() throws SQLException
    {
        try {
            conn = MyJDBC.instance.getConnection();
            cs = conn.prepareCall(SQL_BACKUP);
            cs.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            dispose();
        }
    }
    
    public void restore(int position) throws SQLException
    {
        String restore = " ALTER DATABASE "+ MyJDBC.DATABASE+ " SET SINGLE_USER WITH ROLLBACK IMMEDIATE;"
                
                +"RESTORE DATABASE "+MyJDBC.DATABASE+" FROM DEVICE_QLVT WITH FILE="+position+",REPLACE,RECOVERY;"
                + "RESTORE DATABASE "+MyJDBC.DATABASE+" WITH RECOVERY;"
                
                +"ALTER DATABASE "+MyJDBC.DATABASE+" SET MULTI_USER;";
        
        try {
            conn = MyJDBC.instance.getConnection();
            Statement st = conn.createStatement();
            int x = st.executeUpdate(restore);
            System.out.println(""+x);
        } catch (SQLException e) {
            throw e;
        } finally {
            dispose();
        }
    }
}
