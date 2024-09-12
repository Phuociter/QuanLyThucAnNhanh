package BUS;

import DAO.KhachHangDAO;
import java.util.ArrayList;
import DTO.KhachHang;
import Custom.InputValidator;
import Custom.dialog;

public class KhachHangBUS {

    KhachHangDAO khachhangDao = new KhachHangDAO();

    public KhachHangBUS() {
    }

    public ArrayList<KhachHang> getListKhachHang() {
        return khachhangDao.getListKhachHang();
    }

    //KT có trống không
    private boolean CheckEmpty(KhachHang khachHang, String content) {
        if (InputValidator.IsEmpty(khachHang.getTen()) || InputValidator.IsEmpty(khachHang.getDiaChi()) || InputValidator.IsEmpty(khachHang.getDienThoai()) || InputValidator.IsEmpty(khachHang.getGioiTinh()) || khachHang.getTrangThai() == -1) {
            new dialog(content, dialog.ERROR_DIALOG);
            return true;
        }
        return false;
    }

    //KT SĐT
    private boolean CheckPhoneNumber(String phoneNumber, String content) {
        if (!InputValidator.isValidPhoneNumber(phoneNumber)) {
            new dialog(content, dialog.ERROR_DIALOG);
            return false;
        }
        return true;
    }

    //KT email
    private boolean CheckEmail(String email, String content) {
        if (!InputValidator.isValidEmail(email)) {
            new dialog(content, dialog.ERROR_DIALOG);
            return false;
        }
        return true;
    }

    // KT tên khách hàng chỉ được nhập kí tự
    private boolean CheckName(String name, String content) {
        if (!InputValidator.isValidName(name)) {
            new dialog(content, dialog.ERROR_DIALOG);
            return false;
        }
        return true;
    }

    private boolean checkInfors(KhachHang khachHang) {
        if (CheckEmpty(khachHang, "Không được để trống thông tin")) {
            return false;
        }
        if (!CheckPhoneNumber(khachHang.getDienThoai(), "Số điện thoại không hợp lệ")) {
            return false;
        }
        if (!CheckEmail(khachHang.getEmail(), "Email không hợp lệ")) {
            return false;
        }
        if (!CheckName(khachHang.getTen(), "Tên khách hàng không hợp lệ")) {
            return false;
        }
        return true;
    }

    public boolean Update(KhachHang khachHang) {
        if (!checkInfors(khachHang)) {
            return false;
        }
        boolean rs = khachhangDao.updateKhachHang(khachHang);
        if (!rs) {
            new dialog("Sửa không thành công", dialog.ERROR_DIALOG);
            return false;
        }
        new dialog("Sửa thành công", dialog.SUCCESS_DIALOG);
        return true;
    }

    public boolean Insert(KhachHang khachHang) {
        khachHang.setMaKH(getNextMaKH());
        if (!checkInfors(khachHang)) { //kiểm tra dữ liệu đầu vào 
            return false;
        }
        boolean result = khachhangDao.addKhachHang(khachHang);
        if (!result) {
            new dialog("Thêm không thành công", dialog.ERROR_DIALOG);
            return false;
        }
        new dialog("Thêm thành công", dialog.SUCCESS_DIALOG);
        return true;
    }

    public boolean Delete(KhachHang khachHang) {
        int rs = khachhangDao.deleteKhachHang(khachHang.getMaKH());
        if (rs == 0) {
            new dialog("Xóa không thành công", dialog.ERROR_DIALOG);
            return false;
        }
        new dialog("Xóa thành công", dialog.SUCCESS_DIALOG);
        return true;
    }

    public int getNextMaKH() {
        return khachhangDao.getNewMa() + 1;
    }

    public ArrayList<KhachHang> searchKhachHang(String keyword) {
        ArrayList<KhachHang> resultList = new ArrayList<>();
        ArrayList<KhachHang> khachHangs = khachhangDao.getListKhachHang();
        for (KhachHang kh : khachHangs) {
            if (kh.getTen().toLowerCase().contains(keyword.toLowerCase())) {
                resultList.add(kh);
            }
        }
        return resultList;
    }

    public KhachHang getID(int maKh) {
        return khachhangDao.getKhachHang(maKh);
    }

    public ArrayList<KhachHang> TimKiemKHtheoMavaTen(String keyword) {
        return khachhangDao.searchKhachHang(keyword.trim());
    }
}
