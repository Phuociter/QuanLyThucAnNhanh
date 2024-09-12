package GUI;

import Custom.Mytable;
import Custom.dialog;
import Custom.XuLyFileExcel;
import static Main.Main.changLNF;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import DTO.NhanVien;
import BUS.NhanVienBUS;
import BUS.TaiKhoanBUS;

public class PnNhanVien extends JPanel {

    public PnNhanVien() {
        changLNF("Windows");
        addControls();
        addEventsNhanVienGUI();

    }
    private NhanVienBUS NVBUS = new NhanVienBUS();
    private TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
    final Color colorPanel = new Color(247, 247, 247);
    final Color ClHover = new Color(0, 160, 80);
    final Color ClClick = new Color(76, 204, 76);
    final Color ClMain = new Color(0, 160, 80);

    DefaultTableModel dtmNhanVien;
    JTextField txtMaNV, txtHodem, txtTen, txtDienThoai, txtTimKiem;
    JComboBox<String> cmbGioiTinh;
    Mytable tblNhanVien;
    JButton btnThemNV, btnLuuNV, btnXoaNV, btnTimNV, btnCapTaiKhoan, btnResetMatKhauNV, btnKhoaTaiKhoanNV, btnResetNV, btnXuatExcel, btnNhapExcel;

    private void addControls() {
        Font font = new Font("", Font.PLAIN, 20);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorPanel);

        JPanel pnTitle = new JPanel();
        JLabel lblTitle = new JLabel("<html><h1>QUẢN LÍ NHÂN VIÊN</h1></html>");
        lblTitle.setForeground(ClMain);
        btnResetNV = new JButton(new ImageIcon("image/btn/refresh.png"));
        btnResetNV.setPreferredSize(new Dimension(40, 40));
        btnResetNV.setBorder(null); // xóa viền
        btnResetNV.setFocusable(false); // bỏ focus để không hiển thị viền khi focus
        btnResetNV.setOpaque(false); // nền trong suốt 
        pnTitle.add(lblTitle);
        pnTitle.add(btnResetNV);
        this.add(pnTitle);

