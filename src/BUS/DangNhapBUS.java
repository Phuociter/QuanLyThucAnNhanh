package BUS;

import Custom.InputValidator;
import Custom.dialog;
import DAO.DangNhapDAO;
import DTO.TaiKhoan;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class DangNhapBUS {

    DangNhapDAO dangNhapDAO = new DangNhapDAO();
    public static TaiKhoan taiKhoanLogin = null;

    public DangNhapBUS() {
    }

    public void login(String username, String password) {
        if (checkEmpty(username, password)) {
            new dialog("Không được để trống", dialog.ERROR_DIALOG);
            return;
        }
        if (!checkAccount(username, password)) {
            new dialog("Tài khoản không đúng hoặc đã bị khóa", dialog.ERROR_DIALOG);
            return;
        }
        new dialog("Đăng nhập thành công", dialog.SUCCESS_DIALOG);

    }

    private boolean checkAccount(String username, String password) {
        TaiKhoan tk = dangNhapDAO.checkAccount(new TaiKhoan(0, 0, username, password, 0));
        if (tk == null) {
            return false;
        }
        taiKhoanLogin = tk;
        return true;
    }

    private boolean checkEmpty(String username, String password) {
        if (InputValidator.IsEmpty(username) || InputValidator.IsEmpty(password)) {
            return true;
        }
        return false;
    }

    public String layTkdaghinho() {
        try {
            FileInputStream fileInputStream = new FileInputStream("account.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            bufferedReader.close();
            return line;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    public void GhiNhoTK(String username,String password,boolean flag){
        try {
            if (!flag) {
                username = "";
                password = "";
            }
            FileWriter fileWriter = new FileWriter("account.txt");
            fileWriter.write(username + "|" + password);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
