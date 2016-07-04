/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtung.bean;

/**
 *
 * @author admin
 */
public class AccountBean {
    String username;
    String hoTen;
    String role;
    String loginname;

    public AccountBean(String username, String hoTen, String role) {
        this.username = username;
        this.hoTen = hoTen;
        this.role = role;
    }
    
    public AccountBean(String username, String hoTen, String role, String loginname) {
        this(username,hoTen,role);
        this.loginname = loginname;
    }

    public AccountBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
    
    
}
