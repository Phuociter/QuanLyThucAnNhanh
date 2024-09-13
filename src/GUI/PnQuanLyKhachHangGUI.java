package GUI;

import BUS.KhachHangBUS;
import Custom.Mytable;
import Custom.NonEditableTableModel;
import DTO.KhachHang;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import Custom.dialog;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

public class PnQuanLyKhachHangGUI extends javax.swing.JPanel {

    NonEditableTableModel dtmKhachHang; // dtm này không cho người dùng sửa bảng
    KhachHangBUS khachHangBus = new KhachHangBUS();
    private KhachHang selectedKhachHang = null;

    public KhachHang getselectedKhachHag() {
        return selectedKhachHang;
    }

    //Hàm khởi tạo
    public PnQuanLyKhachHangGUI() {
        initComponents();
        custom();
        loadData();
        addEvents();
    }

    //lưu trữ dữ liệu của bảng
    public void custom() {
        dtmKhachHang = new NonEditableTableModel();

        dtmKhachHang.addColumn("Mã khác hàng");
        dtmKhachHang.addColumn("Tên khách hàng");
        dtmKhachHang.addColumn("Giới tính");
        dtmKhachHang.addColumn("SĐT");
        dtmKhachHang.addColumn("Email");
        dtmKhachHang.addColumn("Địa chỉ");
        dtmKhachHang.addColumn("Tổng chi tiêu");
        dtmKhachHang.addColumn("Trạng thái");

        tbKhachHang.setModel(dtmKhachHang);
        tbKhachHang.getColumnModel().getColumn(0).setPreferredWidth(10);

    }

    private void showDlg() {

        this.setVisible(true);
    }

