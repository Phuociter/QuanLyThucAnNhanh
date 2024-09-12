package BUS;

import Custom.dialog;
import DAO.PhanQuyenDAO;
import DTO.PhanQuyen;
import java.util.ArrayList;

public class PhanQuyenBUS {

    public static PhanQuyen currentQuyen = null;
    private PhanQuyenDAO phanQuyenDAO = new PhanQuyenDAO();

    public PhanQuyenBUS() {
    }

    private boolean checkDuplicateName(String tenQuyen) {
        ArrayList<PhanQuyen> phanQuyens = phanQuyenDAO.selectAll();
        for (PhanQuyen phanQuyen : phanQuyens) {
            if (phanQuyen.getTenQuyen().equals(tenQuyen.strip())) {
                new dialog("Tên quyền đã tồn tại", dialog.ERROR_DIALOG);
                return true;
            }
        }
        return false;
    }

    public boolean Insert(String tenQuyen) {
        if (checkDuplicateName(tenQuyen)) {
            return false;
        }
        int maQuyen = getNextMaQuyen();
        if (phanQuyenDAO.insert(new PhanQuyen(maQuyen, tenQuyen, 0, 0, 0, 0, 0, 1)) == 0) {
            new dialog("Không thể thêm quyền", dialog.ERROR_DIALOG);
            return false;
        }
        new dialog("Thêm quyền thành công", dialog.SUCCESS_DIALOG);
        return true;
    }

    public boolean Update(PhanQuyen t) {
        if (phanQuyenDAO.update(t) == 0) {
            new dialog("Không thể sửa quyền", dialog.ERROR_DIALOG);
            return false;
        }
        new dialog("Sửa quyền thành công", dialog.SUCCESS_DIALOG);
        return true;
    }

    public boolean Delete(int ma) {
        if (phanQuyenDAO.delete(ma) == 0) {
            new dialog("Không thể xóa", dialog.ERROR_DIALOG);
            return false;
        }
        new dialog("Xóa thành công", dialog.SUCCESS_DIALOG);
        return true;
    }

    public PhanQuyen getById(int ma) {
        return phanQuyenDAO.selectById(new PhanQuyen(ma, null, 0, 0, 0, 0, 0, 0));
    }

    public ArrayList<PhanQuyen> getList() {
        return phanQuyenDAO.selectAll();
    }

    public void UpdateCurrentQuyen() {
        if (DangNhapBUS.taiKhoanLogin == null) {
            return;
        }
        int maQuyen = DangNhapBUS.taiKhoanLogin.getMaQuyen();
        PhanQuyen phanQuyentmp = new PhanQuyen();
        phanQuyentmp.setMaQuyen(maQuyen);
        currentQuyen = phanQuyenDAO.selectById(phanQuyentmp);
    }

    private int getNextMaQuyen() {
        return phanQuyenDAO.getNewMa() + 1;
    }
}
