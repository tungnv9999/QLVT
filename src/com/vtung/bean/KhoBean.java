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
public class KhoBean implements CommonObject{
    String makho;
    String ten;
    String diachi;

    public KhoBean() {
    }

    public KhoBean(String makho, String ten, String diachi) {
        this.makho = makho;
        this.ten = ten;
        this.diachi = diachi;
    }

    public String getMakho() {
        return makho;
    }

    public void setMakho(String makho) {
        this.makho = makho;
    }
@Override
    public String getTen() {
        return ten;
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

    @Override
    public String getId() {
        return makho;
    }
    
    
    
}
