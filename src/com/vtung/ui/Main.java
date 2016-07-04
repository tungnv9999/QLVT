/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vtung.ui;

import com.vtung.bean.AccountBean;
import com.vtung.bean.CTPhatSinhBean;
import com.vtung.bean.CommonObject;
import com.vtung.bean.KhoBean;
import com.vtung.bean.NhanvienBean;
import com.vtung.bean.PhatSinhBean;
import com.vtung.bean.VattuBean;
import com.vtung.business.Reports;
import com.vtung.dao.AccountDAO;
import com.vtung.dao.EmployeeDAO;
import com.vtung.dao.InterfaceObjectDAO;
import com.vtung.dao.KhoDAO;
import com.vtung.dao.NhapxuatDAO;
import com.vtung.dao.ThuchiDAO;
import com.vtung.dao.VattuDAO;
import com.vtung.exception.DupplicateLoginException;
import com.vtung.exception.DupplicateUserException;
import com.vtung.exception.NonLoginException;
import com.vtung.utils.CT_NhapXuatTableModel;
import com.vtung.utils.Constrains;
import com.vtung.utils.JTextFieldLimit;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;
import org.netbeans.validation.api.builtin.stringvalidation.StringValidators;
import org.netbeans.validation.api.ui.swing.SwingValidationGroup;

/**
 *
 * @author admin
 */
public class Main extends javax.swing.JFrame {

    public static final String MESSAGE_LOGIN_FAIL = "<html>Sai tên đăng nhập hoặc mật khẩu !</html>";
    public static final String MESSAGE_LOGIN_SUCCESS = "Chào ";
    private static final String MESSAGE_CHANGE_PASSWORD_SUCCESS = "<html>Đổi mật khẩu thành công !</html>";
    private static final String MESSAGE_LOGIN_WELCOME = "<html>Vui lòng đăng nhập !</html>";
    private static final String MESSAGE_EMPTY_LOGINNAME = "<html>Chưa nhập username !</html>";
    private static final String MESSAGE_DUPPLICATE_LOGINNAME = "<html>Username bị trùng !</html>";
    private static final String MESSAGE_DUPPLICATE_USERNAME = "<html>Mã nhân viên bị trùng !</html>";
    private static final String MESSAGE_EMPTY_PASSWORD = "<html>Mật khẩu không hợp lệ !</html>";
    private static final String MESSAGE_WRONG_VERIFY_PASSWORD = "<html>Mật khẩu xác nhận không giống !</html>";

    private static final String MESSAGE_INSERT_SUCCESS = "<html>Thêm thành công !</html>";
    private static final String MESSAGE_UPDATE_SUCCESS = "<html>Sửa thành công !</html>";
    private static final String MESSAGE_SIGNUP_SUCCESS = "<html>Tạo thành công !</html>";
    private static final String MESSAGE_DELETE_SUCCESS = "<html>Xóa thành công !</html>";
    private static final String MESSAGE_INVALID_CHITIETPHATSINH = "<html>Chưa chọn Vật tư hoặc chưa nhập số lượng, đơn giá !</html>";
    private static final String MESSAGE_CONFIRM_DELETE = "<html>Bạn có chắc chắn muốn xóa ";

    private static final String TOOLTIP_LUONG = "<html>Lương phải là số dương và >= 8000000</html>";
    private static final String TOOLTIP_NGAYSINH = "<html>Ngày sinh phải có định dạng dd/MM/yyyy và < ngày hiện tại !</html></html>";
    private static final String TOOLTIP_MANV = "<html>Mã nhân viên phải là số nguyên không dấu!</html></html>";

    private static final String TOOLTIP_MAKHO = "<html>Mã kho không quá 2 kí tự !</html>";
    private static final String TOOLTIP_MAPHIEU = "<html>Mã phiếu không được bỏ trống !</html>";
    private static final String TOOLTIP_TENKHO = "<html>Tên kho không được trùng !</html>";
    private static final String TOOLTIP_DIACHI = "<html>Địa chỉ không được bỏ trống !</html>";
    private static final String TOOLTIP_MAVT = "<html>Mã kho không quá 4 kí tự !</html>";
    private static final String TOOLTIP_THANHTIEN = "<html>Thành tiền phải lớn hơn 0!</html>";
    private static final String TOOLTIP_TENVT = "<html>Tên vật tư không được trùng !</html>";

    public static final String[] EMPLOYEE_TABLE_HEADERS = {"Mã nhân viên", "Họ", "Tên",
        "Địa chỉ", "Ngày sinh", "Lương", "Ghi chú"};

    public static final String[] VATTU_TABLE_HEADERS = {"Mã vật tư", "Tên vật tư",
        "Đơn vị tính"};

    public static final String[] KHO_TABLE_HEADERS = {"Mã kho", "Tên kho",
        "Địa chỉ"};
    public static final String[] BACKUP_TABLE_HEADERS = {"Bản", "Ngày"};
    public static final String[] NHAPXUAT_TABLE_HEADERS = {"Mã phiếu", "Ngày", "Loại", "Họ tên KH", "Thành tiền",
        "Mã nhân viên", "Mã kho"};

    public static final String[] THUCHI_TABLE_HEADERS = {"Mã phiếu", "Ngày", "Loại", "Mã nhân viên", "Thành tiền", "Kho"};
    public static final String[] CT_PHIEU_TABLE_HEADERS = {"Mã vật tư", "Tên vật tư", "Số lượng", "Đơn giá", "Thành tiền",};

    public static final String MESSAGE_SQL_EXCEPTION = "<html>Lỗi SQL. Vui Lòng đăng nhập lại !</html>";
    public static final String TITLE_ERROR = "CẢNH BÁO LỖI";
    public static final String TITLE_DELETE = "XÁC NHẬN XÓA";

    public static final String MESSAGE_APPLICATION_ERROR = "<html>Chương trình bị lỗi!</html>";
    public static final String MESSAGE_NHANVIEN_EMPTY = "<html>Nhân viên không tồn tại</html>";
    public static final String MESSAGE_KHO_EMPTY = "<html>Kho không tồn tại</html>";
    public static final String MESSAGE_EDITTING_PHIEU = "<html>Sửa phiếu</html>";
    public static final String MESSAGE_INSERTING_PHIEU = "<html>Thêm phiếu</html>";
    public static final String MESSAGE_DATA_EMPTY = "<html>Không có dữ liệu</html>";
    public static final String MESSAGE_NOT_ALLOW_DELETE = "<html>Có khóa ngoại! Không được xóa!</html>";
    public static final String MESSAGE_DUPPLICATE_VATTU_NAME = "<html>Tên bị trùng !</html>";

    AccountDAO accountDAO;
    EmployeeDAO employeeDAO;
    VattuDAO vattuDAO;
    KhoDAO khoDAO;
    NhapxuatDAO nhapxuatDAO;
    ThuchiDAO thuchiDAO;

    Vector tableData;
    Vector tableHeader;

    SimpleDateFormat df;

    Object selectedBean;
    AccountBean account;

    Stack<Object[]> undoList;

    /**
     * Creates new form Main
     */
    public Main() {
        accountDAO = new AccountDAO();
        employeeDAO = new EmployeeDAO();
        vattuDAO = new VattuDAO();
        khoDAO = new KhoDAO();
        nhapxuatDAO = new NhapxuatDAO();
        thuchiDAO = new ThuchiDAO();

        undoList = new Stack<>();
        undoList.setSize(3);

        initComponents();
        this.setLocationRelativeTo(null);
        loginView(true);

        //Khoi tao dinh dang cho Ngay
        df = new SimpleDateFormat("dd/MM/yyyy");

        //Set login to center
        displayToCenter(frmLogin, pnlMain);

    }

    public void setValidator() {

        SwingValidationGroup validationGroup = SwingValidationGroup.create();
        validationGroup.add(tbMaPhieu_Phieu_Insert,
                StringValidators.REQUIRE_NON_EMPTY_STRING,
                StringValidators.NO_WHITESPACE);
        //pnlThongtinPhieu.add(panel);
        JTextComponent label = (JTextComponent) validationGroup.createProblemLabel();
        label.setBackground(Color.CYAN);
        pnlThongbao.add(label);
    }

    ;

    private void displayToCenter(JComponent child, JComponent parent) {
        Dimension desktopSize = parent.getSize();
        Dimension jInternalFrameSize = child.getSize();
        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;
        child.setLocation(width, height);
    }

    private void displayEmployee(NhanvienBean bean) {
        selectedBean = bean;
        tbxMaNv.setText(bean.getManv() + "");
        tbxNgaySinh.setText(df.format(bean.getNgaysinh()));
        tbxDiachi.setText(bean.getDiachi());
        tbxGhichu.setText(bean.getGhichu());
        tbxHo.setText(bean.getHo());
        tbxTen.setText(bean.getTen());
        tbxLuong.setText(bean.getLuong() + "");
    }

    private void displayVattu(VattuBean bean) {
        selectedBean = bean;
        tbxMavt.setText(bean.getMavt() + "");
        tbxTenVattu.setText(bean.getTenvt());
        tbxDvt.setText(bean.getDvt());
    }

    private void displayKho(KhoBean bean) {
        selectedBean = bean;
        tbxMakho.setText(bean.getMakho() + "");
        tbxTenKho.setText(bean.getTen());
        tbxDiachiKho.setText(bean.getDiachi());
    }

