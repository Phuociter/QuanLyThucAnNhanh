package GUI;

import BUS.CTPhieuNhapBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import Custom.Mytable;
import Custom.NonEditableTableModel;
import DTO.CTPhieuNhap;
import DTO.NhaCungCap;
import DTO.NhanVien;
import DTO.PhieuNhap;
import DTO.SanPham;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class XuatPhieuNhapGUI extends javax.swing.JDialog {

    private NonEditableTableModel dtmCTPN;
    private NhanVien nhanVien;
    private NhaCungCap nhaCungCap;
    private int tongTien;
    private ArrayList<CTPhieuNhap> cTPhieuNhaps;
    private SanPhamBUS sanPhamBUS = new SanPhamBUS();
    private PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
    private CTPhieuNhapBUS cTPhieuNhapBUS = new CTPhieuNhapBUS();
    private boolean isSuccess = false;

    public XuatPhieuNhapGUI(NhanVien nhanVien, NhaCungCap nhaCungCap, int tongTien, ArrayList<CTPhieuNhap> cTPhieuNhaps) {
        this.nhanVien = nhanVien;
        this.nhaCungCap = nhaCungCap;
        this.cTPhieuNhaps = cTPhieuNhaps;
        this.tongTien = tongTien;
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
        btnInPhieuNhap.setVisible(false);

        dtmCTPN = new NonEditableTableModel();
        dtmCTPN.addColumn("Mã SP");
        dtmCTPN.addColumn("Tên SP");
        dtmCTPN.addColumn("Số lượng");
        dtmCTPN.addColumn("Đơn giá");
        dtmCTPN.addColumn("Thành tiền");
        tblCTPN.setModel(dtmCTPN);
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
        txtNhaCungCap.setText(nhaCungCap.getMaNCC() + " - " + nhaCungCap.getTenNCC());
        txtNgayLap.setText(DinhDangTGHienTai());
        txtTongTien.setText(DinhDangTongTien(tongTien));

        dtmCTPN.setRowCount(0);
        for (CTPhieuNhap cTPhieuNhap : cTPhieuNhaps) {
            SanPham sp = sanPhamBUS.getById(cTPhieuNhap.getMaSP());
            dtmCTPN.addRow(new Object[]{cTPhieuNhap.getMaSP(), sp.getTenSP(), cTPhieuNhap.getSoLuong(), cTPhieuNhap.getDonGia(), cTPhieuNhap.getThanhTien()});
        }
    }

    private void XuLyThanhToan() {
        PhieuNhap phieuNhap = new PhieuNhap(0, nhaCungCap.getMaNCC(), nhanVien.getMaNV(), new Date(), tongTien);
        if (!phieuNhapBUS.Insert(phieuNhap)) {
            return;
        }
        int maPN = phieuNhapBUS.getNewMaPN();
        String htmlCTPN = "<style> "
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
        htmlCTPN += "<h1 style='text-align:center;'>CHI TIẾT PHIẾU NHẬP</h1>";
        htmlCTPN += "Nhân viên: " + nhanVien.getMaNV() + " - " + nhanVien.getHo() + " " + nhanVien.getTen() + "<br/>";
        htmlCTPN += "Ngày lập: " + DinhDangTGHienTai() + "<br/>";
        htmlCTPN += "Nhà cung cấp: " + nhaCungCap.getMaNCC() + " - " + nhaCungCap.getTenNCC() + "<br/>";
        htmlCTPN += "<div style='text-align:center;'>==========================================</div><br/>";
        htmlCTPN += "<div style='text-align:center'>";
        htmlCTPN += "<table style='max-width:100%'>";
        htmlCTPN += "<tr>"
                + "<th>Mã SP</th>"
                + "<th>Tên SP</th>"
                + "<th>Số lượng</th>"
                + "<th>Đơn giá</th>"
                + "<th>Thành tiền</th>"
                + "</tr>";
        for (CTPhieuNhap ctpn : cTPhieuNhaps) {
            htmlCTPN += "<tr>";
            htmlCTPN += "<td style='text-align:center;'>" + ctpn.getMaSP() + "</td>";
            SanPham sp = sanPhamBUS.getById(ctpn.getMaSP());
            htmlCTPN += "<td style='text-align:left;'>" + sp.getTenSP() + "</td>";
            htmlCTPN += "<td style='text-align:center;'>" + ctpn.getSoLuong() + "</td>";
            htmlCTPN += "<td style='text-align:center;'>" + ctpn.getDonGia() + "</td>";
            htmlCTPN += "<td style='text-align:center;'>" + ctpn.getThanhTien() + "</td>";
            htmlCTPN += "</tr>";

            //===================================================
            //===================LƯU CTPN VÀO DB=================
            //===================================================
            ctpn.setMaPN(maPN);
            if (!cTPhieuNhapBUS.Insert(ctpn)) {
                return;
            }
        }

        htmlCTPN += "<tr>";
        htmlCTPN += "<td style='text-align:center;'>" + "</td>";
        htmlCTPN += "<td style='text-align:left;'>" + "</td>";
        htmlCTPN += "<td style='text-align:center;'>" + "</td>";
        htmlCTPN += "<td style='text-align:center;font-weight:bold'>Thành tiền</td>";
        htmlCTPN += "<td style='text-align:center;'>" + DinhDangTongTien(tongTien) + "</td>";
        htmlCTPN += "</tr>";
        htmlCTPN += "</table>";
        htmlCTPN += "</div>";
        htmlCTPN += "<div style='text-align:center;'>==========================================</div><br/>";

        txtCTPN.setContentType("text/html");
        txtCTPN.setText(htmlCTPN);
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
        txtCTPN = new javax.swing.JEditorPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        txtNhanVien = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        txtNgayLap = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        txtNhaCungCap = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        txtTongTien = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTPN = new Mytable();
        jPanel18 = new javax.swing.JPanel();
        btnXacNhanNhap = new javax.swing.JButton();
        btnInPhieuNhap = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 160, 80));
        jLabel1.setText("Thông tin phiếu nhập");
        jPanel2.add(jLabel1);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        pnCTPN.setLayout(new java.awt.BorderLayout());

        scrEdtCTPN.setViewportView(txtCTPN);

        pnCTPN.add(scrEdtCTPN, java.awt.BorderLayout.CENTER);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.Y_AXIS));

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Nhân viên");
        jPanel7.add(jLabel4);

        jPanel6.add(jPanel7);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ngày lập");
        jPanel8.add(jLabel5);

        jPanel6.add(jPanel8);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Nhà cung cấp");
        jPanel9.add(jLabel6);

        jPanel6.add(jPanel9);

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

        jPanel13.setPreferredSize(new java.awt.Dimension(300, 30));
        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        txtNgayLap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel13.add(txtNgayLap);

        jPanel11.add(jPanel13);

        jPanel14.setPreferredSize(new java.awt.Dimension(300, 30));
        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        txtNhaCungCap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel14.add(txtNhaCungCap);

        jPanel11.add(jPanel14);

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
        jLabel2.setText("Chi tiết phiếu nhập");
        jPanel17.add(jLabel2);

        jPanel16.add(jPanel17, java.awt.BorderLayout.NORTH);

        tblCTPN.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblCTPN);

        jPanel16.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel16, java.awt.BorderLayout.CENTER);

        pnCTPN.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel1.add(pnCTPN, java.awt.BorderLayout.CENTER);

        btnXacNhanNhap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXacNhanNhap.setForeground(new java.awt.Color(0, 160, 80));
        btnXacNhanNhap.setText("Xác nhận nhập");
        btnXacNhanNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanNhapActionPerformed(evt);
            }
        });
        jPanel18.add(btnXacNhanNhap);

        btnInPhieuNhap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnInPhieuNhap.setForeground(new java.awt.Color(0, 160, 80));
        btnInPhieuNhap.setText("In phiếu nhập");
        btnInPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInPhieuNhapActionPerformed(evt);
            }
        });
        jPanel18.add(btnInPhieuNhap);

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

    private void btnXacNhanNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanNhapActionPerformed
        pnCTPN.removeAll();
        pnCTPN.add(scrEdtCTPN);
        btnInPhieuNhap.setVisible(true);
        btnXacNhanNhap.setVisible(false);
        XuLyThanhToan();
    }//GEN-LAST:event_btnXacNhanNhapActionPerformed

    private void btnInPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInPhieuNhapActionPerformed
        try {
            txtCTPN.print();
        } catch (PrinterException e) {
        }
    }//GEN-LAST:event_btnInPhieuNhapActionPerformed
    public boolean getIsSuccess() {
        return isSuccess;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInPhieuNhap;
    private javax.swing.JButton btnXacNhanNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnCTPN;
    private javax.swing.JScrollPane scrEdtCTPN;
    private javax.swing.JTable tblCTPN;
    private javax.swing.JEditorPane txtCTPN;
    private javax.swing.JLabel txtNgayLap;
    private javax.swing.JLabel txtNhaCungCap;
    private javax.swing.JLabel txtNhanVien;
    private javax.swing.JLabel txtTongTien;
    // End of variables declaration//GEN-END:variables
}
