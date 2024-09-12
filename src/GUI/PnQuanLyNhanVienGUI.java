package GUI;

import static Main.Main.changLNF;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PnQuanLyNhanVienGUI extends JPanel {

    final Color ClMain = new Color(0, 160, 80);
    final Color ClHover = new Color(0, 192, 96);
    final Color ClSelect = new Color(76, 204, 76);

    Font FtTitleText = new Font("Montserrat", Font.BOLD, 20);

    JLabel lbNhanVien, lbQuyen;

    CardLayout cardQLnhanvien;

    JPanel pnCardQLnhanvien;

    public PnQuanLyNhanVienGUI() {
        changLNF("Windows");
        addConTrolsNhanVien();
        addEventsNhanVien();
    }

    public void addConTrolsNhanVien() {
        this.setLayout(new BorderLayout());
        int w = 1030;
        int h = 844;
    
        JPanel pnTop = new JPanel();
        pnTop.setPreferredSize(new Dimension(w, 41));
        pnTop.setLayout(new GridLayout(1, 2));

        lbNhanVien = new JLabel("Nhân Viên");
        lbNhanVien.setFont(FtTitleText);
        lbNhanVien.setForeground(Color.white);
        lbNhanVien.setOpaque(true);
        lbNhanVien.setBackground(ClSelect);
        lbNhanVien.setHorizontalAlignment(JLabel.CENTER);
        lbNhanVien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnTop.add(lbNhanVien);

        lbQuyen = new JLabel("Phân Quyền");
        lbQuyen.setFont(FtTitleText);
        lbQuyen.setForeground(ClMain);
        lbQuyen.setOpaque(true);
        lbQuyen.setBackground(Color.white);
        lbQuyen.setHorizontalAlignment(JLabel.CENTER);
        lbQuyen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbQuyen.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ClMain));
        pnTop.add(lbQuyen);

        this.add(pnTop, BorderLayout.NORTH);
        
        PnNhanVien pnNhanVien=new PnNhanVien();
        PnPhanQuyen pnPhanQuyen=new PnPhanQuyen();
        
        cardQLnhanvien = new CardLayout();
        pnCardQLnhanvien = new JPanel(cardQLnhanvien);
        pnCardQLnhanvien.add(pnNhanVien, "1");
        pnCardQLnhanvien.add(pnPhanQuyen, "2");
        this.add(pnCardQLnhanvien, BorderLayout.CENTER);

    }

    public void addEventsNhanVien() {
        lbNhanVien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lbNhanVien.setBackground(ClSelect);
                lbNhanVien.setForeground(Color.white);
                lbQuyen.setBackground(Color.WHITE);
                lbQuyen.setForeground(ClMain);
                lbQuyen.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ClMain));

                cardQLnhanvien.show(pnCardQLnhanvien, "1");
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
        lbQuyen.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lbQuyen.setBackground(ClSelect);
                lbQuyen.setForeground(Color.white);
                lbNhanVien.setBackground(Color.WHITE);
                lbNhanVien.setForeground(ClMain);
                lbNhanVien.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ClMain));

                cardQLnhanvien.show(pnCardQLnhanvien, "2");
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
