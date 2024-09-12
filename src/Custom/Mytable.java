/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Custom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author nguye
 */
public class Mytable extends JTable {

    final Color ClMain = new Color(0, 160, 80);

    Font FtTitleText = new Font("Montserrat", Font.BOLD, 18);

    public Mytable() {
        this.setIntercellSpacing(new Dimension(0, 0));
        this.setRowHeight(25);
        JTableHeader head = this.getTableHeader();
        head.setOpaque(false);
        head.setPreferredSize(new Dimension(head.getWidth(), 30));
        head.setFont(FtTitleText);
        head.setBackground(ClMain);
        head.setForeground(Color.white);
        head.setReorderingAllowed(false);
        ((DefaultTableCellRenderer) head.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
    }

    public Mytable(DefaultTableModel dtm) {
        this();
        this.setModel(dtm);
//        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dtm);
//        this.setRowSorter(sorter);
    }
}
