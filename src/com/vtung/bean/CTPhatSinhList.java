/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtung.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@XmlRootElement
public class CTPhatSinhList {
    List<CTPhatSinhBean> ctPhatSinhList;

    public CTPhatSinhList() {
        ctPhatSinhList = new ArrayList<>();
    }
   // @XmlElementWrapper
    @XmlElement(name="CTPhatsinh")
    public List<CTPhatSinhBean> getCtPhatSinhList() {
        return ctPhatSinhList;
    }

    public void setCtPhatSinhList(List<CTPhatSinhBean> ctPhatSinhList) {
        this.ctPhatSinhList = ctPhatSinhList;
    }
    
    public void add(CTPhatSinhBean bean)
    {
        ctPhatSinhList.add(bean);
    }
}
