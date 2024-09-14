package BUS;

import java.util.ArrayList;

import Custom.dialog;
import DAO.CTPhieuNhapDAO;
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
}
