package BUS;

import DAO.KhachHangDAO;
import java.util.ArrayList;
import DTO.KhachHang;
import Custom.InputValidator;
import Custom.dialog;
import DTO.NhanVien;

public class KhachHangBUS {

    KhachHangDAO khachhangDao = new KhachHangDAO();

    public KhachHangBUS() {
    }

    public ArrayList<KhachHang> getListKhachHang() {
        return khachhangDao.getListKhachHang();
    }

    //KT có trống không
    private boolean CheckEmpty(KhachHang khachHang) {
        if (InputValidator.IsEmpty(khachHang.getTen())){
            new dialog("Tên khách hàng không được bỏ trống!",dialog.ERROR_DIALOG);
            return true;
        }
        if (InputValidator.IsEmpty(khachHang.getDienThoai())){
            new dialog("Số điện thoại khách hàng không được bỏ trống!",dialog.ERROR_DIALOG);
            return true;
        }
        if (InputValidator.IsEmpty(khachHang.getGioiTinh())){
            new dialog("Vui lòng chọn giới tính của khách hàng!",dialog.ERROR_DIALOG);
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
    private boolean ChechAddress(String address, String content){
        if(!InputValidator.isValidAddress(address)){
            new dialog(content, dialog.ERROR_DIALOG);
            return false;
        }
        return true;
    }

    private boolean checkInfors(KhachHang khachHang) {
        if (CheckEmpty(khachHang)) {
            return false;
        }
        if (khachHang.getTen().trim().length() < 3){
            new dialog("Tên khách hàng chứa từ 3 đến 50 ký tự!", dialog.ERROR_DIALOG);
            return false;
        }
        if (!InputValidator.IsValidNamelength(khachHang.getTen())){
            new dialog("Tên khách hàng không được vượt quá 50 ký tự!", dialog.ERROR_DIALOG);
            return false;
        }
        if (!CheckName(khachHang.getTen(), "Tên khách hàng không hợp lệ!")) {
            return false;
        }
        if (!CheckPhoneNumber(khachHang.getDienThoai(), "Số điện thoại không hợp lệ!")) {
            return false;
        }
        if(!InputValidator.IsEmpty(khachHang.getEmail())) {
            if (!CheckEmail(khachHang.getEmail(), "Email không hợp lệ!")) {
                return false;
            }
        }
        if(!InputValidator.IsEmpty(khachHang.getDiaChi())) {
            if (!ChechAddress(khachHang.getDiaChi(), "Địa chỉ không hợp lệ!")) {
                return false;
            }
        }
//        if(!InputValidator.IsEmpty(khachHang.getDiaChi())){
//            if(!InputValidator.isValidAddress(khachHang.getDiaChi())) {
//                return false;
//            }
//        }
        return true;
    }

    // Dùng cho nút thêm
    private boolean checkduplicatePhoneNumber(String PhoneNumber){
        ArrayList <KhachHang> khachHangs = new ArrayList<>();
        khachHangs = getListKhachHang();
        for (KhachHang kh : khachHangs){
            if (kh.getDienThoai().trim().equals(PhoneNumber.trim())){
                return true;
            }
        }
        return false;
    }

    //Dùng cho nút edit
    private boolean checkduplicatePhoneNumber2(String PhoneNumber, int maKH){
        ArrayList <KhachHang> khachHangs = new ArrayList<>();
        khachHangs = getListKhachHang();
        for (KhachHang kh : khachHangs){
            if (kh.getDienThoai().trim().equals(PhoneNumber.trim()) && kh.getMaKH() != maKH){
                return true;
            }
        }
        return false;
    }

    // Dùng cho nút thêm
    private boolean checkduplicateEmail(String Email){
        ArrayList <KhachHang> khachHangs = new ArrayList<>();
        khachHangs = getListKhachHang();
        for (KhachHang kh : khachHangs){
            if (kh.getEmail().trim().equals(Email.trim()) && !InputValidator.IsEmpty(Email)){
                return true;
            }
        }
        return false;
    }

    //Dùng cho nút edit
    private boolean checkduplicateEmail2(String Email, int maKH){
        ArrayList <KhachHang> khachHangs = new ArrayList<>();
        khachHangs = getListKhachHang();
        for (KhachHang kh : khachHangs){
            if (kh.getEmail().trim().equals(Email.trim()) && kh.getMaKH() != maKH && !InputValidator.IsEmpty(Email)){
                return true;
            }
        }
        return false;
    }


    public boolean Update(KhachHang khachHang) {
        if (!checkInfors(khachHang)) {
            return false;
        }
        if(checkduplicatePhoneNumber2(khachHang.getDienThoai(),khachHang.getMaKH())){
            new dialog("Số điện thoại đã tồn tại!",dialog.ERROR_DIALOG);
            return false;
        }
        if(checkduplicateEmail2(khachHang.getEmail(),khachHang.getMaKH())){
            new dialog("Email đã tồn tại!",dialog.ERROR_DIALOG);
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
        if(checkduplicatePhoneNumber(khachHang.getDienThoai())){
            new dialog("Số điện thoại đã tồn tại!",dialog.ERROR_DIALOG);
            return false;
        }
        if(checkduplicateEmail(khachHang.getEmail())){
            new dialog("Email đã tồn tại!",dialog.ERROR_DIALOG);
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
        return Math.max(khachhangDao.getNewMa() + 1,1);
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