    private void displayNhapxuat(PhatSinhBean bean, List ctps) {

        tbKhoPhieu.setText(bean.getMakho() + " - " + bean.getTenkho());
        tbNhanVienPhieu.setText(bean.getManv() + " - " + bean.getTennv());
        tbTenKH.setText(bean.getHotenKH());
        tbMaPhieu.setText(bean.getMaphieu());
        tbNgayPhieu.setText(df.format(bean.getNgay()));
        tbThanhTien.setText(bean.getThanhtien() + "");
        tbLoaiPhieu.setText(bean.getLoai());

        tblCTPhieu.setModel(new CT_NhapXuatTableModel(ctps, null) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        });

    }

    private void displayThuchi(PhatSinhBean bean) {

        tbMakhoTC.setText(bean.getMakho());
        tbTenkhoTC.setText(bean.getTenkho());
        tbManvTC.setText(bean.getManv() + "");
        tbTennvTC.setText(bean.getTennv());
        tbMaPhieuTC.setText(bean.getMaphieu());
        dpNgayTC.setDate(bean.getNgay());
        tbThanhTienTC.setText(bean.getThanhtien() + "");
        cbbLoaiTC.setSelectedItem(bean.getLoai());

    }

    private void displayNhapxuatTrongNew(PhatSinhBean bean, List ctps) {

        tbMakho_Phieu_Insert.setText(bean.getMakho());
        tbTenkho_Phieu_Insert.setText(bean.getTenkho());
        tbManv_Phieu_Insert.setText(bean.getManv() + "");
        tbTennv_Phieu_Insert.setText(bean.getTennv());
        tbTenKH_Phieu_Insert.setText(bean.getHotenKH());
        tbMaPhieu_Phieu_Insert.setText(bean.getMaphieu());
        dpNgay_Phieu_Insert.setDate(bean.getNgay() == null ? new Date() : bean.getNgay());
        tbThanhtien_Phieu_Insert.setText(bean.getThanhtien() + "");

        if (bean.getNgay() == null) {
            lbThongbao_Phieu_Insert.setText(MESSAGE_INSERTING_PHIEU);
            CT_NhapXuatTableModel model = (CT_NhapXuatTableModel) tblCTPhatsinh_Phieu_Insert.getModel();
            model.clearData();
        } else {
            lbThongbao_Phieu_Insert.setText(MESSAGE_EDITTING_PHIEU);
            CT_NhapXuatTableModel model = (CT_NhapXuatTableModel) tblCTPhatsinh_Phieu_Insert.getModel();
            model.setListCTPhatsinh(ctps);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlChangePass = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        dlAccount = new javax.swing.JDialog();
        btngrpRole = new javax.swing.ButtonGroup();
        buttonGroupHang = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jMenu1 = new javax.swing.JMenu();
        pnlStatus = new javax.swing.JPanel();
        lbStatus = new javax.swing.JLabel();
        pnlMain = new javax.swing.JPanel();
        desktop = new javax.swing.JDesktopPane();
        frmLogin = new javax.swing.JInternalFrame();
        pnlLogin = new javax.swing.JPanel();
        lbUsername = new javax.swing.JLabel();
        lbpassword = new javax.swing.JLabel();
        btnDangNhap = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        txtUsername = new javax.swing.JTextField();
        frmNhanvien = new javax.swing.JInternalFrame("",true,true,true,true);
        pnlDSNhanvien = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        danhsachNhanvien = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        tbxTimKiemNhanvien = new javax.swing.JTextField();
        btnTimkiemNhanvien = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDSNhanvien = new javax.swing.JTable();
        inforAndButton = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tbxMaNv = new javax.swing.JTextField();
        tbxHo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tbxTen = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tbxNgaySinh = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tbxDiachi = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tbxLuong = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbxGhichu = new javax.swing.JTextArea();
        jPanel8 = new javax.swing.JPanel();
        btnThemNhanvien = new javax.swing.JButton();
        btnLuuNhanvien = new javax.swing.JButton();
        btnXoaNhanvien = new javax.swing.JButton();
        btnUndoNhanvien = new javax.swing.JButton();
        btnThoatNhanvien = new javax.swing.JButton();
        lbThongbaoNV = new javax.swing.JLabel();
        frmVattu = new javax.swing.JInternalFrame("",true,true,true,true);
        pnlDSVattu = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        tbTimKiemVattu = new javax.swing.JTextField();
        btnTimkiemVattu = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblVattu = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        btnThemVattu = new javax.swing.JButton();
        btnLuuVattu = new javax.swing.JButton();
        btnXoaVattu = new javax.swing.JButton();
        btnUndoVattu = new javax.swing.JButton();
        btnThoatVattu = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        tbxMavt = new javax.swing.JTextField();
        tbxTenVattu = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        tbxDvt = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        lbThongbaoVatTu = new javax.swing.JLabel();
        frmKho = new javax.swing.JInternalFrame("Quản lý Kho",true,true,true,true);
        pnlDSVattu2 = new javax.swing.JPanel();
        jSplitPane5 = new javax.swing.JSplitPane();
        jPanel16 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        tbxTimKiemKho = new javax.swing.JTextField();
        btnTimkiemKho = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblKho = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        btnThemKho = new javax.swing.JButton();
        btnLuuKho = new javax.swing.JButton();
        btnXoaKho = new javax.swing.JButton();
        btnUndoKho = new javax.swing.JButton();
        btnThoatKho = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        tbxMakho = new javax.swing.JTextField();
        tbxTenKho = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        tbxDiachiKho = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        lbThongBaoKho = new javax.swing.JLabel();
        frmChangePass = new javax.swing.JInternalFrame("Đổi mật khẩu",true,true,true,true);
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tbNewPass2 = new javax.swing.JPasswordField();
        btnChangePass = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnThoatChangePass = new javax.swing.JButton();
        tbNewPass1 = new javax.swing.JPasswordField();
        lbDialogStatus = new javax.swing.JLabel();
        frmAccount = new javax.swing.JInternalFrame("",true,true,true,true);
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxNhanvien = new javax.swing.JComboBox<>();
        tbxMaNVTaiKhoan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tbxLoginnameTaiKhoan = new javax.swing.JTextField();
        tbxPassTaiKhoan = new javax.swing.JPasswordField();
        rbtnAdmin = new javax.swing.JRadioButton();
        rbtnUser = new javax.swing.JRadioButton();
        btnTaoTaiKhoan = new javax.swing.JButton();
        btnThoatTaiKhoan = new javax.swing.JButton();
        btnXoaTaiKhoan = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lbStatusTaikhoan = new javax.swing.JLabel();
        frmThuchi = new javax.swing.JInternalFrame("",true,true,true,true);
        jSplitPane6 = new javax.swing.JSplitPane();
        danhsachNhanvien2 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        tbTimKiemTC = new javax.swing.JTextField();
        btnTimkiemTC = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDSThuchi = new javax.swing.JTable();
        inforAndButton3 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        tbMaPhieuTC = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        Kho2 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        btnThemTC = new javax.swing.JButton();
        btnLuuTC = new javax.swing.JButton();
        btnXoaTC = new javax.swing.JButton();
        btnUndoTC = new javax.swing.JButton();
        btnThoatTC = new javax.swing.JButton();
        dpNgayTC = new org.jdesktop.swingx.JXDatePicker();
        cbbLoaiTC = new javax.swing.JComboBox<>();
        tbTennvTC = new javax.swing.JTextField();
        tbTenkhoTC = new javax.swing.JTextField();
        tbMakhoTC = new javax.swing.JFormattedTextField();
        tbManvTC = new javax.swing.JFormattedTextField();
        tbThanhTienTC = new javax.swing.JFormattedTextField();
        lbThongBaoTC = new javax.swing.JLabel();
        frmNewNhapXuat = new javax.swing.JInternalFrame("",true,true,true,true);
        pnlDSNhanvien2 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        btnLuuThoat_Phieu_Insert = new javax.swing.JButton();
        btnLuu_Phieu_Insert = new javax.swing.JButton();
        btnThoat_Phieu_Insert = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblCTPhatsinh_Phieu_Insert = new javax.swing.JTable();
        btnThemCT_Phieu_Insert = new javax.swing.JButton();
        btnXoaCT_Phieu_Insert = new javax.swing.JButton();
        pnlThongtinPhieu = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        cbbLoai_Phieu_Insert = new javax.swing.JComboBox<>();
        jLabel42 = new javax.swing.JLabel();
        tbMaPhieu_Phieu_Insert = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        dpNgay_Phieu_Insert = new org.jdesktop.swingx.JXDatePicker();
        jLabel40 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        tbTenKH_Phieu_Insert = new javax.swing.JTextField();
        tbManv_Phieu_Insert = new javax.swing.JFormattedTextField();
        Kho1 = new javax.swing.JLabel();
        tbTenkho_Phieu_Insert = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        tbThanhtien_Phieu_Insert = new javax.swing.JFormattedTextField();
        tbMakho_Phieu_Insert = new javax.swing.JFormattedTextField();
        tbTennv_Phieu_Insert = new javax.swing.JTextField();
        pnlThongbao = new javax.swing.JPanel();
        lbThongbao_Phieu_Insert = new javax.swing.JLabel();
        frmHoatDongNhanvien = new javax.swing.JInternalFrame();
        pnlLogin1 = new javax.swing.JPanel();
        cbxMaNV = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        tbHotenHoatdongNV = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        dpFrom = new org.jdesktop.swingx.JXDatePicker();
        jLabel51 = new javax.swing.JLabel();
        dpTo = new org.jdesktop.swingx.JXDatePicker();
        jLabel52 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        frmHangNXReport = new javax.swing.JInternalFrame();
        pnlLogin2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        dpFrom1 = new org.jdesktop.swingx.JXDatePicker();
        dpTo1 = new org.jdesktop.swingx.JXDatePicker();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        radioHangNhap = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        frmNhapXuat = new javax.swing.JInternalFrame("",true,true,true,true);
        pnlDSNhanvien1 = new javax.swing.JPanel();
        jSplitPane4 = new javax.swing.JSplitPane();
        danhsachNhanvien1 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        tbTimKiemNhapXuat = new javax.swing.JTextField();
        btnTimkiemNhapxuat = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDSNhapXuat = new javax.swing.JTable();
        inforAndButton1 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        tbMaPhieu = new javax.swing.JTextField();
        tbNgayPhieu = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        tbLoaiPhieu = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        tbTenKH = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        tbThanhTien = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        tbNhanVienPhieu = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        Kho = new javax.swing.JLabel();
        tbKhoPhieu = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblCTPhieu = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        btnThemNhatxuat = new javax.swing.JButton();
        btnSuaNhapxuat = new javax.swing.JButton();
        btnXoaNhapxuat = new javax.swing.JButton();
        btnUndoNhapxuat = new javax.swing.JButton();
        btnThoatNhanvien1 = new javax.swing.JButton();
        lbThongBaoNX = new javax.swing.JLabel();
        frmThuChiReport = new javax.swing.JInternalFrame();
        pnlLogin3 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        dpFrom2 = new org.jdesktop.swingx.JXDatePicker();
        dpTo2 = new org.jdesktop.swingx.JXDatePicker();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        radioHangNhap1 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        frmTongHopReport = new javax.swing.JInternalFrame();
        pnlLogin4 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        dpFrom3 = new org.jdesktop.swingx.JXDatePicker();
        dpTo3 = new org.jdesktop.swingx.JXDatePicker();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        frmBackupRestore = new javax.swing.JInternalFrame();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblBackup = new javax.swing.JTable();
        menubar = new javax.swing.JMenuBar();
        menuDanhmuc = new javax.swing.JMenu();
        mniVattu = new javax.swing.JMenuItem();
        mniKho = new javax.swing.JMenuItem();
        menuNhanvien = new javax.swing.JMenu();
        menuThaotac = new javax.swing.JMenu();
        miNhanvienReport = new javax.swing.JMenuItem();
        miVattuReport = new javax.swing.JMenuItem();
        menuNghiepvu = new javax.swing.JMenu();
        mniNhapXuat = new javax.swing.JMenuItem();
        mniThuChi = new javax.swing.JMenuItem();
        menuThongke = new javax.swing.JMenu();
        miHangNhapReport = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        menuTaiKhoan = new javax.swing.JMenu();
        mniChangePass = new javax.swing.JMenuItem();
        mniAccount = new javax.swing.JMenuItem();
        miBackupRestore = new javax.swing.JMenuItem();
        mniDangXuat = new javax.swing.JMenuItem();

        dlChangePass.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dlChangePass.setTitle("Đổi mật khẩu");
        dlChangePass.setAlwaysOnTop(true);
        dlChangePass.setFocusCycleRoot(false);
        dlChangePass.setMinimumSize(new java.awt.Dimension(400, 200));
        dlChangePass.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        dlChangePass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dlChangePassKeyPressed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 564, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout dlChangePassLayout = new javax.swing.GroupLayout(dlChangePass.getContentPane());
        dlChangePass.getContentPane().setLayout(dlChangePassLayout);
        dlChangePassLayout.setHorizontalGroup(
            dlChangePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dlChangePassLayout.setVerticalGroup(
            dlChangePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlChangePassLayout.createSequentialGroup()
                .addContainerGap(242, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        dlAccount.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dlAccount.setTitle("Đổi mật khẩu");
        dlAccount.setAlwaysOnTop(true);
        dlAccount.setFocusCycleRoot(false);
        dlAccount.setMinimumSize(new java.awt.Dimension(602, 350));
        dlAccount.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        javax.swing.GroupLayout dlAccountLayout = new javax.swing.GroupLayout(dlAccount.getContentPane());
        dlAccount.getContentPane().setLayout(dlAccountLayout);
        dlAccountLayout.setHorizontalGroup(
            dlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 686, Short.MAX_VALUE)
        );
        dlAccountLayout.setVerticalGroup(
            dlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý vật tư");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new java.awt.Dimension(800, 500));

        pnlStatus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnlStatusLayout = new javax.swing.GroupLayout(pnlStatus);
        pnlStatus.setLayout(pnlStatusLayout);
        pnlStatusLayout.setHorizontalGroup(
            pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlStatusLayout.setVerticalGroup(
            pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        pnlMain.setLayout(new java.awt.BorderLayout());

        desktop.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        desktop.setDragMode(javax.swing.JDesktopPane.OUTLINE_DRAG_MODE);
        desktop.setPreferredSize(new java.awt.Dimension(1600, 900));
        desktop.setSelectedFrame(frmNhanvien);
        desktop.setLayout(new java.awt.FlowLayout());

        frmLogin.setTitle("Đăng nhập");
        frmLogin.setVisible(false);
        frmLogin.getContentPane().setLayout(new javax.swing.BoxLayout(frmLogin.getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        pnlLogin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pnlLoginKeyPressed(evt);
            }
        });

        lbUsername.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbUsername.setText("Username");

        lbpassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbpassword.setText("Password");

        btnDangNhap.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDangNhap.setText("Đăng nhập");
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });

        txtUsername.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pnlLoginLayout = new javax.swing.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(btnDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addComponent(lbUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, Short.MAX_VALUE))
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addComponent(lbpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPassword)
                    .addComponent(txtUsername))
                .addContainerGap())
        );
        pnlLoginLayout.setVerticalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbpassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        frmLogin.getContentPane().add(pnlLogin);

        desktop.add(frmLogin);

        frmNhanvien.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        frmNhanvien.setResizable(true);
        frmNhanvien.setTitle("Quản lý nhân viên");
        frmNhanvien.setVisible(false);
        frmNhanvien.getContentPane().setLayout(new javax.swing.BoxLayout(frmNhanvien.getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        pnlDSNhanvien.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlDSNhanvien.setLayout(new java.awt.BorderLayout());

        jSplitPane3.setOneTouchExpandable(true);

        danhsachNhanvien.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Danh sách nhân viên");

        tbxTimKiemNhanvien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbxTimKiemNhanvien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbxTimKiemNhanvienKeyReleased(evt);
            }
        });

        btnTimkiemNhanvien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTimkiemNhanvien.setText("Tìm kiếm");
        btnTimkiemNhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemNhanvienActionPerformed(evt);
            }
        });

        tblDSNhanvien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblDSNhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDSNhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSNhanvienMouseClicked(evt);
            }
        });
        tblDSNhanvien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblDSNhanvienKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblDSNhanvien);

        javax.swing.GroupLayout danhsachNhanvienLayout = new javax.swing.GroupLayout(danhsachNhanvien);
        danhsachNhanvien.setLayout(danhsachNhanvienLayout);
        danhsachNhanvienLayout.setHorizontalGroup(
            danhsachNhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(danhsachNhanvienLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(danhsachNhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(danhsachNhanvienLayout.createSequentialGroup()
                        .addGroup(danhsachNhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(danhsachNhanvienLayout.createSequentialGroup()
                                .addComponent(tbxTimKiemNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTimkiemNhanvien))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        danhsachNhanvienLayout.setVerticalGroup(
            danhsachNhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(danhsachNhanvienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(danhsachNhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbxTimKiemNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimkiemNhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 873, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jSplitPane3.setLeftComponent(danhsachNhanvien);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Thông tin nhân viên");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Mã NV");

        tbxMaNv.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbxMaNv.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbxMaNvFocusLost(evt);
            }
        });
        tbxMaNv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbxMaNvKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbxMaNvKeyReleased(evt);
            }
        });

        tbxHo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbxHo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbxHoKeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Họ");

        tbxTen.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbxTen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbxTenKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Tên");

        tbxNgaySinh.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbxNgaySinh.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbxNgaySinhFocusLost(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Ngày sinh");

        tbxDiachi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Địa chỉ");

        tbxLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbxLuong.setToolTipText(TOOLTIP_LUONG);
        tbxLuong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbxLuongFocusLost(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Lương");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Ghi chú");

        tbxGhichu.setColumns(20);
        tbxGhichu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbxGhichu.setRows(5);
        jScrollPane2.setViewportView(tbxGhichu);

        jPanel8.setLayout(new java.awt.GridLayout(3, 3, 5, 5));

        btnThemNhanvien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThemNhanvien.setText("Thêm");
        btnThemNhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanvienActionPerformed(evt);
            }
        });
        jPanel8.add(btnThemNhanvien);

        btnLuuNhanvien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLuuNhanvien.setText("Lưu");
        btnLuuNhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuNhanvienActionPerformed(evt);
            }
        });
        jPanel8.add(btnLuuNhanvien);

        btnXoaNhanvien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXoaNhanvien.setText("Xóa");
        btnXoaNhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNhanvienActionPerformed(evt);
            }
        });
        jPanel8.add(btnXoaNhanvien);

        btnUndoNhanvien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnUndoNhanvien.setText("Undo");
        btnUndoNhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUndoNhanvienActionPerformed(evt);
            }
        });
        jPanel8.add(btnUndoNhanvien);

        btnThoatNhanvien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThoatNhanvien.setText("Thoát");
        btnThoatNhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatNhanvienActionPerformed(evt);
            }
        });
        jPanel8.add(btnThoatNhanvien);

        lbThongbaoNV.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        lbThongbaoNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                    .addComponent(jLabel18)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tbxMaNv)
                            .addComponent(tbxHo)
                            .addComponent(tbxTen)
                            .addComponent(tbxNgaySinh)
                            .addComponent(tbxDiachi)
                            .addComponent(tbxLuong))))
                .addContainerGap())
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbThongbaoNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(tbxMaNv, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(tbxHo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(tbxTen, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(tbxNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(tbxDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(tbxLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbThongbaoNV, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout inforAndButtonLayout = new javax.swing.GroupLayout(inforAndButton);
        inforAndButton.setLayout(inforAndButtonLayout);
        inforAndButtonLayout.setHorizontalGroup(
            inforAndButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inforAndButtonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        inforAndButtonLayout.setVerticalGroup(
            inforAndButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inforAndButtonLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jSplitPane3.setRightComponent(inforAndButton);

        pnlDSNhanvien.add(jSplitPane3, java.awt.BorderLayout.CENTER);

        frmNhanvien.getContentPane().add(pnlDSNhanvien);

        desktop.add(frmNhanvien);

        frmVattu.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        frmVattu.setResizable(true);
        frmVattu.setTitle("Quản lý vật tư");
        frmVattu.setAutoscrolls(true);
        frmVattu.setVisible(true);
        frmVattu.getContentPane().setLayout(new javax.swing.BoxLayout(frmVattu.getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        pnlDSVattu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlDSVattu.setLayout(new java.awt.BorderLayout());

        jSplitPane2.setOneTouchExpandable(true);

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.setPreferredSize(new java.awt.Dimension(800, 667));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Danh sách vật tư");

        tbTimKiemVattu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbTimKiemVattu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbTimKiemVattuActionPerformed(evt);
            }
        });
        tbTimKiemVattu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbTimKiemVattuKeyReleased(evt);
            }
        });

        btnTimkiemVattu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTimkiemVattu.setText("Tìm kiếm");
        btnTimkiemVattu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemVattuActionPerformed(evt);
            }
        });

        tblVattu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblVattu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVattuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblVattu);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(tbTimKiemVattu, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimkiemVattu))
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tbTimKiemVattu)
                    .addComponent(btnTimkiemVattu))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane2.setLeftComponent(jPanel9);

        jPanel11.setLayout(new java.awt.GridLayout(3, 3, 5, 5));

        btnThemVattu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThemVattu.setText("Thêm");
        btnThemVattu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVattuActionPerformed(evt);
            }
        });
        jPanel11.add(btnThemVattu);

        btnLuuVattu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLuuVattu.setText("Lưu");
        btnLuuVattu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuVattuActionPerformed(evt);
            }
        });
        jPanel11.add(btnLuuVattu);

        btnXoaVattu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXoaVattu.setText("Xóa");
        btnXoaVattu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaVattuActionPerformed(evt);
            }
        });
        jPanel11.add(btnXoaVattu);

        btnUndoVattu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnUndoVattu.setText("Undo");
        btnUndoVattu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUndoVattuActionPerformed(evt);
            }
        });
        jPanel11.add(btnUndoVattu);

        btnThoatVattu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThoatVattu.setText("Thoát");
        btnThoatVattu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatVattuActionPerformed(evt);
            }
        });
        jPanel11.add(btnThoatVattu);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel20.setText("Thông tin vật tư");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("Mã vật tư");

        tbxMavt.setDocument(new JTextFieldLimit(4,true)
        );
        tbxMavt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbxMavt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbxMavtFocusLost(evt);
            }
        });
        tbxMavt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbxMavtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbxMavtKeyReleased(evt);
            }
        });

        tbxTenVattu.setDocument(new JTextFieldLimit(30, false)
        );
        tbxTenVattu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbxTenVattu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbxTenVattuFocusLost(evt);
            }
        });
        tbxTenVattu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbxTenVattuKeyReleased(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setText("Tên vật tư");

        tbxDvt.setDocument(new JTextFieldLimit(15, false)
        );
        tbxDvt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setText("Đơn vị tính");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tbxTenVattu)
                            .addComponent(tbxMavt)
                            .addComponent(tbxDvt))))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbxMavt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbxTenVattu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbxDvt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbThongbaoVatTu.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbThongbaoVatTu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThongbaoVatTu.setText(" ");
        lbThongbaoVatTu.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbThongbaoVatTu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbThongbaoVatTu, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane2.setRightComponent(jPanel2);

        pnlDSVattu.add(jSplitPane2, java.awt.BorderLayout.CENTER);

        frmVattu.getContentPane().add(pnlDSVattu);

        desktop.add(frmVattu);

        frmKho.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        frmKho.setResizable(true);
        frmKho.setTitle("Quản lý kho");
        frmKho.setAutoscrolls(true);
        frmKho.setVisible(true);
        frmKho.getContentPane().setLayout(new javax.swing.BoxLayout(frmKho.getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        pnlDSVattu2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlDSVattu2.setLayout(new java.awt.BorderLayout());

        jSplitPane5.setOneTouchExpandable(true);

        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Danh sách Kho");

        tbxTimKiemKho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbxTimKiemKhoKeyReleased(evt);
            }
        });

        btnTimkiemKho.setText("Tìm kiếm");
        btnTimkiemKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemKhoActionPerformed(evt);
            }
        });

        tblKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhoMouseClicked(evt);
            }
        });
        tblKho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblKhoKeyPressed(evt);
            }
        });
        jScrollPane6.setViewportView(tblKho);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(tbxTimKiemKho, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimkiemKho))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tbxTimKiemKho)
                    .addComponent(btnTimkiemKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane5.setLeftComponent(jPanel16);

        jPanel17.setLayout(new java.awt.GridLayout(3, 3, 5, 5));

        btnThemKho.setText("Thêm");
        btnThemKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKhoActionPerformed(evt);
            }
        });
        jPanel17.add(btnThemKho);

        btnLuuKho.setText("Lưu");
        btnLuuKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuKhoActionPerformed(evt);
            }
        });
        jPanel17.add(btnLuuKho);

        btnXoaKho.setText("Xóa");
        btnXoaKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKhoActionPerformed(evt);
            }
        });
        jPanel17.add(btnXoaKho);

        btnUndoKho.setText("Undo");
        btnUndoKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUndoKhoActionPerformed(evt);
            }
        });
        jPanel17.add(btnUndoKho);

        btnThoatKho.setText("Thoát");
        btnThoatKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatKhoActionPerformed(evt);
            }
        });
        jPanel17.add(btnThoatKho);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel31.setText("Thông tin kho");

        jLabel32.setText("Mã kho");

        tbxMakho.setDocument(new JTextFieldLimit(2, true)
        );
        tbxMakho.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbxMakhoFocusLost(evt);
            }
        });
        tbxMakho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbxMakhoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbxMakhoKeyReleased(evt);
            }
        });

        tbxTenKho.setDocument(new JTextFieldLimit(30, false)
        );
        tbxTenKho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbxTenKhoKeyReleased(evt);
            }
        });

        jLabel33.setText("Tên kho");

        tbxDiachiKho.setDocument(new JTextFieldLimit(70, false)
        );

        jLabel34.setText("Địa chỉ");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tbxTenKho)
                            .addComponent(tbxMakho)
                            .addComponent(tbxDiachiKho))))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel31)
                .addGap(62, 62, 62)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(tbxMakho, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(tbxTenKho, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(tbxDiachiKho, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbThongBaoKho.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        lbThongBaoKho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThongBaoKho.setText(" ");
        lbThongBaoKho.setToolTipText("");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbThongBaoKho, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbThongBaoKho, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane5.setRightComponent(jPanel5);

        pnlDSVattu2.add(jSplitPane5, java.awt.BorderLayout.CENTER);

        frmKho.getContentPane().add(pnlDSVattu2);

        desktop.add(frmKho);

        frmChangePass.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        frmChangePass.setTitle("Đổi mật khẩu");
        frmChangePass.setToolTipText("");
        frmChangePass.setVisible(false);
        frmChangePass.getContentPane().setLayout(new javax.swing.BoxLayout(frmChangePass.getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Xác nhận mật khẩu");

        tbNewPass2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbNewPass2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbNewPass2KeyPressed(evt);
            }
        });

        btnChangePass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnChangePass.setText("Ok");
        btnChangePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePassActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Mật khẩu mới");

        btnThoatChangePass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThoatChangePass.setText("Hủy");
        btnThoatChangePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatChangePassActionPerformed(evt);
            }
        });

        tbNewPass1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbNewPass1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbNewPass1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbDialogStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(btnChangePass, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addGap(54, 54, 54)
                .addComponent(btnThoatChangePass, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addGap(100, 100, 100))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(84, 84, 84)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tbNewPass1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                    .addComponent(tbNewPass2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tbNewPass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tbNewPass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThoatChangePass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChangePass, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(lbDialogStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        frmChangePass.getContentPane().add(jPanel6);

        desktop.add(frmChangePass);

        frmAccount.setLocation(0, 0);
        frmAccount.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        frmAccount.setTitle("Quản lý tài khoản");
        frmAccount.setVisible(false);
        frmAccount.getContentPane().setLayout(new javax.swing.BoxLayout(frmAccount.getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Tài khoản");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Nhân viên");

        cbxNhanvien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbxNhanvien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxNhanvien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxNhanvienItemStateChanged(evt);
            }
        });

        tbxMaNVTaiKhoan.setEditable(false);
        tbxMaNVTaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Mã");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Username");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Mật khẩu");

        tbxLoginnameTaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tbxPassTaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btngrpRole.add(rbtnAdmin);
        rbtnAdmin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rbtnAdmin.setText("Admin");

        btngrpRole.add(rbtnUser);
        rbtnUser.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rbtnUser.setSelected(true);
        rbtnUser.setText("User");

        btnTaoTaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTaoTaiKhoan.setText("Tạo");
        btnTaoTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoTaiKhoanActionPerformed(evt);
            }
        });

        btnThoatTaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThoatTaiKhoan.setText("Thoát");
        btnThoatTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatTaiKhoanActionPerformed(evt);
            }
        });

        btnXoaTaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXoaTaiKhoan.setText("Xóa");
        btnXoaTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTaiKhoanActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbStatusTaikhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbStatusTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTaoTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThoatTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(46, 46, 46))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(rbtnAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addComponent(rbtnUser)
                .addGap(284, 284, 284))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(tbxPassTaiKhoan)
                    .addComponent(tbxLoginnameTaiKhoan)
                    .addComponent(cbxNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tbxMaNVTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(tbxMaNVTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tbxLoginnameTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tbxPassTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnAdmin)
                    .addComponent(rbtnUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoaTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnTaoTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnThoatTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        frmAccount.getContentPane().add(jPanel4);

        desktop.add(frmAccount);

        frmThuchi.setLocation(10, 10);
        frmThuchi.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        frmThuchi.setTitle("Quản lý Thu chi");
        frmThuchi.setVisible(false);

        jSplitPane6.setMaximumSize(new java.awt.Dimension(3000, 3000));
        jSplitPane6.setName(""); // NOI18N
        jSplitPane6.setOneTouchExpandable(true);

        danhsachNhanvien2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Danh sách Thu chi");

        tbTimKiemTC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbTimKiemTC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbTimKiemTCKeyReleased(evt);
            }
        });

        btnTimkiemTC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTimkiemTC.setText("Tìm kiếm");
        btnTimkiemTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemTCActionPerformed(evt);
            }
        });

        tblDSThuchi.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblDSThuchi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDSThuchi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSThuchiMouseClicked(evt);
            }
        });
        tblDSThuchi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblDSThuchiKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblDSThuchiKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tblDSThuchi);

        javax.swing.GroupLayout danhsachNhanvien2Layout = new javax.swing.GroupLayout(danhsachNhanvien2);
        danhsachNhanvien2.setLayout(danhsachNhanvien2Layout);
        danhsachNhanvien2Layout.setHorizontalGroup(
            danhsachNhanvien2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(danhsachNhanvien2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(danhsachNhanvien2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(danhsachNhanvien2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(tbTimKiemTC, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimkiemTC)
                        .addContainerGap())))
            .addGroup(danhsachNhanvien2Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 958, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        danhsachNhanvien2Layout.setVerticalGroup(
            danhsachNhanvien2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(danhsachNhanvien2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(danhsachNhanvien2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbTimKiemTC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimkiemTC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane6.setLeftComponent(danhsachNhanvien2);

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel46.setText("Thông tin chi tiết");

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel47.setText("Mã phiếu");

        tbMaPhieuTC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbMaPhieuTC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbMaPhieuTCFocusLost(evt);
            }
        });
        tbMaPhieuTC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMaPhieuTCKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbMaPhieuTCKeyReleased(evt);
            }
        });
        tbMaPhieu.getDocument().addDocumentListener(new DocumentListener(){
            public void changedUpdate(DocumentEvent e) {
                enableEdit();
            }
            public void removeUpdate(DocumentEvent e) {
                enableEdit();
            }
            public void insertUpdate(DocumentEvent e) {
                enableEdit();
            }

            public void enableEdit() {
                if (tbMaPhieu.getText().trim().isEmpty()){
                    btnSuaNhapxuat.setEnabled(false);
                }else
                {
                    btnSuaNhapxuat.setEnabled(true);
                }
            }
        });

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel48.setText("Ngày");

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel53.setText("Loại");

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel55.setText("Thành tiền");

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel56.setText("Nhân viên");

        Kho2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Kho2.setText("Kho");

        jPanel22.setLayout(new java.awt.GridLayout(3, 3, 5, 5));

        btnThemTC.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThemTC.setText("Thêm");
        btnThemTC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemTCMouseClicked(evt);
            }
        });
        btnThemTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTCActionPerformed(evt);
            }
        });
        jPanel22.add(btnThemTC);

        btnLuuTC.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLuuTC.setText("Lưu");
        btnLuuTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuTCActionPerformed(evt);
            }
        });
        jPanel22.add(btnLuuTC);

        btnXoaTC.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnXoaTC.setText("Xóa");
        btnXoaTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTCActionPerformed(evt);
            }
        });
        jPanel22.add(btnXoaTC);

        btnUndoTC.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnUndoTC.setText("Undo");
        btnUndoTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUndoTCActionPerformed(evt);
            }
        });
        jPanel22.add(btnUndoTC);

        btnThoatTC.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThoatTC.setText("Thoát");
        btnThoatTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatTCActionPerformed(evt);
            }
        });
        jPanel22.add(btnThoatTC);

        dpNgayTC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        cbbLoaiTC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbbLoaiTC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "T", "C" }));

        tbTennvTC.setEditable(false);
        tbTennvTC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbTennvTC.setToolTipText(TOOLTIP_LUONG);
        tbTennvTC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbTennvTCFocusLost(evt);
            }
        });

        tbTenkhoTC.setEditable(false);
        tbTenkhoTC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbTenkhoTC.setToolTipText(TOOLTIP_LUONG);
        tbTenkhoTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbTenkhoTCActionPerformed(evt);
            }
        });
        tbTenkhoTC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbTenkhoTCFocusLost(evt);
            }
        });

        tbMakhoTC.setDocument(new JTextFieldLimit(2,true)
        );
        tbMakhoTC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbMakhoTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbMakhoTCActionPerformed(evt);
            }
        });
        tbMakhoTC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbMakhoTCFocusLost(evt);
            }
        });

        tbManvTC.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0"))));
        tbManvTC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbManvTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbManvTCActionPerformed(evt);
            }
        });
        tbManvTC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbManvTCFocusLost(evt);
            }
        });

        tbThanhTienTC.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0.00"))));
        tbThanhTienTC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel48)
                            .addComponent(jLabel53)
                            .addComponent(jLabel55)
                            .addComponent(jLabel56)
                            .addComponent(Kho2))
                        .addGap(91, 91, 91)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tbManvTC, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                    .addComponent(tbMakhoTC))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tbTennvTC)
                                    .addComponent(tbTenkhoTC)))
                            .addComponent(tbThanhTienTC)
                            .addComponent(cbbLoaiTC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dpNgayTC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tbMaPhieuTC))))
                .addContainerGap())
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46)
                .addGap(33, 33, 33)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(tbMaPhieuTC, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(dpNgayTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(cbbLoaiTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(tbThanhTienTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(tbManvTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbTennvTC, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Kho2)
                    .addComponent(tbMakhoTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbTenkhoTC, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lbThongBaoTC.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        lbThongBaoTC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThongBaoTC.setText(" ");

        javax.swing.GroupLayout inforAndButton3Layout = new javax.swing.GroupLayout(inforAndButton3);
        inforAndButton3.setLayout(inforAndButton3Layout);
        inforAndButton3Layout.setHorizontalGroup(
            inforAndButton3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inforAndButton3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(inforAndButton3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbThongBaoTC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        inforAndButton3Layout.setVerticalGroup(
            inforAndButton3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inforAndButton3Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbThongBaoTC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane6.setRightComponent(inforAndButton3);

        javax.swing.GroupLayout frmThuchiLayout = new javax.swing.GroupLayout(frmThuchi.getContentPane());
        frmThuchi.getContentPane().setLayout(frmThuchiLayout);
        frmThuchiLayout.setHorizontalGroup(
            frmThuchiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmThuchiLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jSplitPane6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );
        frmThuchiLayout.setVerticalGroup(
            frmThuchiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmThuchiLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jSplitPane6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );

        desktop.add(frmThuchi);

        frmNewNhapXuat.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        frmNewNhapXuat.setTitle("Thêm/Sửa phiếu");
        frmNewNhapXuat.setMaximumSize(new java.awt.Dimension(1440, 900));
        frmNewNhapXuat.setMinimumSize(new java.awt.Dimension(800, 500));
        frmNewNhapXuat.setName(""); // NOI18N
        frmNewNhapXuat.setPreferredSize(new java.awt.Dimension(1200, 750));
        try {
            frmNewNhapXuat.setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        frmNewNhapXuat.setVisible(false);
        frmNewNhapXuat.getContentPane().setLayout(new javax.swing.BoxLayout(frmNewNhapXuat.getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        pnlDSNhanvien2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("THÊM/ SỬA PHIẾU");
        jLabel39.setMinimumSize(new java.awt.Dimension(0, 0));

        btnLuuThoat_Phieu_Insert.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLuuThoat_Phieu_Insert.setIcon(new javax.swing.ImageIcon("Mac\\Host\\My Data\\NetBeansProjects\\WINAPP_QLVT\\src\\img\\save_edit.png")); // NOI18N
        btnLuuThoat_Phieu_Insert.setText("Lưu và Thêm");
        btnLuuThoat_Phieu_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuThoat_Phieu_InsertActionPerformed(evt);
            }
        });

        btnLuu_Phieu_Insert.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLuu_Phieu_Insert.setIcon(new javax.swing.ImageIcon("Mac\\Host\\My Data\\NetBeansProjects\\WINAPP_QLVT\\src\\img\\save.png")); // NOI18N
        btnLuu_Phieu_Insert.setText("Lưu");
        btnLuu_Phieu_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuu_Phieu_InsertActionPerformed(evt);
            }
        });

        btnThoat_Phieu_Insert.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThoat_Phieu_Insert.setIcon(new javax.swing.ImageIcon("Mac\\Host\\My Data\\NetBeansProjects\\WINAPP_QLVT\\src\\img\\exit.png")); // NOI18N
        btnThoat_Phieu_Insert.setText("Thoát");
        btnThoat_Phieu_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoat_Phieu_InsertActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLuu_Phieu_Insert, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLuuThoat_Phieu_Insert, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThoat_Phieu_Insert, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThoat_Phieu_Insert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLuuThoat_Phieu_Insert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLuu_Phieu_Insert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết phát sinh", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        tblCTPhatsinh_Phieu_Insert.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblCTPhatsinh_Phieu_Insert.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã vật tư", "Tên vật tư", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCTPhatsinh_Phieu_Insert.getTableHeader().setReorderingAllowed(false);
        tblCTPhatsinh_Phieu_Insert.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblCTPhatsinh_Phieu_InsertKeyPressed(evt);
            }
        });
        jScrollPane8.setViewportView(tblCTPhatsinh_Phieu_Insert);
        if (tblCTPhatsinh_Phieu_Insert.getColumnModel().getColumnCount() > 0) {
            tblCTPhatsinh_Phieu_Insert.getColumnModel().getColumn(1).setResizable(false);
        }

        btnThemCT_Phieu_Insert.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThemCT_Phieu_Insert.setIcon(new javax.swing.ImageIcon("Mac\\Host\\My Data\\NetBeansProjects\\WINAPP_QLVT\\src\\img\\plus.gif")); // NOI18N
        btnThemCT_Phieu_Insert.setText("Thêm Chi tiết");
        btnThemCT_Phieu_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCT_Phieu_InsertActionPerformed(evt);
            }
        });

        btnXoaCT_Phieu_Insert.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXoaCT_Phieu_Insert.setIcon(new javax.swing.ImageIcon("Mac\\Host\\My Data\\NetBeansProjects\\WINAPP_QLVT\\src\\img\\sub.png")); // NOI18N
        btnXoaCT_Phieu_Insert.setText("Xóa Chi tiết");
        btnXoaCT_Phieu_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCT_Phieu_InsertActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(btnThemCT_Phieu_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaCT_Phieu_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemCT_Phieu_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaCT_Phieu_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlThongtinPhieu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin phiếu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N
        pnlThongtinPhieu.setMaximumSize(new java.awt.Dimension(1440, 900));
        pnlThongtinPhieu.setMinimumSize(new java.awt.Dimension(800, 500));
        pnlThongtinPhieu.setPreferredSize(new java.awt.Dimension(1370, 324));

        cbbLoai_Phieu_Insert.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbbLoai_Phieu_Insert.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N", "X" }));
        cbbLoai_Phieu_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLoai_Phieu_InsertActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel42.setText("Loại");

        tbMaPhieu_Phieu_Insert.setDocument(new JTextFieldLimit(8,true)
        );
        tbMaPhieu_Phieu_Insert.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbMaPhieu_Phieu_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbMaPhieu_Phieu_InsertActionPerformed(evt);
            }
        });
        tbMaPhieu_Phieu_Insert.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tbMaPhieu_Phieu_InsertFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbMaPhieu_Phieu_InsertFocusLost(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel41.setText("Ngày");

        String[] format = new String[1];
        format[0] = "dd/MM/YYYY";
        dpNgay_Phieu_Insert.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dpNgay_Phieu_Insert.setFormats(format);

        jLabel40.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel40.setText("Mã phiếu");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbMaPhieu_Phieu_Insert)
                    .addComponent(dpNgay_Phieu_Insert, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                    .addComponent(cbbLoai_Phieu_Insert, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel40)
                    .addComponent(tbMaPhieu_Phieu_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(dpNgay_Phieu_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(cbbLoai_Phieu_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbTenKH_Phieu_Insert.setDocument(new JTextFieldLimit(40,true));
        tbTenKH_Phieu_Insert.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbTenKH_Phieu_Insert.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbTenKH_Phieu_InsertFocusLost(evt);
            }
        });

        tbManv_Phieu_Insert.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0"))));
        tbManv_Phieu_Insert.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbManv_Phieu_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbManv_Phieu_InsertActionPerformed(evt);
            }
        });
        tbManv_Phieu_Insert.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbManv_Phieu_InsertFocusLost(evt);
            }
        });

        Kho1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Kho1.setText("Kho");

        tbTenkho_Phieu_Insert.setEditable(false);
        tbTenkho_Phieu_Insert.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbTenkho_Phieu_Insert.setToolTipText(TOOLTIP_LUONG);
        tbTenkho_Phieu_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbTenkho_Phieu_InsertActionPerformed(evt);
            }
        });
        tbTenkho_Phieu_Insert.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbTenkho_Phieu_InsertFocusLost(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel44.setText("Thành tiền");

        jLabel43.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel43.setText("Họ tên KH");

        jLabel45.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel45.setText("Nhân viên");

        tbThanhtien_Phieu_Insert.setEditable(false);
        tbThanhtien_Phieu_Insert.setText("0.0");
        tbThanhtien_Phieu_Insert.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tbMakho_Phieu_Insert.setDocument(new JTextFieldLimit(2,true)
        );
        tbMakho_Phieu_Insert.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbMakho_Phieu_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbMakho_Phieu_InsertActionPerformed(evt);
            }
        });
        tbMakho_Phieu_Insert.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbMakho_Phieu_InsertFocusLost(evt);
            }
        });

        tbTennv_Phieu_Insert.setEditable(false);
        tbTennv_Phieu_Insert.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbTennv_Phieu_Insert.setToolTipText(TOOLTIP_LUONG);
        tbTennv_Phieu_Insert.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbTennv_Phieu_InsertFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel45)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Kho1)
                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(60, 60, 60)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(tbMakho_Phieu_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tbTenkho_Phieu_Insert))
                    .addComponent(tbThanhtien_Phieu_Insert)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(tbManv_Phieu_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tbTennv_Phieu_Insert))
                    .addComponent(tbTenKH_Phieu_Insert))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(tbThanhtien_Phieu_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(tbManv_Phieu_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbTennv_Phieu_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Kho1)
                    .addComponent(tbMakho_Phieu_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbTenkho_Phieu_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(tbTenKH_Phieu_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlThongtinPhieuLayout = new javax.swing.GroupLayout(pnlThongtinPhieu);
        pnlThongtinPhieu.setLayout(pnlThongtinPhieuLayout);
        pnlThongtinPhieuLayout.setHorizontalGroup(
            pnlThongtinPhieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongtinPhieuLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlThongtinPhieuLayout.setVerticalGroup(
            pnlThongtinPhieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongtinPhieuLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlThongtinPhieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlThongbao.setLayout(new javax.swing.BoxLayout(pnlThongbao, javax.swing.BoxLayout.LINE_AXIS));

        lbThongbao_Phieu_Insert.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbThongbao_Phieu_Insert.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThongbao_Phieu_Insert.setText(" ");
        lbThongbao_Phieu_Insert.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pnlThongbao.add(lbThongbao_Phieu_Insert);

        javax.swing.GroupLayout pnlDSNhanvien2Layout = new javax.swing.GroupLayout(pnlDSNhanvien2);
        pnlDSNhanvien2.setLayout(pnlDSNhanvien2Layout);
        pnlDSNhanvien2Layout.setHorizontalGroup(
            pnlDSNhanvien2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDSNhanvien2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDSNhanvien2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDSNhanvien2Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlThongbao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlThongtinPhieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDSNhanvien2Layout.setVerticalGroup(
            pnlDSNhanvien2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDSNhanvien2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(pnlThongtinPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDSNhanvien2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlThongbao, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        frmNewNhapXuat.getContentPane().add(pnlDSNhanvien2);

        desktop.add(frmNewNhapXuat);

        frmHoatDongNhanvien.setTitle("Đăng nhập");
        frmHoatDongNhanvien.setVisible(false);
        frmHoatDongNhanvien.getContentPane().setLayout(new java.awt.CardLayout());

        pnlLogin1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlLogin1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pnlLogin1KeyPressed(evt);
            }
        });

        cbxMaNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        cbxMaNV.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMaNVItemStateChanged(evt);
            }
        });

        jLabel49.setText("Mã nhân viên");

        jLabel50.setText("Họ và tên");

        tbHotenHoatdongNV.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HOẠT ĐỘNG NHÂN VIÊN");

        jLabel51.setText("Từ ngày");

        jLabel52.setText("Đến ngày");

        jButton3.setText("Preview");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Exit");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLogin1Layout = new javax.swing.GroupLayout(pnlLogin1);
        pnlLogin1.setLayout(pnlLogin1Layout);
        pnlLogin1Layout.setHorizontalGroup(
            pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogin1Layout.createSequentialGroup()
                .addGroup(pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlLogin1Layout.createSequentialGroup()
                        .addGap(572, 572, 572)
                        .addComponent(dpTo, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlLogin1Layout.createSequentialGroup()
                        .addGroup(pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlLogin1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel49)
                                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel51))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tbHotenHoatdongNV)
                                    .addComponent(dpFrom, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                                    .addComponent(cbxMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlLogin1Layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlLogin1Layout.setVerticalGroup(
            pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogin1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbHotenHoatdongNV, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpTo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(pnlLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        frmHoatDongNhanvien.getContentPane().add(pnlLogin1, "card2");

        desktop.add(frmHoatDongNhanvien);

        frmHangNXReport.setTitle("Thống Kê Hàng");
        frmHangNXReport.setVisible(false);
        frmHangNXReport.getContentPane().setLayout(new java.awt.CardLayout());

        pnlLogin2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlLogin2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pnlLogin2KeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("THỐNG KÊ HÀNG");

        dpFrom1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        dpTo1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setText("Preview");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton6.setText("Exit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel59.setText("Đến ngày");

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel58.setText("Từ ngày");

        buttonGroupHang.add(radioHangNhap);
        radioHangNhap.setSelected(true);
        radioHangNhap.setText("Hàng nhập");

        buttonGroupHang.add(jRadioButton2);
        jRadioButton2.setText("Hàng xuất");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(radioHangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(39, 39, 39)
                .addComponent(jRadioButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(183, 183, 183))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(162, 162, 162))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(dpFrom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dpTo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(86, 86, 86))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioHangNhap)
                    .addComponent(jRadioButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpFrom1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpTo1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout pnlLogin2Layout = new javax.swing.GroupLayout(pnlLogin2);
        pnlLogin2.setLayout(pnlLogin2Layout);
        pnlLogin2Layout.setHorizontalGroup(
            pnlLogin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlLogin2Layout.setVerticalGroup(
            pnlLogin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogin2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(24, 24, 24)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        frmHangNXReport.getContentPane().add(pnlLogin2, "card2");

        desktop.add(frmHangNXReport);

        frmNhapXuat.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        frmNhapXuat.setTitle("Quản lý Nhập xuất");
        frmNhapXuat.setVisible(true);
        frmNhapXuat.getContentPane().setLayout(new javax.swing.BoxLayout(frmNhapXuat.getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        pnlDSNhanvien1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlDSNhanvien1.setLayout(new java.awt.BorderLayout());

        jSplitPane4.setMaximumSize(new java.awt.Dimension(3000, 3000));
        jSplitPane4.setName(""); // NOI18N
        jSplitPane4.setOneTouchExpandable(true);

        danhsachNhanvien1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Danh sách Nhập xuất");

        tbTimKiemNhapXuat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbTimKiemNhapXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbTimKiemNhapXuatActionPerformed(evt);
            }
        });
        tbTimKiemNhapXuat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbTimKiemNhapXuatKeyReleased(evt);
            }
        });

        btnTimkiemNhapxuat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTimkiemNhapxuat.setText("Tìm kiếm");
        btnTimkiemNhapxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemNhapxuatActionPerformed(evt);
            }
        });

        tblDSNhapXuat.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblDSNhapXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDSNhapXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSNhapXuatMouseClicked(evt);
            }
        });
        tblDSNhapXuat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblDSNhapXuatKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblDSNhapXuatKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tblDSNhapXuat);

        javax.swing.GroupLayout danhsachNhanvien1Layout = new javax.swing.GroupLayout(danhsachNhanvien1);
        danhsachNhanvien1.setLayout(danhsachNhanvien1Layout);
        danhsachNhanvien1Layout.setHorizontalGroup(
            danhsachNhanvien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(danhsachNhanvien1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(danhsachNhanvien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(danhsachNhanvien1Layout.createSequentialGroup()
                        .addGroup(danhsachNhanvien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(danhsachNhanvien1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(tbTimKiemNhapXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTimkiemNhapxuat))
                            .addGroup(danhsachNhanvien1Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 984, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        danhsachNhanvien1Layout.setVerticalGroup(
            danhsachNhanvien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(danhsachNhanvien1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(danhsachNhanvien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbTimKiemNhapXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimkiemNhapxuat))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jSplitPane4.setLeftComponent(danhsachNhanvien1);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel25.setText("Thông tin chi tiết");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setText("Mã phiếu");

        tbMaPhieu.setEditable(false);
        tbMaPhieu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbMaPhieu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbMaPhieuFocusLost(evt);
            }
        });
        tbMaPhieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMaPhieuKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbMaPhieuKeyReleased(evt);
            }
        });
        tbMaPhieu.getDocument().addDocumentListener(new DocumentListener(){
            public void changedUpdate(DocumentEvent e) {
                enableEdit();
            }
            public void removeUpdate(DocumentEvent e) {
                enableEdit();
            }
            public void insertUpdate(DocumentEvent e) {
                enableEdit();
            }

            public void enableEdit() {
                if (tbMaPhieu.getText().trim().isEmpty()){
                    btnSuaNhapxuat.setEnabled(false);
                }else
                {
                    btnSuaNhapxuat.setEnabled(true);
                }
            }
        });

        tbNgayPhieu.setEditable(false);
        tbNgayPhieu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbNgayPhieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbNgayPhieuKeyReleased(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setText("Ngày");

        tbLoaiPhieu.setEditable(false);
        tbLoaiPhieu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbLoaiPhieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbLoaiPhieuKeyReleased(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setText("Loại");

        tbTenKH.setEditable(false);
        tbTenKH.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbTenKH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbTenKHFocusLost(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setText("Họ tên KH");

        tbThanhTien.setEditable(false);
        tbThanhTien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel35.setText("Thành tiền");

        tbNhanVienPhieu.setEditable(false);
        tbNhanVienPhieu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbNhanVienPhieu.setToolTipText(TOOLTIP_LUONG);
        tbNhanVienPhieu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbNhanVienPhieuFocusLost(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel36.setText("Nhân viên");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel37.setText("Chi tiết Phát sinh");

        Kho.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Kho.setText("Kho");

        tbKhoPhieu.setEditable(false);
        tbKhoPhieu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbKhoPhieu.setToolTipText(TOOLTIP_LUONG);
        tbKhoPhieu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbKhoPhieuFocusLost(evt);
            }
        });

        tblCTPhieu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblCTPhieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(tblCTPhieu);

        jPanel12.setLayout(new java.awt.GridLayout(3, 3, 5, 5));

        btnThemNhatxuat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThemNhatxuat.setIcon(new javax.swing.ImageIcon("Mac\\Host\\My Data\\NetBeansProjects\\WINAPP_QLVT\\src\\img\\plus.gif")); // NOI18N
        btnThemNhatxuat.setText("Thêm");
        btnThemNhatxuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNhatxuatMouseClicked(evt);
            }
        });
        btnThemNhatxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhatxuatActionPerformed(evt);
            }
        });
        jPanel12.add(btnThemNhatxuat);

        btnSuaNhapxuat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSuaNhapxuat.setText("Sửa");
        btnSuaNhapxuat.setEnabled(false);
        btnSuaNhapxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNhapxuatActionPerformed(evt);
            }
        });
        jPanel12.add(btnSuaNhapxuat);

        btnXoaNhapxuat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnXoaNhapxuat.setText("Xóa");
        btnXoaNhapxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNhapxuatActionPerformed(evt);
            }
        });
        jPanel12.add(btnXoaNhapxuat);

        btnUndoNhapxuat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnUndoNhapxuat.setText("Undo");
        btnUndoNhapxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUndoNhapxuatActionPerformed(evt);
            }
        });
        jPanel12.add(btnUndoNhapxuat);

        btnThoatNhanvien1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThoatNhanvien1.setText("Thoát");
        btnThoatNhanvien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatNhanvien1ActionPerformed(evt);
            }
        });
        jPanel12.add(btnThoatNhanvien1);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36)
                            .addComponent(Kho)
                            .addComponent(jLabel37))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tbMaPhieu)
                            .addComponent(tbNgayPhieu)
                            .addComponent(tbLoaiPhieu)
                            .addComponent(tbTenKH)
                            .addComponent(tbThanhTien)
                            .addComponent(tbNhanVienPhieu)
                            .addComponent(tbKhoPhieu))))
                .addContainerGap())
            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(tbMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(tbNgayPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(tbLoaiPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(tbTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(tbThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(tbNhanVienPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Kho)
                    .addComponent(tbKhoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lbThongBaoNX.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        lbThongBaoNX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThongBaoNX.setText(" ");

        javax.swing.GroupLayout inforAndButton1Layout = new javax.swing.GroupLayout(inforAndButton1);
        inforAndButton1.setLayout(inforAndButton1Layout);
        inforAndButton1Layout.setHorizontalGroup(
            inforAndButton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbThongBaoNX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        inforAndButton1Layout.setVerticalGroup(
            inforAndButton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inforAndButton1Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbThongBaoNX, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane4.setRightComponent(inforAndButton1);

        pnlDSNhanvien1.add(jSplitPane4, java.awt.BorderLayout.CENTER);

        frmNhapXuat.getContentPane().add(pnlDSNhanvien1);

        desktop.add(frmNhapXuat);

        frmThuChiReport.setTitle("Thống Kê Thu Chi");
        frmThuChiReport.setPreferredSize(new java.awt.Dimension(703, 360));
        frmThuChiReport.setVisible(false);
        frmThuChiReport.getContentPane().setLayout(new java.awt.CardLayout());

        pnlLogin3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlLogin3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pnlLogin3KeyPressed(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("THỐNG KÊ THU CHI");

        jPanel20.setPreferredSize(new java.awt.Dimension(740, 300));

        jButton7.setText("Preview");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Exit");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel60.setText("Đến ngày");

        jLabel61.setText("Từ ngày");

        buttonGroup1.add(radioHangNhap1);
        radioHangNhap1.setSelected(true);
        radioHangNhap1.setText("Thu");

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Chi");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(442, 442, 442)
                .addComponent(dpTo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(radioHangNhap1)
                .addGap(39, 39, 39)
                .addComponent(jRadioButton3)
                .addGap(275, 275, 275))
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addGap(28, 28, 28)
                        .addComponent(dpFrom2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioHangNhap1)
                    .addComponent(jRadioButton3))
                .addGap(13, 13, 13)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpFrom2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpTo2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlLogin3Layout = new javax.swing.GroupLayout(pnlLogin3);
        pnlLogin3.setLayout(pnlLogin3Layout);
        pnlLogin3Layout.setHorizontalGroup(
            pnlLogin3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
        );
        pnlLogin3Layout.setVerticalGroup(
            pnlLogin3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogin3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54)
                .addGap(24, 24, 24)
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
        );

        frmThuChiReport.getContentPane().add(pnlLogin3, "card2");

        desktop.add(frmThuChiReport);

        frmTongHopReport.setTitle("Thống Kê Thu Chi");
        frmTongHopReport.setVisible(false);
        frmTongHopReport.getContentPane().setLayout(new java.awt.CardLayout());

        pnlLogin4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlLogin4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pnlLogin4KeyPressed(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("THỐNG KÊ TỔNG HỢP");

        jButton9.setText("Preview");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Exit");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel62.setText("Đến ngày");

        jLabel63.setText("Từ ngày");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel63)
                        .addGap(28, 28, 28)
                        .addComponent(dpFrom3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dpTo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpFrom3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpTo3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlLogin4Layout = new javax.swing.GroupLayout(pnlLogin4);
        pnlLogin4.setLayout(pnlLogin4Layout);
        pnlLogin4Layout.setHorizontalGroup(
            pnlLogin4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlLogin4Layout.createSequentialGroup()
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlLogin4Layout.setVerticalGroup(
            pnlLogin4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogin4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel57)
                .addGap(24, 24, 24)
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        frmTongHopReport.getContentPane().add(pnlLogin4, "card2");

        desktop.add(frmTongHopReport);

        frmBackupRestore.setTitle("Backup/ Restore");
        frmBackupRestore.setVisible(false);
        frmBackupRestore.getContentPane().setLayout(new javax.swing.BoxLayout(frmBackupRestore.getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jButton1.setText("Restore");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Backup");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton11.setText("Thoát");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel26.add(jPanel27);

        tblBackup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Bảng", "Ngày giờ"
            }
        ));
        jScrollPane9.setViewportView(tblBackup);

        jPanel26.add(jScrollPane9);

        frmBackupRestore.getContentPane().add(jPanel26);

        desktop.add(frmBackupRestore);

        pnlMain.add(desktop, java.awt.BorderLayout.CENTER);

        menuDanhmuc.setText("Danh mục");

        mniVattu.setText("Vật tư");
        mniVattu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniVattuActionPerformed(evt);
            }
        });
        menuDanhmuc.add(mniVattu);

        mniKho.setText("Kho");
        mniKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniKhoActionPerformed(evt);
            }
        });
        menuDanhmuc.add(mniKho);

        menuNhanvien.setText("Nhân viên");
        menuNhanvien.setContentAreaFilled(false);
        menuNhanvien.setDelay(0);
        menuNhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuNhanvienMouseClicked(evt);
            }
        });
        menuNhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNhanvienActionPerformed(evt);
            }
        });
        menuDanhmuc.add(menuNhanvien);

        menubar.add(menuDanhmuc);

        menuThaotac.setText("Thao tác");

        miNhanvienReport.setText("In Nhân viên");
        miNhanvienReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miNhanvienReportActionPerformed(evt);
            }
        });
        menuThaotac.add(miNhanvienReport);

        miVattuReport.setText("In Vật tư");
        miVattuReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miVattuReportActionPerformed(evt);
            }
        });
        menuThaotac.add(miVattuReport);

        menubar.add(menuThaotac);

        menuNghiepvu.setText("Nghiệp vụ");

        mniNhapXuat.setText("Phiếu Nhập/Xuất");
        mniNhapXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniNhapXuatActionPerformed(evt);
            }
        });
        menuNghiepvu.add(mniNhapXuat);

        mniThuChi.setText("Phiếu Thu/Chi");
        mniThuChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniThuChiActionPerformed(evt);
            }
        });
        menuNghiepvu.add(mniThuChi);

        menubar.add(menuNghiepvu);

        menuThongke.setText("Thống kê");

        miHangNhapReport.setText("Hàng nhập / xuất");
        miHangNhapReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miHangNhapReportActionPerformed(evt);
            }
        });
        menuThongke.add(miHangNhapReport);

        jMenuItem5.setText("Bảng kê Thu / chi");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        menuThongke.add(jMenuItem5);

        jMenuItem7.setText("Hoạt động Nhân viên");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        menuThongke.add(jMenuItem7);

        jMenuItem8.setText("Tổng hợp Nhập xuất");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        menuThongke.add(jMenuItem8);

        menubar.add(menuThongke);

        menuTaiKhoan.setText("Quản trị");

        mniChangePass.setText("Đổi mật khẩu");
        mniChangePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniChangePassActionPerformed(evt);
            }
        });
        menuTaiKhoan.add(mniChangePass);

        mniAccount.setText("Thêm/Xóa tài khoản");
        mniAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniAccountActionPerformed(evt);
            }
        });
        menuTaiKhoan.add(mniAccount);

        miBackupRestore.setText("Backup/Restore");
        miBackupRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miBackupRestoreActionPerformed(evt);
            }
        });
        menuTaiKhoan.add(miBackupRestore);

        mniDangXuat.setText("Đăng xuất");
        mniDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangXuatActionPerformed(evt);
            }
        });
        menuTaiKhoan.add(mniDangXuat);

        menubar.add(menuTaiKhoan);

        setJMenuBar(menubar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 2112, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(pnlStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        if (username.isEmpty()) {
            displayToolTip(txtUsername, MESSAGE_EMPTY_LOGINNAME);
            return;
        }

        if (password.isEmpty()) {
            displayToolTip(txtPassword, MESSAGE_EMPTY_PASSWORD);
            return;
        }

        lbStatus.setText("Loading...");
        try {
            account = accountDAO.login(username, password);

            loginView(false);
            displayUiWithRole();
        } catch (SQLException e) {
            lbStatus.setText(MESSAGE_LOGIN_FAIL);
        }
    }//GEN-LAST:event_btnDangNhapActionPerformed

    public void loginView(boolean enable) {
        //MyJDBC.instance.dispose();
        frmLogin.setVisible(enable);
        setVisibleAllMenuBar(!enable);

        if (!enable) {
            //DANG NHAP THANH CONG
            lbStatus.setText(MESSAGE_LOGIN_SUCCESS + account.getHoTen() + " - " + account.getRole());
            txtPassword.setText("");
            txtUsername.setText("");
        } else {

            lbStatus.setText(MESSAGE_LOGIN_WELCOME);
        }

        frmKho.setVisible(false);
        frmNhanvien.setVisible(false);
        frmVattu.setVisible(false);
        frmAccount.setVisible(false);
        frmChangePass.setVisible(false);
        frmNhapXuat.setVisible(false);
        frmNewNhapXuat.setVisible(false);
        frmHoatDongNhanvien.setVisible(false);
        frmHangNXReport.setVisible(false);
        frmThuchi.setVisible(false);
        frmThuChiReport.setVisible(false);
        frmTongHopReport.setVisible(false);
        frmBackupRestore.setVisible(false);
    }

    public void setVisibleAllMenuBar(boolean enable) {
        Component[] combonents = menubar.getComponents();
        for (Component combonent : combonents) {
            combonent.setEnabled(enable);
        }

    }

    public void setVisibleAccessMenubar(boolean enable) {
        mniAccount.setEnabled(enable);
    }

    public void setVisibleThaoTacMenubar(boolean enable) {
        menuThaotac.setEnabled(enable);
        Component[] combonents = menuThaotac.getComponents();
        for (Component combonent : combonents) {
            combonent.setEnabled(enable);
        }
    }

    public void setVisibleNghiepvuMenubar(boolean enable) {
        menuNghiepvu.setEnabled(enable);
        Component[] combonents = menuNghiepvu.getComponents();
        for (Component combonent : combonents) {
            combonent.setEnabled(enable);
        }
    }

    public void setVisibleThongkeMenubar(boolean enable) {
        menuThongke.setEnabled(enable);
        Component[] combonents = menuThongke.getComponents();
        for (Component combonent : combonents) {
            combonent.setEnabled(enable);
        }
    }

    public void setVisibleDanhmucMenubar(boolean enable) {
        menuDanhmuc.setEnabled(enable);
        Component[] combonents = menuDanhmuc.getComponents();
        for (Component combonent : combonents) {
            combonent.setEnabled(enable);
        }

        menuThongke.setEnabled(enable);
    }

    public void displayUiWithRole() {
        setVisibleAllMenuBar(false);
        menuTaiKhoan.setEnabled(true);
        mniDangXuat.setEnabled(true);
        mniChangePass.setEnabled(true);

        try {
            List<String> list = accountDAO.getRole(account.getUsername());
            for (String role : list) {
                switch (role.trim()) {
                    case "db_securityadmin":
                        break;
                    case "db_datareader":

                    case "db_datawriter":
                        setVisibleDanhmucMenubar(true);
                        setVisibleNghiepvuMenubar(true);
                        setVisibleThaoTacMenubar(true);
                        setVisibleThongkeMenubar(true);
                        mniAccount.setEnabled(false);
                        miBackupRestore.setEnabled(false);
                        break;
                    case "db_owner":
                        setVisibleAllMenuBar(true);
                        break;
                    case "db_accessadmin":
                        setVisibleAccessMenubar(true);
                        break;
                }
            }
        } catch (SQLException e) {
            sqlException(e);
        } catch (Exception e) {
            errorProgram(e);
        }
    }

    private void mniChangePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniChangePassActionPerformed
        frmChangePass.setVisible(true);
    }//GEN-LAST:event_mniChangePassActionPerformed

    private void btnThoatChangePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatChangePassActionPerformed
        frmChangePass.setVisible(false);
    }//GEN-LAST:event_btnThoatChangePassActionPerformed

    private void btnChangePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePassActionPerformed
        String pass1 = tbNewPass1.getText();
        String pass2 = tbNewPass2.getText();
        if (pass1.isEmpty()) {
            lbStatusTaikhoan.setText(MESSAGE_EMPTY_PASSWORD);
            tbNewPass1.requestFocus();
            displayToolTip(tbNewPass1, MESSAGE_EMPTY_PASSWORD);
            return;
        }
        if (pass2.isEmpty()) {
            lbStatusTaikhoan.setText(MESSAGE_EMPTY_PASSWORD);
            tbNewPass2.requestFocus();
            displayToolTip(tbxPassTaiKhoan, MESSAGE_EMPTY_PASSWORD);
            return;
        }

        if (pass1.equals(pass2)) {
            try {
                accountDAO.changePassword(account.getLoginname(), pass1);
                dlChangePass.setVisible(false);
                lbStatus.setText(MESSAGE_CHANGE_PASSWORD_SUCCESS);
                tbNewPass1.setText("");
                tbNewPass2.setText("");
            } catch (SQLException e) {
                sqlException(e);
            }
        } else {
            lbDialogStatus.setText(MESSAGE_WRONG_VERIFY_PASSWORD);
            tbNewPass2.setText("");
            tbNewPass2.requestFocus();
        }
    }//GEN-LAST:event_btnChangePassActionPerformed

    private void mniDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangXuatActionPerformed

        loginView(true);
    }//GEN-LAST:event_mniDangXuatActionPerformed

    private void mniVattuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniVattuActionPerformed
        frmVattu.setVisible(true);
        //frmVattu.show();
        clearVattuUI();
        loadTableData(tblVattu, vattuDAO, VATTU_TABLE_HEADERS, tbTimKiemVattu);

    }//GEN-LAST:event_mniVattuActionPerformed

    private void mniKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniKhoActionPerformed
        frmKho.setVisible(true);
        //frmVattu.show();
        clearKhoUI();
        loadTableData(tblKho, khoDAO, KHO_TABLE_HEADERS, tbxTimKiemKho);
    }//GEN-LAST:event_mniKhoActionPerformed

    Map<Integer, Integer> nhanvienIndex = new HashMap<Integer, Integer>();

    private void mniAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniAccountActionPerformed

        try {

            List<NhanvienBean> employees = employeeDAO.getEmployees();
            List<String> list = new ArrayList<String>();
            for (NhanvienBean employee : employees) {
                nhanvienIndex.put(list.size(), employee.getManv());
                list.add(employee.getHo() + " " + employee.getTen());
            }

            selectNhanvien(0);

            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(list.toArray());
            cbxNhanvien.setModel(comboBoxModel);
            frmAccount.setVisible(true);

        } catch (SQLException e) {
            sqlException(e);
        } catch (Exception e) {
            errorProgram(e);
        }
    }//GEN-LAST:event_mniAccountActionPerformed

    public void selectNhanvien(int index) {
        try {
            resetFormTaiKhoan();

            int manv = nhanvienIndex.get(index);

            lbStatusTaikhoan.setText(cbxNhanvien.getSelectedItem() + "");
            tbxMaNVTaiKhoan.setText(manv + "");

            AccountBean account = accountDAO.getLogin(manv);
            tbxLoginnameTaiKhoan.setText(account.getLoginname());
            switch (account.getRole()) {
                case "ADMIN":
                    rbtnAdmin.setSelected(true);
                    break;
                case "USER":
                    rbtnUser.setSelected(true);
                    break;
            }
            btnThoatTaiKhoan.setEnabled(true);
            btnXoaTaiKhoan.setEnabled(true);
            btnTaoTaiKhoan.setEnabled(false);
        } catch (SQLException e) {
            sqlException(e);
        } catch (NonLoginException e) {
            btnTaoTaiKhoan.setVisible(true);
        }
    }

    private void cbxNhanvienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxNhanvienItemStateChanged
        int name = cbxNhanvien.getSelectedIndex();
        selectNhanvien(name);
    }//GEN-LAST:event_cbxNhanvienItemStateChanged

    private void btnTaoTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoTaiKhoanActionPerformed
        String loginname = tbxLoginnameTaiKhoan.getText();
        String pass = tbxPassTaiKhoan.getText();
        String role = null;
        if (rbtnAdmin.isSelected()) {
            role = "ADMIN";
        } else {
            role = "USER";
        }
        int manv = Integer.parseInt(tbxMaNVTaiKhoan.getText());

        if (loginname.isEmpty()) {
            lbStatusTaikhoan.setText(MESSAGE_EMPTY_LOGINNAME);
            tbxLoginnameTaiKhoan.requestFocus();
            displayToolTip(tbxLoginnameTaiKhoan, MESSAGE_EMPTY_LOGINNAME);
            return;
        } else if (pass.isEmpty()) {
            lbStatusTaikhoan.setText(MESSAGE_EMPTY_PASSWORD);
            tbxPassTaiKhoan.requestFocus();
            displayToolTip(tbxPassTaiKhoan, MESSAGE_EMPTY_PASSWORD);
            return;
        }

        try {
            accountDAO.signUp(loginname, pass, manv, role);
            lbStatusTaikhoan.setText(MESSAGE_SIGNUP_SUCCESS);
            selectNhanvien(cbxNhanvien.getSelectedIndex());
        } catch (SQLException e) {
            sqlException(e);
        } catch (DupplicateLoginException e) {
            lbStatusTaikhoan.setText(MESSAGE_DUPPLICATE_LOGINNAME);
        } catch (DupplicateUserException e) {
            lbStatusTaikhoan.setText(MESSAGE_DUPPLICATE_USERNAME);
        }

    }//GEN-LAST:event_btnTaoTaiKhoanActionPerformed

    private void btnXoaTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTaiKhoanActionPerformed
        try {

            String loginname = tbxLoginnameTaiKhoan.getText();
            if (confirmDeleteDialog(frmAccount, loginname)) {
                accountDAO.deleteAccount(loginname);
                selectNhanvien(cbxNhanvien.getSelectedIndex());
                lbStatusTaikhoan.setText(MESSAGE_DELETE_SUCCESS);
            }
        } catch (SQLException ex) {
            sqlException(ex);
        }
    }//GEN-LAST:event_btnXoaTaiKhoanActionPerformed

    private void btnThoatTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatTaiKhoanActionPerformed
        frmAccount.dispose();
    }//GEN-LAST:event_btnThoatTaiKhoanActionPerformed

    private void menuNhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuNhanvienMouseClicked
        frmNhanvien.setVisible(true);
        clearNhanvienUI();
        loadTableData(tblDSNhanvien, employeeDAO, EMPLOYEE_TABLE_HEADERS, tbxTimKiemNhanvien);
    }//GEN-LAST:event_menuNhanvienMouseClicked

    private void loadTableData(JTable table, InterfaceObjectDAO DAO, String[] headers, JTextComponent searchTextField) {
        try {
            int selectedRow = table.getSelectedRow();
            Vector header = new Vector<String>();
            for (String h : headers) {
                header.add(h);
            }
            Vector tableData;
            String searchText = searchTextField.getText().trim();
            if (searchText.isEmpty()) {
                tableData = DAO.get();
            } else {
                tableData = DAO.search(searchText);
            }
            table.setModel(new DefaultTableModel(tableData, header) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            if (selectedRow >= 0 && selectedRow < table.getRowCount()) {
                table.setRowSelectionInterval(selectedRow, selectedRow);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            loginView(true);
        }
    }

    private void pnlLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnlLoginKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnDangNhap.doClick();
        }
    }//GEN-LAST:event_pnlLoginKeyPressed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnDangNhap.doClick();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void txtUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnDangNhap.doClick();
        }
    }//GEN-LAST:event_txtUsernameKeyPressed

    private void dlChangePassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dlChangePassKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnChangePass.doClick();
        }
    }//GEN-LAST:event_dlChangePassKeyPressed

    private void tbNewPass2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNewPass2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnChangePass.doClick();
        }
    }//GEN-LAST:event_tbNewPass2KeyPressed

    private void tbNewPass1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNewPass1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnChangePass.doClick();
        }
    }//GEN-LAST:event_tbNewPass1KeyPressed


    private void tbTimKiemVattuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTimKiemVattuKeyReleased
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            btnTimkiemVattu.doClick();
        }
    }//GEN-LAST:event_tbTimKiemVattuKeyReleased

    private void tblVattuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVattuMouseClicked
        selectVattu();
    }//GEN-LAST:event_tblVattuMouseClicked
    private void selectVattu() {
        try {
            int row = tblVattu.getSelectedRow();
            String mavt = (String) tblVattu.getValueAt(row, 0);
            VattuBean nhanvienBean = vattuDAO.get(mavt);

            displayVattu(nhanvienBean);
            lbThongbaoVatTu.setText("");
        } catch (SQLException e) {
            sqlException(e);
        } catch (Exception ex) {
            errorProgram(ex);
        }
    }

    private void tbxMavtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbxMavtFocusLost
        getAndDisplayVattu();
    }//GEN-LAST:event_tbxMavtFocusLost

    private void tbxMavtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbxMavtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbxMavtKeyPressed

    private void tbxMavtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbxMavtKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbxMavtKeyReleased

    private void tbxTenVattuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbxTenVattuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbxTenVattuKeyReleased

    private void btnThemVattuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVattuActionPerformed
        clearVattuUI();
    }//GEN-LAST:event_btnThemVattuActionPerformed

    private void btnLuuVattuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuVattuActionPerformed
        if (tenVattuIsExisted()) {
            return;
        }

        String mavt = tbxMavt.getText().trim();
        String dvt = tbxDvt.getText().trim();
        String ten = tbxTenVattu.getText().trim();

        if (mavt.isEmpty()) {
            displayError(tbxMavt, TOOLTIP_MAVT);
            return;
        }
        dvt = dvt.isEmpty() ? null : dvt;
        ten = ten.isEmpty() ? null : ten;

        try {
            VattuBean bean = new VattuBean(mavt, ten, dvt);
            VattuBean undoBean = vattuDAO.get(mavt);

            int retr = vattuDAO.save(bean);
            if (retr == Constrains.CODE_SQL_INSERT_SUCCESS) {
                //INSERT
                lbThongbaoVatTu.setText(MESSAGE_INSERT_SUCCESS);
                //Undo
                undoList.push(new Object[]{Constrains.ACTION_INSERT, bean});

            } else if (retr == Constrains.CODE_SQL_UPDATE_SUCCESS) {
                //UPDATE
                lbThongbaoVatTu.setText(MESSAGE_UPDATE_SUCCESS);
                //Undo
                undoList.push(new Object[]{Constrains.ACTION_UPDATE, undoBean});
            } else {
                //FAIL
            }
            loadTableData(tblVattu, vattuDAO, VATTU_TABLE_HEADERS, tbTimKiemVattu);

        } catch (SQLException ex) {
            ex.printStackTrace();
            if (ex.getMessage().contains("key")) {
                displayError(tbxMavt, TOOLTIP_MAVT);
            } else if (ex.getMessage().contains("TENVT")) {
                displayError(tbxTenVattu, TOOLTIP_TENVT);
            } else {
                sqlException(ex);
            }
        }
    }//GEN-LAST:event_btnLuuVattuActionPerformed

    private void btnXoaVattuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaVattuActionPerformed

        try {
            String mavt = tbxMavt.getText();
            if (confirmDeleteDialog(frmVattu, mavt) && !mavt.isEmpty()) {

                VattuBean undoBean = vattuDAO.get(mavt);
                int result = vattuDAO.delete(mavt);
                if (result == Constrains.CODE_SQL_FAIL) {
                    lbThongbaoVatTu.setText(MESSAGE_NOT_ALLOW_DELETE);
                } else {
                    lbThongbaoVatTu.setText(MESSAGE_DELETE_SUCCESS);
                    loadTableData(tblVattu, vattuDAO, VATTU_TABLE_HEADERS, tbTimKiemVattu);
                    clearVattuUI();

                    //Undo
                    undoList.push(new Object[]{Constrains.ACTION_DELETE, undoBean});
                }
            }

        } catch (SQLException ex) {
            loginView(true);
        }
    }//GEN-LAST:event_btnXoaVattuActionPerformed

    private void btnUndoVattuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUndoVattuActionPerformed
        //displayVattu((VattuBean) selectedBean);
        undo(vattuDAO);
        loadTableData(tblVattu, vattuDAO, VATTU_TABLE_HEADERS, tbTimKiemVattu);
    }//GEN-LAST:event_btnUndoVattuActionPerformed


    private void btnThoatVattuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatVattuActionPerformed
        loginView(false);
    }//GEN-LAST:event_btnThoatVattuActionPerformed

    private void btnThoatNhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatNhanvienActionPerformed
        loginView(false);
    }//GEN-LAST:event_btnThoatNhanvienActionPerformed

    private void btnUndoNhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUndoNhanvienActionPerformed
        // displayEmployee((NhanvienBean) selectedBean);
        undo(employeeDAO);
        loadTableData(tblDSNhanvien, employeeDAO, EMPLOYEE_TABLE_HEADERS, tbxTimKiemNhanvien);
    }//GEN-LAST:event_btnUndoNhanvienActionPerformed

    private void btnXoaNhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNhanvienActionPerformed
        String manv = tbxMaNv.getText().trim();

        if (confirmDeleteDialog(frmNhanvien, manv)) {
            int result = -1;
            try {
                NhanvienBean bean = employeeDAO.get(manv);
                undoList.push(new Object[]{Constrains.ACTION_DELETE, bean});
                result = employeeDAO.delete(manv);
            } catch (SQLException ex) {
            }
            if (result == Constrains.CODE_SQL_FAIL) {
                lbThongbaoNV.setText(MESSAGE_NOT_ALLOW_DELETE);
            } else {
                lbThongbaoNV.setText(MESSAGE_DELETE_SUCCESS);
                loadTableData(tblDSNhanvien, employeeDAO, EMPLOYEE_TABLE_HEADERS, tbxTimKiemNhanvien);
                clearNhanvienUI();
            }
        }
    }//GEN-LAST:event_btnXoaNhanvienActionPerformed

    private void btnLuuNhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuNhanvienActionPerformed
        String ghichu = tbxGhichu.getText().trim();
        String ho = tbxHo.getText().trim();
        String ten = tbxTen.getText().trim();
        String diachi = tbxDiachi.getText().trim();
        Date ngaysinh = null;
        Float luong = null;
        Integer manv = null;
        try {
            manv = Integer.parseInt(tbxMaNv.getText());
        } catch (Exception e) {
            displayError(tbxMaNv, TOOLTIP_MANV);
            return;
        }

        try {
            ngaysinh = new Date(tbxNgaySinh.getText());
        } catch (Exception e) {
            displayError(tbxNgaySinh, TOOLTIP_NGAYSINH);
            return;
        }

        try {
            luong = Float.parseFloat(tbxLuong.getText());
        } catch (Exception e) {
            displayError(tbxLuong, TOOLTIP_LUONG);
            return;
        }

        ho = ho.isEmpty() ? null : ho;
        ten = ten.isEmpty() ? null : ten;
        diachi = diachi.isEmpty() ? null : diachi;

        try {
            NhanvienBean bean = new NhanvienBean(manv, ho, ten, diachi, ngaysinh, luong, ghichu);

            int retr = employeeDAO.save(bean);
            if (retr == 0) {
                //INSERT
                lbStatus.setText(MESSAGE_INSERT_SUCCESS);

                //Them du lieu vao Undo
                undoList.push(new Object[]{Constrains.ACTION_INSERT, bean});
            } else {
                //UPDATE
                lbStatus.setText(MESSAGE_UPDATE_SUCCESS);
                undoList.push(new Object[]{Constrains.ACTION_UPDATE, bean});
            }
            loadTableData(tblDSNhanvien, employeeDAO, EMPLOYEE_TABLE_HEADERS, tbxTimKiemNhanvien);

        } catch (SQLException ex) {
            ex.printStackTrace();
            if (ex.getMessage().contains("LUONG")) {
                displayError(tbxLuong, TOOLTIP_LUONG);
            } else if (ex.getMessage().contains("key")) {
                displayError(tbxMaNv, TOOLTIP_MANV);
            } else {
                loginView(true);
            }

        }
    }//GEN-LAST:event_btnLuuNhanvienActionPerformed

    private void displayError(JTextComponent com, String text) {
        com.requestFocus();
        lbStatus.setText(text);
        displayToolTip(com, text);
    }

    private void btnThemNhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanvienActionPerformed
        clearNhanvienUI();
        loadTableData(tblDSNhanvien, employeeDAO, EMPLOYEE_TABLE_HEADERS, tbxTimKiemNhanvien);
    }//GEN-LAST:event_btnThemNhanvienActionPerformed

    private void tbxLuongFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbxLuongFocusLost
        //check luong
        try {
            float luong = Float.parseFloat(tbxLuong.getText());
        } catch (NumberFormatException e) {
            displayError(tbxLuong, TOOLTIP_LUONG);
        }
    }//GEN-LAST:event_tbxLuongFocusLost

    private void tbxNgaySinhFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbxNgaySinhFocusLost
        String regNo1 = tbxNgaySinh.getText();
        Pattern pattern1 = Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher matcher1 = pattern1.matcher(regNo1);

        if (!matcher1.matches()) {
            displayError(tbxNgaySinh, TOOLTIP_NGAYSINH);

        }
    }//GEN-LAST:event_tbxNgaySinhFocusLost

    private void tbxTenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbxTenKeyReleased

    }//GEN-LAST:event_tbxTenKeyReleased

    private void tbxHoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbxHoKeyReleased

    }//GEN-LAST:event_tbxHoKeyReleased

    private void tbxMaNvKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbxMaNvKeyReleased

    }//GEN-LAST:event_tbxMaNvKeyReleased

    private void tbxMaNvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbxMaNvKeyPressed
        String regNo1 = tbxMaNv.getText();
        Pattern pattern1 = Pattern.compile("[0-9]");
        Matcher matcher1 = pattern1.matcher(regNo1);

        if (!matcher1.hitEnd()) {
            displayError(tbxMaNv, TOOLTIP_MANV);

            //    tbxMaNv.setText(regNo1.deleteCharAt(regNo1.length()-1).toString());
        }
    }//GEN-LAST:event_tbxMaNvKeyPressed

    private void tbxMaNvFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbxMaNvFocusLost
        //Check mã nhân viên có phải là số nguyên không
        try {
            int manv = Integer.parseInt(tbxMaNv.getText());
            if (manv < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            displayError(tbxMaNv, TOOLTIP_MANV);
        }
    }//GEN-LAST:event_tbxMaNvFocusLost

    private void tblDSNhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSNhanvienMouseClicked
        selectNhanvien();
        loadTableData(tblDSNhanvien, employeeDAO, EMPLOYEE_TABLE_HEADERS, tbxTimKiemNhanvien);
    }//GEN-LAST:event_tblDSNhanvienMouseClicked
    NhanvienBean nhanvienbean;

    private void selectNhanvien() {
        try {
            int row = tblDSNhanvien.getSelectedRow();
            if (row < 0) {
                return;
            }
            System.out.println("com.vtung.ui.TEST.selectNhanvien() ROW " + row);
            int manv = (int) tblDSNhanvien.getValueAt(row, 0);
            NhanvienBean nhanvienBean = employeeDAO.get(manv + "");
            nhanvienBean = nhanvienBean;

            displayEmployee(nhanvienBean);
            lbThongbaoNV.setText("");
        } catch (SQLException ex) {
            sqlException(ex);
        }
    }

    private void tbxTimKiemNhanvienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbxTimKiemNhanvienKeyReleased
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            btnTimkiemNhanvien.doClick();
        }
    }//GEN-LAST:event_tbxTimKiemNhanvienKeyReleased

    private void menuNhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNhanvienActionPerformed
        frmNhanvien.setVisible(true);
        clearNhanvienUI();
        loadTableData(tblDSNhanvien, employeeDAO, EMPLOYEE_TABLE_HEADERS, tbxTimKiemNhanvien);
        undoList.clear();
    }//GEN-LAST:event_menuNhanvienActionPerformed

    private void tbxTimKiemKhoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbxTimKiemKhoKeyReleased
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            btnThoatKho.doClick();
        }
    }//GEN-LAST:event_tbxTimKiemKhoKeyReleased

    private void tblKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhoMouseClicked
        clickKho();
    }//GEN-LAST:event_tblKhoMouseClicked

    private void selectKho() {
        try {
            int row = tblKho.getSelectedRow();
            String mavt = (String) tblKho.getValueAt(row, 0);
            KhoBean khoBean = (KhoBean) khoDAO.get(mavt);

            displayKho(khoBean);
            lbThongBaoKho.setText("");

        } catch (SQLException ex) {
            loginView(true);
        }
    }

    ;
    
    private void btnThemKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKhoActionPerformed
        clearKhoUI();
        loadTableData(tblKho, khoDAO, KHO_TABLE_HEADERS, tbxTimKiemKho);
    }//GEN-LAST:event_btnThemKhoActionPerformed

    private void btnLuuKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuKhoActionPerformed
        String ma = tbxMakho.getText().trim();
        String diachi = tbxDiachiKho.getText().trim();
        String ten = tbxTenKho.getText().trim();

        KhoBean bean = new KhoBean(ma, ten, diachi);
        if (bean.getMakho().isEmpty()) {
            displayError(tbxMakho, TOOLTIP_MAKHO);
            lbThongBaoKho.setText(TOOLTIP_MAKHO);
            return;
        }

        diachi = diachi.isEmpty() ? null : diachi;
        ten = ten.isEmpty() ? null : ten;

        try {

            KhoBean undoBean = khoDAO.get(ma);

            int retr = khoDAO.save(bean);
            if (retr == 0) {
                //INSERT
                lbStatus.setText(MESSAGE_INSERT_SUCCESS);

                //Undo
                undoList.push(new Object[]{Constrains.ACTION_INSERT, bean});
            } else {
                //UPDATE
                lbStatus.setText(MESSAGE_UPDATE_SUCCESS);
                //Undo
                undoList.push(new Object[]{Constrains.ACTION_UPDATE, undoBean});

            }
            loadTableData(tblKho, khoDAO, KHO_TABLE_HEADERS, tbxTimKiemKho);

        } catch (SQLException ex) {
            ex.printStackTrace();
            if (ex.getMessage().contains("key") || ex.getMessage().contains("MAKHO")) {
                tbxMakho.requestFocus();
                displayToolTip(tbxMakho, TOOLTIP_MAKHO);
            } else if (ex.getMessage().contains("TENKHO")) {
                tbxTenKho.requestFocus();
                displayToolTip(tbxTenKho, TOOLTIP_TENKHO);
            } else if (ex.getMessage().contains("DIACHI")) {
                System.out.println("DIACHI");
                tbxDiachiKho.requestFocus();
                displayToolTip(tbxDiachiKho, TOOLTIP_DIACHI);
            } else {
                loginView(true);
            }

        }
    }//GEN-LAST:event_btnLuuKhoActionPerformed


    private void btnXoaKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKhoActionPerformed
        try {
            String makho = tbxMakho.getText().trim();
            if (confirmDeleteDialog(frmKho, makho) && !makho.isEmpty()) {
                KhoBean bean = khoDAO.get(makho);

                int resul = khoDAO.delete(makho);
                if (resul == Constrains.CODE_SQL_FAIL) {
                    lbThongBaoKho.setText(MESSAGE_NOT_ALLOW_DELETE);
                } else {
                    lbThongBaoKho.setText(MESSAGE_DELETE_SUCCESS);
                    loadTableData(tblKho, khoDAO, KHO_TABLE_HEADERS, tbxTimKiemKho);
                    clearKhoUI();
                    //Undo
                    undoList.push(new Object[]{Constrains.ACTION_DELETE, bean});

                }
            }

        } catch (SQLException ex) {
            sqlException(ex);
        }

    }//GEN-LAST:event_btnXoaKhoActionPerformed

    private boolean confirmDeleteDialog(Component root, String mess) {
        int retr = JOptionPane.showConfirmDialog(root, MESSAGE_CONFIRM_DELETE + "<p style=\"color:blue\"> " + mess + " </p> </html>", TITLE_DELETE, JOptionPane.YES_NO_OPTION);
        if (retr == JOptionPane.OK_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    private void btnUndoKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUndoKhoActionPerformed
        undo(khoDAO);
        loadTableData(tblKho, khoDAO, KHO_TABLE_HEADERS, tbxTimKiemKho);

    }//GEN-LAST:event_btnUndoKhoActionPerformed

    private void btnThoatKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatKhoActionPerformed
        frmKho.setVisible(false);
    }//GEN-LAST:event_btnThoatKhoActionPerformed

    private void tbxMakhoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbxMakhoFocusLost
        getAndDisplayKho();
    }//GEN-LAST:event_tbxMakhoFocusLost

    private void tbxMakhoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbxMakhoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbxMakhoKeyPressed

    private void tbxMakhoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbxMakhoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbxMakhoKeyReleased

    private void tbxTenKhoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbxTenKhoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbxTenKhoKeyReleased

    private void tbTimKiemVattuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbTimKiemVattuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTimKiemVattuActionPerformed

    private void btnTimkiemNhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemNhanvienActionPerformed
        loadTableData(tblDSNhanvien, employeeDAO, EMPLOYEE_TABLE_HEADERS, tbxTimKiemNhanvien);
    }//GEN-LAST:event_btnTimkiemNhanvienActionPerformed

    private void btnTimkiemKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemKhoActionPerformed
        loadTableData(tblKho, khoDAO, KHO_TABLE_HEADERS, tbxTimKiemKho);
    }//GEN-LAST:event_btnTimkiemKhoActionPerformed

    private void btnTimkiemVattuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemVattuActionPerformed
        loadTableData(tblVattu, vattuDAO, VATTU_TABLE_HEADERS, tbTimKiemVattu);
    }//GEN-LAST:event_btnTimkiemVattuActionPerformed

    private void tbTimKiemNhapXuatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTimKiemNhapXuatKeyReleased
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            btnTimkiemNhapxuat.doClick();
        }
    }//GEN-LAST:event_tbTimKiemNhapXuatKeyReleased

    private void btnTimkiemNhapxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemNhapxuatActionPerformed
        loadTableData(tblDSNhapXuat, nhapxuatDAO, NHAPXUAT_TABLE_HEADERS, tbTimKiemNhapXuat);
    }//GEN-LAST:event_btnTimkiemNhapxuatActionPerformed

    private void tblDSNhapXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSNhapXuatMouseClicked
        selectPhieuNhapxuat();
        loadTableData(tblDSNhapXuat, nhapxuatDAO, NHAPXUAT_TABLE_HEADERS, tbTimKiemNhapXuat);
    }//GEN-LAST:event_tblDSNhapXuatMouseClicked

    private void selectPhieuNhapxuat() {
        try {
            int row = tblDSNhapXuat.getSelectedRow();
            String maphieu = (String) tblDSNhapXuat.getValueAt(row, 0);

            PhatSinhBean phieu = new PhatSinhBean();
            phieu.setMaphieu(maphieu);

            List<CTPhatSinhBean> ctPhieu = nhapxuatDAO.get(phieu);
            displayNhapxuat(phieu, ctPhieu);

        } catch (SQLException ex) {
            ex.printStackTrace();
            loginView(true);
        }
    }

    private void selectPhieuThuchi() {
        try {
            int row = tblDSThuchi.getSelectedRow();
            String maphieu = (String) tblDSThuchi.getValueAt(row, 0);

            PhatSinhBean phieu = new PhatSinhBean();
            phieu.setMaphieu(maphieu);

            thuchiDAO.get(phieu);
            displayThuchi(phieu);
            lbThongBaoTC.setText("");
        } catch (SQLException ex) {
            sqlException(ex);
        }
    }

    private void btnThemNhatxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhatxuatActionPerformed
        try {
            //Tao combobox danh sach vat tu trong bang
            JComboBox comboBox = new JComboBox();
            List vattuList = vattuDAO.getVattus();
            VattuBean bean = null;
            for (int i = 0; i < vattuList.size(); i++) {
                bean = (VattuBean) vattuList.get(i);
                comboBox.addItem(bean);
            }

            tblCTPhatsinh_Phieu_Insert.setModel(new CT_NhapXuatTableModel(new ArrayList<CTPhatSinhBean>(), tbThanhtien_Phieu_Insert));

            TableColumn vattuCollumn = tblCTPhatsinh_Phieu_Insert.getColumnModel().getColumn(2);
            vattuCollumn.setCellEditor(new DefaultCellEditor(comboBox));

            clearNhapxuatUI_Insert();

            frmNewNhapXuat.setVisible(true);
        } catch (SQLException e) {
            sqlException(e);
        }

    }//GEN-LAST:event_btnThemNhatxuatActionPerformed

    private void btnSuaNhapxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNhapxuatActionPerformed
        btnThemNhatxuatActionPerformed(evt);
        try {
            String maphieu = tbMaPhieu.getText().trim();

            PhatSinhBean phieu = new PhatSinhBean();
            phieu.setMaphieu(maphieu);
            List<CTPhatSinhBean> ctPhieu = nhapxuatDAO.get(phieu);
            displayNhapxuatTrongNew(phieu, ctPhieu);

        } catch (SQLException ex) {
            sqlException(ex);
        }
    }//GEN-LAST:event_btnSuaNhapxuatActionPerformed

    private void btnXoaNhapxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNhapxuatActionPerformed
        try {
            String maphieu = tbMaPhieu.getText().trim();
            if (confirmDeleteDialog(frmNhapXuat, maphieu) && !maphieu.isEmpty()) {

                PhatSinhBean undoBean = new PhatSinhBean();
                undoBean.setMaphieu(maphieu);
                List<CTPhatSinhBean> undoList = nhapxuatDAO.get(undoBean);

                lbThongBaoNX.setText(MESSAGE_DELETE_SUCCESS);
                nhapxuatDAO.delete(maphieu);
                loadTableData(tblDSNhapXuat, nhapxuatDAO, NHAPXUAT_TABLE_HEADERS, tbTimKiemNhapXuat);
                clearNhapxuatUI();

                //Undo
                this.undoList.push(new Object[]{Constrains.ACTION_DELETE, undoBean, undoList});

            }

        } catch (SQLException ex) {
            sqlException(ex);
        }
    }//GEN-LAST:event_btnXoaNhapxuatActionPerformed

    private void recordAction(int action, Object bean) {
        Object[] ob = new Object[2];
        ob[0] = action;
        ob[1] = bean;
        undoList.push(ob);
    }

    private void btnUndoNhapxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUndoNhapxuatActionPerformed
        undo(nhapxuatDAO);
        loadTableData(tblDSNhapXuat, nhapxuatDAO, NHAPXUAT_TABLE_HEADERS, tbTimKiemNhapXuat);

    }//GEN-LAST:event_btnUndoNhapxuatActionPerformed

    private void undo(InterfaceObjectDAO dao) {
        try {
            if (undoList.empty()) {
                return;
            }
            Object[] ob = undoList.pop();
            if (ob == null) {
                return;
            }
            int action = (int) ob[0];
            String actionName = "";
            switch (action) {
                case Constrains.ACTION_DELETE:
                    if (dao instanceof NhapxuatDAO) {
                        ((NhapxuatDAO) dao).save((PhatSinhBean) ob[1], (List<CTPhatSinhBean>) ob[2]);
                    } else {
                        dao.save(ob[1]);
                    }

                    actionName = "DELETE";
                    break;
                case Constrains.ACTION_INSERT:
                    CommonObject o = (CommonObject) ob[1];
                    dao.delete(o.getId());
                    actionName = "INSERT";
                    break;
                case Constrains.ACTION_UPDATE:
                    if (dao instanceof NhapxuatDAO) {
                        ((NhapxuatDAO) dao).save((PhatSinhBean) ob[1], (List<CTPhatSinhBean>) ob[2]);
                    } else {
                        dao.save(ob[1]);
                    }
                    actionName = "UPDATE";
                    break;
            }

            JOptionPane.showMessageDialog(this, "Đã Undo lại Action :" + actionName);
        } catch (SQLException ex) {
            sqlException(ex);
        }
    }

    private void btnThoatNhanvien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatNhanvien1ActionPerformed
        frmNhapXuat.setVisible(false);
    }//GEN-LAST:event_btnThoatNhanvien1ActionPerformed

    private void tbMaPhieuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbMaPhieuFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMaPhieuFocusLost

    private void tbMaPhieuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMaPhieuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMaPhieuKeyPressed

    private void tbMaPhieuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMaPhieuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMaPhieuKeyReleased

    private void tbNgayPhieuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNgayPhieuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbNgayPhieuKeyReleased

    private void tbLoaiPhieuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbLoaiPhieuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbLoaiPhieuKeyReleased

    private void tbTenKHFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbTenKHFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTenKHFocusLost

    private void tbNhanVienPhieuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbNhanVienPhieuFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tbNhanVienPhieuFocusLost

    private void tbKhoPhieuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbKhoPhieuFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tbKhoPhieuFocusLost

    private void mniNhapXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniNhapXuatActionPerformed
        frmNhapXuat.show();
        clearNhapxuatUI();
        loadTableData(tblDSNhapXuat, nhapxuatDAO, NHAPXUAT_TABLE_HEADERS, tbTimKiemNhapXuat);
    }//GEN-LAST:event_mniNhapXuatActionPerformed

    private void tbTenKH_Phieu_InsertFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbTenKH_Phieu_InsertFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTenKH_Phieu_InsertFocusLost

    private void btnThemCT_Phieu_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCT_Phieu_InsertActionPerformed
        CT_NhapXuatTableModel chitietNewTable = (CT_NhapXuatTableModel) tblCTPhatsinh_Phieu_Insert.getModel();
        chitietNewTable.addRow();
    }//GEN-LAST:event_btnThemCT_Phieu_InsertActionPerformed

    private void miNhanvienReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miNhanvienReportActionPerformed
        try {
            String typePath = "src\\reports\\DMNhanvien.jrxml";
            Map params = new HashMap();

            Reports.viewReport(typePath, params);
        } catch (SQLException e) {
            sqlException(e);

        } catch (Exception e) {
            errorProgram(e);

        }
    }//GEN-LAST:event_miNhanvienReportActionPerformed

    private void miHangNhapReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miHangNhapReportActionPerformed
        frmHangNXReport.setVisible(true);
    }//GEN-LAST:event_miHangNhapReportActionPerformed

    private void miVattuReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miVattuReportActionPerformed
        try {
            String typePath = "src\\reports\\DMVattu.jrxml";
            Map params = new HashMap();

            Reports.viewReport(typePath, params);
        } catch (SQLException e) {
            sqlException(e);

        } catch (Exception e) {
            errorProgram(e);

        }
    }//GEN-LAST:event_miVattuReportActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        frmThuChiReport.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        try {
            List manvs = employeeDAO.getMaNhanvien();
            DefaultComboBoxModel model = new DefaultComboBoxModel(manvs.toArray());
            cbxMaNV.setModel(model);
            frmHoatDongNhanvien.setVisible(true);

            getObjectAndDisplay(new JTextField("0"), tbHotenHoatdongNV, employeeDAO);
        } catch (SQLException e) {
            sqlException(e);
        } catch (Exception e) {
            errorProgram(e);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        frmTongHopReport.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void pnlLogin1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnlLogin1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlLogin1KeyPressed

    private void cbxMaNVItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMaNVItemStateChanged
        int manv = (int) cbxMaNV.getSelectedItem();
        getObjectAndDisplay(new JTextField(manv + ""), tbHotenHoatdongNV, employeeDAO);
    }//GEN-LAST:event_cbxMaNVItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // String typePath = "src\\reports\\Nhanvien.jrxml";
        try {
            String typePath = "src\\reports\\Nhanvien.jrxml";
            Map params = new HashMap();

            params.put("FROM", dpFrom.getDate());
            params.put("TO", dpTo.getDate());
            params.put("MANV", cbxMaNV.getSelectedItem() + "");
            params.put("SUBREPORT_DIR", "src\\reports\\");

            Reports.viewReport(typePath, params);
        } catch (SQLException e) {
            sqlException(e);

        } catch (Exception e) {
            errorProgram(e);

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnLuuThoat_Phieu_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuThoat_Phieu_InsertActionPerformed
        savePhatsinh();
        clearNhapxuatUI_Insert();

    }//GEN-LAST:event_btnLuuThoat_Phieu_InsertActionPerformed


    private void btnLuu_Phieu_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuu_Phieu_InsertActionPerformed
        savePhatsinh();
    }//GEN-LAST:event_btnLuu_Phieu_InsertActionPerformed

    private void savePhatsinh() {
        String maphieu = tbMaPhieu_Phieu_Insert.getText().trim();
        java.sql.Date ngay = new java.sql.Date(dpNgay_Phieu_Insert.getDate().getTime());
        String loai = (String) cbbLoai_Phieu_Insert.getSelectedItem();
        String makho = tbMakho_Phieu_Insert.getText().trim();
        String tenKH = tbTenKH_Phieu_Insert.getText().trim();
        String manv = tbManv_Phieu_Insert.getText().trim();

        String status = "";

        if (maphieu == null || maphieu.isEmpty()) {
            status = TOOLTIP_MAPHIEU;
            displayToolTip(tbMaPhieu_Phieu_Insert, TOOLTIP_MAPHIEU);
        } else if (manv == null || manv.isEmpty()) {
            status = TOOLTIP_MANV;
            displayToolTip(tbManv_Phieu_Insert, TOOLTIP_MANV);
        } else if (makho == null || makho.isEmpty()) {
            status = TOOLTIP_MAKHO;
            displayToolTip(tbMakho_Phieu_Insert, TOOLTIP_MAKHO);
        } else {
            float thanhtien = Float.parseFloat(tbThanhtien_Phieu_Insert.getText());

            PhatSinhBean phatSinhBean = new PhatSinhBean(maphieu, ngay, loai, tenKH, thanhtien, Integer.parseInt(manv), "", makho, "");
            CT_NhapXuatTableModel model = (CT_NhapXuatTableModel) tblCTPhatsinh_Phieu_Insert.getModel();
            List<CTPhatSinhBean> ctPhatsinhList = model.getListCTPhatsinh();

            boolean invalid = false;
            for (Iterator<CTPhatSinhBean> list = ctPhatsinhList.iterator(); list.hasNext();) {
                CTPhatSinhBean ctps = list.next();
                if (ctps.getMavt().isEmpty() || ctps.getSoluong() == 0 || ctps.getDongia() == 0) {
                    invalid = true;
                    break;
                }
            }

            if (ctPhatsinhList.isEmpty() || invalid) {
                lbThongbao_Phieu_Insert.setText(MESSAGE_INVALID_CHITIETPHATSINH);
                return;
            }

            try {
                PhatSinhBean undoBean = new PhatSinhBean();
                undoBean.setMaphieu(maphieu);
                List<CTPhatSinhBean> undoList = nhapxuatDAO.get(undoBean);

                int retr = nhapxuatDAO.save(phatSinhBean, ctPhatsinhList);
                if (retr == Constrains.CODE_SQL_INSERT_SUCCESS) {
                    status = MESSAGE_INSERT_SUCCESS;
                    //Undo
                    this.undoList.push(new Object[]{Constrains.ACTION_INSERT, phatSinhBean, ctPhatsinhList});

                } else if (retr == Constrains.CODE_SQL_UPDATE_SUCCESS) {
                    status = MESSAGE_UPDATE_SUCCESS;
                    //Undo
                    this.undoList.push(new Object[]{Constrains.ACTION_INSERT, undoBean, undoList});

                }
            } catch (SQLException e) {
                sqlException(e);
            }

        }
        lbThongbao_Phieu_Insert.setText(status);

    }

    private void saveThuchi() {
        String maphieu = tbMaPhieuTC.getText().trim();
        java.sql.Date ngay = new java.sql.Date(dpNgayTC.getDate().getTime());
        String loai = (String) cbbLoaiTC.getSelectedItem();
        String makho = tbMakhoTC.getText().trim();
        String manv = tbManvTC.getText().trim();
        float thanhtien = Float.parseFloat(tbThanhTienTC.getText());

        String status = "";

        if (maphieu == null || maphieu.isEmpty()) {
            status = TOOLTIP_MAPHIEU;
            displayToolTip(tbMaPhieuTC, TOOLTIP_MAPHIEU);
        } else if (manv == null || manv.isEmpty()) {
            status = TOOLTIP_MANV;
            displayToolTip(tbManvTC, TOOLTIP_MANV);
        } else if (makho == null || makho.isEmpty()) {
            status = TOOLTIP_MAKHO;
            displayToolTip(tbMakhoTC, TOOLTIP_MAKHO);
        } else if (thanhtien <= 0) {
            status = TOOLTIP_THANHTIEN;
            displayToolTip(tbThanhTienTC, status);
        } else {

            try {
                PhatSinhBean phatSinhBean = new PhatSinhBean(maphieu, ngay, loai, "", thanhtien, Integer.parseInt(manv), "", makho, "");
                PhatSinhBean undoBean = new PhatSinhBean();
                undoBean.setMaphieu(maphieu);
                thuchiDAO.get(undoBean);

                int retr = thuchiDAO.save(phatSinhBean);
                if (retr == Constrains.CODE_SQL_INSERT_SUCCESS) {
                    status = MESSAGE_INSERT_SUCCESS;
                    //Undo
                    undoList.push(new Object[]{Constrains.ACTION_INSERT, phatSinhBean});

                } else if (retr == Constrains.CODE_SQL_UPDATE_SUCCESS) {
                    status = MESSAGE_UPDATE_SUCCESS;
                    //Undo
                    undoList.push(new Object[]{Constrains.ACTION_UPDATE, undoBean});

                }
            } catch (SQLException e) {
                sqlException(e);
            }

        }
        lbThongBaoTC.setText(status);

    }

    private void btnThoat_Phieu_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoat_Phieu_InsertActionPerformed
        frmNewNhapXuat.setVisible(false);
        loadTableData(tblDSNhapXuat, nhapxuatDAO, NHAPXUAT_TABLE_HEADERS, tbTimKiemNhapXuat);
    }//GEN-LAST:event_btnThoat_Phieu_InsertActionPerformed

    private void tbTenkho_Phieu_InsertFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbTenkho_Phieu_InsertFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTenkho_Phieu_InsertFocusLost

    private void tbTennv_Phieu_InsertFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbTennv_Phieu_InsertFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTennv_Phieu_InsertFocusLost

    private void tbMaPhieu_Phieu_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbMaPhieu_Phieu_InsertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMaPhieu_Phieu_InsertActionPerformed

    private void tbManv_Phieu_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbManv_Phieu_InsertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbManv_Phieu_InsertActionPerformed

    private void tbMakho_Phieu_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbMakho_Phieu_InsertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMakho_Phieu_InsertActionPerformed

    private void tbManv_Phieu_InsertFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbManv_Phieu_InsertFocusLost
        getObjectAndDisplay(tbManv_Phieu_Insert, tbTennv_Phieu_Insert, employeeDAO);
    }//GEN-LAST:event_tbManv_Phieu_InsertFocusLost

    private void getObjectAndDisplay(JTextComponent ma, JTextComponent tbTennv, InterfaceObjectDAO dao) {
        String value = ma.getText().trim();
        if (value.isEmpty()) {
            return;
        }
        try {
            CommonObject bean;

            bean = (CommonObject) dao.get(value);

            if (bean == null) {
                tbTennv.setText("");
                displayToolTip(ma, MESSAGE_DATA_EMPTY);
                //Focus to error field
                ma.requestFocus();
            } else {
                tbTennv.setText(bean.getTen());
            }
        } catch (SQLException e) {
            sqlException(e);
        } catch (Exception e) {
            errorProgram(e);
        }
    }

    private void sqlException(Exception e) {
        int retr = JOptionPane.showConfirmDialog(this, MESSAGE_SQL_EXCEPTION, TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
        if (retr == 0) {
            loginView(true);
        } else {
            System.exit(1);
        }
    }

    private void errorProgram(Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, MESSAGE_APPLICATION_ERROR);
        System.exit(1);
    }


    private void tbMakho_Phieu_InsertFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbMakho_Phieu_InsertFocusLost
        getObjectAndDisplay(tbMakho_Phieu_Insert, tbTenkho_Phieu_Insert, khoDAO);

    }//GEN-LAST:event_tbMakho_Phieu_InsertFocusLost

    private void btnXoaCT_Phieu_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCT_Phieu_InsertActionPerformed
        CT_NhapXuatTableModel chitietNewTable = (CT_NhapXuatTableModel) tblCTPhatsinh_Phieu_Insert.getModel();
        int deletedRowIndex = tblCTPhatsinh_Phieu_Insert.getSelectedRow();
        if (deletedRowIndex >= 0) {
            chitietNewTable.removeRow(deletedRowIndex);
        }
    }//GEN-LAST:event_btnXoaCT_Phieu_InsertActionPerformed

    private void tbTenkho_Phieu_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbTenkho_Phieu_InsertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTenkho_Phieu_InsertActionPerformed

    private void tblCTPhatsinh_Phieu_InsertKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCTPhatsinh_Phieu_InsertKeyPressed

    }//GEN-LAST:event_tblCTPhatsinh_Phieu_InsertKeyPressed

    private void mniThuChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniThuChiActionPerformed
        frmThuchi.show();
        clearThuchiUI();
        loadTableData(tblDSThuchi, thuchiDAO, THUCHI_TABLE_HEADERS, tbTimKiemTC);
    }//GEN-LAST:event_mniThuChiActionPerformed

    private void tbMaPhieu_Phieu_InsertFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbMaPhieu_Phieu_InsertFocusLost
        getAndDisplayPhieuInsert();
    }//GEN-LAST:event_tbMaPhieu_Phieu_InsertFocusLost

    private void getAndDisplayPhieuInsert() {
        try {
            String maphieu = tbMaPhieu_Phieu_Insert.getText().trim();
            if (maphieu.isEmpty()) {
                return;
            }
            PhatSinhBean phieu = new PhatSinhBean();
            phieu.setMaphieu(maphieu);
            List<CTPhatSinhBean> ctPhieu = nhapxuatDAO.get(phieu);
            displayNhapxuatTrongNew(phieu, ctPhieu);

        } catch (SQLException ex) {
            sqlException(ex);
        }
    }

    private void getAndDisplayThuchi() {
        try {
            String maphieu = tbMaPhieuTC.getText().trim();
            if (maphieu.isEmpty()) {
                return;
            }
            PhatSinhBean phieu = new PhatSinhBean();
            phieu.setMaphieu(maphieu);
            thuchiDAO.get(phieu);
            if (phieu.getMakho() != null) {
                displayThuchi(phieu);
            }

        } catch (SQLException ex) {
            sqlException(ex);
        }
    }

    private void getAndDisplayKho() {
        try {
            String makho = tbxMakho.getText().trim();

            if (makho.isEmpty()) {
                return;
            }
            KhoBean kho = khoDAO.get(makho);
            if (kho != null) {
                displayKho(kho);
            }

        } catch (SQLException ex) {
            sqlException(ex);
        }
    }

    private void getAndDisplayVattu() {
        try {
            String mavt = tbxMavt.getText().trim();

            if (mavt.isEmpty()) {
                return;
            }
            VattuBean vattu = vattuDAO.get(mavt);
            if (vattu != null) {
                displayVattu(vattu);
            }
        } catch (SQLException ex) {
            sqlException(ex);
        }
    }


    private void tblDSNhapXuatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDSNhapXuatKeyPressed

    }//GEN-LAST:event_tblDSNhapXuatKeyPressed

    private void tblDSNhapXuatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDSNhapXuatKeyReleased
        selectPhieuNhapxuat();
        loadTableData(tblDSNhapXuat, nhapxuatDAO, NHAPXUAT_TABLE_HEADERS, tbTimKiemNhapXuat);
    }//GEN-LAST:event_tblDSNhapXuatKeyReleased

    private void btnThemNhatxuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNhatxuatMouseClicked

    }//GEN-LAST:event_btnThemNhatxuatMouseClicked

    private void tbTimKiemTCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTimKiemTCKeyReleased
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            btnTimkiemTC.doClick();
        }
    }//GEN-LAST:event_tbTimKiemTCKeyReleased

    private void btnTimkiemTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemTCActionPerformed
        loadTableData(tblDSThuchi, thuchiDAO, THUCHI_TABLE_HEADERS, tbTimKiemTC);
    }//GEN-LAST:event_btnTimkiemTCActionPerformed

    private void tblDSThuchiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSThuchiMouseClicked
        selectPhieuThuchi();
        loadTableData(tblDSThuchi, thuchiDAO, THUCHI_TABLE_HEADERS, tbTimKiemTC);
    }//GEN-LAST:event_tblDSThuchiMouseClicked

    private void tblDSThuchiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDSThuchiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDSThuchiKeyPressed

    private void tblDSThuchiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDSThuchiKeyReleased
        selectPhieuThuchi();
        loadTableData(tblDSThuchi, thuchiDAO, THUCHI_TABLE_HEADERS, tbTimKiemTC);
    }//GEN-LAST:event_tblDSThuchiKeyReleased


    private void tbMaPhieuTCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbMaPhieuTCFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMaPhieuTCFocusLost

    private void tbMaPhieuTCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMaPhieuTCKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMaPhieuTCKeyPressed

    private void tbMaPhieuTCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMaPhieuTCKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMaPhieuTCKeyReleased

    private void btnThemTCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemTCMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemTCMouseClicked

    private void btnThemTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTCActionPerformed
        clearThuchiUI();
    }//GEN-LAST:event_btnThemTCActionPerformed

    private void btnLuuTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuTCActionPerformed
        saveThuchi();
        loadTableData(tblDSThuchi, thuchiDAO, THUCHI_TABLE_HEADERS, tbTimKiemTC);
    }//GEN-LAST:event_btnLuuTCActionPerformed

    private void btnXoaTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTCActionPerformed
        try {
            String maphieu = tbMaPhieuTC.getText().trim();
            if (confirmDeleteDialog(frmThuchi, maphieu) && !maphieu.isEmpty()) {
                PhatSinhBean undoBean = new PhatSinhBean();
                undoBean.setMaphieu(maphieu);
                thuchiDAO.get(undoBean);

                thuchiDAO.delete(maphieu);

                lbThongBaoTC.setText(MESSAGE_DELETE_SUCCESS);
                loadTableData(tblDSThuchi, thuchiDAO, THUCHI_TABLE_HEADERS, tbTimKiemTC);
                clearThuchiUI();

                //Undo
                undoList.push(new Object[]{Constrains.ACTION_DELETE, undoBean});

            }

        } catch (SQLException ex) {
            sqlException(ex);
        }

    }//GEN-LAST:event_btnXoaTCActionPerformed

    private void btnUndoTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUndoTCActionPerformed
        //selectPhieuThuchi();
        undo(thuchiDAO);
        clearThuchiUI();
        loadTableData(tblDSThuchi, thuchiDAO, THUCHI_TABLE_HEADERS, tbTimKiemTC);
    }//GEN-LAST:event_btnUndoTCActionPerformed

    private void btnThoatTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatTCActionPerformed
        frmThuchi.setVisible(false);
    }//GEN-LAST:event_btnThoatTCActionPerformed

    private void tbTimKiemNhapXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbTimKiemNhapXuatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTimKiemNhapXuatActionPerformed

    private void cbbLoai_Phieu_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLoai_Phieu_InsertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbLoai_Phieu_InsertActionPerformed

    private void tbMaPhieu_Phieu_InsertFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbMaPhieu_Phieu_InsertFocusGained
        lbThongbao_Phieu_Insert.setText(TITLE_ERROR);
    }//GEN-LAST:event_tbMaPhieu_Phieu_InsertFocusGained

    private void tbTennvTCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbTennvTCFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTennvTCFocusLost

    private void tbTenkhoTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbTenkhoTCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTenkhoTCActionPerformed

    private void tbTenkhoTCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbTenkhoTCFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTenkhoTCFocusLost

    private void tbMakhoTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbMakhoTCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMakhoTCActionPerformed

    private void tbMakhoTCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbMakhoTCFocusLost
        getObjectAndDisplay(tbMakhoTC, tbTenkhoTC, khoDAO);
    }//GEN-LAST:event_tbMakhoTCFocusLost

    private void tbManvTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbManvTCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbManvTCActionPerformed

    private void tbManvTCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbManvTCFocusLost
        getObjectAndDisplay(tbManvTC, tbTennvTC, employeeDAO);
    }//GEN-LAST:event_tbManvTCFocusLost

    private void tblKhoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblKhoKeyPressed
        clickKho();
    }//GEN-LAST:event_tblKhoKeyPressed

    private void clickKho() {
        selectKho();
        loadTableData(tblKho, khoDAO, KHO_TABLE_HEADERS, tbxTimKiemKho);
        lbThongBaoKho.setText("");
    }

    private void tbxTenVattuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbxTenVattuFocusLost
        tenVattuIsExisted();
    }//GEN-LAST:event_tbxTenVattuFocusLost

    private void tblDSNhanvienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDSNhanvienKeyReleased
        selectNhanvien();
    }//GEN-LAST:event_tblDSNhanvienKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        frmHoatDongNhanvien.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
