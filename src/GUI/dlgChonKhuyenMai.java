package GUI;

import BUS.GiamGiaBUS;
import Custom.Mytable;
import Custom.NonEditableTableModel;
import Custom.dialog;
import DTO.GiamGia;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class dlgChonKhuyenMai extends javax.swing.JDialog {

    NonEditableTableModel dtmKhuyenMai;
    GiamGiaBUS giamGiaBUS = new GiamGiaBUS();
    GiamGia selectedGiamGia = null;
    int tongTien;

    public dlgChonKhuyenMai(int tongTien) {
        this.tongTien = tongTien;
        initComponents();
        Custom();
        addEvents();    
        showDlg();
    }

    private void Custom() {
        dtmKhuyenMai = new NonEditableTableModel();
        dtmKhuyenMai.addColumn("Mã");
        dtmKhuyenMai.addColumn("Chương trình");
        dtmKhuyenMai.addColumn("% KM");
        dtmKhuyenMai.addColumn("Điều kiện");
        dtmKhuyenMai.addColumn("Bắt đầu");
        dtmKhuyenMai.addColumn("Kết thúc");
        dtmKhuyenMai.addColumn("Trạng thái");
        tbKhuyenMai.setModel(dtmKhuyenMai);
        loadData();
    }

    private void loadData() {
        dtmKhuyenMai.setRowCount(0);
        ArrayList<GiamGia> giamGias = giamGiaBUS.getList();
        if (giamGias == null) {
            return;
        }
        Date currentDate = new Date();
        for (GiamGia giamGia : giamGias) {
            String trangThai = "Hết hiệu lực";
            if ((giamGia.getNgayBD() == null && giamGia.getNgayKT() == null) || (currentDate.after(giamGia.getNgayBD()) && currentDate.before(giamGia.getNgayKT()))) {
                trangThai = "Có hiệu lực";
            }
            dtmKhuyenMai.addRow(new Object[]{giamGia.getMaGiam(), giamGia.getTenGiamGia(), giamGia.getPhanTramGiam(), "> " + giamGia.getDieuKien(), giamGia.getNgayBD(), giamGia.getNgayKT(), trangThai});
        }
    }

    private void showDlg() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setVisible(true);
    }

    private int checkSelectRow() {
        if (tbKhuyenMai.getSelectedRows().length > 1) {
            new dialog("Chỉ chọn 1 chương trình giảm giá", dialog.ERROR_DIALOG);
            return -1;
        }
        int row = tbKhuyenMai.getSelectedRow();
        if (row < 0) {
            new dialog("Vui lòng chọn chương trình giảm giá", dialog.ERROR_DIALOG);
        }
        return row;
    }

    //lấy dữ liệu từ hàng người dùng chọn
    private GiamGia selectedRow(int row) {
        if (tbKhuyenMai.getValueAt(row, 6).equals("Hết hiệu lực")) {
            new dialog("Mã hết hiệu lực", dialog.ERROR_DIALOG);
            return null;
        }
        int maGiam = Integer.parseInt("" + tbKhuyenMai.getValueAt(row, 0));
        GiamGia giamGia = giamGiaBUS.getById(maGiam);
        if (giamGia != null) {
            if (giamGia.getDieuKien() >= tongTien) {
                new dialog("Không đủ điều kiện", dialog.ERROR_DIALOG);
                return null;
            }
        }
        return giamGiaBUS.getById(maGiam);
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
        tbKhuyenMai = new Mytable();
        jPanel5 = new javax.swing.JPanel();
        btnChon = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 160, 80));
        jLabel1.setText("Chọn khuyến mãi");
        jPanel2.add(jLabel1);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Tìm kiếm");
        jPanel4.add(jLabel2);

        txtTimKiem.setColumns(25);
        jPanel4.add(txtTimKiem);

        jPanel3.add(jPanel4, java.awt.BorderLayout.NORTH);

        tbKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbKhuyenMai);

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

        btnThoat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(0, 160, 80));
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        jPanel5.add(btnThoat);

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
        selectedGiamGia = selectedRow(row);
        if (selectedGiamGia == null) {
            return;
        }
        this.dispose();
    }//GEN-LAST:event_btnChonActionPerformed
    public GiamGia getKhuyenMai() {
        return selectedGiamGia;
    }
    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnThoatActionPerformed
    private void XuLyTimKiem() {
        dtmKhuyenMai.setRowCount(0);
        ArrayList<GiamGia> giamGias = giamGiaBUS.searchGiamGias(txtTimKiem.getText());
        if (giamGias == null) {
            return;
        }
        Date currentDate = new Date();
        for (GiamGia giamGia : giamGias) {
            String trangThai = "Hết hiệu lực";
            if ((giamGia.getNgayBD() == null && giamGia.getNgayKT() == null) || (currentDate.after(giamGia.getNgayBD()) && currentDate.before(giamGia.getNgayKT()))) {
                trangThai = "Có hiệu lực";
            }
            dtmKhuyenMai.addRow(new Object[]{giamGia.getMaGiam(), giamGia.getTenGiamGia(), giamGia.getPhanTramGiam(), "> " + giamGia.getDieuKien(), giamGia.getNgayBD(), giamGia.getNgayKT(), trangThai});
        }
    }

    private void addEvents() {
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
    private javax.swing.JButton btnThoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbKhuyenMai;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
