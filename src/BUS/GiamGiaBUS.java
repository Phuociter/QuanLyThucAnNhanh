package BUS;

import Custom.dialog;
import DAO.GiamGiaDAO;
import DTO.GiamGia;
import java.util.ArrayList;

public class GiamGiaBUS {

    GiamGiaDAO giamGiaDAO = new GiamGiaDAO();

    public GiamGiaBUS() {
    }

    public ArrayList<GiamGia> getList() {
        return giamGiaDAO.getListGiamGia();
    }

    public GiamGia getById(int maGiamGia) {
        return giamGiaDAO.selectById(maGiamGia);
    }

    public boolean Insert(GiamGia giamGia) {
        giamGia.setMaGiam(getNextMaGiam());
        if (checkDuplicateName(giamGia.getTenGiamGia(), giamGia.getMaGiam())) {
            return false;
        }
        if (giamGiaDAO.addGiamGia(giamGia)) {
            new dialog("Thêm giảm giá thành công", dialog.SUCCESS_DIALOG);
            return true;
        }
        new dialog("Không thể thêm giảm giá", dialog.ERROR_DIALOG);
        return false;
    }

    public boolean Edit(GiamGia giamGia) {
        if (checkDuplicateName(giamGia.getTenGiamGia(), giamGia.getMaGiam())) {
            return false;
        }
        if (giamGiaDAO.updateGiamGia(0, giamGia)) {
            new dialog("Sửa giảm giá thành công", dialog.SUCCESS_DIALOG);
            return true;
        }
        new dialog("Không thể sửa giảm giá", dialog.ERROR_DIALOG);
        return false;
    }

    public boolean Delete(int maGiamGia) {
        if (giamGiaDAO.deleteGiamGia(maGiamGia)) {
            new dialog("Xóa giảm giá thành công", dialog.SUCCESS_DIALOG);
            return true;
        }
        new dialog("Không thể xóa giảm giá mã "+maGiamGia, dialog.ERROR_DIALOG);
        return false;
    }

    private int getNextMaGiam() {
        return giamGiaDAO.getNewMa() + 1;
    }

    private boolean checkDuplicateName(String tenGiamGia, int maGiamGia) {
        ArrayList<GiamGia> giamGias = giamGiaDAO.getListGiamGia();
        for (GiamGia giamGia : giamGias) {
            if (giamGia.getMaGiam() == maGiamGia) {
                continue;
            }
            if (giamGia.getTenGiamGia().equals(tenGiamGia.strip())) {
                new dialog("Tên giảm giá đã tồn tại", dialog.ERROR_DIALOG);
                return true;
            }
        }
        return false;
    }
    public ArrayList<GiamGia> searchGiamGias (String keyword){
        return giamGiaDAO.searchGiamGias(keyword.trim());
    }
}
