/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtung.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@XmlRootElement
public class CTPhatSinhBean {

    private String mavt;
    private String tenvt;
    private int soluong;
    private float dongia;

    public CTPhatSinhBean() {
        mavt = "";
        tenvt = "";
    }

    public CTPhatSinhBean(String mavt, String tenvt, int soluong, float dongia) {
        this.mavt = mavt;
        this.tenvt = tenvt;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public String getTenvt() {
        return tenvt;
    }

    public void setTenvt(String tenvt) {
        this.tenvt = tenvt;
    }

    @XmlElement
    public String getMavt() {
        return mavt;
    }

    public void setMavt(String mavt) {
        this.mavt = mavt;
    }

    @XmlElement
    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    @XmlElement
    public float getDongia() {
        return dongia;
    }

    public void setDongia(float dongia) {
        this.dongia = dongia;
    }

}
