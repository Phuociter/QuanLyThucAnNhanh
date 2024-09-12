package GUI;

import BUS.DangNhapBUS;
import BUS.NhanVienBUS;
import BUS.SanPhamBUS;
import Custom.InputValidator;
import Custom.Mytable;
import Custom.NonEditableTableModel;
import Custom.ScaleImage;
import Custom.dialog;
import DTO.CTPhieuNhap;
import DTO.NhaCungCap;
import DTO.NhanVien;
import DTO.SanPham;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PnNhapHang extends javax.swing.JPanel {

    private NhaCungCap currentNCC;
    private SanPham currentSanPham = null;
    private SanPhamBUS sanPhamBUS = new SanPhamBUS();
    private NonEditableTableModel dtmNhapHang, dtmChoNhap;
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    private NhanVien currentNhanVien = nhanVienBUS.getById(DangNhapBUS.taiKhoanLogin.getMaNhanVien());

    public PnNhapHang() {
        initComponents();
        custom();
        loadData();
        addEvents();
    }

    private void custom() {
        dtmNhapHang = new NonEditableTableModel();
        dtmNhapHang.addColumn("Mã SP");
        dtmNhapHang.addColumn("Tên SP");
        dtmNhapHang.addColumn("Tồn kho");

        tblKhoHang.setModel(dtmNhapHang);

        tblKhoHang.getColumnModel().getColumn(1).setPreferredWidth(200);

        dtmChoNhap = new NonEditableTableModel();
        dtmChoNhap.addColumn("Mã SP");
        dtmChoNhap.addColumn("Tên SP");
        dtmChoNhap.addColumn("Số lượng");
        dtmChoNhap.addColumn("Đơn giá");
        dtmChoNhap.addColumn("Thành tiền");

        tblChoNhap.setModel(dtmChoNhap);

        tblChoNhap.getColumnModel().getColumn(1).setPreferredWidth(200);
    }

    private void loadData() {
        dtmNhapHang.setRowCount(0);
        ArrayList<SanPham> sanPhams = sanPhamBUS.getList();
        if (sanPhams == null) {
            return;
        }
        for (SanPham sanPham : sanPhams) {
            dtmNhapHang.addRow(new Object[]{sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getSoLuong()});
        }

        cmbNhanVien.removeAllItems();
        cmbNhanVien.addItem(currentNhanVien.getMaNV() + " - " + currentNhanVien.getHo() + " " + currentNhanVien.getTen());
    }

    private void XuLyTimKiem() {
        dtmNhapHang.setRowCount(0);
        ArrayList<SanPham> sanPhams = sanPhamBUS.TimKiemSPnhapHang(txtTimKiem.getText());
        if (sanPhams == null) {
            return;
        }
        for (SanPham sanPham : sanPhams) {
            dtmNhapHang.addRow(new Object[]{sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getSoLuong()});
        }
    }

    private void addEvents() {
        tblKhoHang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                int row = tblKhoHang.getSelectedRow();
                if (row < 0) {
                    return;
                }
                int maSP = Integer.parseInt(tblKhoHang.getValueAt(row, 0) + "");
                currentSanPham = sanPhamBUS.getById(maSP);
                ImageIcon imageIcon = ScaleImage.scaleImage("image/products/" + currentSanPham.getHinhAnh(), 200, 200);
                lbAnh.setIcon(imageIcon);
            }
        });

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

    private int duplicateSP(SanPham sp) {
        for (int i = 0; i < dtmChoNhap.getRowCount(); i++) {
            if (sp.getMaSP() == Integer.parseInt(tblChoNhap.getValueAt(i, 0) + "")) {
                return i;
            }
        }
        return -1;
    }

    private void addRowTblChoNhap() {
        if (currentSanPham == null) {
            new dialog("Vui lòng chọn sản phẩm", dialog.ERROR_DIALOG);
            return;
        }
        if (InputValidator.IsEmpty(txtSoLuong.getText()) || InputValidator.IsEmpty(txtDonGia.getText()) || !InputValidator.isPositiveNumber(txtSoLuong.getText()) || !InputValidator.isPositiveNumber(txtDonGia.getText())) {
            new dialog("Vui lòng nhập số", dialog.ERROR_DIALOG);
            return;
        }
        if (InputValidator.OverflowChecker(txtSoLuong.getText()) || InputValidator.OverflowChecker(txtDonGia.getText())) {
            new dialog("Số lượng hoặc đơn giá quá lớn", dialog.ERROR_DIALOG);
            return;
        }
        int donGia = Integer.parseInt(txtDonGia.getText());
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        if (donGia > Integer.MAX_VALUE / soLuong) {
            new dialog("Thành tiền quá lớn", dialog.ERROR_DIALOG);
            return;
        }
        int DuplicateRow = duplicateSP(currentSanPham); // lấy dòng trùng mã SP
        //Nếu không trùng
        if (DuplicateRow < 0) {
            dtmChoNhap.addRow(new Object[]{currentSanPham.getMaSP(), currentSanPham.getTenSP(), soLuong, donGia, donGia * soLuong});
            return;
        }
        int NewSoLuong = Integer.parseInt(tblChoNhap.getValueAt(DuplicateRow, 2) + "") + soLuong;
        tblChoNhap.setValueAt(NewSoLuong, DuplicateRow, 2);
        tblChoNhap.setValueAt(donGia, DuplicateRow, 3);
        tblChoNhap.setValueAt(NewSoLuong * donGia, DuplicateRow, 4);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnTable1 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        btnResetKho = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        scrTblKhoHang = new javax.swing.JScrollPane();
        tblKhoHang = new Mytable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChoNhap = new Mytable();
        jPanel3 = new javax.swing.JPanel();
        pnThongTin1 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        btnResetTT = new javax.swing.JButton();
        btnXacNhan = new javax.swing.JButton();
        pnAnhSP = new javax.swing.JPanel();
        lbAnh = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtNhaCungCap = new javax.swing.JTextField();
        btnChonNhaCungCap = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        cmbNhanVien = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnXoaChoNhap = new javax.swing.JButton();
        btnResetChoNhap = new javax.swing.JButton();
        btnNhap = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        setPreferredSize(new java.awt.Dimension(1030, 809));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));

        pnTable1.setPreferredSize(new java.awt.Dimension(1033, 844));
        pnTable1.setLayout(new javax.swing.BoxLayout(pnTable1, javax.swing.BoxLayout.Y_AXIS));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 160, 80));
        jLabel11.setText("Kho hàng");
        jPanel12.add(jLabel11);

        btnResetKho.setIcon(new javax.swing.ImageIcon("C:\\Users\\nguye\\OneDrive\\Documents\\NetBeansProjects\\DoAn_QuanLyBanDoAnNuocUong\\image\\btn\\refresh.png")); // NOI18N
        btnResetKho.setBorder(null);
        btnResetKho.setFocusable(false);
        btnResetKho.setPreferredSize(new java.awt.Dimension(40, 40));
        btnResetKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetKhoActionPerformed(evt);
            }
        });
        jPanel12.add(btnResetKho);

        pnTable1.add(jPanel12);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Tìm kiếm");
        jPanel16.add(jLabel13);

        txtTimKiem.setColumns(20);
        txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel16.add(txtTimKiem);

        pnTable1.add(jPanel16);

        tblKhoHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Tồn kho"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhoHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhoHangMouseClicked(evt);
            }
        });
        scrTblKhoHang.setViewportView(tblKhoHang);

        pnTable1.add(scrTblKhoHang);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 160, 80));
        jLabel1.setText("Chờ nhập");
        jLabel1.setToolTipText("");
        jPanel1.add(jLabel1);

        pnTable1.add(jPanel1);

        tblChoNhap.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblChoNhap);

        pnTable1.add(jScrollPane1);

        add(pnTable1);

        jPanel3.setPreferredSize(new java.awt.Dimension(500, 1071));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.Y_AXIS));

        pnThongTin1.setPreferredSize(new java.awt.Dimension(364, 50));
        pnThongTin1.setLayout(new javax.swing.BoxLayout(pnThongTin1, javax.swing.BoxLayout.Y_AXIS));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 160, 80));
        jLabel19.setText("Thông tin nhập");
        jPanel21.add(jLabel19);

        pnThongTin1.add(jPanel21);

        jPanel19.setPreferredSize(new java.awt.Dimension(370, 10));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Đơn giá nhập");
        jLabel17.setPreferredSize(new java.awt.Dimension(116, 22));
        jPanel19.add(jLabel17);

        txtDonGia.setColumns(15);
        txtDonGia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDonGia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel19.add(txtDonGia);

        pnThongTin1.add(jPanel19);

        jPanel26.setPreferredSize(new java.awt.Dimension(370, 10));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setText("Số lượng nhập");
        jPanel26.add(jLabel22);

        txtSoLuong.setColumns(15);
        txtSoLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtSoLuong.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel26.add(txtSoLuong);

        pnThongTin1.add(jPanel26);

        jPanel23.setPreferredSize(new java.awt.Dimension(297, 40));

        btnResetTT.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnResetTT.setForeground(new java.awt.Color(0, 160, 80));
        btnResetTT.setText("Reset");
        btnResetTT.setPreferredSize(new java.awt.Dimension(141, 41));
        btnResetTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTTActionPerformed(evt);
            }
        });
        jPanel23.add(btnResetTT);

        btnXacNhan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXacNhan.setForeground(new java.awt.Color(0, 160, 80));
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.setMaximumSize(new java.awt.Dimension(73, 29));
        btnXacNhan.setMinimumSize(new java.awt.Dimension(73, 29));
        btnXacNhan.setPreferredSize(new java.awt.Dimension(141, 41));
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });
        jPanel23.add(btnXacNhan);

        pnThongTin1.add(jPanel23);

        jPanel3.add(pnThongTin1);

        pnAnhSP.setMinimumSize(new java.awt.Dimension(100, 100));
        pnAnhSP.setPreferredSize(new java.awt.Dimension(100, 150));
        pnAnhSP.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        lbAnh.setIcon(new javax.swing.ImageIcon("C:\\Users\\nguye\\OneDrive\\Documents\\NetBeansProjects\\DoAn_QuanLyBanDoAnNuocUong\\image\\products\\white.png")); // NOI18N
        pnAnhSP.add(lbAnh);

        jPanel3.add(pnAnhSP);

        jPanel24.setLayout(new javax.swing.BoxLayout(jPanel24, javax.swing.BoxLayout.Y_AXIS));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Nhà cung cấp");

        txtNhaCungCap.setEditable(false);
        txtNhaCungCap.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNhaCungCap.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNhaCungCapActionPerformed(evt);
            }
        });

        btnChonNhaCungCap.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnChonNhaCungCap.setText("...");
        btnChonNhaCungCap.setFocusable(false);
        btnChonNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonNhaCungCapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel20)
                        .addGap(0, 263, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtNhaCungCap)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChonNhaCungCap)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel24.add(jPanel4);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("Nhân viên");

        cmbNhanVien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbNhanVien.setEnabled(false);
        cmbNhanVien.setPreferredSize(new java.awt.Dimension(280, 28));
        cmbNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(0, 291, Short.MAX_VALUE))
                    .addComponent(cmbNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel24.add(jPanel25);

        btnXoaChoNhap.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnXoaChoNhap.setForeground(new java.awt.Color(0, 160, 80));
        btnXoaChoNhap.setText("Xóa");
        btnXoaChoNhap.setPreferredSize(new java.awt.Dimension(141, 41));
        btnXoaChoNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaChoNhapActionPerformed(evt);
            }
        });
        jPanel2.add(btnXoaChoNhap);

        btnResetChoNhap.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnResetChoNhap.setForeground(new java.awt.Color(0, 160, 80));
        btnResetChoNhap.setText("Reset");
        btnResetChoNhap.setPreferredSize(new java.awt.Dimension(141, 41));
        btnResetChoNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetChoNhapActionPerformed(evt);
            }
        });
        jPanel2.add(btnResetChoNhap);

        btnNhap.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnNhap.setForeground(new java.awt.Color(0, 160, 80));
        btnNhap.setText("Nhập");
        btnNhap.setPreferredSize(new java.awt.Dimension(141, 41));
        btnNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapActionPerformed(evt);
            }
        });
        jPanel2.add(btnNhap);

        jPanel24.add(jPanel2);

        jPanel3.add(jPanel24);

        add(jPanel3);

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonNhaCungCapActionPerformed
        dlgChonNhaCungCap dlChonNhaCungCap = new dlgChonNhaCungCap();
        currentNCC = dlChonNhaCungCap.getSelectedNhaCungCap();
        if (currentNCC == null) {
            txtNhaCungCap.setText("");
            return;
        }
        txtNhaCungCap.setText(currentNCC.getTenNCC());
    }//GEN-LAST:event_btnChonNhaCungCapActionPerformed

    private void tblKhoHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhoHangMouseClicked
    }//GEN-LAST:event_tblKhoHangMouseClicked

    private void btnResetKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetKhoActionPerformed
        loadData();
    }//GEN-LAST:event_btnResetKhoActionPerformed

    private void txtNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNhaCungCapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNhaCungCapActionPerformed

    private void cmbNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbNhanVienActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        addRowTblChoNhap();
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnResetTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTTActionPerformed
        txtSoLuong.setText("");
        txtDonGia.setText("");
    }//GEN-LAST:event_btnResetTTActionPerformed

    private void btnXoaChoNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaChoNhapActionPerformed
        int[] rows = tblChoNhap.getSelectedRows();
        if (rows.length == 0) {
            new dialog("Vui lòng chọn sản phẩm muốn xóa", dialog.ERROR_DIALOG);
            return;
        }
        for (int i = 0; i < rows.length; i++)
            dtmChoNhap.removeRow(i);
    }//GEN-LAST:event_btnXoaChoNhapActionPerformed

    private void btnResetChoNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetChoNhapActionPerformed
        dtmChoNhap.setRowCount(0);
    }//GEN-LAST:event_btnResetChoNhapActionPerformed

    private void XuLyNhap() {
        int tongTien = 0;
        ArrayList<CTPhieuNhap> cTPhieuNhaps = new ArrayList<>();
        for (int i = 0; i < dtmChoNhap.getRowCount(); i++) {
            int thanhTien = Integer.parseInt(tblChoNhap.getValueAt(i, 4) + "");
            if (thanhTien > Integer.MAX_VALUE - tongTien) {
                new dialog("Thành tiền quá lớn!", dialog.ERROR_DIALOG);
                return;
            }
            int maSP = Integer.parseInt(tblChoNhap.getValueAt(i, 0) + "");
            int soLuong = Integer.parseInt(tblChoNhap.getValueAt(i, 2) + "");
            int donGia = Integer.parseInt(tblChoNhap.getValueAt(i, 3) + "");
            cTPhieuNhaps.add(new CTPhieuNhap(0, maSP, soLuong, donGia, thanhTien));
            tongTien += thanhTien;
        }
        XuatPhieuNhapGUI xuatPhieuNhapGUI = new XuatPhieuNhapGUI(currentNhanVien, currentNCC, tongTien, cTPhieuNhaps);
        if (!xuatPhieuNhapGUI.getIsSuccess()) {
            return;
        }
        dtmChoNhap.setRowCount(0);
        loadData();
    }

    private void btnNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapActionPerformed
        if (dtmChoNhap.getRowCount() == 0) {
            new dialog("Chưa chọn sản phẩm để nhập", dialog.ERROR_DIALOG);
            return;
        }
        if (currentNCC == null) {
            new dialog("Vui lòng chọn nhà cung cấp", dialog.ERROR_DIALOG);
            return;
        }
        XuLyNhap();

    }//GEN-LAST:event_btnNhapActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonNhaCungCap;
    private javax.swing.JButton btnNhap;
    private javax.swing.JButton btnResetChoNhap;
    private javax.swing.JButton btnResetKho;
    private javax.swing.JButton btnResetTT;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JButton btnXoaChoNhap;
    private javax.swing.JComboBox<String> cmbNhanVien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAnh;
    private javax.swing.JPanel pnAnhSP;
    private javax.swing.JPanel pnTable1;
    private javax.swing.JPanel pnThongTin1;
    private javax.swing.JScrollPane scrTblKhoHang;
    private javax.swing.JTable tblChoNhap;
    private javax.swing.JTable tblKhoHang;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtNhaCungCap;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
