package GUI;

import BUS.HoaDonBUS;
import Custom.InputValidator;
import Custom.Mytable;
import DTO.HoaDon;
import com.toedter.calendar.JDateChooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PnHoaDon extends JPanel {

    final Color ClMain = new Color(0, 160, 80);
    final Color ClHover = new Color(0, 192, 96);
    final Color ClSelect = new Color(76, 204, 76);

    Font FtTitleText = new Font("Montserrat", Font.BOLD, 20);
    Font font = new Font("", Font.PLAIN, 20);

    public PnHoaDon() {
        addControls();
    }

    private void addControls() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel pnTltHoaDon = new JPanel();
        JLabel lbTltHoaDon = new JLabel("<html><h1>Thông tin hóa đơn</h1></html>");
        lbTltHoaDon.setForeground(ClMain);
        pnTltHoaDon.add(lbTltHoaDon);
        this.add(pnTltHoaDon);

        JPanel pnSearchHoaDon = new JPanel(); // tạo panel tìm kiếm
        pnSearchHoaDon.setLayout(new BoxLayout(pnSearchHoaDon, BoxLayout.X_AXIS));

        JPanel pnSearchMaHD = new JPanel(); // panel tìm kiếm theo mã
        pnSearchMaHD.setLayout(new BoxLayout(pnSearchMaHD, BoxLayout.Y_AXIS));

        JPanel pnTltSearchMaHD = new JPanel();
        JLabel lbTltSearchMAHD = new JLabel("Tìm kiếm Mã Hóa Đơn");
        lbTltSearchMAHD.setFont(FtTitleText);
        pnTltSearchMaHD.add(lbTltSearchMAHD);
        pnSearchMaHD.add(pnTltSearchMaHD);

        JPanel pntxtSearchMaHD = new JPanel();
        JLabel lbtxtSearchMaHD = new JLabel("Mã HD");
        lbtxtSearchMaHD.setFont(font);
        JTextField txtSearchMaHD = new JTextField(25);
        txtSearchMaHD.setFont(font);
        pntxtSearchMaHD.add(lbtxtSearchMaHD);
        pntxtSearchMaHD.add(txtSearchMaHD);

        JButton btnSearchMaHD = new JButton(new ImageIcon("image/btn/search.png"));
        btnSearchMaHD.setPreferredSize(new Dimension(40, 40));
        btnSearchMaHD.setBorder(null);
        btnSearchMaHD.setFocusable(false);
        pntxtSearchMaHD.add(btnSearchMaHD);
        pnSearchMaHD.add(pntxtSearchMaHD);

        JPanel pnSearchDate = new JPanel();// tạo panel tìm kiếm theo ngày
        pnSearchDate.setLayout(new BoxLayout(pnSearchDate, BoxLayout.Y_AXIS));

        JPanel pnTltSearchDate = new JPanel();
        JLabel lbTltSearchDate = new JLabel("Tìm kiếm Hóa Đơn");
        lbTltSearchDate.setFont(FtTitleText);
        pnTltSearchDate.add(lbTltSearchDate);
        pnSearchDate.add(pnTltSearchDate);

        JPanel pntxtStartDate = new JPanel();
        JLabel lbtxtStartDate = new JLabel("Từ ngày");
        lbtxtStartDate.setFont(font);
        JDateChooser dtcStartDate = new JDateChooser();
        dtcStartDate.setPreferredSize(new Dimension(150, 30));
        pntxtStartDate.add(lbtxtStartDate);
        pntxtStartDate.add(dtcStartDate);
        pnSearchDate.add(pntxtStartDate);

        JPanel pntxtEndDate = new JPanel();

        JLabel lbtxtEndDate = new JLabel("Đến ngày");
        lbtxtEndDate.setFont(font);
        JDateChooser dtcEndDate = new JDateChooser();
        dtcEndDate.setPreferredSize(new Dimension(150, 30));
        pntxtEndDate.add(lbtxtEndDate);
        pntxtEndDate.add(dtcEndDate);
        pnSearchDate.add(pntxtEndDate);

        JPanel pntxtStartPrice = new JPanel();
        JLabel lbtxtStartPrice = new JLabel("Giá từ");
        lbtxtStartPrice.setFont(font);
        JTextField txtStartPrice = new JTextField();
        txtStartPrice.setPreferredSize(dtcEndDate.getPreferredSize());
        txtStartPrice.setFont(font);
        pntxtStartPrice.add(lbtxtStartPrice);
        pntxtStartPrice.add(txtStartPrice);
        pnSearchDate.add(pntxtStartPrice);

        JPanel pntxtEndPrice = new JPanel();
        JLabel lbtxtEndPrice = new JLabel("Đến");
        lbtxtEndPrice.setFont(font);
        JTextField txtEndPrice = new JTextField();
        txtEndPrice.setPreferredSize(dtcEndDate.getPreferredSize());
        txtEndPrice.setFont(font);
        pntxtEndPrice.add(lbtxtEndPrice);
        pntxtEndPrice.add(txtEndPrice);
        pnSearchDate.add(pntxtEndPrice);

        JButton btnSearchDate = new JButton(new ImageIcon("image/btn/search.png"));
        btnSearchDate.setPreferredSize(new Dimension(40, 40));
        btnSearchDate.setBorder(null);
        btnSearchDate.setFocusable(false);
        pnSearchDate.add(btnSearchDate);

        lbtxtStartPrice.setPreferredSize(lbtxtEndDate.getPreferredSize());
        lbtxtEndPrice.setPreferredSize(lbtxtEndDate.getPreferredSize());
        lbtxtStartDate.setPreferredSize(lbtxtEndDate.getPreferredSize());
        lbtxtStartDate.setPreferredSize(lbtxtEndDate.getPreferredSize());

        pnSearchMaHD.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, ClHover));
        pnSearchDate.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, ClHover));

        pnSearchHoaDon.add(pnSearchMaHD);
        pnSearchHoaDon.add(pnSearchDate);
        this.add(pnSearchHoaDon);

        JPanel pnTbHoaDon = new JPanel(new BorderLayout());//tạo khung chứa giỏ hàng
        String[] coltbHoaDon = {"Mã HD", "Mã KH", "Mã NV", "Mã giảm giá", "Ngày tạo", "Tổng tiền"};
        DefaultTableModel dtmHoaDon = new DefaultTableModel(coltbHoaDon, 0);
        Mytable mtbHoaDon = new Mytable(dtmHoaDon) {
            public boolean isCellEditable(int row, int column) { // không cho phép sửa nội dung trong table
                return false;
            }
        };

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();//lấy định dạng mặc định của ô
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);//set căn giữa nội dung cho định dạng

        mtbHoaDon.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        mtbHoaDon.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        mtbHoaDon.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        mtbHoaDon.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        mtbHoaDon.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        mtbHoaDon.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        mtbHoaDon.getColumnModel().getColumn(0).setPreferredWidth(10);
        mtbHoaDon.getColumnModel().getColumn(1).setPreferredWidth(10);
        mtbHoaDon.getColumnModel().getColumn(2).setPreferredWidth(10);
        mtbHoaDon.getColumnModel().getColumn(3).setPreferredWidth(10);
        mtbHoaDon.getColumnModel().getColumn(4).setPreferredWidth(10);
        mtbHoaDon.getColumnModel().getColumn(5).setPreferredWidth(10);

        JScrollPane scrmtbHoaDon = new JScrollPane(mtbHoaDon);
        pnTbHoaDon.add(scrmtbHoaDon, BorderLayout.CENTER);
        this.add(pnTbHoaDon);
        addrowTable(dtmHoaDon);
        TimKiemtheoMHD(btnSearchMaHD, dtmHoaDon, txtSearchMaHD);
        setEventTable(mtbHoaDon);
        TimKiemTheoGiaTienVaDate(btnSearchDate, dtcStartDate, dtcEndDate, txtStartPrice, txtEndPrice, dtmHoaDon);
    }

    public void addrowTable(DefaultTableModel tble) {
        HoaDonBUS list = new HoaDonBUS();
        ArrayList<HoaDon> listHD = list.getlistHD();
        for (int i = 0; i < list.getlistHD().size(); i++) {
            Object[] newRowData = {listHD.get(i).getMaHD(), listHD.get(i).getMaKH(), listHD.get(i).getMaNV(), listHD.get(i).getMaGiam(), listHD.get(i).getNgayLap(), listHD.get(i).getTongTien()};
            tble.addRow(newRowData);
        }
    }

    public void setEventTable(JTable tble) {
        tble.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = (int) tble.getSelectedRow();
                int MHD = (int) tble.getValueAt(tble.getSelectedRow(), 0);
                if (row != -1) {
                    PnChiTietHoaDon popup = new PnChiTietHoaDon(MHD);
                    JDialog dialog = new JDialog();
                    dialog.add(popup);
                    dialog.pack();
                    dialog.setModal(true);
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);
                }
            }

        });
    }

    public void TimKiemtheoMHD(JButton btnTimKiem, DefaultTableModel tble, JTextField txt) {
        HoaDonBUS HD = new HoaDonBUS();

        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (InputValidator.IsEmpty(txt.getText())) {
                    tble.setRowCount(0);
                    addrowTable(tble);
                } else {
                    String MHD = txt.getText();
                    if (HD.getlisttheoMHD(MHD) == null) {
                        tble.setRowCount(0);
                    } else {
                        tble.setRowCount(0);
                        Object[] data = {HD.getlisttheoMHD(MHD).getMaHD(), HD.getlisttheoMHD(MHD).getMaKH(), HD.getlisttheoMHD(MHD).getMaNV(), HD.getlisttheoMHD(MHD).getMaGiam(), HD.getlisttheoMHD(MHD).getNgayLap(), HD.getlisttheoMHD(MHD).getTongTien()};
                        tble.addRow(data);
                    }
                }
            }
        });
    }

    public void TimKiemTheoGiaTienVaDate(JButton btnTimKiem, JDateChooser dateMin, JDateChooser dateMax, JTextField priceMin, JTextField priceMax, DefaultTableModel tble) {
        HoaDonBUS list = new HoaDonBUS();
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tble.setRowCount(0);
                ArrayList<HoaDon> listHD = list.getListHD_Price_Date(dateMin.getDate(), dateMax.getDate(), priceMin.getText(), priceMax.getText());
                if (listHD != null) {
                    for (int i = 0; i < listHD.size(); i++) {
                        Object[] newRowData = {listHD.get(i).getMaHD(), listHD.get(i).getMaKH(), listHD.get(i).getMaNV(), listHD.get(i).getMaGiam(), listHD.get(i).getNgayLap(), listHD.get(i).getTongTien()};
                        tble.addRow(newRowData);
                    }
                }
            }
        });
    }
}
