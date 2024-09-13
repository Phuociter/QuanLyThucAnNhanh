package GUI;

import BUS.PhanQuyenBUS;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class MainQuanLyGUI extends JFrame {

    JPanel pnTitle, pnCardListMenu, pnMain;

    CardLayout cardListMenu;

    JLabel btnDoiMatKhau, btnClose, btnMinimize, lbBanhang, lbKhuyenmai, lbNhaphang, lbSanpham, lbNhanvien, lbKhachhang, lbThongke;

    Font FtTitleText = new Font("Montserrat", Font.BOLD, 20);

    ArrayList<JLabel> listMenuLeft;

    final Color ClMain = new Color(0, 160, 80); //#00A050
    final Color ClHover = new Color(0, 192, 96);
    final Color ClSelect = new Color(76, 204, 76);

    int xMouse = 0;
    int yMouse = 0;

    boolean resizing = false;
    boolean canMove = false;
    Point initialClick;

    public MainQuanLyGUI() {
        this.setTitle("Quản lý bán đồ ăn thức uống");
        this.setSize(1280, 900);
        Image icon = new ImageIcon("image/logo/logo32x32.png").getImage();
        this.setIconImage(icon);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        addControls();
        addEvents();
    }

    private void addControls() {
        int width = this.getWidth();
        int height = this.getHeight();

        Container con = getContentPane();

        pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());

        //title
        pnTitle = new JPanel(null);
        pnTitle.setLayout(new BorderLayout());
        pnTitle.setPreferredSize(new Dimension(width, 50));
        pnTitle.setBackground(ClMain);
        pnTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));

        btnDoiMatKhau = new JLabel(new ImageIcon("image/btn/gear.png"));
        btnDoiMatKhau.setToolTipText("Đổi mật khẩu");
        btnDoiMatKhau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnTitle.add(btnDoiMatKhau, BorderLayout.WEST);

        JLabel lbTitleText = new JLabel("Phần mềm quản lý cửa hàng đồ ăn nước uống");
        lbTitleText.setFont(FtTitleText);
        lbTitleText.setForeground(Color.white);
        lbTitleText.setHorizontalAlignment(JLabel.CENTER);
        pnTitle.add(lbTitleText, BorderLayout.CENTER);

        JPanel pnBtn = new JPanel();
        FlowLayout flopnBtn = new FlowLayout();
        flopnBtn.setHgap(10);
        pnBtn.setLayout(flopnBtn);
        pnBtn.setOpaque(false);
        btnClose = new JLabel(new ImageIcon("image/btn/close.png"));
        btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnMinimize = new JLabel(new ImageIcon("image/btn/minimize.png"));
        btnMinimize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnBtn.add(btnMinimize);
        pnBtn.add(btnClose);
        pnTitle.add(pnBtn, BorderLayout.EAST);
        pnMain.add(pnTitle, BorderLayout.NORTH);
        //menuleft
        JPanel pnMenuLeft = new JPanel();
        pnMenuLeft.setPreferredSize(new Dimension(250, height - pnTitle.getHeight()));
        pnMenuLeft.setBackground(ClMain);
        pnMenuLeft.setLayout(new BoxLayout(pnMenuLeft, BoxLayout.Y_AXIS));
        pnMenuLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.white));

        JLabel iconApp = new JLabel(new ImageIcon("image/logo/logo.png"));
        iconApp.setHorizontalAlignment(JLabel.CENTER);
        iconApp.setPreferredSize(new Dimension(250, 210));
        iconApp.setOpaque(true);
        pnMenuLeft.add(iconApp);

        lbBanhang = new JLabel(new ImageIcon("image/Menu/lblBanHang.png"));
        lbKhuyenmai = new JLabel(new ImageIcon("image/Menu/lblKhuyenMai.png"));
        lbNhanvien = new JLabel(new ImageIcon("image/Menu/lblNhanVien.png"));
        lbSanpham = new JLabel(new ImageIcon("image/Menu/lblSanPham.png"));
        lbKhachhang = new JLabel(new ImageIcon("image/Menu/lblKhachHang.png"));
        lbNhaphang = new JLabel(new ImageIcon("image/Menu/lblNhapHang.png"));
        lbThongke = new JLabel(new ImageIcon("image/Menu/lblThongke.png"));

        listMenuLeft = new ArrayList<>();
        listMenuLeft.add(lbBanhang);
        listMenuLeft.add(lbKhuyenmai);
        listMenuLeft.add(lbSanpham);
        listMenuLeft.add(lbNhanvien);
        listMenuLeft.add(lbKhachhang);
        listMenuLeft.add(lbNhaphang);
        listMenuLeft.add(lbThongke);

        for (JLabel opt : listMenuLeft) {
            opt.setVisible(false);
            opt.setOpaque(false);
            opt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pnMenuLeft.add(opt);
        }
        lbBanhang.setBackground(ClSelect);
        lbBanhang.setOpaque(true);
        pnMain.add(pnMenuLeft, BorderLayout.WEST);

        JPanel pnBanHang = new JPanel();
        JPanel pnKhuyenMai = new JPanel();
        JPanel pnNhapHang = new JPanel();
        JPanel pnSanPham = new JPanel();
        JPanel pnNhanVien = new JPanel();
        JPanel pnKhachHang = new JPanel();
        JPanel pnThongKe = new JPanel();

        cardListMenu = new CardLayout();
        pnCardListMenu = new JPanel(cardListMenu);
        pnCardListMenu.add(pnBanHang, "1");
        pnCardListMenu.add(pnKhuyenMai, "2");
        pnCardListMenu.add(pnSanPham, "3");
        pnCardListMenu.add(pnNhanVien, "4");
        pnCardListMenu.add(pnKhachHang, "5");
        pnCardListMenu.add(pnNhapHang, "6");
        pnCardListMenu.add(pnThongKe, "7");

        PnQuanLyBanHangGUI pnQuanLyBanHangGUI = new PnQuanLyBanHangGUI();
        pnBanHang.setLayout(new BorderLayout());
        pnBanHang.add(pnQuanLyBanHangGUI, BorderLayout.CENTER);
        lbBanhang.setVisible(true);

        PnQuanLyKhuyenMaiGUI pnQuanLyKhuyenMaiGUI = new PnQuanLyKhuyenMaiGUI();
        pnKhuyenMai.setLayout(new BorderLayout());
        pnKhuyenMai.add(pnQuanLyKhuyenMaiGUI, BorderLayout.CENTER);
        lbKhuyenmai.setVisible(true);

        PhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
        phanQuyenBUS.UpdateCurrentQuyen();

        if (PhanQuyenBUS.currentQuyen != null) {
            if (PhanQuyenBUS.currentQuyen.getQlNhanVien() == 1) {
                PnQuanLyNhanVienGUI pnQuanLyNhanVienGUI = new PnQuanLyNhanVienGUI();
                pnNhanVien.setLayout(new BorderLayout());
                pnNhanVien.add(pnQuanLyNhanVienGUI, BorderLayout.CENTER);
                lbNhanvien.setVisible(true);
            }
            if (PhanQuyenBUS.currentQuyen.getQlSanPham() == 1) {
                PnQuanLySanPhamGUI pnQuanLySanPhamGUI = new PnQuanLySanPhamGUI();
                pnSanPham.setLayout(new BorderLayout());
                pnSanPham.add(pnQuanLySanPhamGUI, BorderLayout.CENTER);
                lbSanpham.setVisible(true);
            }
            if (PhanQuyenBUS.currentQuyen.getQlNhapHang() == 1) {
                PnQuanLyNhapHangGUI pnQuanLyNhapHangGUI = new PnQuanLyNhapHangGUI();
                pnNhapHang.setLayout(new BorderLayout());
                pnNhapHang.add(pnQuanLyNhapHangGUI, BorderLayout.CENTER);
                lbNhaphang.setVisible(true);
            }
            if (PhanQuyenBUS.currentQuyen.getQlKhachHang() == 1) {
                PnQuanLyKhachHangGUI pnQuanLyKhachHangGUI = new PnQuanLyKhachHangGUI();
                pnKhachHang.setLayout(new BorderLayout());
                pnKhachHang.add(pnQuanLyKhachHangGUI, BorderLayout.CENTER);
                lbKhachhang.setVisible(true);
            }
            if (PhanQuyenBUS.currentQuyen.getThongKe() == 1) {
                PnThongKe pnQuanLyThongKe = new PnThongKe();
                pnThongKe.setLayout(new BorderLayout());
                pnThongKe.add(pnQuanLyThongKe, BorderLayout.CENTER);
                lbThongke.setVisible(true);
            }
        }

        pnMain.add(pnCardListMenu, BorderLayout.CENTER);
        pnMain.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        con.add(pnMain);

    }

    private void addEvents() {
        pnMain.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getLocationOnScreen(); //lấy vị trí chuột khi ấn
                resizing = true;
                if (e.getX() <= 1) {
                    canMove = true;
                } else if (e.getX() >= getWidth() - 1) {
                    canMove = false;
                } else if (e.getY() <= 1) {
                    canMove = true;
                } else if (e.getY() >= getHeight() - 1) {
                    canMove = false;
                } else {
                    resizing = false;
                    canMove = false;
                }
            }

            public void mouseReleased(MouseEvent e) {
                resizing = false;
                setCursor(null);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        pnMain.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                if (initialClick == null) {
                    return;
                }
                if (resizing) {
                    Point currentClick = e.getLocationOnScreen();
                    int dx = currentClick.x - initialClick.x;
                    int dy = currentClick.y - initialClick.y;
                    if (canMove) {
                        moveFrame(e.getXOnScreen(), e.getYOnScreen());
                        dx = -dx;
                        dy = -dy;
                    }
                    int newWidth = getWidth() + dx;
                    int newHeight = getHeight() + dy;

                    int minWidth = 100;
                    int minHeight = 100;

                    if (newWidth >= minWidth && newHeight >= minHeight) {
                        setSize(newWidth, newHeight);
                        initialClick = currentClick;
                    }
                }
            }

            public void mouseMoved(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
                setCursor(null);
                if (e.getX() <= 1) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
                } else if (e.getX() >= getWidth() - 1) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                } else if (e.getY() <= 1) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                } else if (e.getY() >= getHeight() - 1) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                } else if (e.getX() <= 1 && e.getY() <= 1) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
                } else if (e.getX() >= getWidth() - 1 && e.getY() <= 1) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
                } else if (e.getX() <= 1 && e.getY() >= getHeight() - 1) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
                } else if (e.getX() >= getWidth() - 1 && e.getY() >= getHeight() - 1) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                }
            }
        });

        pnTitle.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) { //gọi khi kéo chuột
                moveFrame(e.getXOnScreen(), e.getYOnScreen());
            }

            @Override
            public void mouseMoved(MouseEvent e) { //gọi khi di chuyển chuột
                xMouse = e.getX();
                yMouse = e.getY();
                setCursor(null);
            }
        });

        btnClose.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnClose.setIcon(new ImageIcon("image/btn/close-hover.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnClose.setIcon(new ImageIcon("image/btn/close.png"));
            }
        });

        btnMinimize.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                minimizeFrame();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnMinimize.setIcon(new ImageIcon("image/btn/minimize-hover.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnMinimize.setIcon(new ImageIcon("image/btn/minimize.png"));
            }
        });

        for (JLabel opt : listMenuLeft) {
            opt.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (JLabel lb : listMenuLeft) {
                        lb.setOpaque(false);
                        lb.setBackground(null);
                    }
                    opt.setOpaque(true);
                    opt.setBackground(ClSelect);
                    String cardName = "";
                    if (opt == lbBanhang) {
                        cardName = "1";
                    } else if (opt == lbKhuyenmai) {
                        cardName = "2";
                    } else if (opt == lbSanpham) {
                        cardName = "3";
                    } else if (opt == lbNhanvien) {
                        cardName = "4";
                    } else if (opt == lbKhachhang) {
                        cardName = "5";
                    } else if (opt == lbNhaphang) {
                        cardName = "6";
                    } else if (opt == lbThongke) {
                        cardName = "7";
                    }
                    cardListMenu.show(pnCardListMenu, cardName);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!opt.getBackground().equals(ClSelect)) {
                        opt.setOpaque(true);
                        opt.setBackground(ClHover);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (opt.getBackground().equals(ClHover)) {
                        opt.setOpaque(false);
                        opt.setBackground(null);
                    }
                }
            });
        }
        btnDoiMatKhau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dlgDoiMatKhau dmk = new dlgDoiMatKhau();
            }

        });
    }

    private void minimizeFrame() {
        this.setState(Frame.ICONIFIED);
    }

    private void moveFrame(int x, int y) {
        this.setLocation(x - xMouse, y - yMouse);
    }
}
