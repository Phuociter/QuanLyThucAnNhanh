package BUS;

import Custom.InputValidator;
import Custom.dialog;
import java.util.ArrayList;
import java.sql.*;

import DAO.PhieuNhapDAO;
import DTO.PhieuNhap;

public class PhieuNhapBUS {

    private PhieuNhapDAO PhieuNhapDAO = new PhieuNhapDAO();

    public PhieuNhapBUS() {

    }

    public ArrayList<PhieuNhap> getList() {
        return PhieuNhapDAO.getallPhieuNhap();
    }
    public ArrayList<PhieuNhap> findPNBydate(Date startdate, Date enddate){
        return PhieuNhapDAO.getPhieuNhapByNgayLap(startdate, enddate);
    }
    public ArrayList<PhieuNhap> FindPNByPNid(int MaPN) {
        return PhieuNhapDAO.getPhieuNhapbyId(MaPN);
    }

    public ArrayList<PhieuNhap> findPNByRange(Date startDate, Date endDate, String minPrice, String maxPrice) {

        minPrice = minPrice.trim();
        maxPrice = maxPrice.trim();
    
        if (!startDate.after(endDate)) {
            if (InputValidator.IsEmpty(minPrice) && InputValidator.IsEmpty(maxPrice)) {
                return PhieuNhapDAO.getPhieuNhapByNgayLap(startDate, endDate);
            } else if (InputValidator.IsEmpty(minPrice) || InputValidator.IsEmpty(maxPrice)) {
                new dialog("vui lòng nhập đầy đủ ô giá", dialog.ERROR_DIALOG);
                return null;
            }
            try {
                int min = Integer.parseInt(minPrice);
                int max = Integer.parseInt(maxPrice);
    
                if (min > max || min < 0 || max < 0) {
                    new dialog("Vui lòng nhập khoảng giá hợp lệ", dialog.ERROR_DIALOG);
                    return null;
                } else {
                    return PhieuNhapDAO.getPhieuNhapByNgayLapVaGia(startDate, endDate, min, max);
                }
            } catch (NumberFormatException e) {
                new dialog("Giá nhập vào không hợp lệ", dialog.ERROR_DIALOG);
                return null;
            }
        } else {
            new dialog("Vui lòng nhập đúng khoảng ngày!", dialog.ERROR_DIALOG);
            return null;
        }
    }
    


    public boolean Insert(PhieuNhap phieuNhap) {
        if (PhieuNhapDAO.themPhieuNhap(phieuNhap)) {
            new dialog("Lập phiếu nhập thành công!", dialog.SUCCESS_DIALOG);
            return true;
        }
        new dialog("Lập phiếu nhập không thành công!", dialog.ERROR_DIALOG);
        return false;
    }

    public int getNewMaPN() {
        return PhieuNhapDAO.getNewId();
    }
    public PhieuNhap getById(int maPN){
        return PhieuNhapDAO.getById(maPN);
    }
}

