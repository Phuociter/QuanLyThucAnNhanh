package GUI;

import BUS.LoaiSPBUS;
import BUS.SanPhamBUS;
import Custom.Mytable;
import Custom.dialog;
import Custom.listCard;
import DTO.CTHoaDon;
import DTO.SanPham;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PnBanHang extends JPanel {

    final Color ClMain = new Color(0, 160, 80);
    final Color ClHover = new Color(0, 192, 96);
    final Color ClSelect = new Color(76, 204, 76);

    Font FtTitleText = new Font("Montserrat", Font.BOLD, 20);
    Font font = new Font("", Font.PLAIN, 20);

    listCard listCardSP;
    JButton btnRemove;
    Mytable mtbGioHang;
    JButton btnReset;
    JButton btnThanhToan;

    SanPhamBUS list = new SanPhamBUS();

    public static DefaultTableModel dtmGioHang;

    public PnBanHang() {
        addControls();
        XoaPhanTuTrongGioHang();
        ResetCart();
        ThanhToan();
        loadData();
    }

    private void addControls() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();//lấy định dạng mặc định của ô
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);//set căn giữa nội dung cho định dạng
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//sắp xếp theo chiều dọc

        JPanel pnTitleBanHang = new JPanel();//tạo khung chứa title và nút reset
        JLabel textTitle = new JLabel("<html><h1>Danh sách sản phẩm</h1></html>");
        textTitle.setForeground(ClMain);//thêm màu xanh
        JButton btnResetBanHang = new JButton(new ImageIcon("image/btn/refresh.png")); //đổi thành JButton
        btnResetBanHang.setPreferredSize(new Dimension(40, 40));// đặt kích thước ưa thích 40x40
        btnResetBanHang.setFocusable(false);// bỏ focus để mất viền của focus
        btnResetBanHang.setBorder(null);// xóa viền
        pnTitleBanHang.add(textTitle);
        pnTitleBanHang.add(btnResetBanHang);

        this.add(pnTitleBanHang);//thêm vào khung bán hàng

        JPanel pnSearchSP = new JPanel(); //tạo khung chứa nơi tìm kiếm
        pnSearchSP.setLayout(new BoxLayout(pnSearchSP, BoxLayout.X_AXIS));

        JPanel pnTypeSP = new JPanel();//tạo khung chứa nơi chọn loại sp
        JLabel lbTypeSP = new JLabel("Loại");
        lbTypeSP.setFont(font);
        JComboBox<String> cmbTypeSP = loaisp();
        pnTypeSP.add(lbTypeSP);
        pnTypeSP.add(cmbTypeSP);
        pnSearchSP.add(pnTypeSP);

        JPanel pnFindSP = new JPanel();//tạo khung chứa thanh tìm sp
        pnFindSP.setLayout(new BoxLayout(pnFindSP, BoxLayout.X_AXIS));
        JLabel lbFindSP = new JLabel("Tìm kiếm");
        lbFindSP.setFont(font);
        JTextField textFind = new JTextField(100);
        textFind.setMaximumSize(new Dimension(2000, 30));
        textFind.setFont(font);
        JButton btnFindSP = new JButton(new ImageIcon("image/btn/search.png"));
        btnFindSP.setPreferredSize(new Dimension(40, 40));
        btnFindSP.setBorder(null);
        btnFindSP.setFocusable(false);
        pnFindSP.add(lbFindSP);
        pnFindSP.add(textFind);
        pnFindSP.add(btnFindSP);
        pnSearchSP.add(pnFindSP);

        this.add(pnSearchSP);
        listCardSP = new listCard();
        this.add(listCardSP);
        eventCombobox(cmbTypeSP);
        eventBTN(btnFindSP, textFind, cmbTypeSP);

        JPanel pnTltGioHang = new JPanel();//tạo khung chứa tên giỏ hàng
        JLabel lbTltGioHang = new JLabel("<html><h1>Giỏ hàng</h1></html>");
        lbTltGioHang.setForeground(ClMain);
        pnTltGioHang.add(lbTltGioHang);
        this.add(pnTltGioHang);

        JPanel pnTbGioHang = new JPanel(new BorderLayout());//tạo khung chứa giỏ hàng
        String[] columnTable = {"Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Thành Tiền"};
        dtmGioHang = new DefaultTableModel(columnTable, 0);
        mtbGioHang = new Mytable(dtmGioHang) {
            public boolean isCellEditable(int row, int column) { // không cho phép sửa nội dung trong table
                return false;
            }
        };

        mtbGioHang.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        mtbGioHang.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        mtbGioHang.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        mtbGioHang.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        mtbGioHang.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        mtbGioHang.getColumnModel().getColumn(0).setPreferredWidth(10);
        mtbGioHang.getColumnModel().getColumn(1).setPreferredWidth(100);
        mtbGioHang.getColumnModel().getColumn(2).setPreferredWidth(20);
        mtbGioHang.getColumnModel().getColumn(3).setPreferredWidth(20);
        mtbGioHang.getColumnModel().getColumn(4).setPreferredWidth(20);
        JScrollPane scrmtbGioHang = new JScrollPane(mtbGioHang);

        JPanel GroupBTN = new JPanel();
        GroupBTN.setLayout(new BoxLayout(GroupBTN, BoxLayout.Y_AXIS));
        GroupBTN.setPreferredSize(new Dimension(200, 400));
        btnThanhToan = new JButton("Thanh Toán");
        btnThanhToan.setMaximumSize(new Dimension(180, 70));
        btnThanhToan.setFont(font);
        btnRemove = new JButton("Xóa");
        btnRemove.setMaximumSize(new Dimension(180, 70));
        btnRemove.setFont(font);
        btnReset = new JButton("Đặt lại giỏ hàng");
        btnReset.setMaximumSize(new Dimension(180, 70));
        btnReset.setFont(font);
        GroupBTN.add(Box.createVerticalStrut(70));
        GroupBTN.add(btnThanhToan);
        GroupBTN.add(Box.createVerticalStrut(20));
        GroupBTN.add(btnRemove);
        GroupBTN.add(Box.createVerticalStrut(20));
        GroupBTN.add(btnReset);
        btnThanhToan.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRemove.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnReset.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnTbGioHang.add(GroupBTN, BorderLayout.EAST);
        pnTbGioHang.add(scrmtbGioHang, BorderLayout.CENTER);
        this.add(pnTbGioHang);
    }

    private JComboBox<String> loaisp() // lấy hết tên loại và gán vào combobox
    {
        LoaiSPBUS listLoai = new LoaiSPBUS();
        JComboBox<String> cmbTypeSP = new JComboBox<>();
        cmbTypeSP.addItem("Loại sản phẩm"); // mặc định
        for (int i = 0; i < listLoai.getDanhSachLoai().size(); i++) {
            cmbTypeSP.addItem(listLoai.getDanhSachLoai().get(i).getTenLoai());
        }
        cmbTypeSP.setFont(font);
        cmbTypeSP.setFocusable(false);
        return cmbTypeSP;
    }

    private void eventCombobox(JComboBox CBB) { // set sự kiện của combobox phaan loai
        CBB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CBB.getSelectedIndex() == 0) {
                    listCardSP.removeAll();
                    listCardSP.addCards(list.getListSPConHang());
                } else {
                    listCardSP.removeAll();
                    listCardSP.addCards(list.listSPtheoLoai("" + CBB.getSelectedItem()));
                }
                listCardSP.revalidate(); // cập nhật lại danh sách
                listCardSP.repaint();
            }
        });
    }

    private void eventBTN(JButton BTN, JTextField TF, JComboBox CBB) {
        BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CBB.getSelectedIndex() == 0) {
                    listCardSP.removeAll();
                    listCardSP.addCards(list.listSPtheoten(TF.getText()));
                } else {
                    listCardSP.removeAll();
                    listCardSP.addCards(list.listSPtheoLoaivaTenSP("" + CBB.getSelectedItem(), TF.getText()));
                }
                listCardSP.revalidate(); // cập nhật lại danh sách
                listCardSP.repaint();
            }
        });
    }

    private void loadData() {
        ArrayList<SanPham> sanPhams = list.getListSPConHang();
        if (sanPhams == null) {
            return;
        }
        listCardSP.removeAll();
        listCardSP.addCards(sanPhams);
        listCardSP.revalidate(); // cập nhật lại danh sách
        listCardSP.repaint();
    }

    public static void addOneRow(SanPham SP, int SoLuong) {
        for (int i = 0; i < dtmGioHang.getRowCount(); i++) {
            if (SP.getMaSP() == (int) dtmGioHang.getValueAt(i, 0)) {
                dtmGioHang.setValueAt((int) dtmGioHang.getValueAt(i, 2) + SoLuong, i, 2);
                dtmGioHang.setValueAt((int) dtmGioHang.getValueAt(i, 2) * (int) dtmGioHang.getValueAt(i, 3), i, 4);
                return;
            }
        }
        Object[] data = new Object[]{SP.getMaSP(), SP.getTenSP(), SoLuong, SP.getDonGia(), SoLuong * SP.getDonGia()};
        dtmGioHang.addRow(data);
    }

    private void XoaPhanTuTrongGioHang() {
        mtbGioHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int slRow = mtbGioHang.getSelectedRow();
                if (slRow == -1) {
                    new dialog("Vui lòng chọn sản phẩm muốn xóa!", dialog.ERROR_DIALOG);
                    return;
                }
                dtmGioHang.removeRow(slRow);
                mtbGioHang.revalidate();
            }
        });
    }

    private void ResetCart() {
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dtmGioHang.setRowCount(0);
            }
        });
    }

    private void ThanhToan() {
        btnThanhToan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                XyLyThanhToan();
            }
        });
    }

    private void XyLyThanhToan() {
        if (mtbGioHang.getRowCount() == 0) {
            new dialog("Giỏ hàng trống!", dialog.ERROR_DIALOG);
            return;
        }
        int tongTien = 0;
        ArrayList<CTHoaDon> cTHoaDons = new ArrayList<>();
        for (int i = 0; i < mtbGioHang.getRowCount(); i++) {
            int thanhTien = Integer.parseInt(mtbGioHang.getValueAt(i, 4) + "");
            if (thanhTien > Integer.MAX_VALUE - tongTien) {
                new dialog("Tổng tiền quá lớn", dialog.ERROR_DIALOG);
                return;
            }
            tongTien += thanhTien;
            int maSP = Integer.parseInt(mtbGioHang.getValueAt(i, 0) + "");
            int soLuong = Integer.parseInt(mtbGioHang.getValueAt(i, 2) + "");
            int donGia = Integer.parseInt(mtbGioHang.getValueAt(i, 3) + "");
            cTHoaDons.add(new CTHoaDon(0, maSP, soLuong, donGia, thanhTien));
        }
        XuatHoaDonGUI xuatHoaDonGUI = new XuatHoaDonGUI(tongTien, cTHoaDons);
        if (!xuatHoaDonGUI.getIsSuccess()) {
            return;
        }
        dtmGioHang.setRowCount(0);
        loadData();
    }
}
