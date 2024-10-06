package GUI;

import BUS.CTHoaDonBUS;
import BUS.GiamGiaBUS;
import BUS.HoaDonBUS;
import BUS.SanPhamBUS;
import Custom.Mytable;
import DAO.GiamGiaDAO;
import DAO.KhachHangDAO;
import DTO.GiamGia;
import DAO.HoaDonDAO;
import DTO.CTHoaDon;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PnChiTietHoaDon extends JPanel {
    private int W = 500;
    private int H = 500;
    final Color ClMain = new Color(0, 160, 80); //#00A050
    final Color ClHover = new Color(0, 192, 96);
    final Color ClSelect = new Color(76, 204, 76);
    Font FtTitleText = new Font("Montserrat", Font.BOLD, 15);


    public PnChiTietHoaDon(int MHD) {
        addGUI(MHD);
    }

    private void addGUI(int MHD) {
        this.setPreferredSize(new Dimension(W, H));
        this.setLayout(new BorderLayout());
        KhachHangDAO khachHangDAO = new KhachHangDAO();
        HoaDonDAO hoaDonDAO = new HoaDonDAO();
        // title
        JLabel TitleCTHD = new JLabel("---------- Chi Tiết Hóa Đơn ----------",JLabel.CENTER);
        TitleCTHD.setFont(FtTitleText);
        JLabel TenKH = new JLabel("Tên Khách hàng: "+ khachHangDAO.getKhachHang(hoaDonDAO.getHoaDonTheoMHD(MHD).getMaKH()).getTen(),JLabel.CENTER);
        TenKH.setFont(FtTitleText);
        Panel pntltCTHD = new Panel(new BorderLayout());
        pntltCTHD.add(TitleCTHD,BorderLayout.NORTH);
        pntltCTHD.add(TenKH,BorderLayout.SOUTH);
        this.add(pntltCTHD, BorderLayout.NORTH);


        // table
        String NameColume[] = {"Mã SP", "Tên SP", "Đơn Giá", "Số Lượng", "Thành Tiền"};
        DefaultTableModel model = new DefaultTableModel(NameColume, 0);
        Mytable TB = new Mytable(model) {
            public boolean isCellEditable(int row, int column) { // không cho phép sửa nội dung trong table
                return false;
            }
        };;

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();//lấy định dạng mặc định của ô
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);//set căn giữa nội dung cho định dạng
        TB.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        TB.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        TB.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        TB.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        TB.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        TB.getColumnModel().getColumn(0).setPreferredWidth(10);
        TB.getColumnModel().getColumn(1).setPreferredWidth(10);
        TB.getColumnModel().getColumn(2).setPreferredWidth(10);
        TB.getColumnModel().getColumn(3).setPreferredWidth(10);
        TB.getColumnModel().getColumn(4).setPreferredWidth(10);
        TB.getTableHeader().setFont(FtTitleText);
        addRowtable(model, MHD);

        JScrollPane Scl = new JScrollPane(TB); // Thêm JTable vào JScrollPane
        this.add(Scl, BorderLayout.CENTER);
        HoaDonDAO hd = new HoaDonDAO();
        GiamGiaDAO KM = new GiamGiaDAO();
        JLabel KhuyenMai = new JLabel("Chương trình khuyến mãi: " + KM.getTenMaGiam(hd.GetMaGiam(MHD)));
        KhuyenMai.setFont(FtTitleText);
        Panel pnKhuyenMai = new  Panel(new FlowLayout(FlowLayout.CENTER));
        pnKhuyenMai.add(KhuyenMai);

        HoaDonBUS TongTienHD = new HoaDonBUS();
        JLabel KQTongTien = new JLabel("" + TongTienHD.getlisttheoMHD(MHD + "").getTongTien() + "đ");// thay giá trị tổng tiền
        KQTongTien.setFont(FtTitleText);
        Panel pnTongTien = new Panel(new FlowLayout(FlowLayout.RIGHT));
        pnTongTien.add(KQTongTien);
        Panel Content = new Panel(new GridLayout(2,1));
        Content.add(pnKhuyenMai);
        Content.add(pnTongTien);
        this.add(Content,BorderLayout.SOUTH);
    }

    public void addRowtable(DefaultTableModel tble, int MHD) {
        CTHoaDonBUS listBUS = new CTHoaDonBUS();
        ArrayList<CTHoaDon> listCTHD = new ArrayList<>();
        listCTHD = listBUS.getlistCTHD();
        SanPhamBUS temp = new SanPhamBUS();
        for (int i = 0; i < listCTHD.size(); i++) {
            if (listCTHD.get(i).getMaHD() == MHD) {
                Object[] data = {listCTHD.get(i).getMaSP(), temp.getById(listCTHD.get(i).getMaSP()).getTenSP(), listCTHD.get(i).getDonGia(), listCTHD.get(i).getSoLuong(), listCTHD.get(i).getThanhTien()};
                tble.addRow(data);
            }
        }

    }
}
