package GUI;

import BUS.GiamGiaBUS;
import Custom.InputValidator;
import Custom.Mytable;
import Custom.NonEditableTableModel;
import Custom.dialog;
import DTO.GiamGia;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PnQuanLyKhuyenMaiGUI extends javax.swing.JPanel {

    NonEditableTableModel dtmGiamGia;
    GiamGiaBUS giamGiaBUS = new GiamGiaBUS();

    public PnQuanLyKhuyenMaiGUI() {
        initComponents();
        Custom();
        loadData();
        addEvents();
    }

    private void Custom() {
        dtmGiamGia = new NonEditableTableModel();
        dtmGiamGia.addColumn("Mã khuyến mãi");
        dtmGiamGia.addColumn("Tên khuyến mãi");
        dtmGiamGia.addColumn("Phần trăm giảm");
        dtmGiamGia.addColumn("Điều kiện");
        dtmGiamGia.addColumn("Ngày bắt đầu");
        dtmGiamGia.addColumn("Ngày kết thúc");

        tblGiamGia.setModel(dtmGiamGia);
    }

    private void loadData() {
        dtmGiamGia.setRowCount(0);
        ArrayList<GiamGia> giamgias = giamGiaBUS.getList();
        if (giamgias == null) {
            return;
        }
        for (GiamGia giamGia : giamgias) {
            dtmGiamGia.addRow(new Object[]{giamGia.getMaGiam(), giamGia.getTenGiamGia(), giamGia.getPhanTramGiam(), " > " + giamGia.getDieuKien(), giamGia.getNgayBD(), giamGia.getNgayKT()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtmaKM = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txttenKM = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtmucGiamGia = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        ngayBD = new com.toedter.calendar.JDateChooser();
        jPanel20 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        ngayKT = new com.toedter.calendar.JDateChooser();
        jPanel18 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        spnDieuKien = new javax.swing.JSpinner();
        jPanel12 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGiamGia = new Mytable();

        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 160, 80));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Chương trình khuyến mãi");
        add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel4.setName(""); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(300, 350));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 300));
        jPanel3.setLayout(new java.awt.GridLayout(3, 1));

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25));
        jPanel1.setLayout(new java.awt.GridLayout(2, 1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mã khuyến mãi:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(204, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel5);

        txtmaKM.setEnabled(false);
        jPanel1.add(txtmaKM);

        jPanel3.add(jPanel1);

        jPanel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25));
        jPanel7.setLayout(new java.awt.GridLayout(2, 1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tên chương trình khuyến mãi:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(212, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel6);
        jPanel7.add(txttenKM);

        jPanel3.add(jPanel7);

        jPanel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25));
        jPanel8.setLayout(new java.awt.GridLayout(2, 1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Mức giảm giá:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(285, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.add(jPanel9);
        jPanel8.add(txtmucGiamGia);

        jPanel3.add(jPanel8);

        jPanel4.add(jPanel3, java.awt.BorderLayout.LINE_START);

        jPanel10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.setPreferredSize(new java.awt.Dimension(517, 250));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel13.setPreferredSize(new java.awt.Dimension(546, 35));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Thời gian sử dụng");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel13, java.awt.BorderLayout.PAGE_START);

        jPanel15.setLayout(new java.awt.GridLayout(2, 1));

        jPanel16.setLayout(new java.awt.GridLayout(2, 1));

        jPanel19.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 50, 10, 100));
        jPanel19.setLayout(new java.awt.BorderLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Thời gian bắt đầu giảm giá");
        jLabel7.setPreferredSize(new java.awt.Dimension(200, 16));
        jPanel19.add(jLabel7, java.awt.BorderLayout.LINE_START);
        jPanel19.add(ngayBD, java.awt.BorderLayout.CENTER);

        jPanel16.add(jPanel19);

        jPanel20.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 50, 10, 100));
        jPanel20.setLayout(new java.awt.BorderLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Thời gian kết thúc giảm giá ");
        jLabel8.setPreferredSize(new java.awt.Dimension(200, 16));
        jPanel20.add(jLabel8, java.awt.BorderLayout.LINE_START);
        jPanel20.add(ngayKT, java.awt.BorderLayout.CENTER);

        jPanel16.add(jPanel20);

        jPanel15.add(jPanel16);

        jPanel18.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 50, 10, 50));
        jPanel18.setLayout(new java.awt.BorderLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Điều kiện ( > X )");
        jLabel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));
        jPanel18.add(jLabel9, java.awt.BorderLayout.PAGE_START);
        jPanel18.add(spnDieuKien, java.awt.BorderLayout.CENTER);

        jPanel15.add(jPanel18);

        jPanel11.add(jPanel15, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel11, java.awt.BorderLayout.PAGE_START);

        jPanel12.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 100, 10, 100));
        jPanel12.setLayout(new java.awt.GridLayout(2, 2));

        btnAdd.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(0, 160, 80));
        btnAdd.setText("ADD");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel12.add(btnAdd);

        btnDelete.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 160, 80));
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel12.add(btnDelete);

        btnEdit.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(0, 160, 80));
        btnEdit.setText("EDIT");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel12.add(btnEdit);

        btnReset.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnReset.setForeground(new java.awt.Color(0, 160, 80));
        btnReset.setText("RESET");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel12.add(btnReset);

        jPanel10.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel21.setLayout(new java.awt.BorderLayout());

        jPanel22.setPreferredSize(new java.awt.Dimension(1050, 50));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Danh sách sản phẩm");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(823, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel21.add(jPanel22, java.awt.BorderLayout.PAGE_START);

        tblGiamGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblGiamGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã khuyến mãi", "Tên chương trình", "Hình thức giảm giá", "Giảm giá", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái", "Mô tả"
            }
        ));
        jScrollPane1.setViewportView(tblGiamGia);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel21.add(jPanel23, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel21, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!checkInfo()) {
            return;
        }
        GiamGia giamGia = new GiamGia();
        giamGia.setMaGiam(0);
        giamGia.setTenGiamGia(txttenKM.getText());
        giamGia.setPhanTramGiam(Integer.parseInt(txtmucGiamGia.getText()));
        giamGia.setDieuKien(Integer.parseInt(spnDieuKien.getValue() + ""));
        giamGia.setNgayBD(ngayBD.getDate());
        giamGia.setNgayKT(ngayKT.getDate());
        giamGia.setTrangThai(1);
        if (giamGiaBUS.Insert(giamGia)) {
            loadData();
            resetInfo();
        }

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (tblGiamGia.getSelectedRow() < 0) {
            new dialog("Chưa chọn giảm giá cần sửa", dialog.ERROR_DIALOG);
            return;
        }
        if (!checkInfo()) {
            return;
        }
        GiamGia giamGia = new GiamGia();
        giamGia.setMaGiam(Integer.parseInt(txtmaKM.getText()));
        giamGia.setTenGiamGia(txttenKM.getText());
        giamGia.setPhanTramGiam(Integer.parseInt(txtmucGiamGia.getText()));
        giamGia.setDieuKien(Integer.parseInt(spnDieuKien.getValue() + ""));
        giamGia.setNgayBD(ngayBD.getDate());
        giamGia.setNgayKT(ngayKT.getDate());
        giamGia.setTrangThai(1);
        if (giamGiaBUS.Edit(giamGia)) {
            loadData();
            resetInfo();
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        resetInfo();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int[] rows=tblGiamGia.getSelectedRows();
        if(rows.length == 0){
            new dialog("Chọn giảm giá muốn xóa", dialog.ERROR_DIALOG);
            return;
        }
        for(int i =0;i<rows.length;i++){
            int maGiamGia=Integer.parseInt(tblGiamGia.getValueAt(rows[i], 0)+"");
            if(!giamGiaBUS.Delete(maGiamGia))
            {
                return;
            }
        }
        loadData();
        resetInfo();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void resetInfo() {
        txtmaKM.setText("");
        txttenKM.setText("");
        txtmucGiamGia.setText("");
        spnDieuKien.setValue(0);
        ngayBD.setDate(new Date());
        ngayKT.setDate(new Date());
    }

    private void addEvents() {
        tblGiamGia.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) { //nếu dữ liệu được chọn đang thay đổi thì return
                    return;
                }
                int row = tblGiamGia.getSelectedRow();
                if (row < 0) { //tránh lỗi khi loadData
                    return;
                }
                GiamGia giamGia = giamGiaBUS.getById(Integer.parseInt(tblGiamGia.getValueAt(row, 0) + ""));
                txtmaKM.setText(giamGia.getMaGiam() + "");
                txttenKM.setText(giamGia.getTenGiamGia());
                txtmucGiamGia.setText(giamGia.getPhanTramGiam() + "");
                spnDieuKien.setValue(giamGia.getDieuKien());
                if (giamGia.getNgayBD() == null || giamGia.getNgayKT() == null) {
                    return;
                }
                ngayBD.setDate(giamGia.getNgayBD());
                ngayKT.setDate(giamGia.getNgayKT());
            }
        });
    }

    private boolean checkInfo() {
        if (InputValidator.IsEmpty(txttenKM.getText()) || InputValidator.IsEmpty(txtmucGiamGia.getText()) || InputValidator.IsEmpty(spnDieuKien.getValue() + "")) {
            new dialog("Không được để trống", dialog.ERROR_DIALOG);
            return false;
        }
        if (!InputValidator.isPositiveNumber(txtmucGiamGia.getText()) || !InputValidator.isPositiveNumber(spnDieuKien.getValue() + "")) {
            new dialog("Mức giảm giá hoặc điều kiện không hợp lệ!", dialog.ERROR_DIALOG);
            return false;
        }
        if (InputValidator.OverflowChecker(txtmucGiamGia.getText()) || Integer.parseInt(txtmucGiamGia.getText()) > 100) {
            new dialog("Mức giảm giá không được quá 100!", dialog.ERROR_DIALOG);
            return false;
        }
        if (InputValidator.OverflowChecker(spnDieuKien.getValue() + "")) {
            new dialog("Điều kiện để giảm giá quá lớn!", dialog.ERROR_DIALOG);
            return false;
        }
        if (ngayBD.getDate().after(ngayKT.getDate())) {
            new dialog("Thời gian giảm giá không hợp lệ!", dialog.ERROR_DIALOG);
            return false;
        }
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser ngayBD;
    private com.toedter.calendar.JDateChooser ngayKT;
    private javax.swing.JSpinner spnDieuKien;
    private javax.swing.JTable tblGiamGia;
    private javax.swing.JTextField txtmaKM;
    private javax.swing.JTextField txtmucGiamGia;
    private javax.swing.JTextField txttenKM;
    // End of variables declaration//GEN-END:variables
}
