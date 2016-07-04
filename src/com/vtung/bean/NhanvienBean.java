/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtung.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author admin
 */
public class NhanvienBean implements CommonObject{
    private int manv;
     private String ho;
     private String ten;
     private String diachi;
     private Date ngaysinh;
     private float luong;
     private String ghichu;

    public NhanvienBean() {
    }

     
    public NhanvienBean(int manv, String ho, String ten, String diachi, Date ngaysinh, float luong, String ghichu) {
        this.manv = manv;
        this.ho = ho;
        this.ten = ten;
        this.diachi = diachi;
        this.ngaysinh = ngaysinh;
        this.luong = luong;
        this.ghichu = ghichu;
    }

    public NhanvienBean(NhanvienBean bean) {
        this.manv = bean.manv;
        this.ho = bean.ho;
        this.ten = bean.ten;
        this.diachi = bean.diachi;
        this.ngaysinh = bean.ngaysinh;
        this.luong = bean.luong;
        this.ghichu = bean.ghichu;
    }
    
    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }
@Override
    public String getTen() {
        return ho+" "+ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public float getLuong() {
        return luong;
    }

    public void setLuong(float luong) {
        this.luong = luong;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    @Override
    public String getId() {
       return manv+"";
    }
    
    

    
     
}