// String typePath = "src\\reports\\Nhanvien.jrxml";
        try {
            String typePath = "src\\reports\\PhatSinh.jrxml";
            Map params = new HashMap();

            params.put("FROM", dpFrom1.getDate());
            params.put("TO", dpTo1.getDate());
            if (radioHangNhap.isSelected()) {
                params.put("LOAI", "N");
            } else {
                params.put("LOAI", "X");
            }

            Reports.viewReport(typePath, params);
        } catch (SQLException e) {
            sqlException(e);

        } catch (Exception e) {
            errorProgram(e);

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        frmHangNXReport.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void pnlLogin2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnlLogin2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlLogin2KeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            String typePath = "src\\reports\\ThuChi.jrxml";
            Map params = new HashMap();

            params.put("FROM", dpFrom2.getDate());
            params.put("TO", dpTo2.getDate());
            if (radioHangNhap1.isSelected()) {
                params.put("LOAI", "T");
            } else {
                params.put("LOAI", "C");
            }

            Reports.viewReport(typePath, params);
        } catch (SQLException e) {
            sqlException(e);

        } catch (Exception e) {
            errorProgram(e);

        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        frmThuChiReport.setVisible(false);

    }//GEN-LAST:event_jButton8ActionPerformed

    private void pnlLogin3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnlLogin3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlLogin3KeyPressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            String typePath = "src\\reports\\TongHopNhapXuat.jrxml";
            Map params = new HashMap();

            params.put("FROM", dpFrom3.getDate());
            params.put("TO", dpTo3.getDate());

            Reports.viewReport(typePath, params);
        } catch (SQLException e) {
            sqlException(e);
        } catch (Exception e) {
            errorProgram(e);

        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        frmTongHopReport.setVisible(false);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void pnlLogin4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnlLogin4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlLogin4KeyPressed

    private void miBackupRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miBackupRestoreActionPerformed

        frmBackupRestore.setVisible(true);
        loadBackupTable();

    }//GEN-LAST:event_miBackupRestoreActionPerformed

    private void loadBackupTable() {
        try {
            Vector header = new Vector<String>();
            for (String h : BACKUP_TABLE_HEADERS) {
                header.add(h);
            }

            Vector data = accountDAO.getBackupHistory();
            tblBackup.setModel(new DefaultTableModel(data, header));
        } catch (SQLException ex) {
            ex.printStackTrace();
            sqlException(ex);
        }
    }

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        frmBackupRestore.setVisible(false);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            accountDAO.backup();
            loadBackupTable();
        } catch (SQLException e) {
            e.printStackTrace();
            sqlException(e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            int value = (int) tblBackup.getValueAt(tblBackup.getSelectedRow(), 0);
            accountDAO.restore(value);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed
    private boolean tenVattuIsExisted() {
        String ten = tbxTenVattu.getText().trim();
        if (ten.isEmpty()) {
            return false;
        }
        try {
            VattuBean vattu = vattuDAO.getByTen(ten);
            if (vattu != null) {
                if ((!vattu.getMavt().trim().equals(tbxMavt.getText().trim()))) {
                    lbThongbaoVatTu.setText(MESSAGE_DUPPLICATE_VATTU_NAME);
                    tbxTenVattu.requestFocus();
                    return true;
                }
            }
        } catch (SQLException e) {
            sqlException(e);
        }
        return false;
    }

    private void displayToolTip(final JTextComponent component, String text) {
        final ToolTipManager ttm = ToolTipManager.sharedInstance();
        final MouseEvent event = new MouseEvent(component, 0, 0, 0,
                0, 0, // X-Y of the mouse for the tool tip
                0, false);
        final int oldDelay = ttm.getInitialDelay();
        final String oldText = component.getToolTipText(event);
        component.setToolTipText(text);
        ttm.setInitialDelay(0);
        ttm.setDismissDelay(2000);
        ttm.mouseMoved(event);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ttm.setInitialDelay(oldDelay);
                component.setToolTipText(oldText);
            }
        }, ttm.getDismissDelay());
    }

    private void clearNhanvienUI() {
        tbxMaNv.setText("");
        tbxNgaySinh.setText("");
        tbxDiachi.setText("");
        tbxGhichu.setText("");
        tbxHo.setText("");
        tbxTen.setText("");
        tbxLuong.setText("");
    }

    private void clearNhapxuatUI() {
        tblCTPhieu.setModel(new CT_NhapXuatTableModel(new ArrayList<CTPhatSinhBean>(), null));
        tbKhoPhieu.setText("");
        tbNhanVienPhieu.setText("");
        tbTenKH.setText("");
        tbMaPhieu.setText("");
        tbLoaiPhieu.setText("");
        tbNgayPhieu.setText("");
        tbThanhTien.setText("");
    }

    private void clearThuchiUI() {
        tbMakhoTC.setText("");
        tbTenkhoTC.setText("");
        tbManvTC.setText("");
        tbTennvTC.setText("");
        tbMaPhieuTC.setText("");
        cbbLoaiTC.setSelectedIndex(0);
        dpNgayTC.setDate(new Date());
        tbThanhTienTC.setText("0");
    }

    private void clearNhapxuatUI_Insert() {
        tbMaPhieu_Phieu_Insert.setText("");
        tbMakho_Phieu_Insert.setText("");
        tbManv_Phieu_Insert.setText("");
        tbTenKH_Phieu_Insert.setText("");
        tbTenkho_Phieu_Insert.setText("");
        tbTennv_Phieu_Insert.setText("");
        dpNgay_Phieu_Insert.setDate(new Date());
        tbThanhtien_Phieu_Insert.setText("");

        CT_NhapXuatTableModel model = (CT_NhapXuatTableModel) tblCTPhatsinh_Phieu_Insert.getModel();
        model.clearData();
    }

    private void clearVattuUI() {
        tbxDvt.setText("");
        tbxMavt.setText("");
        tbxTenVattu.setText("");
    }

    private void clearKhoUI() {
        tbxMakho.setText("");
        tbxTenKho.setText("");
        tbxDiachiKho.setText("");
    }

    public void resetFormTaiKhoan() {
        tbxLoginnameTaiKhoan.setText("");
        tbxPassTaiKhoan.setText("");
        btnTaoTaiKhoan.setEnabled(true);
        btnThoatTaiKhoan.setEnabled(false);
        btnXoaTaiKhoan.setEnabled(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Kho;
    private javax.swing.JLabel Kho1;
    private javax.swing.JLabel Kho2;
    private javax.swing.JButton btnChangePass;
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JButton btnLuuKho;
    private javax.swing.JButton btnLuuNhanvien;
    private javax.swing.JButton btnLuuTC;
    private javax.swing.JButton btnLuuThoat_Phieu_Insert;
    private javax.swing.JButton btnLuuVattu;
    private javax.swing.JButton btnLuu_Phieu_Insert;
    private javax.swing.JButton btnSuaNhapxuat;
    private javax.swing.JButton btnTaoTaiKhoan;
    private javax.swing.JButton btnThemCT_Phieu_Insert;
    private javax.swing.JButton btnThemKho;
    private javax.swing.JButton btnThemNhanvien;
    private javax.swing.JButton btnThemNhatxuat;
    private javax.swing.JButton btnThemTC;
    private javax.swing.JButton btnThemVattu;
    private javax.swing.JButton btnThoatChangePass;
    private javax.swing.JButton btnThoatKho;
    private javax.swing.JButton btnThoatNhanvien;
    private javax.swing.JButton btnThoatNhanvien1;
    private javax.swing.JButton btnThoatTC;
    private javax.swing.JButton btnThoatTaiKhoan;
    private javax.swing.JButton btnThoatVattu;
    private javax.swing.JButton btnThoat_Phieu_Insert;
    private javax.swing.JButton btnTimkiemKho;
    private javax.swing.JButton btnTimkiemNhanvien;
    private javax.swing.JButton btnTimkiemNhapxuat;
    private javax.swing.JButton btnTimkiemTC;
    private javax.swing.JButton btnTimkiemVattu;
    private javax.swing.JButton btnUndoKho;
    private javax.swing.JButton btnUndoNhanvien;
    private javax.swing.JButton btnUndoNhapxuat;
    private javax.swing.JButton btnUndoTC;
    private javax.swing.JButton btnUndoVattu;
    private javax.swing.JButton btnXoaCT_Phieu_Insert;
    private javax.swing.JButton btnXoaKho;
    private javax.swing.JButton btnXoaNhanvien;
    private javax.swing.JButton btnXoaNhapxuat;
    private javax.swing.JButton btnXoaTC;
    private javax.swing.JButton btnXoaTaiKhoan;
    private javax.swing.JButton btnXoaVattu;
    private javax.swing.ButtonGroup btngrpRole;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroupHang;
    private javax.swing.JComboBox<String> cbbLoaiTC;
    private javax.swing.JComboBox<String> cbbLoai_Phieu_Insert;
    private javax.swing.JComboBox<String> cbxMaNV;
    private javax.swing.JComboBox<String> cbxNhanvien;
    private javax.swing.JPanel danhsachNhanvien;
    private javax.swing.JPanel danhsachNhanvien1;
    private javax.swing.JPanel danhsachNhanvien2;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JDialog dlAccount;
    private javax.swing.JDialog dlChangePass;
    private org.jdesktop.swingx.JXDatePicker dpFrom;
    private org.jdesktop.swingx.JXDatePicker dpFrom1;
    private org.jdesktop.swingx.JXDatePicker dpFrom2;
    private org.jdesktop.swingx.JXDatePicker dpFrom3;
    private org.jdesktop.swingx.JXDatePicker dpNgayTC;
    private org.jdesktop.swingx.JXDatePicker dpNgay_Phieu_Insert;
    private org.jdesktop.swingx.JXDatePicker dpTo;
    private org.jdesktop.swingx.JXDatePicker dpTo1;
    private org.jdesktop.swingx.JXDatePicker dpTo2;
    private org.jdesktop.swingx.JXDatePicker dpTo3;
    private javax.swing.JInternalFrame frmAccount;
    private javax.swing.JInternalFrame frmBackupRestore;
    private javax.swing.JInternalFrame frmChangePass;
    private javax.swing.JInternalFrame frmHangNXReport;
    private javax.swing.JInternalFrame frmHoatDongNhanvien;
    private javax.swing.JInternalFrame frmKho;
    private javax.swing.JInternalFrame frmLogin;
    private javax.swing.JInternalFrame frmNewNhapXuat;
    private javax.swing.JInternalFrame frmNhanvien;
    private javax.swing.JInternalFrame frmNhapXuat;
    private javax.swing.JInternalFrame frmThuChiReport;
    private javax.swing.JInternalFrame frmThuchi;
    private javax.swing.JInternalFrame frmTongHopReport;
    private javax.swing.JInternalFrame frmVattu;
    private javax.swing.JPanel inforAndButton;
    private javax.swing.JPanel inforAndButton1;
    private javax.swing.JPanel inforAndButton3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JSplitPane jSplitPane5;
    private javax.swing.JSplitPane jSplitPane6;
    private javax.swing.JLabel lbDialogStatus;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbStatusTaikhoan;
    private javax.swing.JLabel lbThongBaoKho;
    private javax.swing.JLabel lbThongBaoNX;
    private javax.swing.JLabel lbThongBaoTC;
    private javax.swing.JLabel lbThongbaoNV;
    private javax.swing.JLabel lbThongbaoVatTu;
    private javax.swing.JLabel lbThongbao_Phieu_Insert;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JLabel lbpassword;
    private javax.swing.JMenu menuDanhmuc;
    private javax.swing.JMenu menuNghiepvu;
    private javax.swing.JMenu menuNhanvien;
    private javax.swing.JMenu menuTaiKhoan;
    private javax.swing.JMenu menuThaotac;
    private javax.swing.JMenu menuThongke;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JMenuItem miBackupRestore;
    private javax.swing.JMenuItem miHangNhapReport;
    private javax.swing.JMenuItem miNhanvienReport;
    private javax.swing.JMenuItem miVattuReport;
    private javax.swing.JMenuItem mniAccount;
    private javax.swing.JMenuItem mniChangePass;
    private javax.swing.JMenuItem mniDangXuat;
    private javax.swing.JMenuItem mniKho;
    private javax.swing.JMenuItem mniNhapXuat;
    private javax.swing.JMenuItem mniThuChi;
    private javax.swing.JMenuItem mniVattu;
    private javax.swing.JPanel pnlDSNhanvien;
    private javax.swing.JPanel pnlDSNhanvien1;
    private javax.swing.JPanel pnlDSNhanvien2;
    private javax.swing.JPanel pnlDSVattu;
    private javax.swing.JPanel pnlDSVattu2;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPanel pnlLogin1;
    private javax.swing.JPanel pnlLogin2;
    private javax.swing.JPanel pnlLogin3;
    private javax.swing.JPanel pnlLogin4;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlStatus;
    private javax.swing.JPanel pnlThongbao;
    private javax.swing.JPanel pnlThongtinPhieu;
    private javax.swing.JRadioButton radioHangNhap;
    private javax.swing.JRadioButton radioHangNhap1;
    private javax.swing.JRadioButton rbtnAdmin;
    private javax.swing.JRadioButton rbtnUser;
    private javax.swing.JTextField tbHotenHoatdongNV;
    private javax.swing.JTextField tbKhoPhieu;
    private javax.swing.JTextField tbLoaiPhieu;
    private javax.swing.JTextField tbMaPhieu;
    private javax.swing.JTextField tbMaPhieuTC;
    private javax.swing.JTextField tbMaPhieu_Phieu_Insert;
    private javax.swing.JFormattedTextField tbMakhoTC;
    private javax.swing.JFormattedTextField tbMakho_Phieu_Insert;
    private javax.swing.JFormattedTextField tbManvTC;
    private javax.swing.JFormattedTextField tbManv_Phieu_Insert;
    private javax.swing.JPasswordField tbNewPass1;
    private javax.swing.JPasswordField tbNewPass2;
    private javax.swing.JTextField tbNgayPhieu;
    private javax.swing.JTextField tbNhanVienPhieu;
    private javax.swing.JTextField tbTenKH;
    private javax.swing.JTextField tbTenKH_Phieu_Insert;
    private javax.swing.JTextField tbTenkhoTC;
    private javax.swing.JTextField tbTenkho_Phieu_Insert;
    private javax.swing.JTextField tbTennvTC;
    private javax.swing.JTextField tbTennv_Phieu_Insert;
    private javax.swing.JTextField tbThanhTien;
    private javax.swing.JFormattedTextField tbThanhTienTC;
    private javax.swing.JFormattedTextField tbThanhtien_Phieu_Insert;
    private javax.swing.JTextField tbTimKiemNhapXuat;
    private javax.swing.JTextField tbTimKiemTC;
    private javax.swing.JTextField tbTimKiemVattu;
    private javax.swing.JTable tblBackup;
    private javax.swing.JTable tblCTPhatsinh_Phieu_Insert;
    private javax.swing.JTable tblCTPhieu;
    private javax.swing.JTable tblDSNhanvien;
    private javax.swing.JTable tblDSNhapXuat;
    private javax.swing.JTable tblDSThuchi;
    private javax.swing.JTable tblKho;
    private javax.swing.JTable tblVattu;
    private javax.swing.JTextField tbxDiachi;
    private javax.swing.JTextField tbxDiachiKho;
    private javax.swing.JTextField tbxDvt;
    private javax.swing.JTextArea tbxGhichu;
    private javax.swing.JTextField tbxHo;
    private javax.swing.JTextField tbxLoginnameTaiKhoan;
    private javax.swing.JTextField tbxLuong;
    private javax.swing.JTextField tbxMaNVTaiKhoan;
    private javax.swing.JTextField tbxMaNv;
    private javax.swing.JTextField tbxMakho;
    private javax.swing.JTextField tbxMavt;
    private javax.swing.JTextField tbxNgaySinh;
    private javax.swing.JPasswordField tbxPassTaiKhoan;
    private javax.swing.JTextField tbxTen;
    private javax.swing.JTextField tbxTenKho;
    private javax.swing.JTextField tbxTenVattu;
    private javax.swing.JTextField tbxTimKiemKho;
    private javax.swing.JTextField tbxTimKiemNhanvien;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

}
