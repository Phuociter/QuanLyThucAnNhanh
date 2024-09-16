package BUS;

import java.util.ArrayList;

import Custom.dialog;
import DAO.CTPhieuNhapDAO;
import DAO.SanPhamDAO;
import DTO.CTPhieuNhap;


public class CTPhieuNhapBUS { 
    
    
    CTPhieuNhapDAO cTPhieuNhapDAO = new CTPhieuNhapDAO();
    
    public CTPhieuNhapBUS() {
    }
    public ArrayList<CTPhieuNhap> getlistPhieuNhaps(){
        return cTPhieuNhapDAO.getListCTPhieuNhapByMaPN();
    }
    public boolean Insert(CTPhieuNhap ctpn){
        if(!cTPhieuNhapDAO.addCTPhieuNhap(ctpn))
        {
            new dialog("Lỗi thêm chi tiết phiếu nhập!", dialog.ERROR_DIALOG);
            return false;
        }
        return true;
    }
    //Đình Thái thêm phần này
    public CTPhieuNhap getCTPhieuNhapByMaSP(int maSP) {
        return cTPhieuNhapDAO.getCTPhieuNhapByMaSP(maSP);
    }

    public String getTenSPByMaSP(int maSP) {
        SanPhamDAO spDAO = new SanPhamDAO();
        return spDAO.getTenSPByMaSP(maSP);
    }

    public CTPhieuNhap getCTPNbyMaPhieuNhap(int mapn){
        CTPhieuNhapDAO ctpn = new CTPhieuNhapDAO();
        ctpn.timkiemCTPhieuNhap2(mapn);
        return ctpn.timkiemCTPhieuNhap2(mapn);
    }
    
    
}
