package GUI;

import BUS.ThongKeBUS;
import Custom.Mytable;
import Custom.NonEditableTableModel;
import DTO.KhachHang;
import DTO.SPDaBan;
import DTO.ThongKe;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import java.text.NumberFormat;
import java.util.Locale;

public class PnThongKe extends JPanel {

    final Color ClMain = new Color(0, 160, 80); //#00A050
    final Color ClHover = new Color(0, 192, 96);
    final Color ClSelect = new Color(76, 204, 76);

    JComboBox<Integer> cmbYear;

    JPanel pnThongKe, pnbtnSwitch;

    JLabel lbtltTongDoanhThuNam,lbtltTongLoiNhuanNam, lbbtnSwitch, lbbtnRefresh;
    JButton btnLoiNhuan = new JButton();
    CardLayout cardLayout;

    Font FtTitleText = new Font("Montserrat", Font.BOLD, 20);

    ImageIcon imgBtnSwitch = new ImageIcon("image/btn/icons8_view_40px.png");
    ImageIcon imgBtnBack = new ImageIcon("image/btn/icons8_undo_40px.png");

    ThongKeBUS thongKeBUS = new ThongKeBUS();
    ChartPanel chartBarPanel, chartPiePanel, chartBarPanelLoiNhuan, chartPiePanelLoiNhuan;
    NonEditableTableModel dtmSanPham, dtmKhachHang;
    Mytable tbSanPham, tbKhachHang;

    public PnThongKe() {
        addControls();
        addEvents();
        loadData();

    }

