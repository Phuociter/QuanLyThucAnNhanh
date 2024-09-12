package GUI;

import BUS.PhanQuyenBUS;
import Custom.Mytable;
import Custom.NonEditableTableModel;
import Custom.dialog;
import DTO.PhanQuyen;
import static Main.Main.changLNF;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;

public class PnPhanQuyen extends JPanel {

    final Color ClMain = new Color(0, 160, 80);
    Font font = new Font("", Font.PLAIN, 20);

    ArrayList<JCheckBox> listckb;
    JCheckBox ckbQLnhaphang, ckbQLsanpham, ckbQLnhanvien, ckbQLkhachhang, ckbQLthongke;
    JButton btnThemquyen, btnSuaquyen, btnXoaquyen;
    JComboBox<String> cmbQuyen;
    NonEditableTableModel dtm;
    PhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();

    public PnPhanQuyen() {
        changLNF("Windows");
        addControls();
        addEvents();
    }

    private void addControls() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel pnTitlePhanQuyen = new JPanel();
        JLabel lbTitlePhanQuyen = new JLabel("<html><h1>Quản lý phân quyền</h1></html>");
        lbTitlePhanQuyen.setForeground(ClMain);
        pnTitlePhanQuyen.add(lbTitlePhanQuyen);
        this.add(pnTitlePhanQuyen);

        JPanel pnCbQuyen = new JPanel();
        JLabel lbCbQuyen = new JLabel("<html><b>Nhóm quyền:<b><html>");
        lbCbQuyen.setFont(font);
        cmbQuyen = new JComboBox<>();
        cmbQuyen.setFont(font);
        cmbQuyen.setFocusable(false);
        cmbQuyen.setBackground(ClMain);
        pnCbQuyen.add(lbCbQuyen);
        pnCbQuyen.add(cmbQuyen);
        this.add(pnCbQuyen);

        JPanel pnCkQLnhaphang = new JPanel();
        ckbQLnhaphang = new JCheckBox("Quản lý nhập hàng");
        ckbQLnhaphang.setFocusable(false);
        ckbQLnhaphang.setFont(font);
        pnCkQLnhaphang.add(ckbQLnhaphang);
        this.add(pnCkQLnhaphang);

        JPanel pnCkQLsanpham = new JPanel();
        ckbQLsanpham = new JCheckBox("Quản lý sản phẩm");
        ckbQLsanpham.setFocusable(false);
        ckbQLsanpham.setFont(font);
        pnCkQLsanpham.add(ckbQLsanpham);
        this.add(pnCkQLsanpham);

        JPanel pnCkQLnhanvien = new JPanel();
        ckbQLnhanvien = new JCheckBox("Quản lý nhân viên");
        ckbQLnhanvien.setFocusable(false);
        ckbQLnhanvien.setFont(font);
        pnCkQLnhanvien.add(ckbQLnhanvien);
        this.add(pnCkQLnhanvien);

        JPanel pnCkQLkhachhang = new JPanel();
        ckbQLkhachhang = new JCheckBox("Quản lý khách hàng");
        ckbQLkhachhang.setFocusable(false);
        ckbQLkhachhang.setFont(font);
        pnCkQLkhachhang.add(ckbQLkhachhang);
        this.add(pnCkQLkhachhang);

        JPanel pnCkQLthongke = new JPanel();
        ckbQLthongke = new JCheckBox("Quản lý thống kê");
        ckbQLthongke.setFocusable(false);
        ckbQLthongke.setFont(font);
        pnCkQLthongke.add(ckbQLthongke);
        this.add(pnCkQLthongke);

        Dimension ckbSize = new Dimension(ckbQLkhachhang.getPreferredSize());
        ckbQLnhanvien.setPreferredSize(ckbSize);
        ckbQLnhaphang.setPreferredSize(ckbSize);
        ckbQLsanpham.setPreferredSize(ckbSize);
        ckbQLthongke.setPreferredSize(ckbSize);

        listckb = new ArrayList<>();
        listckb.add(ckbQLnhaphang);
        listckb.add(ckbQLsanpham);
        listckb.add(ckbQLnhanvien);
        listckb.add(ckbQLkhachhang);
        listckb.add(ckbQLthongke);

        JPanel pnBtnQuyen = new JPanel();
        btnThemquyen = new JButton("Thêm quyền");
        btnSuaquyen = new JButton("Sửa quyền");
        btnXoaquyen = new JButton("Xóa quyền");
        btnThemquyen.setIcon(new ImageIcon("image/btn/plus.png"));
        btnSuaquyen.setIcon(new ImageIcon("image/btn/write.png"));
        btnXoaquyen.setIcon(new ImageIcon("image/btn/bin.png"));
        btnThemquyen.setFocusable(false);
        btnSuaquyen.setFocusable(false);
        btnXoaquyen.setFocusable(false);
        btnThemquyen.setForeground(ClMain);
        btnSuaquyen.setForeground(ClMain);
        btnXoaquyen.setForeground(ClMain);
        btnThemquyen.setFont(font);
        btnSuaquyen.setFont(font);
        btnXoaquyen.setFont(font);
        pnBtnQuyen.add(btnThemquyen);
        pnBtnQuyen.add(btnSuaquyen);
        pnBtnQuyen.add(btnXoaquyen);
        this.add(pnBtnQuyen);

