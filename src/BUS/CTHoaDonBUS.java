package BUS;

import Custom.dialog;
import DAO.CTHoaDonDAO;
import DTO.CTHoaDon;

import java.util.ArrayList;

public class CTHoaDonBUS {

    CTHoaDonDAO CTHD = new CTHoaDonDAO();

    public ArrayList<CTHoaDon> getlistCTHD() {
        return CTHD.getListCTHD();
    }

    public boolean Insert(CTHoaDon cTHoaDon) {
        if (!CTHD.addCTHoaDon(cTHoaDon)) {
            new dialog("Lỗi thêm chi tiết hóa đơn!", dialog.ERROR_DIALOG);
            return false;
        }
        return true;
    }
}