    private void addControls() {
        JPanel pnMain = new JPanel(new BorderLayout());
        //btn
        pnbtnSwitch = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lbbtnSwitch = new JLabel(imgBtnSwitch);
        lbbtnRefresh = new JLabel(new ImageIcon("image/btn/refresh.png"));
        pnbtnSwitch.add(lbbtnSwitch, BorderLayout.WEST);
        pnbtnSwitch.add(lbbtnRefresh, BorderLayout.EAST);
        pnMain.add(pnbtnSwitch, BorderLayout.NORTH);

        btnLoiNhuan = new JButton("Thống kê lợi nhuận");
        btnLoiNhuan.setSize(new Dimension(100,100));
        btnLoiNhuan.setFont(new Font("Arial",Font.PLAIN,20));
        
        pnbtnSwitch.add(btnLoiNhuan);
        //------------------------Thống kê doanh thu----------------------------
        JPanel pnThongkeDoanhThu = new JPanel();
        pnThongkeDoanhThu.setLayout(new BoxLayout(pnThongkeDoanhThu, BoxLayout.Y_AXIS));
        
        // cmb year
        int year = Calendar.getInstance().get(Calendar.YEAR);
        cmbYear = new JComboBox<>();// tạo thêm 1 cmbYear nữa cho lợi nhuận
        cmbYear.setFont(FtTitleText);
        cmbYear.setCursor(null);
        JPanel pnCmbYear = new JPanel();
        pnCmbYear.add(cmbYear);
        JPanel pnCmbYearLN = new JPanel();
        pnCmbYearLN.add(cmbYear);
        int OldestYeat;
        OldestYeat = thongKeBUS.getOldestCreationYear();
        int difference = year - OldestYeat;
        System.out.println(OldestYeat);
        for (int i = year; i >= year - difference; i--) {
            cmbYear.addItem(i);
        }
        // biểu đồ cột
        chartBarPanel = new ChartPanel(null);
        JPanel pnBarChart = new JPanel(new BorderLayout());
        pnBarChart.add(pnCmbYear, BorderLayout.SOUTH);
        pnBarChart.add(chartBarPanel, BorderLayout.CENTER);
        pnThongkeDoanhThu.add(pnBarChart);
        // biểu đồ tròn
        chartPiePanel = new ChartPanel(null);
        chartPiePanel.setPreferredSize(new Dimension(500, 300));
        JPanel pnPieChart = new JPanel(new BorderLayout());
        // pn Tổng doanh thu
        lbtltTongDoanhThuNam = new JLabel();
        lbtltTongDoanhThuNam.setFont(FtTitleText);
        lbtltTongDoanhThuNam.setForeground(Color.white);
        lbtltTongDoanhThuNam.setHorizontalAlignment(JLabel.CENTER);
        lbtltTongDoanhThuNam.setVerticalAlignment(JLabel.CENTER);
        JLabel lbbgTongDoanhThuNam = new JLabel(new ImageIcon("image/Menu/thongkeDoanhThu.png"));
        lbbgTongDoanhThuNam.setLayout(new BorderLayout());
        lbbgTongDoanhThuNam.add(lbtltTongDoanhThuNam, BorderLayout.CENTER);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 50));
        lbbgTongDoanhThuNam.add(panel, BorderLayout.SOUTH);
        JPanel pnlbTongDoanhThuNam = new JPanel(new BorderLayout());
        pnlbTongDoanhThuNam.add(lbbgTongDoanhThuNam, BorderLayout.CENTER);

        pnPieChart.add(chartPiePanel, BorderLayout.CENTER);
        pnPieChart.add(pnlbTongDoanhThuNam, BorderLayout.EAST);
        pnThongkeDoanhThu.add(pnPieChart);
        //--------------------------------Thống kê sản phẩm---------------------
        JPanel pnThongKeSanPham = new JPanel(new BorderLayout());
        pnThongKeSanPham.setPreferredSize(new Dimension(pnThongKeSanPham.getPreferredSize().width, 350));
        //tên bảng
        JLabel lbtitleDSSP = new JLabel("Danh sách sản phẩm đã bán");
        lbtitleDSSP.setForeground(ClMain);
        lbtitleDSSP.setFont(FtTitleText);
        JPanel pnlbtltDSSP = new JPanel();
        pnlbtltDSSP.add(lbtitleDSSP);
        //bảng
        dtmSanPham = new NonEditableTableModel();
        dtmSanPham.addColumn("Mã SP");
        dtmSanPham.addColumn("Tên Sản phẩm");
        dtmSanPham.addColumn("Đã bán");
        tbSanPham = new Mytable(dtmSanPham);
        JScrollPane scrSanPham = new JScrollPane(tbSanPham);

        pnThongKeSanPham.add(pnlbtltDSSP, BorderLayout.NORTH);
        pnThongKeSanPham.add(scrSanPham, BorderLayout.CENTER);
        //-------------------------------Thống kê chi tiêu khách hàng--------------------
        JPanel pnThongKeChiTieuKhachHang = new JPanel(new BorderLayout());
        pnThongKeChiTieuKhachHang.setPreferredSize(new Dimension(pnThongKeChiTieuKhachHang.getPreferredSize().width, 350));
        //tên bảng
        JLabel lbtitleCTKH = new JLabel("Top 10 chi tiêu khách hàng");
        lbtitleCTKH.setForeground(ClMain);
        lbtitleCTKH.setFont(FtTitleText);
        JPanel pnlbtltCTKH = new JPanel();
        pnlbtltCTKH.add(lbtitleCTKH);
        //Bảng
        dtmKhachHang = new NonEditableTableModel();
        dtmKhachHang.addColumn("Mã khách hàng");
        dtmKhachHang.addColumn("Tên khách hàng");
        dtmKhachHang.addColumn("Tổng chi tiêu");
        tbKhachHang = new Mytable(dtmKhachHang);
        JScrollPane scrKhachHang = new JScrollPane(tbKhachHang);

        pnThongKeChiTieuKhachHang.add(pnlbtltCTKH, BorderLayout.NORTH);
        pnThongKeChiTieuKhachHang.add(scrKhachHang, BorderLayout.CENTER);
        //--------------------------------------------------------------------
        JPanel pnThongKeSPvsKH = new JPanel();
        pnThongKeSPvsKH.setLayout(new BoxLayout(pnThongKeSPvsKH, BoxLayout.Y_AXIS));
        pnThongKeSPvsKH.add(pnThongKeSanPham);
        pnThongKeSPvsKH.add(pnThongKeChiTieuKhachHang);

        //LoiNhuan
        JPanel pnThongKeLoiNhuan = new JPanel();
        pnThongKeLoiNhuan.setLayout(new BoxLayout(pnThongKeLoiNhuan, BoxLayout.Y_AXIS));
        // biểu đồ cột
        chartBarPanelLoiNhuan = new ChartPanel(null);
        JPanel pnBarChartLoiNhuan = new JPanel(new BorderLayout());
        pnBarChartLoiNhuan.add(pnCmbYearLN, BorderLayout.NORTH);
        pnBarChartLoiNhuan.add(chartBarPanelLoiNhuan, BorderLayout.CENTER);
        pnThongKeLoiNhuan.add(pnBarChartLoiNhuan);
        // biểu đồ tròn
        chartPiePanelLoiNhuan = new ChartPanel(null);
        chartPiePanelLoiNhuan.setPreferredSize(new Dimension(500, 300));
        JPanel pnPieChartLoiNhuan = new JPanel(new BorderLayout());
        // pn lợi nhuận
        lbtltTongLoiNhuanNam = new JLabel();
        lbtltTongLoiNhuanNam.setFont(FtTitleText);
        lbtltTongLoiNhuanNam.setForeground(Color.white);
        lbtltTongLoiNhuanNam.setHorizontalAlignment(JLabel.CENTER);
        lbtltTongLoiNhuanNam.setVerticalAlignment(JLabel.CENTER);
        JLabel lbbgTongLoiNhuanNam = new JLabel(new ImageIcon("image/Menu/thongkeloinhuan.png"));
        lbbgTongLoiNhuanNam.setLayout(new BorderLayout());
        lbbgTongLoiNhuanNam.add(lbtltTongLoiNhuanNam, BorderLayout.CENTER);
        JPanel panelLoiNhuan = new JPanel();
        panelLoiNhuan.setPreferredSize(new Dimension(100, 50));
        lbbgTongLoiNhuanNam.add(panelLoiNhuan, BorderLayout.SOUTH);
        JPanel pnlbTongLoiNhuanNam = new JPanel(new BorderLayout());
        pnlbTongLoiNhuanNam.add(lbbgTongLoiNhuanNam, BorderLayout.CENTER);

        pnPieChartLoiNhuan.add(chartPiePanelLoiNhuan, BorderLayout.CENTER);
        pnPieChartLoiNhuan.add(pnlbTongLoiNhuanNam, BorderLayout.EAST);
        pnThongKeLoiNhuan.add(pnPieChartLoiNhuan);

        cardLayout = new CardLayout();
        pnThongKe = new JPanel(cardLayout);
        pnThongKe.add(pnThongkeDoanhThu, "1");
        pnThongKe.add(pnThongKeSPvsKH, "2");
        pnThongKe.add(pnThongKeLoiNhuan, "3");
        pnMain.add(pnThongKe, BorderLayout.CENTER);
        this.add(pnMain);


    }
    

    private void loadData() {
        // doanh thu
        ThongKe thongKe = thongKeBUS.thongKe((int) cmbYear.getSelectedItem());
        chartBarPanel.setChart(createBarChart(thongKe.getDoanhThuCacThang()));
        chartPiePanel.setChart(createPieChart(thongKe.getPhanTramDoanhThuTheoQuy()));
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.of("vi", "VN"));
        String formattedDoanhThu = numberFormat.format(thongKe.getTongDoanhThu());
        lbtltTongDoanhThuNam.setText(formattedDoanhThu + "");
        loadDataTbl(thongKe.getsPDaBans(), thongKe.getListTopKhachHang());

        //lợi nhuận
        chartBarPanelLoiNhuan.setChart(createBarChartLoiNhuan(thongKe.getLoiNhuanCacThang()));
        chartPiePanelLoiNhuan.setChart(createPieChartLoiNhuan(thongKe.getPhanTramLoiNhuanTheoQuy()));
        NumberFormat numberFormat2 = NumberFormat.getInstance(Locale.of("vi", "VN"));
        String formattedLoiNhuan = numberFormat2.format(thongKe.getTongLoiNhuan());
        lbtltTongLoiNhuanNam.setText(formattedLoiNhuan + "");

    }

    private void loadDataTbl(ArrayList<SPDaBan> sPDaBans, ArrayList<KhachHang> khachHangs) {
        dtmKhachHang.setRowCount(0);
        dtmSanPham.setRowCount(0);
        for (SPDaBan sPDaBan : sPDaBans) {
            dtmSanPham.addRow(new Object[]{sPDaBan.getMaSP(), sPDaBan.getTenSP(), sPDaBan.getDaBan()});
        }
        for (KhachHang khachHang : khachHangs) {
            dtmKhachHang.addRow(new Object[]{khachHang.getMaKH(), khachHang.getTen(), khachHang.getTongChiTieu()});
        }
    }

    private void addEvents() {
        lbbtnSwitch.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (lbbtnSwitch.getIcon() == imgBtnSwitch) {
                    lbbtnSwitch.setIcon(imgBtnBack);
                    cardLayout.show(pnThongKe, "2");
                    return;
                }
                lbbtnSwitch.setIcon(imgBtnSwitch);
                cardLayout.show(pnThongKe, "1");
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

        btnLoiNhuan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnLoiNhuan.getText().equals("Thống kê lợi nhuận")) {
                    btnLoiNhuan.setText("Quay lại");
                    cardLayout.show(pnThongKe, "3");
                } else {
                    btnLoiNhuan.setText("Thống kê lợi nhuận");
                    cardLayout.show(pnThongKe, "1");
                }
            }
        });
        
        cmbYear.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                loadData();
            }
        });
        lbbtnRefresh.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                loadData();
            }
            
        });

        btnLoiNhuan.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                loadData();
            }
            
        });
    }

    private JFreeChart createBarChart(ArrayList<Integer> DoanhThuCacThang) {
        JFreeChart freeChart = ChartFactory.createBarChart("Doanh thu năm " + cmbYear.getSelectedItem(), "Tháng", "Doanh thu", createBarDataset(DoanhThuCacThang), PlotOrientation.VERTICAL, false, false, false);
        return freeChart;
    }

    private CategoryDataset createBarDataset(ArrayList<Integer> DoanhThuCacThang) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

        for (int i = 0; i < 12; i++) {
            dataSet.addValue(DoanhThuCacThang.get(i), "Doanh thu", (i + 1) + "");
        }
        return dataSet;
    }

    private JFreeChart createPieChart(ArrayList<Double> PhanTramTheoQuy) {
        JFreeChart freeChart = ChartFactory.createPieChart("Doanh thu theo quý (%)", createPieDataset(PhanTramTheoQuy), true, true, false);
        return freeChart;
    }

    ////
    private JFreeChart createBarChartLoiNhuan(ArrayList<Integer> DoanhThuCacThang) {
        JFreeChart freeChart = ChartFactory.createBarChart("Lợi nhuận năm " + cmbYear.getSelectedItem(), "Tháng", "Lợi Nhuận", createBarDatasetLoiNhuan(DoanhThuCacThang), PlotOrientation.VERTICAL, false, false, false);
        return freeChart;
    }

    private CategoryDataset createBarDatasetLoiNhuan(ArrayList<Integer> DoanhThuCacThang) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

        for (int i = 0; i < 12; i++) {
            dataSet.addValue(DoanhThuCacThang.get(i), "Lợi Nhuận", (i + 1) + "");
        }
        return dataSet;
    }

    private JFreeChart createPieChartLoiNhuan(ArrayList<Double> PhanTramTheoQuy) {
        JFreeChart freeChart = ChartFactory.createPieChart("Lợi nhuận theo quý (%)", createPieDataset(PhanTramTheoQuy), true, true, false);
        return freeChart;
    }
    private PieDataset createPieDataset(ArrayList<Double> PhanTramTheoQuy) {
        DefaultPieDataset dataSet = new DefaultPieDataset();
        for (int i = 0; i < 4; i++) {
            dataSet.setValue("Quý " + (i + 1), PhanTramTheoQuy.get(i));
        }
        return dataSet;
    }
}
