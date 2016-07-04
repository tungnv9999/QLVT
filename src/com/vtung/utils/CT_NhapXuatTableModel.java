/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtung.utils;

import com.vtung.bean.CTPhatSinhBean;
import com.vtung.bean.VattuBean;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author admin
 */
public class CT_NhapXuatTableModel extends AbstractTableModel {

    private String[] columnNames = {"STT", "Mã vật tư", "Tên vật tư", "Số lượng", "Đơn giá", "Thành tiền",};
    private List<CTPhatSinhBean> listCTPhatsinh = new ArrayList<>();

    private JTextField tbxThanhtien;

    public CT_NhapXuatTableModel(List<CTPhatSinhBean> listCTPhatsinh, JTextField tbxThanhtien) {
        this.listCTPhatsinh.addAll(listCTPhatsinh);
        this.tbxThanhtien = tbxThanhtien;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }

    @Override
    public int getRowCount() {
        return listCTPhatsinh.size();
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        CTPhatSinhBean ctps = listCTPhatsinh.get(rowIndex);
        switch (columnIndex) {
            
            case 2:
                VattuBean vattuBean = (VattuBean) value;
                ctps.setTenvt(vattuBean.getTenvt());
                ctps.setMavt(vattuBean.getMavt());
                break;
            case 3:   
                ctps.setSoluong((int) value);
                break;
            case 4:
                ctps.setDongia((float) value);
                break;
        }
        if (tbxThanhtien != null) {
            tbxThanhtien.setText(getTotal() + "");
        }
        fireTableDataChanged();
    }

    public float getTotal() {
        float total = 0;
        for (CTPhatSinhBean ctps : listCTPhatsinh) {
            total += ctps.getDongia() * ctps.getSoluong();
        }
        return total;
    }
    
    public void clearData(){
        listCTPhatsinh.clear();
        fireTableDataChanged();
    };
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object returnValue = null;
        CTPhatSinhBean bean = listCTPhatsinh.get(rowIndex);

        switch (columnIndex) {
            case 0:
                returnValue = rowIndex + 1;
                break;
            case 1:
                returnValue = bean.getMavt();
                break;
            case 2:
                returnValue = bean.getTenvt();
                break;
            case 3:
                returnValue = bean.getSoluong();
                break;
            case 4:
                returnValue = bean.getDongia();
                break;
            case 5:
                returnValue = bean.getDongia() * bean.getSoluong();
                break;
        }

        return returnValue;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0 || columnIndex==1||columnIndex==5)
            return false;
        return columnIndex > 0;
        
    }

    public void addRow() {
        listCTPhatsinh.add(new CTPhatSinhBean());
        fireTableDataChanged();
    }

    ;
    
    public void removeRow(int index) {
        listCTPhatsinh.remove(index);
        fireTableDataChanged();
    }

    public List<CTPhatSinhBean> getListCTPhatsinh() {
        return listCTPhatsinh;
    }

    public void setListCTPhatsinh(List<CTPhatSinhBean> listCTPhatsinh) {
        this.listCTPhatsinh = listCTPhatsinh;
        fireTableDataChanged();
    }

}
