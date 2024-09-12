package GUI;

import BUS.LoaiSPBUS;
import DTO.SanPham;
import GUI.PnBanHang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.*;

import static Main.Main.changLNF;

public class PnChiTietSP extends JPanel {

    private int W = 400;
    private int H = 600;
    final Color ClMain = new Color(0, 160, 80); //#00A050
    final Color ClHover = new Color(0, 192, 96);
    final Color ClSelect = new Color(76, 204, 76);
    Border customBorder = BorderFactory.createLineBorder(ClMain, 2);
    Font FtTitleText = new Font("Montserrat", Font.BOLD, 15);
    JButton btnadd;
    JSpinner spinner;
    SanPham SP;
    LoaiSPBUS loaiSPBUS = new LoaiSPBUS();

    public PnChiTietSP(SanPham Sp) {
        this.SP = Sp;
        changLNF("Windows");
        addGUI(Sp);
        addEventGioHang();
    }

    private void addGUI(SanPham Sp) {
        this.setPreferredSize(new Dimension(W, H));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // ảnh sp
        JPanel ImagePanel = new JPanel();
//        ImagePanel.setBounds(50, 5, 300, 300);
        ImagePanel.setLayout(new FlowLayout());
        ImageIcon imageIcon = new ImageIcon("image/products/" + Sp.getHinhAnh());// hình ảnh của sản phẩm vào đây
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel ImageProduct = new JLabel(new ImageIcon(image));
        ImageProduct.setPreferredSize(new Dimension(300, 300));
        ImagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImagePanel.add(ImageProduct);
        this.add(ImagePanel);

        // id sp
        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel idSanPham = new JLabel("ID Sản Phẩm: ");
        idSanPham.setFont(FtTitleText);
        JLabel txtID = new JLabel(Sp.getMaSP() + ""); // Mã của sản phẩm
        txtID.setFont(FtTitleText);
        idPanel.add(idSanPham);
        idPanel.add(txtID);
        this.add(idPanel);

        // tên sp
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nameSanPham = new JLabel("Tên Sản Phẩm: ");
        nameSanPham.setFont(FtTitleText);
        JLabel txtName = new JLabel(Sp.getTenSP()); // tên sản phẩm
        txtName.setFont(FtTitleText);
        namePanel.add(nameSanPham);
        namePanel.add(txtName);
        this.add(namePanel);

        // loại sp
        JPanel LoaiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel loaiSanPham = new JLabel("Loại Sản Phẩm: ");
        loaiSanPham.setFont(FtTitleText);
        String loaisp = loaiSPBUS.getTenLoai(Sp.getMaLoai());
        JLabel txtLoai = new JLabel(loaisp);// Loại sản phẩm
        txtLoai.setFont(FtTitleText);
        LoaiPanel.add(loaiSanPham);
        LoaiPanel.add(txtLoai);
        this.add(LoaiPanel);

        //sl sp
        JPanel SLPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel SLSanPham = new JLabel("SL Sản Phẩm: ");
        SLSanPham.setFont(FtTitleText);
        JLabel txtSL = new JLabel(Sp.getSoLuong() + "");// Số lượng
        txtSL.setFont(FtTitleText);
        SLPanel.add(SLSanPham);
        SLPanel.add(txtSL);
        this.add(SLPanel);

        // đơn giá
        JPanel PricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel PriceSanPham = new JLabel("Đơn Giá Sản Phẩm: ");
        PriceSanPham.setFont(FtTitleText);
        JLabel txtPrice = new JLabel(Sp.getDonGia() + "");// Đơn giá
        txtPrice.setFont(FtTitleText);
        PricePanel.add(PriceSanPham);
        PricePanel.add(txtPrice);
        this.add(PricePanel);

        // đơn vị tính của sp, cái, ...
        JPanel DonViPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel DonViSanPham = new JLabel("Đơn Vị Tính Sản Phẩm: ");
        DonViSanPham.setFont(FtTitleText);
        JLabel txtDonVi = new JLabel(Sp.getDonViTinh());
        txtDonVi.setFont(FtTitleText);
        DonViPanel.add(DonViSanPham);
        DonViPanel.add(txtDonVi);
        this.add(DonViPanel);

        Dimension lbSize = DonViSanPham.getPreferredSize();
        idSanPham.setPreferredSize(lbSize);
        nameSanPham.setPreferredSize(lbSize);
        loaiSanPham.setPreferredSize(lbSize);
        SLSanPham.setPreferredSize(lbSize);
        PriceSanPham.setPreferredSize(lbSize);

        JPanel addtoCart = new JPanel(new BorderLayout());
        addtoCart.setBounds(0, 500, 400, 100);

        JPanel north = new JPanel();
        JLabel txtChoiceSL = new JLabel("Chọn số lượng:");
        txtChoiceSL.setFont(new java.awt.Font("Segoe UI", 1, 23));
        north.add(txtChoiceSL);
        north.add(Box.createHorizontalStrut(5));
        spinner = new JSpinner(new SpinnerNumberModel(1, 1, Sp.getSoLuong(), 1));
        spinner.setPreferredSize(new Dimension(70, 25));
        spinner.setEditor(new JSpinner.DefaultEditor(spinner));
        //set size số trong spinner
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JSpinner.DefaultEditor defaultEditor = (JSpinner.DefaultEditor) editor;
            defaultEditor.getTextField().setFont(new Font("Arial", Font.PLAIN, 15)); // Thiết lập font và kích thước cho con số
        }
        north.add(spinner);
        addtoCart.add(north, BorderLayout.NORTH);

        JPanel south = new JPanel();
        btnadd = new JButton();// set sự kiện thêm vào giỏ hàng thì lấy cái btnadd này
        btnadd.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnadd.setForeground(new java.awt.Color(0, 160, 80));
        btnadd.setText("Thêm Vào Giỏ Hàng");
        btnadd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnadd.setFocusable(false);
        btnadd.setPreferredSize(new java.awt.Dimension(260, 40));
        south.add(btnadd);
        addtoCart.add(south, BorderLayout.SOUTH);
        this.add(addtoCart);
    }

    public void addEventGioHang() {
        this.btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int SoLuong = (int) spinner.getValue();
                PnBanHang.addOneRow(SP, SoLuong);
            }
        });
    }
}
