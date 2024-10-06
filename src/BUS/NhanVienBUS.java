package BUS;

import Custom.InputValidator;
import Custom.JDBCUtil;
import DAO.NhanVienDAO;
import DTO.NhanVien;
import Custom.dialog;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class NhanVienBUS {

    private ArrayList<NhanVien> listNV = null;
    private NhanVienDAO nvDAO = new NhanVienDAO();

    public NhanVienBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        listNV = nvDAO.getDanhSachNhanVien();
    }

    public ArrayList<NhanVien> getlistNV() {
        if (listNV == null) {
            docDanhSach();
        }
        return listNV;
    }
    private boolean duplicatePhoneNumber(String phoneNumber){
        ArrayList <NhanVien> nhanViens = new ArrayList<>();
        nhanViens = getlistNV();
        for (NhanVien nv : nhanViens){
            if (nv.getDienThoai().trim().equals(phoneNumber.trim())){
                return true;
            }
        }
        return false;
    }
    private boolean duplicatePhoneNumber2(String phoneNumber, int maNV){
        ArrayList <NhanVien> nhanViens = new ArrayList<>();
        nhanViens = getlistNV();
        for (NhanVien nv : nhanViens){
            if (nv.getDienThoai().trim().equals(phoneNumber.trim()) && nv.getMaNV() != maNV){
                return true;
            }
        }
        return false;
    }

    public NhanVien getById(int maNV) {
        return nvDAO.getNhanVien(maNV);
    }

    public boolean themNhanVien(String ho, String ten, String gioiTinh, String dienThoai,int luong, int trangThai) {
        ho = ho.trim();
        ten = ten.trim();
        dienThoai = dienThoai.trim();
        int tThai = trangThai;
        if (InputValidator.IsEmpty(ho)){
            new dialog("Họ không được để trống!", dialog.ERROR_DIALOG);
            return false;
        }
        if (ten.equals("")) {
            new dialog("Tên không được để trống!", dialog.ERROR_DIALOG);
            return false;
        }
        if (!InputValidator.IsValidNamelength(ho)){
            new dialog("Họ không được vượt quá 50 ký tự!", dialog.ERROR_DIALOG);
            return false;
        }
        if (!InputValidator.IsValidNamelength(ten)){
            new dialog("Tên không được vượt quá 50 ký tự!", dialog.ERROR_DIALOG);
            return false;
        }
        if (!InputValidator.isValidName(ho)){
            new dialog("Họ không hợp lệ!",dialog.ERROR_DIALOG);
            return false;
        }
        if (!InputValidator.isValidName(ten)){
            new dialog("Tên không hợp lệ!",dialog.ERROR_DIALOG);
            return false;
        }
        if (dienThoai.equals("")) {
            new dialog("Điện thoại không được để trống!", dialog.ERROR_DIALOG);
            return false;
        }
        if (!InputValidator.isValidPhoneNumber(dienThoai)) {
            new dialog("Số điện thoại không hợp lệ", dialog.ERROR_DIALOG);
            return false;
        }
        if (duplicatePhoneNumber(dienThoai)){
            new dialog("Số điện thoại đã tồn tại", dialog.ERROR_DIALOG);
            return false;
        }
        if(InputValidator.OverflowChecker(dienThoai))
        if (!InputValidator.isPositiveNumber(String.valueOf(luong))){
            new dialog("Lương không được để âm", dialog.ERROR_DIALOG);
            return false;
        }
        NhanVien nv = new NhanVien();
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setGioiTinh(gioiTinh);
        nv.setDienThoai(dienThoai);
        nv.setLuong(luong);
        nv.setTrangThai(tThai);
        boolean flag = nvDAO.themNhanVien(nv);
        if (flag) {
            new dialog("Thêm thất bại!", dialog.ERROR_DIALOG);
        } else {
            new dialog("Thêm thành công!", dialog.SUCCESS_DIALOG);
        }
        return flag;
    }

    public boolean updateNhanVien(String ma, String ho, String ten, String gioiTinh, String dienThoai, int luong) {
        int maNV = Integer.parseInt(ma);
        ho = ho.trim();
        ten = ten.trim();
        dienThoai = dienThoai.trim();
        if (InputValidator.IsEmpty(ho)){
            new dialog("Họ không được để trống!", dialog.ERROR_DIALOG);
            return false;
        }
        if (ten.equals("")) {
            new dialog("Tên không được để trống!", dialog.ERROR_DIALOG);
            return false;
        }
        if (!InputValidator.IsValidNamelength(ho)){
            new dialog("Họ không được vượt quá 50 ký tự!", dialog.ERROR_DIALOG);
            return false;
        }
        if (!InputValidator.IsValidNamelength(ten)){
            new dialog("Tên không được vượt quá 50 ký tự!", dialog.ERROR_DIALOG);
            return false;
        }
        if (!InputValidator.isValidName(ho)){
            new dialog("Họ không hợp lệ!",dialog.ERROR_DIALOG);
            return false;
        }
        if (!InputValidator.isValidName(ten)){
            new dialog("Tên không hợp lệ!",dialog.ERROR_DIALOG);
            return false;
        }
        if (dienThoai.equals("")) {
            new dialog("Điện thoại không được để trống!", dialog.ERROR_DIALOG);
            return false;
        }
        if (!InputValidator.isValidPhoneNumber(dienThoai)) {
            new dialog("Số điện thoại không hợp lệ", dialog.ERROR_DIALOG);
            return false;
        }
        if (duplicatePhoneNumber2(dienThoai,maNV)){
            new dialog("Số điện thoại đã tồn tại", dialog.ERROR_DIALOG);
            return false;
        }
        if (!InputValidator.isPositiveNumber(String.valueOf(luong))){
            new dialog("Lương không được để âm", dialog.ERROR_DIALOG);
            return false;
        }
        NhanVien nv = new NhanVien();
        NhanVienBUS nvbus = new NhanVienBUS();
        nv.setMaNV(maNV);
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setGioiTinh(gioiTinh);
        nv.setDienThoai(dienThoai);
        nv.setChucVu(nvbus.getById(maNV).getChucVu());
        nv.setLuong(luong);
        boolean flag = nvDAO.updateInfoNhanVien(nv);
        if (flag) {
            new dialog("Cập nhập thất bại!", dialog.ERROR_DIALOG);
        } else {
            new dialog("Cập nhập thành công!", dialog.SUCCESS_DIALOG);
        }
        return flag;
    }

    public ArrayList<NhanVien> timNhanVien(String tuKhoa, JComboBox<String> selection) {
        NhanVienBUS nhanVienBUS = new NhanVienBUS();
        tuKhoa = tuKhoa.toLowerCase();
        String selectedItem = (String) selection.getSelectedItem();
        ArrayList<NhanVien> dsnv = new ArrayList<>();
        if (selectedItem.trim().equals("Theo mã")){
            if(nhanVienBUS.getById(Integer.parseInt(tuKhoa)) != null ){
                new dialog("Tìm thấy 1 nhân viên!",dialog.INFO_DIALOG);
                dsnv.add(nhanVienBUS.getById(Integer.parseInt(tuKhoa)));
                return dsnv;
            }
            else {
                new dialog("Không tìm thấy nhân viên nhân viên!",dialog.INFO_DIALOG);
                return null;
            }
        }
        else {
            for (NhanVien nv : listNV) {
                if(nv.getTen().toLowerCase().contains(tuKhoa.toLowerCase())){
                        System.out.println(nv.getTen().toLowerCase().contains(tuKhoa.toLowerCase()));
                        dsnv.add(nv);
                    }
                }
            if (dsnv.size() > 0) {
                new dialog("Tìm thấy " + dsnv.size() + " nhân viên!", dialog.INFO_DIALOG);
                return dsnv;
            }
            else {
                new dialog("Không tìm thấy nhân viên!",dialog.INFO_DIALOG);
                return null;
            }
        }

    }

    public boolean xoaNhanVien(String ma) {
        try {
            int maNV = Integer.parseInt(ma);
            dialog dlg = new dialog("Bạn có chắc chắn muốn xoá không?", dialog.WARNING_DIALOG);
            boolean flag = false;
            if (dlg.getAction() == dialog.OK_OPTION) {
                flag = nvDAO.deleteNhanVien(maNV);
                if (flag) {
                    new dialog("Xoá thành công!", dialog.SUCCESS_DIALOG);
                } else {
                    new dialog("Xoá thất bại!", dialog.ERROR_DIALOG);
                }
            }
            return flag;
        } catch (Exception e) {
            new dialog("Bạn chưa chọn nhân viên!", dialog.ERROR_DIALOG);
        }
        return false;
    }

    public boolean xoaFKHoadon_PhieuNhap_NV() {
        nvDAO.deletaFKHoandon_PhieuNhap();
        boolean ketqua = nvDAO.deletaFKHoandon_PhieuNhap();
        return ketqua;
    }

    public boolean updateFKHoadon_PhieuNhap_NV() {
        nvDAO.updateFKHoandon_PhieuNhap();
        boolean ketqua = nvDAO.updateFKHoandon_PhieuNhap();
        return ketqua;
    }

    public boolean xoaAllNhanVien() {
        nvDAO.xoaAllInfor();
        boolean ketqua = nvDAO.xoaAllInfor();
        return ketqua;
    }

    public boolean nhapExcel(String manv, String ho, String ten, String gioiTinh, String dienThoai,String luong, int trangThai) {
        int tThai = trangThai;
        int maNV = Integer.parseInt(manv);
        int Luong = Integer.parseInt(luong);
        NhanVien nv = new NhanVien();
        nv.setMaNV(maNV);
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setGioiTinh(gioiTinh);
        nv.setDienThoai(dienThoai);
        nv.setLuong(Luong);
        nv.setTrangThai(tThai);
        boolean flag = nvDAO.importNhanVienFromExcel(nv);
        return flag;
    }

    public void CapNhatChucVu(NhanVien nv) {
        if (nv.getChucVu().equals("Quản trị")){
            return;
        }
        if (!nvDAO.capNhatChucVu(nv)) {
            new dialog("Cập nhật chức vụ thất bại", dialog.ERROR_DIALOG);
        }
    }
}
