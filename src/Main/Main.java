package Main;

import Custom.JDBCUtil;
import GUI.DangNhapGUI;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        Connection c = JDBCUtil.getConnection();
        if (c == null) {
            return;
        }
        JDBCUtil.closeConnection(c);
        DangNhapGUI dangNhapGUI = new DangNhapGUI();
        dangNhapGUI.setVisible(true);
    }

    public static void changLNF(String nameLNF) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (nameLNF.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        }
    }
}
