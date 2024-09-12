package BUS;

import Custom.InputValidator;
import DAO.TaiKhoanDAO;
import DTO.TaiKhoan;
import Custom.dialog;
import DAO.PhanQuyenDAO;

public class TaiKhoanBUS {

    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
    private PhanQuyenDAO phanQuyenDAO = new PhanQuyenDAO();

    public String getTenDangNhapTheoMa(String ma) {
        int maNV = Integer.parseInt(ma);
        return taiKhoanDAO.layTenDangNhapTheoMa(maNV);
    }

    public int getQuyenTheoMa(int ma) {
        return taiKhoanDAO.layQuyenTheoMa(ma);
    }

    public void datLaiMatKhau(String ma, String tenDangNhap) {
        int maNV = Integer.parseInt(ma);
        boolean flag = taiKhoanDAO.datLaiMatKhau(maNV, tenDangNhap);
        if (flag) {
            new dialog("Đặt lại thành công! Mật khẩu mới là: " + tenDangNhap, dialog.SUCCESS_DIALOG);
        } else {
            new dialog("Đặt lại thất bại!", dialog.ERROR_DIALOG);
        }
    }

    public void datLaiQuyen(String ma, String quyen) {
        int maNV = Integer.parseInt(ma);
        int maQuyen = phanQuyenDAO.getIdByName(quyen.strip());
        if (maQuyen < 0) {
            new dialog("Không tìm thấy quyền", dialog.ERROR_DIALOG);
            return;
        }
        boolean flag = taiKhoanDAO.datLaiQuyen(maNV, maQuyen);
        if (flag) {
            new dialog("Đặt lại thành công!", dialog.SUCCESS_DIALOG);
        } else {
            new dialog("Đặt lại thất bại!", dialog.ERROR_DIALOG);
        }
    }

    public boolean kiemTraTrungTenDangNhap(String tenDangNhap) {
        return taiKhoanDAO.kiemTraTrungTenDangNhap(tenDangNhap);
    }

    public TaiKhoan getById(int maNV) {
        return taiKhoanDAO.selectById(maNV, 1);
    }

    public boolean hasAccount(int maNV) {
        TaiKhoan taiKhoan = taiKhoanDAO.selectById(maNV, 0); // lấy tất cả tài khoản bao gồm tk đã bị khóa
        if (taiKhoan == null) {
            return false;
        }
        if (taiKhoan.getTrangThai() == 1) {
            new dialog("Nhân viên đã có tài khoản", dialog.ERROR_DIALOG);
            return true;
        }
        // đã có tài khoản nhưng bị khóa
        dialog dlg = new dialog("Tài khoản nhân viên bị khóa, xác nhận mở khóa?", dialog.WARNING_DIALOG);
        if (dlg.getAction() == dialog.OK_OPTION) {
            moKhoaTaiKhoan(maNV + "");
        }
        return true;
    }

    public boolean themTaiKhoan(String ma, String tenDangNhap, String tenQuyen) {
        int maNV = Integer.parseInt(ma);
        if (tenDangNhap.trim().equals("")) {
            new dialog("Không được để trống Tên đăng nhập!", dialog.ERROR_DIALOG);
            return false;
        }
        if (!InputValidator.isValidPass(tenDangNhap.strip())) { // kiểm tra ký tự
            new dialog("Tên đăng nhập không được có ký tự", dialog.ERROR_DIALOG);
            return false;
        }
        if (kiemTraTrungTenDangNhap(tenDangNhap)) {
            dialog dlg = new dialog("Tên đăng nhập bị trùng!\nCó thể tài khoản bị khoá,\nthực hiện mở khoá?", dialog.WARNING_DIALOG);
            if (dlg.getAction() == dialog.OK_OPTION) {
                moKhoaTaiKhoan(ma);
                return true;
            }
            return false;
        }
        int maQuyen = phanQuyenDAO.getIdByName(tenQuyen.strip()); // tìm mã quyền bằng tên quyền
        if (maQuyen < 0) {
            new dialog("Không tìm thấy quyền", dialog.ERROR_DIALOG);
            return false;
        }
        TaiKhoan tk = new TaiKhoan(maNV, maQuyen, tenDangNhap, tenDangNhap, 1);
        boolean flag = taiKhoanDAO.insert(tk);
        if (flag) {
            new dialog("Cấp tài khoản thành công! Mật khẩu là " + tenDangNhap, dialog.SUCCESS_DIALOG);
        } else {
            new dialog("Cấp tài khoản thất bại!", dialog.ERROR_DIALOG);
        }
        return flag;
    }

    public void khoaTaiKhoan(String ma) {
        int maNV = Integer.parseInt(ma);
        boolean flag = taiKhoanDAO.khoaTaiKhoan(maNV);
        if (flag) {
            new dialog("Khoá tài khoản thành công!", dialog.SUCCESS_DIALOG);
        } else {
            new dialog("Khoá tài khoản thất bại!", dialog.ERROR_DIALOG);
        }
    }

    public void moKhoaTaiKhoan(String ma) {
        int maNV = Integer.parseInt(ma);
        boolean flag = taiKhoanDAO.moKhoaTaiKhoan(maNV);
        if (flag) {
            new dialog("Mở khoá tài khoản thành công!", dialog.SUCCESS_DIALOG);
        } else {
            new dialog("Mở khoá tài khoản thất bại!", dialog.ERROR_DIALOG);
        }
    }

    public boolean doiMatKhau(String matKhauCu, String matKhauMoi, String nhapLaiMatKhau) {
        if (!matKhauMoi.equals(nhapLaiMatKhau)) {
            new dialog("Mật khẩu mới không khớp!", dialog.ERROR_DIALOG);
            return false;
        }
        boolean flag = taiKhoanDAO.doiMatKhau(matKhauCu, matKhauMoi);
        if (flag) {
            new dialog("Đổi thành công!", dialog.SUCCESS_DIALOG);
        } else {
            new dialog("Mật khẩu cũ nhập sai!", dialog.ERROR_DIALOG);
        }
        return flag;
    }

    public int getTrangThai(String maNV) {
        int ma = Integer.parseInt(maNV);
        return taiKhoanDAO.getTrangThai(ma);
    }

}
