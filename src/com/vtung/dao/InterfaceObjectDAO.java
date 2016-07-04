/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtung.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author admin
 */
public interface InterfaceObjectDAO {
    int delete(Object ma) throws SQLException;
    int save(Object bean)throws SQLException;
    Vector get()throws SQLException;
    Object get(Object ma)throws SQLException;
    Vector search(String searchText)throws SQLException;
    void dispose()throws SQLException;
    
}
