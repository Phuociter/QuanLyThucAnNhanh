package BUS;

import Custom.InputValidator;
import Custom.dialog;
import DAO.HoaDonDAO;
import DTO.HoaDon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class HoaDonBUS {

    private HoaDonDAO HDDAO = new HoaDonDAO();
    private ArrayList <HoaDon> currentList = new ArrayList<>();
    public ArrayList<HoaDon> getlistHD() {
        return HDDAO.getListHoaDon();
    }
    // lấy hóa đơn theo mã hóa đơn
    public HoaDon getHoaDonByMaHD(int maHD){
        return HDDAO.getHoaDonTheoMHD(maHD);
    }


    public ArrayList<HoaDon> getlistHDtheoDateVaTongTien(Date DateMin, Date DateMax, int TongTienMin, int TongTienMax) {
        if (DateMin.after(DateMax)) {
            new dialog("Khoảng ngày không hợp lệ!", dialog.ERROR_DIALOG);
            return null;
        }
        if (TongTienMin > TongTienMax) {
            new dialog("Khoảng tiền không hợp lệ", dialog.ERROR_DIALOG);
        }
        return HDDAO.getListHoaDonTheoDateVaTongTien((java.sql.Date) DateMin, (java.sql.Date) DateMax, TongTienMin, TongTienMax);
    }

    public HoaDon getlisttheoMHD(String MHD) {

        String temp = "" + MHD;
        temp = temp.trim();
        int MHDint = Integer.parseInt(temp);
        if (HDDAO.getHoaDonTheoMHD(MHDint) == null) {
            return null;
        }
        return HDDAO.getHoaDonTheoMHD(MHDint);
    }

    public ArrayList<HoaDon> getListHD_Price_Date(Date Min, Date Max, String GiaMin, String GiaMax) throws SQLException {
        if (currentList == null){
            currentList = HDDAO.getListHoaDon();
        }
        GiaMin = GiaMin.trim();
        GiaMax = GiaMax.trim();
        java.sql.Date sqlMin = new java.sql.Date(Min.getTime());
        java.sql.Date sqlMax = new java.sql.Date(Max.getTime());
        if (!Min.after(Max)) {
            if (InputValidator.IsEmpty(GiaMin) && InputValidator.IsEmpty(GiaMax)) {
                currentList = HDDAO.getListHoaDonTheoDate(sqlMin, sqlMax);
                return currentList;
            }
//            if (InputValidator.IsEmpty(GiaMin) || InputValidator.IsEmpty(GiaMax)) {
//                new dialog("Vui lòng nhập đầy đủ ô giá", dialog.ERROR_DIALOG);
//                return null;
//            }
            if (InputValidator.IsEmpty(GiaMax)){
                try {
                    int PriceMin = Integer.parseInt(GiaMin);
                    int PriceMax = HDDAO.getMaxTongTien();
                    currentList = HDDAO.getListHoaDonTheoDateVaTongTien(sqlMin,sqlMax,PriceMin,PriceMax);
                    return currentList;

                }
                catch (Exception e){
                    new dialog("Vui lòng nhập đúng định dạng", dialog.ERROR_DIALOG);
                    return null;
                }
            }
            if (InputValidator.IsEmpty(GiaMin)){
                try {
                    int PriceMax = Integer.parseInt(GiaMax);
                    currentList = HDDAO.getListHoaDonTheoDateVaTongTien(sqlMin,sqlMax,0,PriceMax);
                    return currentList;
                }
                catch (Exception e){
                    new dialog("Vui lòng nhập đúng định dạng", dialog.ERROR_DIALOG);
                    return null;
                }

            }
            if (!InputValidator.IsEmpty(GiaMin) && !InputValidator.IsEmpty(GiaMax)) {
                try{
                    int PriceMin = Integer.parseInt(GiaMin);
                    int PriceMax = Integer.parseInt(GiaMax);
                    if (PriceMin > PriceMax || PriceMin < 0 || PriceMax < 0) {
                        new dialog("Vui lòng nhập khoảng giá hợp lệ", dialog.ERROR_DIALOG);
                        return null;
                    } else {
                        return currentList = HDDAO.getListHoaDonTheoDateVaTongTien(sqlMin, sqlMax, PriceMin, PriceMax);
                    }
                }
                catch (Exception e){
                    new dialog("Vui lòng nhập đúng định dạng", dialog.ERROR_DIALOG);
                    return null;
                }
            }
        } else {
            new dialog("Vui lòng nhập đúng khoảng ngày!", dialog.ERROR_DIALOG);
            return null;
        }
        System.out.println("test");
        return currentList;
        //return HDDAO.getListHoaDonTheoDateVaTongTien(sqlMin,sqlMax, PriceMin, PriceMax);
    }

    public int getNewId() {
        return HDDAO.getNewId();
    }

    public boolean Insert(HoaDon hoaDon) {
        hoaDon.setMaHD(getNewId()+1);
        if (HDDAO.addHoaDon(hoaDon)) {
            new dialog("Thanh toán thành công!", dialog.SUCCESS_DIALOG);
            return true;
        }
        new dialog("Thanh toán không thành công!", dialog.ERROR_DIALOG);
        return false;
    }


}
