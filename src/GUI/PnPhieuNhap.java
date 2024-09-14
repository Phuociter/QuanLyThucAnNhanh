package GUI;

import Custom.Mytable;
import Custom.dialog;
import DTO.PhieuNhap;

import com.toedter.calendar.JDateChooser;
import BUS.PhieuNhapBUS;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PnPhieuNhap extends JPanel {

    final Color ClMain = new Color(0, 160, 80);
    final Color ClHover = new Color(0, 192, 96);
    final Color ClSelect = new Color(76, 204, 76);

    Font FtTitleText = new Font("Montserrat", Font.BOLD, 20);
    Font font = new Font("", Font.PLAIN, 20);
    DefaultTableModel dtmPhieuNhap;
    PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
    JButton btnSearchMaPN;
    JTextField txtSearchMaPN, txtStartPrice, txtEndPrice;

    JButton btnSearchDate;
    JDateChooser dtcStartDate, dtcEndDate;
    private JButton btnReset;

    public PnPhieuNhap() {
        addControls();
        addEvents();
    }

    private void addControls() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel pnTltHoaDon = new JPanel();
        JLabel lbTltHoaDon = new JLabel("<html><h1>Thông tin phiếu nhập</h1></html>");
        lbTltHoaDon.setForeground(ClMain);

        btnReset = new JButton(new ImageIcon("image/btn/refresh.png"));
        btnReset.setPreferredSize(new Dimension(40, 40));
        btnReset.setFocusable(false);
        btnReset.setBorder(null);
        pnTltHoaDon.add(lbTltHoaDon);
        pnTltHoaDon.add(btnReset);
        this.add(pnTltHoaDon);

        JPanel pnSearchPhieuNhap = new JPanel(); // tạo panel tìm kiếm
        pnSearchPhieuNhap.setLayout(new BoxLayout(pnSearchPhieuNhap, BoxLayout.X_AXIS));

        JPanel pnSearchMaPN = new JPanel(); // panel tìm kiếm theo mã
        pnSearchMaPN.setLayout(new BoxLayout(pnSearchMaPN, BoxLayout.Y_AXIS));

        JPanel pnTltSearchMaPN = new JPanel();
        JLabel lbTltSearchMaPN = new JLabel("Tìm kiếm Mã Phiếu Nhập");
        lbTltSearchMaPN.setFont(FtTitleText);
        pnTltSearchMaPN.add(lbTltSearchMaPN);
        pnSearchMaPN.add(pnTltSearchMaPN);

        JPanel pntxtSearchMaPN = new JPanel();
        JLabel lbtxtSearchMaPN = new JLabel("Mã PN");
        lbtxtSearchMaPN.setFont(font);
        txtSearchMaPN = new JTextField(25);
        txtSearchMaPN.setFont(font);
        pntxtSearchMaPN.add(lbtxtSearchMaPN);
        pntxtSearchMaPN.add(txtSearchMaPN);

        btnSearchMaPN = new JButton(new ImageIcon("image/btn/search.png"));
        btnSearchMaPN.setPreferredSize(new Dimension(40, 40));
        btnSearchMaPN.setBorder(null);
        btnSearchMaPN.setFocusable(false);
        pntxtSearchMaPN.add(btnSearchMaPN);
        pnSearchMaPN.add(pntxtSearchMaPN);

        JPanel pnSearchDate = new JPanel();// tạo panel tìm kiếm theo ngày
        pnSearchDate.setLayout(new BoxLayout(pnSearchDate, BoxLayout.Y_AXIS));

        JPanel pnTltSearchDate = new JPanel();
        JLabel lbTltSearchDate = new JLabel("Tìm kiếm Phiếu Nhập");
        lbTltSearchDate.setFont(FtTitleText);
        pnTltSearchDate.add(lbTltSearchDate);
        pnSearchDate.add(pnTltSearchDate);

        JPanel pntxtStartDate = new JPanel();
        JLabel lbtxtStartDate = new JLabel("Từ ngày");
        lbtxtStartDate.setFont(font);
        dtcStartDate = new JDateChooser();
        dtcStartDate.setPreferredSize(new Dimension(150, 30));
        pntxtStartDate.add(lbtxtStartDate);
        pntxtStartDate.add(dtcStartDate);
        pnSearchDate.add(pntxtStartDate);

        JPanel pntxtEndDate = new JPanel();

        JLabel lbtxtEndDate = new JLabel("Đến ngày");
        lbtxtEndDate.setFont(font);
        dtcEndDate = new JDateChooser();
        dtcEndDate.setPreferredSize(new Dimension(150, 30));
        pntxtEndDate.add(lbtxtEndDate);
        pntxtEndDate.add(dtcEndDate);
        pnSearchDate.add(pntxtEndDate);

        JPanel pntxtStartPrice = new JPanel();
        JLabel lbtxtStartPrice = new JLabel("Giá từ");
        lbtxtStartPrice.setFont(font);
        txtStartPrice = new JTextField();
        txtStartPrice.setPreferredSize(dtcEndDate.getPreferredSize());
        txtStartPrice.setFont(font);
        pntxtStartPrice.add(lbtxtStartPrice);
        pntxtStartPrice.add(txtStartPrice);
        pnSearchDate.add(pntxtStartPrice);

        JPanel pntxtEndPrice = new JPanel();
        JLabel lbtxtEndPrice = new JLabel("Đến");
        lbtxtEndPrice.setFont(font);
        txtEndPrice = new JTextField();
        txtEndPrice.setPreferredSize(dtcEndDate.getPreferredSize());
        txtEndPrice.setFont(font);
        pntxtEndPrice.add(lbtxtEndPrice);
        pntxtEndPrice.add(txtEndPrice);
        pnSearchDate.add(pntxtEndPrice);

        btnSearchDate = new JButton(new ImageIcon("image/btn/search.png"));
        btnSearchDate.setPreferredSize(new Dimension(40, 40));
        btnSearchDate.setBorder(null);
        btnSearchDate.setFocusable(false);
        pnSearchDate.add(btnSearchDate);

        lbtxtStartPrice.setPreferredSize(lbtxtEndDate.getPreferredSize());
        lbtxtEndPrice.setPreferredSize(lbtxtEndDate.getPreferredSize());
        lbtxtStartDate.setPreferredSize(lbtxtEndDate.getPreferredSize());
        lbtxtStartDate.setPreferredSize(lbtxtEndDate.getPreferredSize());

        pnSearchMaPN.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, ClHover));
        pnSearchDate.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, ClHover));

        pnSearchPhieuNhap.add(pnSearchMaPN);
        pnSearchPhieuNhap.add(pnSearchDate);
        this.add(pnSearchPhieuNhap);

        JPanel pnTbPhieuNhap = new JPanel(new BorderLayout());// tạo khung chứa table
        String[] coltbPhieuNhap = {"Mã PN", "Mã NCC", "Mã Nv", "Ngày tạo", "Tổng tiền"};
        dtmPhieuNhap = new DefaultTableModel(coltbPhieuNhap, 0);
        Mytable mtbPhieuNhap = new Mytable(dtmPhieuNhap) {
            public boolean isCellEditable(int row, int column) { // không cho phép sửa nội dung trong table
                return false;
            }
        };
        JScrollPane scrmtbPhieuNhap = new JScrollPane(mtbPhieuNhap);
        pnTbPhieuNhap.add(scrmtbPhieuNhap, BorderLayout.CENTER);
        this.add(pnTbPhieuNhap);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();// lấy định dạng mặc định của ô
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);// set căn giữa nội dung cho định dạng

        mtbPhieuNhap.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        mtbPhieuNhap.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        mtbPhieuNhap.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        mtbPhieuNhap.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        mtbPhieuNhap.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        mtbPhieuNhap.getColumnModel().getColumn(0).setPreferredWidth(10);
        mtbPhieuNhap.getColumnModel().getColumn(1).setPreferredWidth(10);
        mtbPhieuNhap.getColumnModel().getColumn(2).setPreferredWidth(10);
        mtbPhieuNhap.getColumnModel().getColumn(3).setPreferredWidth(10);
        mtbPhieuNhap.getColumnModel().getColumn(4).setPreferredWidth(10);
        loaddata();
        setEventTable(mtbPhieuNhap);

    }

    private void loaddata() {
        ArrayList<PhieuNhap> phieuNhaps = phieuNhapBUS.getList();
        if (phieuNhaps == null) {
            return;
        }
        updateTableData(phieuNhaps);
    }

    public void setEventTable(JTable tble) {
        tble.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = (int) tble.getSelectedRow();
                int MPN = (int) tble.getValueAt(tble.getSelectedRow(), 0);
                if (row != -1) {
                    PnChiTietPhieuNhap popup = new PnChiTietPhieuNhap(MPN);
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

    private void addEvents() {
        btnSearchDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Convert JDateChooser dates to java.util.Date objects
                java.util.Date startDate = dtcStartDate.getDate();
                java.util.Date endDate = dtcEndDate.getDate();

                java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());

                java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

                ArrayList<PhieuNhap> searchResults = phieuNhapBUS.findPNByRange(sqlStartDate, sqlEndDate, txtStartPrice.getText(), txtEndPrice.getText());
                if (searchResults == null) {
                    //new dialog("không tìm thấy dữ liệu yêu cầu", dialog.INFO_DIALOG);
                }
                else{
                    if(searchResults.size() == 0)
                    {
                        new dialog("không tìm thấy dữ liệu yêu cầu", dialog.INFO_DIALOG);
                    }
                }
                // Update the table with search results
                updateTableData(searchResults);

            }
        });
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<PhieuNhap> phieuNhaps = phieuNhapBUS.getList();
                updateTableData(phieuNhaps);
            }
        });
        btnSearchMaPN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = txtSearchMaPN.getText().trim();

                // Attempt to parse the search text as an integer
                try {
                    int maPN = Integer.parseInt(text);

                    // If text is not empty, search by ID
                    if (!text.isEmpty()) {
                        ArrayList<PhieuNhap> searchResults = phieuNhapBUS.FindPNByPNid(maPN);
                        if (searchResults == null) {
                            new dialog("không tìm thấy dữ liệu", dialog.INFO_DIALOG);
                            return;
                        } else {
                            updateTableData(searchResults);
                        }
                    }
                } catch (NumberFormatException ex) {
                    // Handle non-numeric input
                    new dialog("Vui lòng nhập mã phiếu nhập là số!", dialog.ERROR_DIALOG);
                }
            }

        });

    }

    private void updateTableData(ArrayList<PhieuNhap> searchResults) {
        dtmPhieuNhap.setRowCount(0); // Clear existing data
        if(searchResults == null){
            return;
        }
        for (PhieuNhap pn : searchResults) {
            dtmPhieuNhap
                    .addRow(new Object[]{pn.getMaPN(), pn.getMaNCC(), pn.getMaNV(), pn.getNgayLap(), pn.getTongTien()});
        }
    }
}
