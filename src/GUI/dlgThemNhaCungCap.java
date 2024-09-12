package GUI;

import BUS.NhaCungCapBUS;
import DTO.NhaCungCap;

public class dlgThemNhaCungCap extends javax.swing.JDialog {

    NhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();

    public dlgThemNhaCungCap() {
        initComponents();
        addCurrentInfo();
        showDlg();
    }

    private void showDlg() {
        this.getRootPane().setDefaultButton(btnThem);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setVisible(true);
    }

    //load maNCC tiếp theo
    private void addCurrentInfo() {
        txtMaNCC.setText("" + nhaCungCapBUS.getNextMaNCC());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNCC = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTenNCC = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtDiaChiNCC = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtDienThoaiNCC = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);

        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 160, 80));
        jLabel1.setText("Thêm nhà cung cấp");
        jPanel2.add(jLabel1);

        jPanel1.add(jPanel2);

        jPanel4.setPreferredSize(new java.awt.Dimension(614, 200));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));

        jPanel5.setPreferredSize(new java.awt.Dimension(109, 10));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 160, 80));
        jLabel2.setText("Mã NCC");
        jLabel2.setPreferredSize(new java.awt.Dimension(118, 32));
        jPanel5.add(jLabel2);

        txtMaNCC.setColumns(35);
        txtMaNCC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaNCC.setEnabled(false);
        jPanel5.add(txtMaNCC);

        jPanel4.add(jPanel5);

        jPanel6.setPreferredSize(new java.awt.Dimension(109, 10));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 160, 80));
        jLabel3.setText("Tên NCC");
        jLabel3.setPreferredSize(new java.awt.Dimension(118, 32));
        jPanel6.add(jLabel3);

        txtTenNCC.setColumns(35);
        txtTenNCC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel6.add(txtTenNCC);

        jPanel4.add(jPanel6);

        jPanel7.setPreferredSize(new java.awt.Dimension(109, 10));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 160, 80));
        jLabel4.setText("Địa chỉ");
        jLabel4.setPreferredSize(new java.awt.Dimension(118, 32));
        jPanel7.add(jLabel4);

        txtDiaChiNCC.setColumns(35);
        txtDiaChiNCC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel7.add(txtDiaChiNCC);

        jPanel4.add(jPanel7);

        jPanel8.setPreferredSize(new java.awt.Dimension(109, 10));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 160, 80));
        jLabel5.setText("Điện thoại");
        jPanel8.add(jLabel5);

        txtDienThoaiNCC.setColumns(35);
        txtDienThoaiNCC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel8.add(txtDienThoaiNCC);

        jPanel4.add(jPanel8);

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //nút reset
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtTenNCC.setText("");
        txtDiaChiNCC.setText("");
        txtDienThoaiNCC.setText("");
    }//GEN-LAST:event_btnResetActionPerformed
    //nút thêm
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        NhaCungCap nhaCungCap = new NhaCungCap();
        nhaCungCap.setMaNCC(Integer.parseInt(txtMaNCC.getText()));
        nhaCungCap.setTenNCC(txtTenNCC.getText());
        nhaCungCap.setDiaChi(txtDiaChiNCC.getText());
        nhaCungCap.setDienThoai(txtDienThoaiNCC.getText());
        nhaCungCap.setTrangThai(1);
        if (nhaCungCapBUS.Insert(nhaCungCap))
            this.dispose();
    }//GEN-LAST:event_btnThemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTextField txtDiaChiNCC;
    private javax.swing.JTextField txtDienThoaiNCC;
    private javax.swing.JTextField txtMaNCC;
    private javax.swing.JTextField txtTenNCC;
    // End of variables declaration//GEN-END:variables
}
