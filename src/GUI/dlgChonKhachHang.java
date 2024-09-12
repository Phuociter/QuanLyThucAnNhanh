package GUI;

import BUS.KhachHangBUS;
import Custom.Mytable;
import Custom.NonEditableTableModel;
import Custom.dialog;
import DTO.KhachHang;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class dlgChonKhachHang extends javax.swing.JDialog {

    NonEditableTableModel dtmKhachHang;
    KhachHangBUS khachHangBUS = new KhachHangBUS();
    KhachHang selectedKhachHang = null;

    public dlgChonKhachHang() {
        initComponents();
        Custom();
        addEvents();
        showDlg();
    }

    private void Custom() {
        dtmKhachHang = new NonEditableTableModel();
        dtmKhachHang.addColumn("Mã khác hàng");
        dtmKhachHang.addColumn("Tên khách hàng");
        dtmKhachHang.addColumn("Giới tính");
        dtmKhachHang.addColumn("SĐT");
        dtmKhachHang.addColumn("Email");
        dtmKhachHang.addColumn("Địa chỉ");
        dtmKhachHang.addColumn("Tổng chi tiêu");
        tbKhachHang.setModel(dtmKhachHang);
        loadData();
    }

    private void loadData() {
        dtmKhachHang.setRowCount(0);
        ArrayList<KhachHang> khachHangs = khachHangBUS.getListKhachHang();
        if (khachHangs == null) {
            return;
        }
        for (KhachHang khachHang : khachHangs) {
            dtmKhachHang.addRow(new Object[]{khachHang.getMaKH(), khachHang.getTen(),
                khachHang.getGioiTinh(), khachHang.getDienThoai(), khachHang.getEmail(), khachHang.getDiaChi(),
                khachHang.getTongChiTieu()});
        }
    }

    private void showDlg() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setVisible(true);
    }

    private int checkSelectRow() {
        if (tbKhachHang.getSelectedRows().length > 1) {
            new dialog("Chỉ chọn 1 khách hàng", dialog.ERROR_DIALOG);
            return -1;
        }
        int row = tbKhachHang.getSelectedRow();
        if (row < 0) {
            new dialog("Vui lòng chọn khách hàng", dialog.ERROR_DIALOG);
        }
        return row;
    }

    //lấy dữ liệu từ hàng người dùng chọn
    private KhachHang selectedRow(int row) {
        int maKH = Integer.parseInt("" + tbKhachHang.getValueAt(row, 0));
        return khachHangBUS.getID(maKH);
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
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKhachHang = new Mytable();
        jPanel5 = new javax.swing.JPanel();
        btnChon = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 160, 80));
        jLabel1.setText("Chọn khách hàng");
        jPanel2.add(jLabel1);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Tìm kiếm");
        jPanel4.add(jLabel2);

        txtTimKiem.setColumns(25);
        jPanel4.add(txtTimKiem);

        jPanel3.add(jPanel4, java.awt.BorderLayout.NORTH);

        tbKhachHang.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbKhachHang);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        btnChon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnChon.setForeground(new java.awt.Color(0, 160, 80));
        btnChon.setText("Chọn");
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
            }
        });
        jPanel5.add(btnChon);

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 160, 80));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel5.add(btnThem);

        jPanel3.add(jPanel5, java.awt.BorderLayout.SOUTH);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 851, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed
        int row = checkSelectRow();
        if (row < 0) {
            return;
        }
        selectedKhachHang = selectedRow(row);
        this.dispose();
    }//GEN-LAST:event_btnChonActionPerformed
    public KhachHang getKhachHang() {
        return selectedKhachHang;
    }
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        dlgThemKhachHang dThemKhachHang = new dlgThemKhachHang();
        loadData();
    }//GEN-LAST:event_btnThemActionPerformed
    private void XuLyTimKiem() {
        dtmKhachHang.setRowCount(0);
        ArrayList<KhachHang> khachHangs = khachHangBUS.TimKiemKHtheoMavaTen(txtTimKiem.getText());
        if (khachHangs == null) {
            return;
        }
        for (KhachHang khachHang : khachHangs) {
            dtmKhachHang.addRow(new Object[]{khachHang.getMaKH(), khachHang.getTen(),
                khachHang.getGioiTinh(), khachHang.getDienThoai(), khachHang.getEmail(), khachHang.getDiaChi(),
                khachHang.getTongChiTieu()});
        }
    }
    private void addEvents(){
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                XuLyTimKiem();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                XuLyTimKiem();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChon;
    private javax.swing.JButton btnThem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbKhachHang;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
