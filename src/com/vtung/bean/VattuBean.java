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
public class VattuBean implements CommonObject{
    String mavt;
    String tenvt;
    String dvt;

    public VattuBean() {
    
    }

    public VattuBean(String mavt, String tenvt, String dvt) {
        this.mavt = mavt;
        this.tenvt = tenvt;
        this.dvt = dvt;
    }

    public String getMavt() {
        return mavt;
    }

    public void setMavt(String mavt) {
        this.mavt = mavt;
    }
    
    public String getTenvt() {
        return tenvt;
    }

    public void setTenvt(String tenvt) {
        this.tenvt = tenvt;
    }

    public String getDvt() {
        return dvt;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }
    
    public String toString(){
        return tenvt;
    }

    @Override
    public String getTen() {
        return tenvt;
    }

    @Override
    public String getId() {
        return mavt;
    }
    
}
