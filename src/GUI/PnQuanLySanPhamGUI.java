package GUI;

import DTO.SanPham;
import BUS.SanPhamBUS;
import BUS.LoaiSPBUS;

import static Main.Main.changLNF;

import Custom.XuLyFileExcel;
import Custom.dialog;
import Custom.MyFileChooser;
import Custom.Mytable;
import DTO.LoaiSP;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class PnQuanLySanPhamGUI extends JPanel {

    DefaultTableModel dtmSanPham;
    JTextField txtMa, txtTen, txtsoLuong, txtdonViTinh, txtdonGia, txtTimKiem;
    JComboBox<String> cmbLoai;
    JButton btnThemSP, btnCapNhat, btnXoa, btnTim, btnChonAnh, btnReset, btnXuatExcel, btnNhapExcel;
    JLabel lblAnhSP;
    Mytable tblSanPham;
    final Color ClHover = new Color(0, 160, 80);
    final Color colorPanel = new Color(247, 247, 247);

    public PnQuanLySanPhamGUI() {
        changLNF("Windows");
        addControlsSanPham();
        addEventsSanPham();
    }

    SanPhamBUS SPBUS = new SanPhamBUS();
    LoaiSPBUS LBUS = new LoaiSPBUS();

    private void addControlsSanPham() {
        Font font = new Font("Tahoma", Font.PLAIN, 20);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorPanel);

        JPanel pnTitle = new JPanel();
        JLabel lblTitle = new JLabel("<html><h1>QUẢN LÝ SẢN PHẨM</h1></html>");
        lblTitle.setForeground(ClHover);
        btnReset = new JButton(new ImageIcon("image/btn/refresh.png"));
        btnReset.setPreferredSize(new Dimension(40, 40));
        btnReset.setFocusable(false);
        btnReset.setBorder(null);
        pnTitle.add(lblTitle);
        pnTitle.add(btnReset);
        this.add(pnTitle);

        JPanel pnThongTin = new JPanel();
        pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.X_AXIS));

        //================PANEL INPUT=========
        JPanel pnTextField = new JPanel();
        pnTextField.setLayout(new BoxLayout(pnTextField, BoxLayout.Y_AXIS));
        JLabel lblMa, lblTen, lblLoai, lblSoLuong, lblDonViTinh, lblDonGia;

        lblMa = new JLabel("Mã SP");
        lblTen = new JLabel("Tên SP");
        lblLoai = new JLabel("Loại");
        lblSoLuong = new JLabel("Số lượng");
        lblDonViTinh = new JLabel("Đơn vị tính");
        lblDonGia = new JLabel("Đơn giá");

        txtMa = new JTextField(15);
        txtMa.setEditable(false);
        txtTen = new JTextField(15);
        cmbLoai = new JComboBox<String>();
        txtsoLuong = new JTextField(15);
        txtdonViTinh = new JTextField(15);
        txtdonGia = new JTextField(15);

        JPanel pnMa = new JPanel();
        lblMa.setFont(font);
        txtMa.setFont(font);
        pnMa.add(lblMa);
        pnMa.add(txtMa);
        pnTextField.add(pnMa);

        JPanel pnTen = new JPanel();
        lblTen.setFont(font);
        txtTen.setFont(font);
        pnTen.add(lblTen);
        pnTen.add(txtTen);
        pnTextField.add(pnTen);

        JPanel pnLoai = new JPanel();
        lblLoai.setFont(font);
        cmbLoai.setFont(font);
        cmbLoai.setPreferredSize(txtMa.getPreferredSize());
        cmbLoai.setFocusable(false);
        pnLoai.add(lblLoai);
        pnLoai.add(cmbLoai);
        pnTextField.add(pnLoai);

        JPanel pnSoLuong = new JPanel();
        lblSoLuong.setFont(font);
        txtsoLuong.setFont(font);
        pnSoLuong.add(lblSoLuong);
        pnSoLuong.add(txtsoLuong);
        pnTextField.add(pnSoLuong);

        JPanel pnDonViTinh = new JPanel();
        lblDonViTinh.setFont(font);
        txtdonViTinh.setFont(font);
        pnDonViTinh.add(lblDonViTinh);
        pnDonViTinh.add(txtdonViTinh);
        pnTextField.add(pnDonViTinh);

        JPanel pnDonGia = new JPanel();
        lblDonGia.setFont(font);
        txtdonGia.setFont(font);
        pnDonGia.add(lblDonGia);
        pnDonGia.add(txtdonGia);
        pnTextField.add(pnDonGia);

        Dimension lblSize = lblDonViTinh.getPreferredSize();
        lblMa.setPreferredSize(lblSize);
        lblTen.setPreferredSize(lblSize);
        lblLoai.setPreferredSize(lblSize);
        lblSoLuong.setPreferredSize(lblSize);
        lblDonViTinh.setPreferredSize(lblSize);
        lblDonGia.setPreferredSize(lblSize);

        pnThongTin.add(pnTextField);

        //=================PANEL ẢNH==========
        JPanel pnAnh = new JPanel();
        pnAnh.setLayout(new BoxLayout(pnAnh, BoxLayout.Y_AXIS));

        JPanel pnChuaAnh = new JPanel();
        pnChuaAnh.setPreferredSize(new Dimension((int) pnAnh.getPreferredSize().getWidth(), 250));
        lblAnhSP = new JLabel();
        lblAnhSP.setPreferredSize(new Dimension(200, 200));
        lblAnhSP.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblAnhSP.setIcon(getAnhSP(""));
        pnChuaAnh.add(lblAnhSP);
        pnAnh.add(pnChuaAnh);

        JPanel pnButtonAnh = new JPanel();
        pnButtonAnh.setPreferredSize(new Dimension(
                (int) pnChuaAnh.getPreferredSize().getHeight(), 40));
        btnChonAnh = new JButton("Chọn ảnh");
        btnChonAnh.setFont(font);
        btnChonAnh.setBackground(colorPanel);
        btnChonAnh.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnButtonAnh.add(btnChonAnh);
        pnChuaAnh.add(pnButtonAnh);

        pnThongTin.add(pnAnh);
        this.add(pnThongTin);

        JPanel pnButton = new JPanel();

        btnThemSP = new JButton("Thêm");
        btnCapNhat = new JButton("Lưu");
        btnXoa = new JButton("Xoá");
        btnTim = new JButton("Tìm kiếm");
        btnXuatExcel = new JButton("Xuất");
        btnNhapExcel = new JButton("Nhập");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThemSP.setFont(fontButton);
        btnCapNhat.setFont(fontButton);
        btnXoa.setFont(fontButton);
        btnTim.setFont(fontButton);
        btnXuatExcel.setFont(fontButton);
        btnNhapExcel.setFont(fontButton);

        btnThemSP.setIcon(new ImageIcon("image/btn/plus.png"));
        btnCapNhat.setIcon(new ImageIcon("image/btn/write.png"));
        btnXoa.setIcon(new ImageIcon("image/btn/bin.png"));
        btnTim.setIcon(new ImageIcon("image/btn/search.png"));
        btnXuatExcel.setIcon(new ImageIcon("image/btn/excel.png"));
        btnNhapExcel.setIcon(new ImageIcon("image/btn/excel.png"));

        JPanel pnTimKiem = new JPanel();
        JLabel lblTimKiem = new JLabel("Từ khoá tìm");
        lblTimKiem.setFont(font);
        txtTimKiem = new JTextField(20);
        txtTimKiem.setFont(font);
        pnTimKiem.add(lblTimKiem);
        pnTimKiem.add(txtTimKiem);
        this.add(pnTimKiem);

        pnButton.add(btnThemSP);
        pnButton.add(btnCapNhat);
        pnButton.add(btnXoa);
        pnButton.add(btnTim);
        pnButton.add(btnXuatExcel);
        pnButton.add(btnNhapExcel);

        Dimension btnSize = btnTim.getPreferredSize();
        btnThemSP.setPreferredSize(btnSize);
        btnCapNhat.setPreferredSize(btnSize);
        btnXoa.setPreferredSize(btnSize);
        btnTim.setPreferredSize(btnSize);
        btnXuatExcel.setPreferredSize(btnSize);
        btnNhapExcel.setPreferredSize(btnSize);

        btnThemSP.setFocusable(false);
        btnCapNhat.setFocusable(false);
        btnXoa.setFocusable(false);
        btnTim.setFocusable(false);
        btnXuatExcel.setFocusable(false);
        btnNhapExcel.setFocusable(false);

        this.add(pnButton);

        //============PANEL BẢNG===========
        JPanel pnTable = new JPanel(new BorderLayout());
        //====================Bảng hàng hoá====================
        dtmSanPham = new DefaultTableModel();
        dtmSanPham.addColumn("Mã SP");
        dtmSanPham.addColumn("Tên SP");
        dtmSanPham.addColumn("Loại SP");
        dtmSanPham.addColumn("Đơn giá");
        dtmSanPham.addColumn("Số lượng");
        dtmSanPham.addColumn("Đơn vị tính");
        dtmSanPham.addColumn("Ảnh");
        tblSanPham = new Mytable(dtmSanPham) {
            public boolean isCellEditable(int row, int column) { // không cho phép sửa nội dung trong table
                return false;
            }
        };

        tblSanPham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblSanPham.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblSanPham.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblSanPham.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblSanPham.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblSanPham.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tblSanPham.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        TableColumnModel columnModelBanHang = tblSanPham.getColumnModel();
        columnModelBanHang.getColumn(0).setPreferredWidth(10);
        columnModelBanHang.getColumn(1).setPreferredWidth(100);
        columnModelBanHang.getColumn(2).setPreferredWidth(20);
        columnModelBanHang.getColumn(3).setPreferredWidth(20);
        columnModelBanHang.getColumn(4).setPreferredWidth(20);
        columnModelBanHang.getColumn(5).setPreferredWidth(20);
        columnModelBanHang.getColumn(6).setPreferredWidth(20);

        JScrollPane scrTblSanPham = new JScrollPane(tblSanPham);
        pnTable.add(scrTblSanPham, BorderLayout.CENTER);
        this.add(pnTable);

        loadDataCmbLoai();
        loadDataLenBangSanPham();
    }

    private void addEventsSanPham() {

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAnh("");
                loadDataLenBangSanPham();
                loadDataCmbLoai();
                txtMa.setText("");
                txtTen.setText("");
                txtdonGia.setText("");
                txtdonViTinh.setText("");
                txtsoLuong.setText("");
                cmbLoai.setSelectedIndex(0);
            }
        });

        tblSanPham.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblSanPham();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        cmbLoai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemLoai();
            }
        });

        btnChonAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChonAnh();
            }
        });

        btnThemSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemSanPham();
            }
        });

        btnCapNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaSanPham();
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaSanPham();
            }
        });

        txtTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiem();
            }
        });

        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiem();
            }
        });

        btnXuatExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXuatFileExcel();
            }
        });

        btnNhapExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyNhapFileExcel();
            }
        });

    }

    private void xuLyNhapFileExcel() {
        dialog dlg = new dialog("Dữ liệu cũ sẽ bị xoá, tiếp tục?", dialog.WARNING_DIALOG);
        if (dlg.getAction() != dialog.OK_OPTION) {
            return;
        }

        XuLyFileExcel nhapFile = new XuLyFileExcel();
        nhapFile.nhapExcel(tblSanPham);
        SPBUS.xoaFKHoadon_PhieuNhap_NV();
        SPBUS.xoaAllSanPhamInTable();

        int row = tblSanPham.getRowCount();
        for (int i = 0; i < row; i++) {
            String ma = tblSanPham.getValueAt(i, 0) + "";
            String ten = tblSanPham.getValueAt(i, 1) + "";
            String loai = tblSanPham.getValueAt(i, 2) + "";
            String donGia = tblSanPham.getValueAt(i, 3) + "";
            String soLuong = tblSanPham.getValueAt(i, 4) + "";
            String donViTinh = tblSanPham.getValueAt(i, 5) + "";
            String anh = tblSanPham.getValueAt(i, 6) + "";

            SPBUS.importSanPhamTuExcel(ma, ten, loai, soLuong, donViTinh, anh, donGia);
        }
        SPBUS.updateFKHoadon_PhieuNhap_NV();
    }

    private void xuLyXuatFileExcel() {
        XuLyFileExcel xuatFile = new XuLyFileExcel();
        xuatFile.xuatExcel(tblSanPham);
    }

    private void xuLyTimKiem() {
        String ten = txtTimKiem.getText().toLowerCase();
        dtmSanPham.setRowCount(0);
        ArrayList<SanPham> dssp = null;
        dssp = SPBUS.getSPTheoTen(ten);
        DecimalFormat dcf = new DecimalFormat("###,###");
        for (SanPham sp : dssp) {
            Vector<Object> vec = new Vector<>();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            String tenLoai = LBUS.getTenLoai(sp.getMaLoai());
            vec.add(tenLoai);
            vec.add(dcf.format(sp.getDonGia()));
            vec.add(dcf.format(sp.getSoLuong()));
            vec.add(sp.getDonViTinh());
            vec.add(sp.getHinhAnh());
            dtmSanPham.addRow(vec);
        }
        dialog dlg = new dialog("Số kết quả tìm được: " + dssp.size(), dialog.INFO_DIALOG);
    }

    private void xuLyXoaSanPham() {
        dialog dlg = new dialog("Bạn có chắc chắn muốn xoá?", dialog.WARNING_DIALOG);
        if (dlg.OK_OPTION == dlg.getAction()) {
            boolean flag = SPBUS.xoaSanPham(txtMa.getText());
            if (flag) {
                loadDataLenBangSanPham();
                //reset txtMa tránh việc lấy lại mã sp đã xóa 
                txtMa.setText("");
            }
        }
    }

    private void loadAnh(String anh) {
        lblAnhSP.setIcon(getAnhSP(anh));
    }

    private void xuLyClickTblSanPham() {
        int row = tblSanPham.getSelectedRow();
        if (row > -1) {
            String ma = tblSanPham.getValueAt(row, 0) + "";
            String ten = tblSanPham.getValueAt(row, 1) + "";
            String loai = tblSanPham.getValueAt(row, 2) + "";
            String donGia = tblSanPham.getValueAt(row, 3) + "";
            String soLuong = tblSanPham.getValueAt(row, 4) + "";
            String donViTinh = tblSanPham.getValueAt(row, 5) + "";
            String anh = tblSanPham.getValueAt(row, 6) + "";

            txtMa.setText(ma);
            txtTen.setText(ten);
            txtdonGia.setText(donGia);
            txtsoLuong.setText(soLuong);
            txtdonViTinh.setText(donViTinh.replace(",", ""));

            int flag = 0;
            for (int i = 0; i < cmbLoai.getItemCount(); i++) {
                if (cmbLoai.getItemAt(i).contains(loai)) {
                    flag = i;
                    break;
                }
            }
            cmbLoai.setSelectedIndex(flag);
            loadAnh("image/products/" + anh);
        }
    }

    private void loadDataLenBangSanPham() {
        SPBUS.readListSanPham();
        dtmSanPham.setRowCount(0);

        ArrayList<SanPham> dssp = SPBUS.getListSP();

        DecimalFormat dcf = new DecimalFormat("###,###");

        for (SanPham sp : dssp) {
            Vector<Object> vec = new Vector<>();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            String tenLoai = LBUS.getTenLoai(sp.getMaLoai());
            vec.add(tenLoai);
            vec.add(dcf.format(sp.getDonGia()));
            vec.add(dcf.format(sp.getSoLuong()));
            vec.add(sp.getDonViTinh());
            vec.add(sp.getHinhAnh());
            dtmSanPham.addRow(vec);
        }
    }

    private void loadDataCmbLoai() {
        cmbLoai.removeAllItems();
        LBUS.docDanhSachLoai();
        ArrayList<LoaiSP> dsl = LBUS.getDanhSachLoai();
        cmbLoai.addItem("0 - Chọn loại");
        for (LoaiSP loai : dsl) {
            if (loai.getTrangThai() != 0) {
                cmbLoai.addItem(loai.getMaLoai() + " - " + loai.getTenLoai());
            }
        }
        cmbLoai.addItem("Khác...");
    }

    private void xuLyThemLoai() {
        String x = "Khác...";
        if (x.equals(cmbLoai.getSelectedItem())) { //sử dụng so sánh chuỗi tránh bug
            dlgQuanLyLoaiSP loaiGUI = new dlgQuanLyLoaiSP();
            loaiGUI.setVisible(true);
            loadDataCmbLoai();
        }
    }

    private void xuLyThemSanPham() {
        String anh = "default.png";// tạo sẵn biến ảnh với ảnh mặc định
        if (fileAnhSP != null) { // fileAnhSP khác null mới được getName
            anh = fileAnhSP.getName();
        }
        SPBUS.themSanPham(txtTen.getText(),
                cmbLoai.getSelectedItem() + "",
                txtsoLuong.getText(),
                txtdonViTinh.getText(),
                anh,
                txtdonGia.getText(),
                "1");
        SPBUS.readListSanPham();
        loadDataLenBangSanPham();
        luuFileAnh();
    }

    File fileAnhSP;

    private void xuLySuaSanPham() {
        int row = tblSanPham.getSelectedRow();
        String anh = "default.png";
        if (row > -1) {
            anh = tblSanPham.getValueAt(row, 6) + ""; // lấy ảnh từ dòng được chọn nếu có
        }
        if (fileAnhSP != null) {
            anh = fileAnhSP.getName();
        }
        SPBUS.capNhatThongTinSanPham(txtMa.getText(),
                txtTen.getText(),
                cmbLoai.getSelectedItem() + "",
                txtsoLuong.getText(),
                txtdonViTinh.getText(),
                anh,
                txtdonGia.getText(),
                "1");
        SPBUS.readListSanPham();
        loadDataLenBangSanPham();
        luuFileAnh();
    }

    private void luuFileAnh() {
        if (fileAnhSP == null) { // nếu người dùng không chọn ảnh thì tương đương fileAnhSP = null thì return không làm gì cả
            return;
        }
        BufferedImage bImage = null;
        try {
            File initialImage = new File(fileAnhSP.getPath());
            bImage = ImageIO.read(initialImage);
            ImageIO.write(bImage, "png", new File("image/products/" + fileAnhSP.getName()));

        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }

    private void xuLyChonAnh() {
        JFileChooser fileChooser = new MyFileChooser("image/products/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Tệp hình ảnh", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileAnhSP = fileChooser.getSelectedFile();
            lblAnhSP.setIcon(getAnhSP(fileAnhSP.getPath()));
        }
    }

    private ImageIcon getAnhSP(String src) {
        src = src.trim().equals("") ? "image/products/default.png" : src;
        //Xử lý ảnh
        ImageIcon imageIcon = new ImageIcon(src);
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        return imageIcon;
    }
}
