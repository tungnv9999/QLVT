/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtung.bean;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class PhatSinhBean implements CommonObject{
    String maphieu;
    Date ngay;
    String loai;
    String hotenKH;
    float thanhtien;
    int manv;
    String tennv;
    String makho;
    String tenkho;
    
    
    
    public PhatSinhBean() {
    }

    public PhatSinhBean(String maphieu, Date ngay, String loai, String hotenKH, float thanhtien, int manv, String tennv, String makho, String tenkho) {
        this.maphieu = maphieu;
        this.ngay = ngay;
        this.loai = loai;
        this.hotenKH = hotenKH;
        this.thanhtien = thanhtien;
        this.manv = manv;
        this.tennv = tennv;
        this.makho = makho;
        this.tenkho = tenkho;
    }

    

    public String getMaphieu() {
        return maphieu;
    }

    public void setMaphieu(String maphieu) {
        this.maphieu = maphieu;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getHotenKH() {
        return hotenKH;
    }

    public void setHotenKH(String hotenKH) {
        this.hotenKH = hotenKH;
    }

    public float getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(float thanhtien) {
        this.thanhtien = thanhtien;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getMakho() {
        return makho;
    }

    public void setMakho(String makho) {
        this.makho = makho;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getTenkho() {
        return tenkho;
    }

    public void setTenkho(String tenkho) {
        this.tenkho = tenkho;
    }

    @Override
    public String getTen() {
        return toString();
    }

    @Override
    public String getId() {
       return maphieu;
    }
    
    
}