        JPanel pnThongTin = new JPanel();
        pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.X_AXIS));

        //================PANEL INPUT=========
        JPanel pnTextField = new JPanel();
        pnTextField.setLayout(new BoxLayout(pnTextField, BoxLayout.Y_AXIS));

        JLabel lblMaNV, lblHoDem, lblTen, lblGioiTinh, lblDienThoai, lblTimKiem;

        lblMaNV = new JLabel("Mã NV");
        lblHoDem = new JLabel("Họ đệm");
        lblTen = new JLabel("Tên");
        lblGioiTinh = new JLabel("Giới tính");
        lblDienThoai = new JLabel("Điện thoại");
        lblTimKiem = new JLabel("Từ khóa tìm");
        txtMaNV = new JTextField(25);
        txtMaNV.setEditable(false);
        txtHodem = new JTextField(25);
        txtTen = new JTextField(25);
        cmbGioiTinh = new JComboBox<String>();
        txtDienThoai = new JTextField(25);
        txtTimKiem = new JTextField(25);

        JPanel pnMaNV = new JPanel();
        lblMaNV.setFont(font);
        txtMaNV.setFont(font);
        pnMaNV.add(lblMaNV);
        pnMaNV.add(txtMaNV);
        pnTextField.add(pnMaNV);

        JPanel pnHoDem = new JPanel();
        lblHoDem.setFont(font);
        txtHodem.setFont(font);
        pnHoDem.add(lblHoDem);
        pnHoDem.add(txtHodem);
        pnTextField.add(pnHoDem);

        JPanel pnTen = new JPanel();
        lblTen.setFont(font);
        txtTen.setFont(font);
        pnTen.add(lblTen);
        pnTen.add(txtTen);
        pnTextField.add(pnTen);

        JPanel pnGioiTinh = new JPanel();
        lblGioiTinh.setFont(font);
        cmbGioiTinh.setFont(font);
        cmbGioiTinh.setPreferredSize(txtMaNV.getPreferredSize());
        cmbGioiTinh.setFocusable(false); // bỏ focus 
        pnGioiTinh.add(lblGioiTinh);
        pnGioiTinh.add(cmbGioiTinh);
        pnTextField.add(pnGioiTinh);

        JPanel pnChucVu = new JPanel();
        lblDienThoai.setFont(font);
        txtDienThoai.setFont(font);
        pnChucVu.add(lblDienThoai);
        pnChucVu.add(txtDienThoai);
        pnTextField.add(pnChucVu);

        JPanel TimKiem = new JPanel();
        lblTimKiem.setFont(font);
        txtTimKiem.setFont(font);
        TimKiem.add(lblTimKiem);
        TimKiem.add(txtTimKiem);
        pnTextField.add(TimKiem);

        Dimension lblSize = lblTimKiem.getPreferredSize();// nên lấy kích thước của label có độ rộng lớn nhất
        lblMaNV.setPreferredSize(lblSize);
        lblHoDem.setPreferredSize(lblSize);
        lblGioiTinh.setPreferredSize(lblSize);
        lblTen.setPreferredSize(lblSize);
        lblDienThoai.setPreferredSize(lblSize);
        lblTimKiem.setPreferredSize(lblSize);

        pnThongTin.add(pnTextField);
        this.add(pnThongTin);

        JPanel pnButton = new JPanel();

        btnThemNV = new JButton("Thêm");
        btnLuuNV = new JButton("Lưu");
        btnXoaNV = new JButton("Xoá");
        btnTimNV = new JButton("Tìm kiếm");
        btnCapTaiKhoan = new JButton("Cấp tài khoản");
        btnResetMatKhauNV = new JButton("Mật khẩu/Quyền");
        btnKhoaTaiKhoanNV = new JButton("Khóa tài khoản");
        btnXuatExcel = new JButton("Xuất");
        btnNhapExcel = new JButton("Nhập");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThemNV.setFont(fontButton);
        btnLuuNV.setFont(fontButton);
        btnXoaNV.setFont(fontButton);
        btnTimNV.setFont(fontButton);
        btnCapTaiKhoan.setFont(fontButton);
        btnResetMatKhauNV.setFont(fontButton);
        btnKhoaTaiKhoanNV.setFont(fontButton);
        btnXuatExcel.setFont(fontButton);
        btnNhapExcel.setFont(fontButton);

        btnThemNV.setIcon(new ImageIcon("image/btn/plus.png"));
        btnLuuNV.setIcon(new ImageIcon("image/btn/write.png"));
        btnXoaNV.setIcon(new ImageIcon("image/btn/bin.png"));
        btnTimNV.setIcon(new ImageIcon("image/btn/search.png"));
        btnCapTaiKhoan.setIcon(new ImageIcon("image/btn/avatar.png"));
        btnResetMatKhauNV.setIcon(new ImageIcon("image/btn/reset-password.png"));
        btnKhoaTaiKhoanNV.setIcon(new ImageIcon("image/btn/lock.png"));
        btnXuatExcel.setIcon(new ImageIcon("image/btn/excel.png"));
        btnNhapExcel.setIcon(new ImageIcon("image/btn/excel.png"));
        //setFocusable(false) để bỏ viền khi click vào
        btnThemNV.setFocusable(false);
        btnLuuNV.setFocusable(false);
        btnXoaNV.setFocusable(false);
        btnTimNV.setFocusable(false);
        btnCapTaiKhoan.setFocusable(false);
        btnResetMatKhauNV.setFocusable(false);
        btnKhoaTaiKhoanNV.setFocusable(false);
        btnXuatExcel.setFocusable(false);
        btnNhapExcel.setFocusable(false);

        pnButton.add(btnThemNV);
        pnButton.add(btnLuuNV);
        pnButton.add(btnXoaNV);
        pnButton.add(btnTimNV);
        pnButton.add(btnXuatExcel);
        pnButton.add(btnNhapExcel);
        //tách ra 2 dòng button 
        JPanel pnButton2 = new JPanel();
        pnButton2.add(btnCapTaiKhoan);
        pnButton2.add(btnResetMatKhauNV);
        pnButton2.add(btnKhoaTaiKhoanNV);

        Dimension btnSize = btnTimNV.getPreferredSize();
        btnThemNV.setPreferredSize(btnSize);
        btnLuuNV.setPreferredSize(btnSize);
        btnXoaNV.setPreferredSize(btnSize);
        btnTimNV.setPreferredSize(btnSize);
        btnXuatExcel.setPreferredSize(btnSize);
        btnNhapExcel.setPreferredSize(btnSize);

        Dimension btnSize2 = btnResetMatKhauNV.getPreferredSize();
        btnCapTaiKhoan.setPreferredSize(btnSize2);
        btnResetMatKhauNV.setPreferredSize(btnSize2);

        this.add(pnButton);
        this.add(pnButton2);

        //============PANEL BẢNG===========
        JPanel pnTable = new JPanel(new BorderLayout());
        //====================Bảng hàng hoá====================
        dtmNhanVien = new DefaultTableModel();
        dtmNhanVien.addColumn("Mã Nhân viên");
        dtmNhanVien.addColumn("Họ đệm");
        dtmNhanVien.addColumn("Tên");
        dtmNhanVien.addColumn("Giới tính");
        dtmNhanVien.addColumn("Điện thoại");
        dtmNhanVien.addColumn("Chức vụ");
        dtmNhanVien.addColumn("Tài khoản");
        tblNhanVien = new Mytable(dtmNhanVien) {
            public boolean isCellEditable(int row, int column) { // không cho phép sửa nội dung trong table
                return false;
            }
        };;

        TableColumnModel columnModelBanHang = tblNhanVien.getColumnModel();
        columnModelBanHang.getColumn(0).setPreferredWidth(20);
        columnModelBanHang.getColumn(1).setPreferredWidth(20);
        columnModelBanHang.getColumn(2).setPreferredWidth(20);
        columnModelBanHang.getColumn(3).setPreferredWidth(20);
        columnModelBanHang.getColumn(4).setPreferredWidth(20);
        columnModelBanHang.getColumn(5).setPreferredWidth(20);

        JScrollPane scrTblNhanVien = new JScrollPane(tblNhanVien);
        pnTable.add(scrTblNhanVien, BorderLayout.CENTER);
        this.add(pnTable);

        loadDataCmbGioiTinh();
        loadDataTblNhanVien();
    }

    private void loadDataCmbGioiTinh() {
        cmbGioiTinh.removeAllItems();
        cmbGioiTinh.addItem("Chọn giới tính");
        cmbGioiTinh.addItem("Nữ");
        cmbGioiTinh.addItem("Nam");
        cmbGioiTinh.addItem("Khác");
    }

    private void addEventsNhanVienGUI() {

        btnResetNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataTblNhanVien();
                txtMaNV.setText("");
                txtHodem.setText("");
                txtTen.setText("");
                txtDienThoai.setText("");
                txtTimKiem.setText("");
                cmbGioiTinh.setSelectedIndex(0);
            }
        });

        tblNhanVien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblNhanVien();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        txtTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiemNhanVien();
            }
        });

        btnTimNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiemNhanVien();
            }
        });

        btnThemNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemNhanVien();
            }
        });

        btnLuuNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaNhanVien();
            }
        });

        btnXoaNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaNhanVien();
            }
        });

        btnXuatExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXuatExcel();
            }
        });

        btnNhapExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyNhapExcel();
            }
        });

        btnCapTaiKhoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyCapTaiKhoan();
            }
        });

        btnResetMatKhauNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyResetMatKhau();
            }
        });

        btnKhoaTaiKhoanNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyKhoaTaiKhoan();
            }
        });
    }

    private void xuLyKhoaTaiKhoan() {
        taiKhoanBUS.khoaTaiKhoan(txtMaNV.getText());
        loadDataTblNhanVien();
    }

    private void xuLyResetMatKhau() {
        String maNV = txtMaNV.getText();
        if (maNV.trim().equals("")) {
            new dialog("Bạn chưa chọn nhân viên!", dialog.ERROR_DIALOG);
            return;
        }
        dlgQuyen_MK dialog = new dlgQuyen_MK(maNV);
    }

    private void xuLyCapTaiKhoan() {
        if (txtMaNV.getText().trim().equals("")) {
            new dialog("Bạn chưa chọn nhân viên!", dialog.ERROR_DIALOG);
            return;
        }
        int maNV = Integer.parseInt(txtMaNV.getText());
        if (taiKhoanBUS.hasAccount(maNV)) { // kiểm tra nhân viên đã có tài khoản chưa
            return;
        }
        dlgCapTaiKhoan dialog = new dlgCapTaiKhoan(txtMaNV.getText());
        dialog.setVisible(true);
        loadDataTblNhanVien();
    }

    private void xuLyNhapExcel() {
        dialog dlg = new dialog("Dữ liệu cũ sẽ bị xoá, tiếp tục?", dialog.WARNING_DIALOG);
        if (dlg.getAction() != dialog.OK_OPTION) {
            return;
        }

        XuLyFileExcel nhapExcel = new XuLyFileExcel();
        nhapExcel.nhapExcel(tblNhanVien);
        NVBUS.xoaFKHoadon_PhieuNhap_NV();
        NVBUS.xoaAllNhanVien();

        int row = tblNhanVien.getRowCount();
        for (int i = 0; i < row; i++) {
            String manv = tblNhanVien.getValueAt(i, 0) + "";
            String ho = tblNhanVien.getValueAt(i, 1) + "";
            String ten = tblNhanVien.getValueAt(i, 2) + "";
            String gioiTinh = tblNhanVien.getValueAt(i, 3) + "";
            String dienthoai = tblNhanVien.getValueAt(i, 4) + "";

            NVBUS.nhapExcel(manv, ho, ten, gioiTinh, dienthoai, 1);
        }
        NVBUS.updateFKHoadon_PhieuNhap_NV();
    }

    private void xuLyXuatExcel() {
        XuLyFileExcel xuatExcel = new XuLyFileExcel();
        xuatExcel.xuatExcel(tblNhanVien);
    }

    private void xuLyXoaNhanVien() {
        String ma = txtMaNV.getText();
        boolean flag = NVBUS.xoaNhanVien(ma);
        if (flag) {
            NVBUS.docDanhSach();
            loadDataTblNhanVien();
            //reset lại txtMaNV tránh việc lấy mã nv đã bị xóa trên form
            txtMaNV.setText("");
        }
    }

    private void xuLySuaNhanVien() {
        if (cmbGioiTinh.getSelectedIndex() == 0) {
            new dialog("Bạn chưa chọn giới tính!", dialog.ERROR_DIALOG);
            return;
        }
        String ma = txtMaNV.getText();
        String ho = txtHodem.getText();
        String ten = txtTen.getText();
        String gioiTinh = cmbGioiTinh.getSelectedItem() + "";
        String dienthoai = txtDienThoai.getText();
        if (NVBUS.updateNhanVien(ma, ho, ten, gioiTinh, dienthoai)) {
            NVBUS.docDanhSach();
            loadDataTblNhanVien();
        }
    }

    private void xuLyThemNhanVien() {
        if (cmbGioiTinh.getSelectedIndex() == 0) {
            new dialog("Bạn chưa chọn giới tính!", dialog.ERROR_DIALOG);
            return;
        }
        String ho = txtHodem.getText();
        String ten = txtTen.getText();
        String gioiTinh = cmbGioiTinh.getSelectedItem() + "";
        String dienthoai = txtDienThoai.getText();
        if (NVBUS.themNhanVien(ho, ten, gioiTinh, dienthoai, 1)) {
            NVBUS.docDanhSach();
            loadDataTblNhanVien();
        }
    }

    private void xuLyTimKiemNhanVien() {
        ArrayList<NhanVien> dsnv = NVBUS.timNhanVien(txtTimKiem.getText());
        dtmNhanVien.setRowCount(0);
        for (NhanVien nv : dsnv) {
            Vector<Object> vec = new Vector<>();
            vec.add(nv.getMaNV());
            vec.add(nv.getHo());
            vec.add(nv.getTen());
            vec.add(nv.getGioiTinh());
            vec.add(nv.getDienThoai());
            vec.add(nv.getChucVu());
            int trangThai = taiKhoanBUS.getTrangThai(nv.getMaNV() + "");
            if (trangThai == 1) {
                vec.add("Hoạt động");
            }
            if (trangThai == 0) {
                vec.add("Khoá");
            } else {
                vec.add("Chưa có");
            }
            dtmNhanVien.addRow(vec);
        }
    }

    private void xuLyClickTblNhanVien() {
        int row = tblNhanVien.getSelectedRow();
        if (row > -1) {
            txtMaNV.setText(tblNhanVien.getValueAt(row, 0) + "");
            txtHodem.setText(tblNhanVien.getValueAt(row, 1) + "");
            txtTen.setText(tblNhanVien.getValueAt(row, 2) + "");
            txtDienThoai.setText(tblNhanVien.getValueAt(row, 4) + "");

            if ((tblNhanVien.getValueAt(row, 3) + "").equals("Nam")) {
                cmbGioiTinh.setSelectedIndex(2);
            } else {
                cmbGioiTinh.setSelectedIndex(1);
            }
        }
    }

    private void loadDataTblNhanVien() {
        NVBUS.docDanhSach();
        dtmNhanVien.setRowCount(0);
        ArrayList<NhanVien> dsnv = NVBUS.getlistNV();

        for (NhanVien nv : dsnv) {
            Vector<Object> vec = new Vector<>();
            vec.add(nv.getMaNV());
            vec.add(nv.getHo());
            vec.add(nv.getTen());
            vec.add(nv.getGioiTinh());
            vec.add(nv.getDienThoai());
            vec.add(nv.getChucVu());
            int trangThai = taiKhoanBUS.getTrangThai(nv.getMaNV() + "");
            if (trangThai == 1) {
                vec.add("Hoạt động");
            }
            if (trangThai == 0) {
                vec.add("Khoá");
            } else {
                vec.add("Chưa có");
            }
            dtmNhanVien.addRow(vec);
        }
    }
}
