package GUI;

import static Main.Main.changLNF;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PnQuanLyNhapHangGUI extends JPanel {

    final Color ClMain = new Color(0, 160, 80);
    final Color ClHover = new Color(0, 192, 96);
    final Color ClSelect = new Color(76, 204, 76);

    Font FtTitleText = new Font("Montserrat", Font.BOLD, 20);
    Font font = new Font("", Font.PLAIN, 20);

    JLabel lbNhapHang, lbPhieuNhap;

    CardLayout cardQLNhapHang;

    JPanel pnCardQLNhapHang;

    public PnQuanLyNhapHangGUI() {
        changLNF("Windows");
        addConTrolsNhapHang();
        addEventsNhapHang();
    }

    private void addConTrolsNhapHang() {
        this.setLayout(new BorderLayout());
        int w = 1030;
        int h = 844;
        
        JPanel pnTop = new JPanel();
        pnTop.setPreferredSize(new Dimension(w, 41));
        pnTop.setLayout(new GridLayout(1, 2));

        lbNhapHang = new JLabel("Nhập hàng");
        lbNhapHang.setFont(FtTitleText);
        lbNhapHang.setForeground(Color.white);
        lbNhapHang.setOpaque(true);
        lbNhapHang.setBackground(ClSelect);
        lbNhapHang.setHorizontalAlignment(JLabel.CENTER);
        lbNhapHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnTop.add(lbNhapHang);

        lbPhieuNhap = new JLabel("Phiếu nhập");
        lbPhieuNhap.setFont(FtTitleText);
        lbPhieuNhap.setForeground(ClMain);
        lbPhieuNhap.setOpaque(true);
        lbPhieuNhap.setBackground(Color.white);
        lbPhieuNhap.setHorizontalAlignment(JLabel.CENTER);
        lbPhieuNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbPhieuNhap.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ClMain));
        pnTop.add(lbPhieuNhap);

        this.add(pnTop, BorderLayout.NORTH);

        PnNhapHang nhapHang = new PnNhapHang();
        PnPhieuNhap phieuNhap = new PnPhieuNhap();

        cardQLNhapHang = new CardLayout();
        pnCardQLNhapHang = new JPanel(cardQLNhapHang);
        pnCardQLNhapHang.add(nhapHang, "1");
        pnCardQLNhapHang.add(phieuNhap, "2");
        this.add(pnCardQLNhapHang, BorderLayout.CENTER);
    }

    private void addEventsNhapHang() {
        lbNhapHang.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lbNhapHang.setBackground(ClSelect);
                lbNhapHang.setForeground(Color.white);
                lbPhieuNhap.setBackground(Color.WHITE);
                lbPhieuNhap.setForeground(ClMain);
                lbPhieuNhap.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ClMain));

                cardQLNhapHang.show(pnCardQLNhapHang, "1");
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
        lbPhieuNhap.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lbPhieuNhap.setBackground(ClSelect);
                lbPhieuNhap.setForeground(Color.white);
                lbNhapHang.setBackground(Color.WHITE);
                lbNhapHang.setForeground(ClMain);
                lbNhapHang.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ClMain));

                cardQLNhapHang.show(pnCardQLNhapHang, "2");
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
    }
}