    public void loadData() {
        dtmKhachHang.setRowCount(0);
        ArrayList<KhachHang> khachHangs = khachHangBus.getListKhachHang();
        if (khachHangs.isEmpty()) {
            return;
        }
        for (KhachHang khachHang : khachHangs) {
            dtmKhachHang.addRow(new Object[]{khachHang.getMaKH(), khachHang.getTen(),
                khachHang.getGioiTinh(), khachHang.getDienThoai(), khachHang.getEmail(), khachHang.getDiaChi(),
                khachHang.getTongChiTieu(), khachHang.getTrangThai()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        rdbNam = new javax.swing.JRadioButton();
        rdbNu = new javax.swing.JRadioButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtDT = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        rdbOnl = new javax.swing.JRadioButton();
        rdbOff = new javax.swing.JRadioButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtTongChiTieu = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        search = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbKhachHang = new Mytable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(1121, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 160, 80));
        jLabel1.setText("QUẢN LÍ KHÁCH HÀNG");
        jLabel1.setAutoscrolls(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(470, 470, 470)
                .addComponent(jLabel1)
                .addContainerGap(435, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(1121, 350));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel7.setPreferredSize(new java.awt.Dimension(1121, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel2.setText("Thiết lập thông tin khách hàng");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 834, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel7, java.awt.BorderLayout.PAGE_START);

        jPanel8.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel9.setPreferredSize(new java.awt.Dimension(445, 320));
        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.LINE_AXIS));

        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.Y_AXIS));

        jPanel13.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 20, 25, 20));
        jPanel13.setPreferredSize(new java.awt.Dimension(445, 82));
        jPanel13.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Mã khách hàng:");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 16));
        jPanel13.add(jLabel3, java.awt.BorderLayout.LINE_START);

        txtMaKH.setEnabled(false);
        jPanel13.add(txtMaKH, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel13);

        jPanel14.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 20, 25, 20));
        jPanel14.setPreferredSize(new java.awt.Dimension(445, 80));
        jPanel14.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tên khách hàng:");
        jLabel4.setPreferredSize(new java.awt.Dimension(150, 16));
        jPanel14.add(jLabel4, java.awt.BorderLayout.LINE_START);
        jPanel14.add(txtTen, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel14);

        jPanel15.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanel15.setPreferredSize(new java.awt.Dimension(445, 80));
        jPanel15.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Giới tính");
        jLabel6.setPreferredSize(new java.awt.Dimension(150, 16));
        jPanel15.add(jLabel6, java.awt.BorderLayout.LINE_START);

        jPanel5.setPreferredSize(new java.awt.Dimension(408, 50));
        jPanel5.setLayout(new java.awt.GridLayout(1, 2));

        buttonGroup1.add(rdbNam);
        rdbNam.setText("Nam");
        jPanel5.add(rdbNam);

        buttonGroup1.add(rdbNu);
        rdbNu.setText("Nữ");
        jPanel5.add(rdbNu);

        jPanel15.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel15);

        jPanel16.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 20, 25, 20));
        jPanel16.setPreferredSize(new java.awt.Dimension(445, 80));
        jPanel16.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Số điện thoại: ");
        jLabel5.setPreferredSize(new java.awt.Dimension(150, 16));
        jPanel16.add(jLabel5, java.awt.BorderLayout.LINE_START);
        jPanel16.add(txtDT, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel16);

        jPanel9.add(jPanel12);

        jPanel8.add(jPanel9, java.awt.BorderLayout.LINE_START);

        jPanel10.setBorder(javax.swing.BorderFactory.createEmptyBorder(40, 40, 40, 40));
        jPanel10.setPreferredSize(new java.awt.Dimension(220, 320));
        jPanel10.setLayout(new java.awt.GridLayout(4, 1));

        btnAdd.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(0, 160, 80));
        btnAdd.setText("ADD");
        btnAdd.setMargin(new java.awt.Insets(2, 14, 10, 14));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel10.add(btnAdd);

        jButton2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 160, 80));
        jButton2.setText("DELETE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel10.add(jButton2);

        edit.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        edit.setForeground(new java.awt.Color(0, 160, 80));
        edit.setText("EDIT");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed1(evt);
            }
        });
        jPanel10.add(edit);

        jButton6.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 160, 80));
        jButton6.setText("RESET");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        jPanel10.add(jButton6);

        jPanel8.add(jPanel10, java.awt.BorderLayout.LINE_END);

        jPanel11.setLayout(new javax.swing.BoxLayout(jPanel11, javax.swing.BoxLayout.LINE_AXIS));

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));

        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 40, 25, 20));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Email:");
        jLabel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        jLabel7.setPreferredSize(new java.awt.Dimension(150, 16));
        jPanel6.add(jLabel7, java.awt.BorderLayout.LINE_START);
        jPanel6.add(txtEmail, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel6);

        jPanel17.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 40, 25, 20));
        jPanel17.setLayout(new java.awt.BorderLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Trạng thái:");
        jLabel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 27, 1, 1));
        jLabel8.setPreferredSize(new java.awt.Dimension(150, 16));
        jPanel17.add(jLabel8, java.awt.BorderLayout.LINE_START);

        jPanel21.setLayout(new java.awt.GridLayout(1, 2));

        buttonGroup2.add(rdbOnl);
        rdbOnl.setText("Còn hoạt động");
        jPanel21.add(rdbOnl);

        buttonGroup2.add(rdbOff);
        rdbOff.setText("Ngưng hoạt động");
        jPanel21.add(rdbOff);

        jPanel17.add(jPanel21, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel17);

        jPanel18.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 40, 25, 20));
        jPanel18.setLayout(new java.awt.BorderLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Địa chỉ");
        jLabel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        jLabel9.setPreferredSize(new java.awt.Dimension(150, 16));
        jPanel18.add(jLabel9, java.awt.BorderLayout.LINE_START);
        jPanel18.add(txtDiaChi, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel18);

        jPanel19.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 40, 25, 20));
        jPanel19.setLayout(new java.awt.BorderLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Tổng chi tiêu:");
        jLabel10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        jLabel10.setPreferredSize(new java.awt.Dimension(150, 16));
        jPanel19.add(jLabel10, java.awt.BorderLayout.LINE_START);

        txtTongChiTieu.setEnabled(false);
        jPanel19.add(txtTongChiTieu, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel19);

        jPanel11.add(jPanel4);

        jPanel8.add(jPanel11, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel20.setLayout(new java.awt.BorderLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel11.setText("Thông tin khách hàng");
        jLabel11.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 1, 1, 1));
        jPanel20.add(jLabel11, java.awt.BorderLayout.PAGE_START);

        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel23.setLayout(new java.awt.BorderLayout());

        jPanel22.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 40, 5, 500));
        jPanel22.setPreferredSize(new java.awt.Dimension(1121, 40));
        jPanel22.setLayout(new java.awt.BorderLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("Tìm kiếm");
        jLabel13.setPreferredSize(new java.awt.Dimension(100, 16));
        jPanel22.add(jLabel13, java.awt.BorderLayout.LINE_START);

        search.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        search.setForeground(new java.awt.Color(0, 160, 80));
        search.setText("Search");
        search.setPreferredSize(new java.awt.Dimension(100, 23));
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        jPanel22.add(search, java.awt.BorderLayout.LINE_END);
        jPanel22.add(jTextField1, java.awt.BorderLayout.CENTER);

        jPanel23.add(jPanel22, java.awt.BorderLayout.PAGE_START);

        tbKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Giới tính", "SĐT", "Email", "Địa chỉ", "Tổng chi tiêu", "Trạng thái"
            }
        ));
        jScrollPane2.setViewportView(tbKhachHang);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1064, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel23.add(jPanel24, java.awt.BorderLayout.CENTER);

        jPanel20.add(jPanel23, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel20, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        KhachHang khachHang = new KhachHang();
        String gioiTinh = "";
        int trangthai = -1;
        if (rdbNam.isSelected()) {
            gioiTinh = rdbNam.getText();
        }
        if (rdbNu.isSelected()) {
            gioiTinh = rdbNu.getText();
        }
        if (rdbOnl.isSelected()) {
            trangthai = 1;
        }
        if (rdbOff.isSelected()) {
            trangthai = 0;
        }
        khachHang.setGioiTinh(gioiTinh);
        khachHang.setTrangThai(trangthai);
        khachHang.setMaKH(0);
        khachHang.setTen(txtTen.getText());
        khachHang.setDienThoai(txtDT.getText());
        khachHang.setEmail(txtEmail.getText());
        khachHang.setDiaChi(txtDiaChi.getText());
        khachHang.setTongChiTieu(0);
        khachHang.setTrangThai(1);
        if (khachHangBus.Insert(khachHang)) {
            loadData();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        int row = CheckSelectRow();
        if (row < 0) {
            return;
        }
        selectedKhachHang = selectedRow(row);
        dialog dlg = new dialog("Xóa Khách Hàng" + "" + selectedKhachHang.getTen(), dialog.WARNING_DIALOG);// hiển thị dlg xóa
        int action = dlg.getAction();
        if (action == 2) {
            return;
        }
        if (!khachHangBus.Delete(selectedKhachHang)) {
            return;
        }
        selectedKhachHang = null;
        loadData();
    }//GEN-LAST:event_deleteActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        txtMaKH.setText("");
        txtTen.setText("");
        txtDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        txtTongChiTieu.setText("0");
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
    }//GEN-LAST:event_resetActionPerformed

    private void editActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed1
        KhachHang khachHang = new KhachHang();
        String gioiTinh = "";
        int trangthai = -1;
        if (rdbNam.isSelected()) {
            gioiTinh = rdbNam.getText();
        }
        if (rdbNu.isSelected()) {
            gioiTinh = rdbNu.getText();
        }
        if (rdbOnl.isSelected()) {
            trangthai = 1;
        }
        if (rdbOff.isSelected()) {
            trangthai = 0;
        }
        khachHang.setGioiTinh(gioiTinh);
        khachHang.setTrangThai(trangthai);
        khachHang.setMaKH(Integer.parseInt(txtMaKH.getText()));
        khachHang.setTen(txtTen.getText());
        khachHang.setDienThoai(txtDT.getText());
        khachHang.setEmail(txtEmail.getText());
        khachHang.setDiaChi(txtDiaChi.getText());
        khachHang.setTrangThai(1);
        if (khachHangBus.Update(khachHang)) {
            loadData();
        txtMaKH.setText("");
        txtTen.setText("");
        txtDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        }
        
        

    }//GEN-LAST:event_editActionPerformed1
    private void addEvents() {
        tbKhachHang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                int row = tbKhachHang.getSelectedRow();
                if (row < 0) {
                    return;
                }
                KhachHang khachHang = khachHangBus.getID(Integer.parseInt(tbKhachHang.getValueAt(row, 0) + ""));
                txtMaKH.setText(khachHang.getMaKH() + "");
                txtTen.setText(khachHang.getTen());
                txtDT.setText(khachHang.getDienThoai());
                txtEmail.setText(khachHang.getEmail());
                txtDiaChi.setText(khachHang.getDiaChi());
                txtTongChiTieu.setText(khachHang.getTongChiTieu()+"");
                if (!khachHang.getGioiTinh().equals("Nam")) {
                    rdbNu.setSelected(true);
                } else {
                    
                    rdbNam.setSelected(true);
                }
                if (khachHang.getTrangThai() == 1) {
                    rdbOnl.setSelected(true);
                } else {
                    rdbOff.setSelected(true);
                }
            }
        });

    }

    private void searchByName(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tbKhachHang.getModel();
        TableRowSorter sorter = new TableRowSorter(model);
        tbKhachHang.setRowSorter(sorter);
        //lọc kiếm  theo tên
        RowFilter rowFilter = RowFilter.regexFilter("(?i)" + keyword, 1); // (?i) để bỏ qua sự phân biệt chữ hoa chữ thường
        sorter.setRowFilter(rowFilter);
        if (tbKhachHang.getRowCount() == 0) {
            // Nếu không có dòng nào được tìm thấy, hiển thị thông báo lỗi
            new dialog("Không tìm thấy khách hàng", dialog.ERROR_DIALOG);
            // Khôi phục bộ lọc để giữ nguyên dữ liệu ban đầu trong bảng
            sorter.setRowFilter(null);
        }
    }

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // Lấy từ khóa tìm kiếm từ trường văn bản
        String searchKeyword = jTextField1.getText().trim();

        // Kiểm tra xem từ khóa tìm kiếm có rỗng không
        if (searchKeyword.isEmpty()) {
            // Nếu rỗng, hiển thị thông báo lỗi
            new dialog("Vui lòng nhập tên khách hàng cần tìm", dialog.ERROR_DIALOG);
            reloadData();
        }

        // Gọi phương thức để tìm kiếm và hiển thị kết quả
        searchByName(searchKeyword);


    }//GEN-LAST:event_searchActionPerformed
    private void reloadData() {
        DefaultTableModel model = (DefaultTableModel) tbKhachHang.getModel();
        model.setRowCount(0); // Clear existing rows
        loadData(); // Load initial data
    }

    //KT hàng trong bảng
    private int CheckSelectRow() {
        if (tbKhachHang.getSelectedRows().length > 1) {
            new dialog("Chỉ được chọn 1 khách hàng", dialog.ERROR_DIALOG);
            return -1;
        }
        int row = tbKhachHang.getSelectedRow();
        if (row == 0) {
            new dialog(("Vui lòng chọn 1 khách hàng"), dialog.ERROR_DIALOG);
        }
        return row;
    }

    //Lấy dữ liệu từ người dùng
    private KhachHang selectedRow(int row) {
        int maKH = 0; // Giá trị mặc định
        if (tbKhachHang.getValueAt(row, 0) != null) {
            maKH = Integer.parseInt("" + tbKhachHang.getValueAt(row, 0));
        }

        String tenKH = "" + tbKhachHang.getValueAt(row, 1);
        String gioiTinh = "" + tbKhachHang.getValueAt(row, 2);
        String sdt = "" + tbKhachHang.getValueAt(row, 3);
        String email = "" + tbKhachHang.getValueAt(row, 4);
        String diaChi = "" + tbKhachHang.getValueAt(row, 5);

        int tongChiTieu = 0; // Giá trị mặc định
        if (tbKhachHang.getValueAt(row, 6) != null) {
            tongChiTieu = Integer.parseInt("" + tbKhachHang.getValueAt(row, 7));
        }
        int trangThai = 1;
        return new KhachHang(maKH, tenKH, gioiTinh, sdt, email.trim(), diaChi, tongChiTieu, trangThai);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton edit;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JRadioButton rdbNam;
    private javax.swing.JRadioButton rdbNu;
    private javax.swing.JRadioButton rdbOff;
    private javax.swing.JRadioButton rdbOnl;
    private javax.swing.JButton search;
    private javax.swing.JTable tbKhachHang;
    private javax.swing.JTextField txtDT;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTongChiTieu;
    // End of variables declaration//GEN-END:variables

}
