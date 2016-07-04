/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtung.business;

import com.vtung.utils.MyJDBC;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author admin
 */
public class Reports {
    
    public static void viewReport(String reportFile, Map params) throws Exception {
       // JasperDesign jasperDesign = JRXmlLoader.load("\\\\Mac\\Host\\My Data\\NetBeansProjects\\WINAPP_QLVT\\src\\reports\\PhatSinh.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(reportFile);
	 
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, MyJDBC.instance.getConnection());
	JasperViewer.viewReport(jasperPrint,false);
        
    }
   
}
