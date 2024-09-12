package GUI;

import BUS.TaiKhoanBUS;
import Custom.InputValidator;
import Custom.dialog;

public class dlgDoiMatKhau extends javax.swing.JDialog {
    TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
    
    public dlgDoiMatKhau() {
        initComponents();
        showdlg();
    }

    private void showdlg(){
        this.getRootPane().setDefaultButton(btnXanNhan);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setVisible(true);
        txtPassCu.requestFocus();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtPassCu = new javax.swing.JPasswordField();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtPassMoi = new javax.swing.JPasswordField();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtPassMoiAgain = new javax.swing.JPasswordField();
        jPanel7 = new javax.swing.JPanel();
        btnXanNhan = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 160, 80));
        jLabel1.setText("Đổi mật khẩu");
        jPanel2.add(jLabel1);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.Y_AXIS));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Mật khẩu cũ");
        jLabel2.setPreferredSize(new java.awt.Dimension(180, 25));
        jPanel4.add(jLabel2);

        txtPassCu.setColumns(20);
        jPanel4.add(txtPassCu);

        jPanel3.add(jPanel4);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Mật khẩu mới");
        jLabel3.setPreferredSize(new java.awt.Dimension(180, 25));
        jPanel5.add(jLabel3);

        txtPassMoi.setColumns(20);
        jPanel5.add(txtPassMoi);

        jPanel3.add(jPanel5);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Nhập lại mật khẩu mới");
        jPanel6.add(jLabel4);

        txtPassMoiAgain.setColumns(20);
        jPanel6.add(txtPassMoiAgain);

        jPanel3.add(jPanel6);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        btnXanNhan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnXanNhan.setText("Xác nhận");
        btnXanNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXanNhanActionPerformed(evt);
            }
        });
        jPanel7.add(btnXanNhan);

        btnHuy.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.setPreferredSize(new java.awt.Dimension(102, 32));
        jPanel7.add(btnHuy);

        jPanel1.add(jPanel7, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXanNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXanNhanActionPerformed
        if(new String(txtPassCu.getPassword()).isEmpty() || new String(txtPassMoi.getPassword()).isEmpty() || new String(txtPassMoiAgain.getPassword()).isEmpty()){
            new dialog("Không được để trống",dialog.ERROR_DIALOG);
            return;
        }
        if(!InputValidator.isValidPass(new String(txtPassMoi.getPassword()).strip())){
            new dialog("Mật khẩu không được có ký tự",dialog.ERROR_DIALOG);
            return;
        }
        if(taiKhoanBUS.doiMatKhau(new String(txtPassCu.getPassword()),new String(txtPassMoi.getPassword()), new String(txtPassMoiAgain.getPassword()))){
            this.dispose();
        }
    }//GEN-LAST:event_btnXanNhanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnXanNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPasswordField txtPassCu;
    private javax.swing.JPasswordField txtPassMoi;
    private javax.swing.JPasswordField txtPassMoiAgain;
    // End of variables declaration//GEN-END:variables
}
