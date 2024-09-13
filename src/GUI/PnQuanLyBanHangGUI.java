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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class PnQuanLyBanHangGUI extends JPanel {

    final Color ClMain = new Color(0, 160, 80);
    final Color ClHover = new Color(0, 192, 96);
    final Color ClSelect = new Color(76, 204, 76);

    Font FtTitleText = new Font("Montserrat", Font.BOLD, 20);
    Font font = new Font("", Font.PLAIN, 20);

    JLabel lbBanHang, lbHoaDon;

    CardLayout cardQLBanHang;

    JPanel pnCardQLBanHang;

    public PnQuanLyBanHangGUI() {
        changLNF("Windows");
        addControlsBanHang();
        addEventsBanHang();
    }

    private void addControlsBanHang() {
        this.setLayout(new BorderLayout());
        int w = 1030;
        int h = 844;
        
        JPanel pnTop = new JPanel();
        pnTop.setPreferredSize(new Dimension(w, 41));
        pnTop.setLayout(new GridLayout(1, 2));

        lbBanHang = new JLabel("Bán hàng");
        lbBanHang.setFont(FtTitleText);
        lbBanHang.setForeground(Color.white);
        lbBanHang.setOpaque(true);
        lbBanHang.setBackground(ClSelect);
        lbBanHang.setHorizontalAlignment(JLabel.CENTER);
        lbBanHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnTop.add(lbBanHang);

        lbHoaDon = new JLabel("Hóa đơn");
        lbHoaDon.setFont(FtTitleText);
        lbHoaDon.setForeground(ClMain);
        lbHoaDon.setOpaque(true);
        lbHoaDon.setBackground(Color.white);
        lbHoaDon.setHorizontalAlignment(JLabel.CENTER);
        lbHoaDon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbHoaDon.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ClMain));
        pnTop.add(lbHoaDon);

        this.add(pnTop, BorderLayout.NORTH);
        
        PnBanHang pnBanHang = new PnBanHang();
        PnHoaDon pnHoaDon = new PnHoaDon();

        cardQLBanHang = new CardLayout();
        pnCardQLBanHang = new JPanel(cardQLBanHang);
        pnCardQLBanHang.add(pnBanHang, "1");
        pnCardQLBanHang.add(pnHoaDon, "2");

        this.add(pnCardQLBanHang, BorderLayout.CENTER);
    }

    private void addEventsBanHang() {
        lbBanHang.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lbBanHang.setBackground(ClSelect);
                lbBanHang.setForeground(Color.WHITE);
                lbHoaDon.setBackground(Color.WHITE);
                lbHoaDon.setForeground(ClMain);
                lbHoaDon.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ClMain));

                cardQLBanHang.show(pnCardQLBanHang, "1");
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
        lbHoaDon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lbHoaDon.setBackground(ClSelect);
                lbHoaDon.setForeground(Color.WHITE);
                lbBanHang.setBackground(Color.WHITE);
                lbBanHang.setForeground(ClMain);
                lbBanHang.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ClMain));

                cardQLBanHang.show(pnCardQLBanHang, "2");
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
