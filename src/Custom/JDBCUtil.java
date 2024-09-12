package Custom;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;


public class JDBCUtil {

    private static String hostname; //mặc định là localhost
    private static String dbname; //tên database
    private static String username; //tên tài khoản
    private static String password; //mật khẩu

    public static Connection getConnection() {
        Connection c = null;
        readFileText();
        if (checkNullValues()) {
            new dialog("Vui lòng chỉnh sửa file connect.txt", dialog.ERROR_DIALOG);
            return c;
        }
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbUrl = "jdbc:sqlserver://" + hostname + ":1433;DatabaseName=" + dbname + ";encrypt= true;trustServerCertificate=true";
            c = DriverManager.getConnection(dbUrl, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            new dialog("Không thể kết nối tới CSDL", dialog.ERROR_DIALOG);
        }
        return c;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void readFileText() {
        try {
            FileInputStream fileInputStream = new FileInputStream("connect.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            hostname = bufferedReader.readLine();
            dbname = bufferedReader.readLine();
            username = bufferedReader.readLine();
            password = bufferedReader.readLine();

            if (password == null) {
                password = "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkNullValues() {
        return hostname == null || dbname == null || username == null;
    }
}
