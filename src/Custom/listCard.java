package Custom;

import DTO.SanPham;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class listCard extends JPanel {

    private int w = 1000;
    private int h = 350;
    
    JPanel productList;
    JScrollPane scrollPane;

    // khởi tạo khi không có danh sách
    public listCard() {
        addControls();
        addEvents();
    }

    //khởi tạo khi có danh sách
    public listCard(ArrayList<SanPham> listSP) {
        this();
        addCards(listSP);
    }

    private void addControls() {
        this.setPreferredSize(new Dimension(w, h));
        productList=new JPanel();
        productList.setLayout(new GridLayout(0, 5));
        scrollPane = new JScrollPane(productList);
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void addEvents() {
        scrollPane.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateCols();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }
        });
  }

    private void updateCols() {
        int width = scrollPane.getViewport().getWidth();
        int col = Math.max(1, width / 200);
        productList.setLayout(new GridLayout(0, col));
    }

    // thêm khi có danh sách
    public void addCards(ArrayList<SanPham> listSP) {
        for (int i = 0; i < listSP.size(); i++) {
            productList.add(new ProductCard(listSP.get(i)));
        }
    }

    //thêm khi chỉ có 1 sản phẩm
    public void addCard(SanPham sp) {
        productList.add(new ProductCard(sp));
    }
    //clear
    public void removeAll() {
        productList.removeAll();
    }
}