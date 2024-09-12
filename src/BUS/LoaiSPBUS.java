package BUS;

import DAO.LoaiSPDAO;
import DTO.LoaiSP;
import Custom.dialog;
import java.util.ArrayList;

public class LoaiSPBUS {

    private LoaiSPDAO LSPDAO = new LoaiSPDAO();
    private ArrayList<LoaiSP> listLoai = null;
    
    public LoaiSPBUS() {
        docDanhSachLoai();
    }

    public void docDanhSachLoai() {
        this.listLoai = LSPDAO.getLoaiSanPham();
    }

    public ArrayList<LoaiSP> getDanhSachLoai() {
        if (listLoai == null) {
            docDanhSachLoai();
        }
        return this.listLoai;
    }

    public String getTenLoai(int ma) {
        for (LoaiSP loai : listLoai) {
            if (loai.getMaLoai() == ma) {
                return loai.getMaLoai() + " - " + loai.getTenLoai();
            }
        }
        return "";
    }

    public boolean themLoai(int maLoai, String tenLoai) {
        if (tenLoai.trim().equals("")) {
            new dialog("Không được để trống tên loại!", dialog.ERROR_DIALOG);
            return false;
        }
        maLoai += 1;
        LoaiSP loai = new LoaiSP(maLoai, tenLoai,1);
        if (LSPDAO.themLoaiSanPham(loai)) {
            new dialog("Thêm thành công!", dialog.SUCCESS_DIALOG);
            return true;
        } else {
            new dialog("Thêm thất bại!", dialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean xoaLoai(String ma) {
        if (ma.trim().equals("")) {
            new dialog("Chưa chọn loại để xoá!", dialog.SUCCESS_DIALOG);
            return false;
        }
        int maLoai = Integer.parseInt(ma);
        if (LSPDAO.deleteLoaiSP(maLoai)) {
            new dialog("Xoá thành công!", dialog.SUCCESS_DIALOG);
            return true;
        } else {
            new dialog("Xoá thất bại! Loại có sản phẩm con", dialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean suaLoai(String ma, String ten) {
        if (ten.trim().equals("")) {
            new dialog("Không được để trống tên loại!", dialog.ERROR_DIALOG);
            return false;
        }
        int maLoai = Integer.parseInt(ma);
        if (LSPDAO.updateInfoLoaiSP(maLoai, ten)) {
            new dialog("Sửa thành công!", dialog.SUCCESS_DIALOG);
            return true;
        } else {
            new dialog("Sửa thất bại!", dialog.ERROR_DIALOG);
            return false;
        }
    }

}
