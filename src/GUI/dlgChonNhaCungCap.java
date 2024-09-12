package GUI;

import BUS.NhaCungCapBUS;
import Custom.Mytable;
import Custom.NonEditableTableModel;
import Custom.dialog;
import DTO.NhaCungCap;
import java.util.ArrayList;

public class dlgChonNhaCungCap extends javax.swing.JDialog {

    NonEditableTableModel dtmNhaCungCap;
    NhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
    private NhaCungCap selectedNhaCungCap = null;//nhà cung cấp đang được chọn

    public NhaCungCap getSelectedNhaCungCap() {
        return selectedNhaCungCap;
    }

    public dlgChonNhaCungCap() {
        initComponents();
        custom();
        showDlg();
    }

    private void custom() {
        dtmNhaCungCap = new NonEditableTableModel();
        dtmNhaCungCap.addColumn("Mã NCC");
        dtmNhaCungCap.addColumn("Tên NCC");
        dtmNhaCungCap.addColumn("Địa chỉ");
        dtmNhaCungCap.addColumn("Điện thoại");
        tbNCC.setModel(dtmNhaCungCap);

        tbNCC.getColumnModel().getColumn(0).setPreferredWidth(10);

        loadData();
    }

    private void showDlg() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setVisible(true);
    }
    //load dữ liệu 
    private void loadData() {
        dtmNhaCungCap.setRowCount(0);
        ArrayList<NhaCungCap> nhaCungCaps = nhaCungCapBUS.getList();
        if (nhaCungCaps.isEmpty()) {
            return;
        }
        for (NhaCungCap ncc : nhaCungCaps) {
            dtmNhaCungCap.addRow(new Object[]{ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(), ncc.getDienThoai()});
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNCC = new Mytable();
        jPanel3 = new javax.swing.JPanel();
        btnChon = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);

        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 160, 80));
        jLabel1.setText("Chọn nhà cung cấp");
        jPanel2.add(jLabel1);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        tbNCC.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tbNCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã NCC", "Tên NCC", "Địa chỉ", "Điện thoại"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbNCC);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        jScrollPane1.getAccessibleContext().setAccessibleParent(jPanel1);

        btnChon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnChon.setForeground(new java.awt.Color(0, 160, 80));
        btnChon.setText("Chọn");
        btnChon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChon.setFocusable(false);
        btnChon.setPreferredSize(new java.awt.Dimension(100, 50));
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
            }
        });
        jPanel3.add(btnChon);

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

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnSua.setForeground(new java.awt.Color(0, 160, 80));
        btnSua.setText("Sửa");
        btnSua.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSua.setFocusable(false);
        btnSua.setPreferredSize(new java.awt.Dimension(100, 50));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel3.add(btnSua);

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(0, 160, 80));
        btnXoa.setText("Xóa");
        btnXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoa.setFocusable(false);
        btnXoa.setPreferredSize(new java.awt.Dimension(100, 50));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel3.add(btnXoa);

        jPanel1.add(jPanel3, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //kiểm tra hàng người dùng chọn trong bảng
    private int checkSelectRow() {
        if (tbNCC.getSelectedRows().length > 1) {
            new dialog("Chỉ chọn 1 nhà cung cấp", dialog.ERROR_DIALOG);
            return -1;
        }
        int row = tbNCC.getSelectedRow();
        if (row < 0) {
            new dialog("Vui lòng chọn nhà cung cấp", dialog.ERROR_DIALOG);
        }
        return row;
    }
    //lấy dữ liệu từ hàng người dùng chọn
    private NhaCungCap selectedRow(int row) {
        int maNCC = Integer.parseInt("" + tbNCC.getValueAt(row, 0));
        String tenNCC = "" + tbNCC.getValueAt(row, 1);
        String diaChi = "" + tbNCC.getValueAt(row, 2);
        String dienThoai = "" + tbNCC.getValueAt(row, 3);
        int trangThai = 1;
        return new NhaCungCap(maNCC, tenNCC, diaChi, dienThoai, trangThai);
    }
    //nút chọn
    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed
        int row = checkSelectRow();
        if (row < 0) {
            return;
        }
        selectedNhaCungCap = selectedRow(row);
        this.dispose();
    }//GEN-LAST:event_btnChonActionPerformed
    //nút sửa
    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int row = checkSelectRow();
        if (row < 0) {
            return;
        }
        NhaCungCap sltNhaCungCap = selectedRow(row);
        dlgSuaNhaCungCap dlSuaNhaCungCap = new dlgSuaNhaCungCap(sltNhaCungCap);//hiển thị dlg sửa
        loadData();// load lại dữ liệu
    }//GEN-LAST:event_btnSuaActionPerformed
    //nút thêm
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        dlgThemNhaCungCap dltThemNhaCungCap = new dlgThemNhaCungCap();// hiển thị dlg thêm
        loadData();// load lại dữ liệu
    }//GEN-LAST:event_btnThemActionPerformed
    //nút xóa
    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = checkSelectRow();
        if (row < 0) {
            return;
        }
        NhaCungCap sltNhaCungCap = selectedRow(row);
        dialog dlg = new dialog("Xóa nhà cung cấp " + sltNhaCungCap.getTenNCC(), dialog.WARNING_DIALOG);// hiển thị dlg xóa
        int action = dlg.getAction(); // lấy action để check xem người dùng chọn OK hay Cancel
        if (action == 2) { //Nếu chọn Cancel thì không làm gì cả
            return;
        }
        if (!nhaCungCapBUS.Delete(sltNhaCungCap)) {//Tiến hành xóa nếu người dùng chọn OK
            return;
        }
        loadData();// load lại dữ liệu
    }//GEN-LAST:event_btnXoaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChon;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbNCC;
    // End of variables declaration//GEN-END:variables
}