        Dimension btnSize3 = new Dimension(btnThemquyen.getPreferredSize());
        btnSuaquyen.setPreferredSize(btnSize3);
        btnXoaquyen.setPreferredSize(btnSize3);

        JPanel pnTitletbPhanQuyen = new JPanel();
        JLabel lbTitletbPhanQuyen = new JLabel("<html><h1>Danh sách quyền<h1><html>");
        lbTitletbPhanQuyen.setForeground(ClMain);
        pnTitletbPhanQuyen.add(lbTitletbPhanQuyen);
        this.add(pnTitletbPhanQuyen);

        dtm = new NonEditableTableModel();
        dtm.addColumn("Mã quyền");
        dtm.addColumn("Tên quyền");
        dtm.addColumn("QL Nhập hàng");
        dtm.addColumn("QL Sản phẩm");
        dtm.addColumn("QL Nhân viên");
        dtm.addColumn("QL Khách hàng");
        dtm.addColumn("QL Thống kê");

        Mytable mtbQuyen = new Mytable(dtm);
        JScrollPane scrmtbQuyen = new JScrollPane(mtbQuyen, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrmtbQuyen);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        mtbQuyen.getColumnModel().getColumn(0).setPreferredWidth(50);
        mtbQuyen.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        mtbQuyen.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        mtbQuyen.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        mtbQuyen.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        mtbQuyen.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        mtbQuyen.getColumnModel().getColumn(5).setPreferredWidth(100);
        mtbQuyen.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        mtbQuyen.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        loadData();
    }

    private void addEvents() {
        cmbQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCkb();
            }

        });
        btnThemquyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenQuyen = JOptionPane.showInputDialog("Nhập tên quyền");
                if (tenQuyen == null) {
                    return;
                }
                if (phanQuyenBUS.Insert(tenQuyen)) {
                    loadData();
                }
            }
        });
        btnSuaquyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int maQuyen = getMaQuyen();
                if (maQuyen < 0) {
                    new dialog("Vui lòng chọn quyền", dialog.ERROR_DIALOG);
                    return;
                }
                int qlNhapHang = ckbQLnhaphang.isSelected() ? 1 : 0;
                int qlSanPham = ckbQLsanpham.isSelected() ? 1 : 0;
                int qlKhachHang = ckbQLkhachhang.isSelected() ? 1 : 0;
                int qlNhanVien = ckbQLnhanvien.isSelected() ? 1 : 0;
                int ThongKe = ckbQLthongke.isSelected() ? 1 : 0;
                if (phanQuyenBUS.Update(new PhanQuyen(maQuyen, null, qlNhapHang, qlSanPham, qlNhanVien, qlKhachHang, ThongKe, 1))) {
                    loadData();
                }

            }
        });
        btnXoaquyen.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int maQuyen = getMaQuyen();
                if (maQuyen < 0) {
                    new dialog("Vui lòng chọn quyền", dialog.ERROR_DIALOG);
                    return;
                }
                if(phanQuyenBUS.Delete(maQuyen)){
                    loadData();
                }
            }
        });
    }

    private int getMaQuyen() {
        if (cmbQuyen.getSelectedItem() == null || cmbQuyen.getSelectedItem().equals("Chọn quyền")) {
            return -1;
        }
        String SelectedQuyen = cmbQuyen.getSelectedItem() + "";
        return Integer.parseInt(SelectedQuyen.split("[- ]")[0]);
    }

    private void loadCkb() {
        for (JCheckBox checkBox : listckb) {
            checkBox.setSelected(false);
        }
        int maQuyen = getMaQuyen();
        if (maQuyen < 0) {
            return;
        }
        PhanQuyen phanQuyen = phanQuyenBUS.getById(maQuyen);
        if (phanQuyen == null) {
            new dialog("Không tìm thấy quyền", dialog.ERROR_DIALOG);
            return;
        }
        if (phanQuyen.getQlKhachHang() == 1) {
            ckbQLkhachhang.setSelected(true);
        }
        if (phanQuyen.getQlNhanVien() == 1) {
            ckbQLnhanvien.setSelected(true);
        }
        if (phanQuyen.getQlNhapHang() == 1) {
            ckbQLnhaphang.setSelected(true);
        }
        if (phanQuyen.getQlSanPham() == 1) {
            ckbQLsanpham.setSelected(true);
        }
        if (phanQuyen.getThongKe() == 1) {
            ckbQLthongke.setSelected(true);
        }
    }

    private void loadData() {
        ArrayList<PhanQuyen> phanQuyens = phanQuyenBUS.getList();
        if (phanQuyens == null) {
            return;
        }
        cmbQuyen.removeAllItems();
        cmbQuyen.addItem("Chọn quyền");
        dtm.setRowCount(0);
        for (PhanQuyen phanQuyen : phanQuyens) {
            cmbQuyen.addItem(phanQuyen.getMaQuyen() + " - " + phanQuyen.getTenQuyen());
            dtm.addRow(new Object[]{phanQuyen.getMaQuyen(), phanQuyen.getTenQuyen(), phanQuyen.getQlNhapHang(), phanQuyen.getQlSanPham(), phanQuyen.getQlNhanVien(), phanQuyen.getQlKhachHang(), phanQuyen.getThongKe()});
        }

    }
}
