package GUI;

import BUS.KhachHangBUS;
import DTO.KhachHang;

public class dlgThemKhachHang extends javax.swing.JDialog {

    KhachHangBUS khachHangBUS = new KhachHangBUS();

    public dlgThemKhachHang() {
        initComponents();
        Custom();
        showDlg();
    }

    private void Custom() {
        loadCmbGioiTinh();
    }

    private void showDlg() {
        this.getRootPane().setDefaultButton(btnThem);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cmbGioiTinh = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtDienThoai = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);

        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 160, 80));
        jLabel1.setText("Thêm khách hàng");
        jPanel2.add(jLabel1);

        jPanel1.add(jPanel2);

        jPanel4.setPreferredSize(new java.awt.Dimension(614, 200));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));

        jPanel6.setPreferredSize(new java.awt.Dimension(109, 10));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 160, 80));
        jLabel3.setText("Tên KH");
        jLabel3.setPreferredSize(new java.awt.Dimension(125, 32));
        jPanel6.add(jLabel3);

        txtTenKH.setColumns(35);
        txtTenKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel6.add(txtTenKH);

        jPanel4.add(jPanel6);

        jPanel7.setPreferredSize(new java.awt.Dimension(109, 10));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 160, 80));
        jLabel4.setText("Giới tính");
        jLabel4.setPreferredSize(new java.awt.Dimension(125, 32));
        jPanel7.add(jLabel4);

        cmbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbGioiTinh.setPreferredSize(new java.awt.Dimension(434, 26));
        jPanel7.add(cmbGioiTinh);

        jPanel4.add(jPanel7);

        jPanel8.setPreferredSize(new java.awt.Dimension(109, 10));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 160, 80));
        jLabel5.setText("Điện thoại");
        jLabel5.setPreferredSize(new java.awt.Dimension(125, 32));
        jPanel8.add(jLabel5);

        txtDienThoai.setColumns(35);
        txtDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel8.add(txtDienThoai);

        jPanel4.add(jPanel8);

        jPanel9.setPreferredSize(new java.awt.Dimension(109, 10));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 160, 80));
        jLabel6.setText("Email");
        jLabel6.setPreferredSize(new java.awt.Dimension(125, 32));
        jPanel9.add(jLabel6);

        txtEmail.setColumns(35);
        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel9.add(txtEmail);

        jPanel4.add(jPanel9);

        jPanel10.setPreferredSize(new java.awt.Dimension(109, 10));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 160, 80));
        jLabel7.setText("Địa chỉ");
        jLabel7.setPreferredSize(new java.awt.Dimension(125, 32));
        jPanel10.add(jLabel7);

        txtDiaChi.setColumns(35);
        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel10.add(txtDiaChi);

        jPanel4.add(jPanel10);

        jPanel1.add(jPanel4);

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 160, 80));
        btnThem.setText("Thêm");
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThem.setFocusable(false);
        btnThem.setPreferredSize(new java.awt.Dimension(100, 50));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel3.add(btnThem);

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnReset.setForeground(new java.awt.Color(0, 160, 80));
        btnReset.setText("Reset");
        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.setFocusable(false);
        btnReset.setPreferredSize(new java.awt.Dimension(100, 50));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel3.add(btnReset);

        jPanel1.add(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadCmbGioiTinh() {
        cmbGioiTinh.removeAllItems();
        cmbGioiTinh.addItem("Chọn giới tính");
        cmbGioiTinh.addItem("Nam");
        cmbGioiTinh.addItem("Nữ");
    }
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtTenKH.setText("");
        txtDiaChi.setText("");
        txtDienThoai.setText("");
        txtEmail.setText("");
        cmbGioiTinh.setSelectedIndex(0);
    }//GEN-LAST:event_btnResetActionPerformed
    //nút thêm
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        KhachHang khachHang = new KhachHang();
        khachHang.setMaKH(0);
        khachHang.setTen(txtTenKH.getText());
        khachHang.setDiaChi(txtDiaChi.getText());
        khachHang.setDienThoai(txtDienThoai.getText());
        khachHang.setEmail(txtEmail.getText());
        String gioiTinh = "";
        if (cmbGioiTinh.getSelectedIndex() != 0) {
            gioiTinh = cmbGioiTinh.getSelectedItem() + "";
        }
        khachHang.setGioiTinh(gioiTinh);
        khachHang.setTrangThai(1);
        khachHang.setTongChiTieu(0);
        if (khachHangBUS.Insert(khachHang))
            this.dispose();
    }//GEN-LAST:event_btnThemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cmbGioiTinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDienThoai;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
