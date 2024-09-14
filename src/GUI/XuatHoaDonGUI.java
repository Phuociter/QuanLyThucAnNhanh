package GUI;

import BUS.CTHoaDonBUS;
import BUS.DangNhapBUS;
import BUS.HoaDonBUS;
import BUS.NhanVienBUS;
import BUS.SanPhamBUS;
import Custom.Mytable;
import Custom.NonEditableTableModel;
import Custom.dialog;
import DTO.CTHoaDon;
import DTO.GiamGia;
import DTO.HoaDon;
import DTO.KhachHang;
import DTO.NhanVien;
import DTO.SanPham;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class XuatHoaDonGUI extends javax.swing.JDialog {

    NonEditableTableModel dtmCTHD;
    int tongTien;
    int tongTienDaGiam;
    ArrayList<CTHoaDon> cTHoaDons;

    private SanPhamBUS sanPhamBUS = new SanPhamBUS();
    private HoaDonBUS hoaDonBUS = new HoaDonBUS();
    private CTHoaDonBUS cTHoaDonBUS = new CTHoaDonBUS();
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();

    private NhanVien nhanVien = nhanVienBUS.getById(DangNhapBUS.taiKhoanLogin.getMaNhanVien());
    private KhachHang khachHang = null;
    private GiamGia giamGia = null;
    private boolean isSuccess = false;

    public XuatHoaDonGUI(int tongTien, ArrayList<CTHoaDon> cTHoaDons) {
        this.cTHoaDons = cTHoaDons;
        this.tongTien = tongTien;
        this.tongTienDaGiam = tongTien;
        initComponents();
        Custom();
        loadData();
        showDlg();
    }

    private void showDlg() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setVisible(true);
    }

    private void Custom() {
        btnInHoaDon.setVisible(false);

        dtmCTHD = new NonEditableTableModel();
        dtmCTHD.addColumn("Mã SP");
        dtmCTHD.addColumn("Tên SP");
        dtmCTHD.addColumn("Số lượng");
        dtmCTHD.addColumn("Đơn giá");
        dtmCTHD.addColumn("Thành tiền");
        tblCTHD.setModel(dtmCTHD);
    }

    private String DinhDangTGHienTai() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.now();
        return dtf.format(ldt);
    }

    private String DinhDangTongTien(int tongTien) {
        DecimalFormat df = new DecimalFormat("###,### VND");
        return df.format(tongTien);
    }

    private void loadData() {
        txtNhanVien.setText(nhanVien.getMaNV() + " - " + nhanVien.getHo() + " " + nhanVien.getTen());
        txtNgayLap.setText(DinhDangTGHienTai());
        txtTongTien.setText(DinhDangTongTien(tongTienDaGiam));

        dtmCTHD.setRowCount(0);
        for (CTHoaDon cTHoaDon : cTHoaDons) {
            SanPham sp = sanPhamBUS.getById(cTHoaDon.getMaSP());
            dtmCTHD.addRow(new Object[]{cTHoaDon.getMaSP(), sp.getTenSP(), cTHoaDon.getSoLuong(), cTHoaDon.getDonGia(), cTHoaDon.getThanhTien()});
        }
    }

    private void XuLyThanhToan() {
        if (khachHang == null) {
            new dialog("Chưa chọn khách hàng", dialog.ERROR_DIALOG);
            return;
        }
        if (giamGia == null) {
            giamGia = new GiamGia(0, "Không giảm giá", 0, 0, null, null, 0);
        }
        HoaDon hoaDon = new HoaDon(0, khachHang.getMaKH(), nhanVien.getMaNV(), giamGia.getMaGiam(), new Date(), tongTienDaGiam);
        if (!hoaDonBUS.Insert(hoaDon)) {
            return;
        }
        pnCTPN.removeAll();
        pnCTPN.add(scrEdtCTPN);
        btnInHoaDon.setVisible(true);
        btnThanhToan.setVisible(false);
        int maHD = hoaDonBUS.getNewId();
        String htmlCTHD = "<style> "
                + "table {"
                + "border: 1px solid;"
                + "border-bottom: none"
                + "}"
                + "tr {"
                + "border-bottom: 1px solid;"
                + "}"
                + "td {"
                + "padding: 8px;"
                + "} "
                + "th {"
                + "font-size:16pt"
                + "}"
                + "</style>";
        htmlCTHD += "<h1 style='text-align:center;'>CHI TIẾT HÓA ĐƠN</h1>";
        htmlCTHD += "Nhân viên: " + nhanVien.getMaNV() + " - " + nhanVien.getHo() + " " + nhanVien.getTen() + "<br/>";
        htmlCTHD += "Khách hàng: " + khachHang.getMaKH() + " - " + khachHang.getTen() + "<br/>";
        htmlCTHD += "Khuyến mãi: " + giamGia.getMaGiam() + " - " + giamGia.getTenGiamGia() + "<br/>";
        htmlCTHD += "Ngày lập: " + DinhDangTGHienTai() + "<br/>";
        htmlCTHD += "<div style='text-align:center;'>==========================================</div><br/>";
        htmlCTHD += "<div style='text-align:center'>";
        htmlCTHD += "<table style='max-width:100%'>";
        htmlCTHD += "<tr>"
                + "<th>Mã SP</th>"
                + "<th>Tên SP</th>"
                + "<th>Số lượng</th>"
                + "<th>Đơn giá</th>"
                + "<th>Thành tiền</th>"
                + "</tr>";
        for (CTHoaDon cTHoaDon : cTHoaDons) {
            htmlCTHD += "<tr>";
            htmlCTHD += "<td style='text-align:center;'>" + cTHoaDon.getMaSP() + "</td>";
            SanPham sp = sanPhamBUS.getById(cTHoaDon.getMaSP());
            htmlCTHD += "<td style='text-align:left;'>" + sp.getTenSP() + "</td>";
            htmlCTHD += "<td style='text-align:center;'>" + cTHoaDon.getSoLuong() + "</td>";
            htmlCTHD += "<td style='text-align:center;'>" + cTHoaDon.getDonGia() + "</td>";
            htmlCTHD += "<td style='text-align:center;'>" + cTHoaDon.getThanhTien() + "</td>";
            htmlCTHD += "</tr>";

            //===================================================
            //===================LƯU CTPN VÀO DB=================
            //===================================================
            cTHoaDon.setMaHD(maHD);
            if (!cTHoaDonBUS.Insert(cTHoaDon)) {
                return;
            }
        }

        htmlCTHD += "<tr>";
        htmlCTHD += "<td style='text-align:center;'>" + "</td>";
        htmlCTHD += "<td style='text-align:left;'>" + "</td>";
        htmlCTHD += "<td style='text-align:center;'>" + "</td>";
        htmlCTHD += "<td style='text-align:center;font-weight:bold'>Tổng tiền</td>";
        htmlCTHD += "<td style='text-align:center;'>" + DinhDangTongTien(tongTienDaGiam) + "</td>";
        htmlCTHD += "</tr>";
        htmlCTHD += "</table>";
        htmlCTHD += "</div>";
        htmlCTHD += "<div style='text-align:center;'>==========================================</div><br/>";

        txtCTHD.setContentType("text/html");
        txtCTHD.setText(htmlCTHD);
        isSuccess = true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnCTPN = new javax.swing.JPanel();
        scrEdtCTPN = new javax.swing.JScrollPane();
        txtCTHD = new javax.swing.JEditorPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        txtNhanVien = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        txtKhachHang = new javax.swing.JTextField();
        btnChonKhachHang = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        txtKhuyenMai = new javax.swing.JTextField();
        btnChonKhuyenMai = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        txtNgayLap = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        txtTongTien = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTHD = new Mytable();
        jPanel18 = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JButton();
        btnInHoaDon = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 160, 80));
        jLabel1.setText("Thông tin hóa đơn");
        jPanel2.add(jLabel1);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        pnCTPN.setLayout(new java.awt.BorderLayout());

        scrEdtCTPN.setViewportView(txtCTHD);

        pnCTPN.add(scrEdtCTPN, java.awt.BorderLayout.CENTER);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.Y_AXIS));

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Nhân viên");
        jPanel7.add(jLabel4);

        jPanel6.add(jPanel7);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Khách hàng");
        jPanel9.add(jLabel6);

        jPanel6.add(jPanel9);

        jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Khuyến mãi");
        jPanel19.add(jLabel8);

        jPanel6.add(jPanel19);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ngày lập");
        jPanel8.add(jLabel5);

        jPanel6.add(jPanel8);

        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Tổng tiền");
        jPanel10.add(jLabel7);

        jPanel6.add(jPanel10);

        jPanel5.add(jPanel6);

        jPanel11.setLayout(new javax.swing.BoxLayout(jPanel11, javax.swing.BoxLayout.Y_AXIS));

        jPanel12.setPreferredSize(new java.awt.Dimension(300, 30));
        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        txtNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel12.add(txtNhanVien);

        jPanel11.add(jPanel12);

        jPanel14.setPreferredSize(new java.awt.Dimension(300, 30));
        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        txtKhachHang.setColumns(20);
        txtKhachHang.setEnabled(false);
        jPanel14.add(txtKhachHang);

        btnChonKhachHang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnChonKhachHang.setText("...");
        btnChonKhachHang.setPreferredSize(new java.awt.Dimension(29, 20));
        btnChonKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKhachHangActionPerformed(evt);
            }
        });
        jPanel14.add(btnChonKhachHang);

        jPanel11.add(jPanel14);

        jPanel20.setPreferredSize(new java.awt.Dimension(300, 30));
        jPanel20.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        txtKhuyenMai.setColumns(20);
        txtKhuyenMai.setEnabled(false);
        jPanel20.add(txtKhuyenMai);

        btnChonKhuyenMai.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnChonKhuyenMai.setText("...");
        btnChonKhuyenMai.setPreferredSize(new java.awt.Dimension(29, 20));
        btnChonKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKhuyenMaiActionPerformed(evt);
            }
        });
        jPanel20.add(btnChonKhuyenMai);

        jPanel11.add(jPanel20);

        jPanel13.setPreferredSize(new java.awt.Dimension(300, 30));
        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        txtNgayLap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel13.add(txtNgayLap);

        jPanel11.add(jPanel13);

        jPanel15.setPreferredSize(new java.awt.Dimension(300, 30));
        jPanel15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel15.add(txtTongTien);

        jPanel11.add(jPanel15);

        jPanel5.add(jPanel11);

        jPanel4.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel16.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 160, 80));
        jLabel2.setText("Chi tiết hóa đơn");
        jPanel17.add(jLabel2);

        jPanel16.add(jPanel17, java.awt.BorderLayout.NORTH);

        tblCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane1.setViewportView(tblCTHD);

        jPanel16.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel16, java.awt.BorderLayout.CENTER);

        pnCTPN.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel1.add(pnCTPN, java.awt.BorderLayout.CENTER);

        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(0, 160, 80));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        jPanel18.add(btnThanhToan);

        btnInHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnInHoaDon.setForeground(new java.awt.Color(0, 160, 80));
        btnInHoaDon.setText("In hóa đơn");
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });
        jPanel18.add(btnInHoaDon);

        jPanel1.add(jPanel18, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

        XuLyThanhToan();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed
        try {
            txtCTHD.print();
        } catch (PrinterException e) {
        }
    }//GEN-LAST:event_btnInHoaDonActionPerformed

    private void btnChonKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKhachHangActionPerformed
        dlgChonKhachHang dChonKhachHang = new dlgChonKhachHang();
        khachHang = dChonKhachHang.getKhachHang();
        if (khachHang == null) {
            txtKhachHang.setText("");
            return;
        }
        txtKhachHang.setText(khachHang.getMaKH() + " - " + khachHang.getTen());
    }//GEN-LAST:event_btnChonKhachHangActionPerformed

    private void btnChonKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKhuyenMaiActionPerformed
        dlgChonKhuyenMai dChonKhuyenMai = new dlgChonKhuyenMai(tongTien);
        giamGia = dChonKhuyenMai.getKhuyenMai();
        if (giamGia == null) {
            txtKhuyenMai.setText("");
            return;
        }
        txtKhuyenMai.setText(giamGia.getMaGiam() + " - " + giamGia.getTenGiamGia());
        tongTienDaGiam = tinhTongTienGiamGia(tongTien, giamGia.getPhanTramGiam());
        loadData();
    }//GEN-LAST:event_btnChonKhuyenMaiActionPerformed
    private int tinhTongTienGiamGia(int tongTien, int phanTramGiam) {
        return (int) (tongTien * (1.0 - ((phanTramGiam * 1.0) / 100f)));
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonKhachHang;
    private javax.swing.JButton btnChonKhuyenMai;
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnCTPN;
    private javax.swing.JScrollPane scrEdtCTPN;
    private javax.swing.JTable tblCTHD;
    private javax.swing.JEditorPane txtCTHD;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtKhuyenMai;
    private javax.swing.JLabel txtNgayLap;
    private javax.swing.JLabel txtNhanVien;
    private javax.swing.JLabel txtTongTien;
    // End of variables declaration//GEN-END:variables
}
